pragma solidity ^0.4.2;

contract FundManager {

    address admin;
    Member[] public members;
    Event[] public events;
    mapping (address => uint) public memberId;
    uint numEvents;

    constructor() public payable{
        admin = msg.sender;
        addMember(0, "");
    }

    function () payable  public {

    }

    struct Event {
        string title;
        string description;
        uint time;
        uint fund;
        uint minimum_amount;
        uint target_amount;
        Donor[] donationList;
        uint totalDonors;
        bool alive;
    }

    struct Member {
        address member;
        uint memberSince;
        string name;
    }

    struct Donor {
        uint donorId;
        uint amount;
    }

    modifier onlyAdmin {
        require(msg.sender == admin);
        _;
    }

    modifier onlyMembers {
        require(memberId[msg.sender] != 0);
        _;
    }

    function addMember(address targetMember, string memberName) onlyAdmin public {
        uint id = memberId[targetMember];
        if (id == 0) {
            memberId[targetMember] = members.length;
            id = members.length++;
        }
        members[id] = Member({member: targetMember, memberSince: now, name: memberName});
    }

    function removeMember(address targetMember) onlyAdmin public {
        require(memberId[targetMember] != 0);

        for (uint i = memberId[targetMember]; i<members.length-1; i++){
            members[i] = members[i+1];
        }
        delete members[members.length-1];
        members.length--;
    }

    function totalMembers() constant public returns (uint) {
        return members.length;
    }

    function creatEvent(string _title, string _description, uint _time, uint _minimum_amount, uint _target_amount) public returns (uint eventId) {
        eventId = events.length++;
        Event storage newEvent = events[eventId];
        newEvent.title = _title;
        newEvent.description = _description;
        newEvent.time = _time;
        newEvent.minimum_amount = _minimum_amount;
        newEvent.target_amount = _target_amount;
        newEvent.alive = true;
        numEvents = eventId+1;
        return eventId;
    }

    function closeEvent(uint eventId) public returns(bool) {
        Event storage currentEvent = events[eventId];
        if(currentEvent.fund >= currentEvent.target_amount) {
            currentEvent.alive = false;
            return true;
        }
        for(uint i = 0 ; i< currentEvent.totalDonors; i++) {
            refund(currentEvent.donationList[i].donorId, currentEvent.donationList[i].amount);
        }

    }

    function refund(uint  donorId, uint amount) public {
        address receiver = members[donorId].member;
        receiver.transfer(amount);
    }


    function donate(uint _amount) public payable returns (bool) {
        Event storage latestEvent = events[events.length-1];
        if(!latestEvent.alive)
            return false;
        address donor = address(msg.sender);
        if(donor.balance < _amount || _amount < latestEvent.minimum_amount )
            return false;
        address reciever = address(this);
        reciever.transfer(_amount);
        latestEvent.fund += _amount;
        latestEvent.donationList.length++;
        latestEvent.donationList[latestEvent.totalDonors] = Donor({donorId: memberId[donor], amount: _amount});
        latestEvent.totalDonors++;
        return true;
    }

    function queryFund() public constant returns (uint) {
        Event storage latestEvent = events[events.length-1];
        return latestEvent.fund;
    }

    function queryDonation(uint index) public constant returns (uint _donorId, uint _amount) {
        Event storage latestEvent = events[events.length-1];
        _donorId = latestEvent.donationList[index].donorId;
        _amount = latestEvent.donationList[index].amount;
    }

    function totalDonors() public constant returns (uint) {
        Event storage latestEvent = events[events.length-1];
        return latestEvent.totalDonors;
    }

    function queryBalance() public constant returns (uint) {
        return address(this).balance;
    }

    function myBalance() public constant returns (uint) {
        return address(msg.sender).balance;
    }
}