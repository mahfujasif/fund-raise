pragma solidity ^0.4.24;

contract FundManager {

    address public owner;
    Member[] public members;
    Event[] public events;
    mapping (address => uint) public addressMemberIdMap;

    event memberAddedIdAddress(uint indexed memberId, address indexed memberAddress);
    event memberAddedNameSince (string indexed name, uint indexed since);
    event memberRemoved(uint indexed memberId);
    event eventCreated(uint indexed eventId, string indexed title);
    event eventClosed(uint indexed eventId, string indexed status);
    event refunded(address indexed receiver, uint indexed eventId, uint indexed amount);
    event donated(address indexed donor, uint indexed eventId, uint indexed amount);

    constructor() public payable {
        owner = msg.sender;
        addMember(0, "");
        addMember(owner, "owner");
    }

    function () payable {
    }

    struct Event {
        uint creatorId;
        string title;
        string description;
        uint time;
        uint fund;
        uint minimum_amount;
        uint target_amount;
        Donor[] donors;
        string eventStatus;
        bool isAlive;
    }

    struct Member {
        address memberAddress;
        uint since;
        string name;
        uint[] donatedEvents;
        mapping (uint => uint) eventIdAmountMap;
    }

    struct Donor {
        uint memberId;
        uint amount;
    }

    modifier onlyOwner {
        require(msg.sender == owner);
        _;
    }

    modifier onlyMembers {
        require(addressMemberIdMap[msg.sender] != 0);
        _;
    }

    function addMember(address targetMember, string memberName) onlyOwner public {
        uint id = addressMemberIdMap[targetMember];
        if (id == 0) {
            addressMemberIdMap[targetMember] = members.length;
            id = members.length++;
        }
        Member memory newmember;
         newmember.memberAddress = targetMember;
         newmember.since = now;
         newmember.name = memberName;
        members[id] =newmember;
        emit memberAddedIdAddress(id, targetMember);
        emit memberAddedNameSince(memberName, newmember.since);
    }

    function removeMember(uint memberId) onlyOwner public{
      members[memberId].memberAddress = 0;
    }

    function totalMembers() onlyMembers constant public returns (uint) {
        return members.length;
    }

    function totalMemberDonations(uint memberId) onlyMembers constant public returns (uint) {
        Member storage member = members[memberId];
        return member.donatedEvents.length;
    }

    function getMemberDonations(uint memberId, uint index) onlyMembers constant public returns (uint eventId, uint amount) {
         Member storage member = members[memberId];
         eventId = member.donatedEvents[index];
         amount = member.eventIdAmountMap[member.donatedEvents[index]];
     }

    function creatEvent(string _title, string _description, uint _time, uint _minimum_amount, uint _target_amount) onlyMembers public returns (uint) {
        uint eventId = events.length++;
        Event storage newEvent = events[eventId];
        newEvent.creatorId = addressMemberIdMap[msg.sender];
        newEvent.title = _title;
        newEvent.description = _description;
        newEvent.time = _time;
        newEvent.minimum_amount = _minimum_amount;
        newEvent.target_amount = _target_amount;
        newEvent.eventStatus = "online";
        newEvent.isAlive = true;
        emit eventCreated(eventId, _title);
        return eventId;
    }

    function totalEvents() onlyMembers constant public returns (uint) {
        return events.length;
    }

    function totalEventDonars(uint eventId) onlyMembers constant public returns (uint) {
        return events[eventId].donors.length;
    }

    function getEventDonars(uint eventId, uint index) onlyMembers constant public returns (uint donorMemberId, uint amount) {
        Event storage currentEvent = events[eventId];
        donorMemberId = currentEvent.donors[index].memberId;
        amount = currentEvent.donors[index].amount;
    }

    function closeEvent(uint eventId) onlyMembers public returns(bool) {
      if(events[eventId].isAlive = false)
        return false;
      events[eventId].isAlive = false;
        if(events[eventId].fund >= events[eventId].target_amount) {
            refund(addressMemberIdMap[owner], events[eventId].fund, eventId);
            events[eventId].eventStatus = "closed";
            emit eventClosed(eventId, events[eventId].eventStatus);
            return true;
        }
        uint totalDonors = totalEventDonars(eventId);
        for(uint i = 0 ; i< totalDonors; i++) {
            refund(events[eventId].donors[i].memberId, events[eventId].donors[i].amount, eventId);
        }
        events[eventId].eventStatus = "canceled";
        emit eventClosed(eventId, events[eventId].eventStatus);
        return true;
    }

    function refund(uint  donorId, uint amount, uint eventId) onlyMembers public {
        address receiver = members[donorId].memberAddress;
        receiver.transfer(amount);
        emit refunded(receiver, eventId, amount);
    }



    function donate() public payable {
        address(this).transfer(msg.value);
    }

    function updateDonation(uint eventId, uint _amount) public  {

            if(eventId > events.length) {
                return ;
            }

            Event currentEvent = events[eventId];

            if(!currentEvent.isAlive) {
               return ;
            }

            address donor = address(msg.sender);
            uint donorMemberId = addressMemberIdMap[donor];
            if(donor.balance < _amount || _amount < currentEvent.minimum_amount )
                return ;

            currentEvent.fund += _amount;
            uint donorId = currentEvent.donors.length++;
            currentEvent.donors[donorId] = Donor({memberId: donorMemberId, amount: _amount});

            Member storage donorMember = members[donorMemberId];
            uint donatedEventsId = donorMember.donatedEvents.length++;
            donorMember.donatedEvents[donatedEventsId] = eventId;
            donorMember.eventIdAmountMap[eventId] = _amount;

            emit donated(donor, eventId, _amount);
        }

    function queryFund() onlyMembers public constant returns (uint) {
        Event storage latestEvent = events[events.length-1];
        return latestEvent.fund;
    }

    function queryDonation(uint index) onlyMembers public constant returns (uint _donorId, uint _amount) {
        Event storage latestEvent = events[events.length-1];
        _donorId = latestEvent.donors[index].memberId;
        _amount = latestEvent.donors[index].amount;
    }

    function contractBalance() public view returns (uint balance) {
        balance = address(this).balance;
    }

    function queryBalance(uint memberId) public view returns (address balance) {
        balance = members[memberId].memberAddress;
    }
}