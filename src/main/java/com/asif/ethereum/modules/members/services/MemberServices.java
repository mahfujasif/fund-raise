package com.asif.ethereum.modules.members.services;

import com.asif.ethereum.configs.PropertiesConfig;
import com.asif.ethereum.contracts.FundManager;
import com.asif.ethereum.modules.contracts.services.ContractServices;
import com.asif.ethereum.modules.members.Assemblers.MemberResourceAssembler;
import com.asif.ethereum.modules.members.dtos.DonationDTO;
import com.asif.ethereum.modules.members.dtos.MemberDTO;
import com.asif.ethereum.modules.members.dtos.MemberRemoveDTO;
import com.asif.ethereum.modules.members.resources.DonationResource;
import com.asif.ethereum.modules.members.resources.MemberCreateResponce;
import com.asif.ethereum.modules.members.resources.MemberResource;
import com.asif.ethereum.services.AccountsServices;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tuples.generated.Tuple2;
import org.web3j.tuples.generated.Tuple3;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MemberServices {

  private final ContractServices contractServices;
  private final MemberResourceAssembler memberResourceAssembler;
  private final AccountsServices accountsServices;
  private final Web3j web3j;

  private String getAddress(Integer walletId) {
    return accountsServices.IdAccountMap().get(walletId);
  }

  public MemberCreateResponce add(MemberDTO memberDTO) {

    FundManager fundManager = (FundManager)contractServices.load(memberDTO.getContractId());
    BigInteger memberId = null;
    String address = this.getAddress(memberDTO.getWalletId());

    TransactionReceipt transactionReceipt = null;
    try {
      transactionReceipt =
          fundManager.addMember(address, memberDTO.getUserName()).send();
      memberId = fundManager.addressMemberIdMap(address).send();
    } catch (Exception e) {
      e.printStackTrace();
    }
    //FundManager.MemberAddedEventResponse member = fundManager.getMemberAddedEvents(transactionReceipt).get(0);
    MemberCreateResponce member = new MemberCreateResponce()
        .setMemberId(memberId)
        .setMemberAddress(address)
        .setName(memberDTO.getUserName());
    return member;
  }

  private BigInteger getMemberId(FundManager fundManager, String address) {
    BigInteger memberId = null;
    try {
      memberId = fundManager.addressMemberIdMap(address).send();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return memberId;
  }

  private Tuple3<String, BigInteger, String> getMemberNonListResources(FundManager fundManager, BigInteger memberId) {
    Tuple3<String, BigInteger, String> memberResourceTuple = null;
    try {
      memberResourceTuple = fundManager.members(memberId).send();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return memberResourceTuple;
  }

  public MemberResource get(Integer contractId, BigInteger id) {
    FundManager fundManager = (FundManager)contractServices.load(contractId);
    Tuple3<String, BigInteger, String> tuple3 = this.getMemberNonListResources(fundManager, id);
    MemberResource memberResource = memberResourceAssembler.toResource(
        id,
        tuple3,
        null
    );
        return memberResource;
  }


  public List<DonationResource> getDonationsById(FundManager fundManager, BigInteger memberId) {
    Integer totalDonations = null;
    List<DonationResource> donations = new ArrayList<DonationResource>();

    try {
      totalDonations = fundManager.totalMemberDonations(memberId).send().intValue();
    } catch (Exception e) {
      e.printStackTrace();
    }
    for(Integer i = 0; i< totalDonations; i++) {
      try {
        Tuple2<BigInteger, BigInteger> donation = fundManager.getMemberDonations(memberId, new BigInteger(i.toString())).send();
        DonationResource donationResource = new DonationResource();
        donationResource.setEventId(donation.getValue1()).setAmount(donation.getValue2());
        donations.add(donationResource);
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    return donations;
  }

  public List<DonationResource> getDonationsById(Integer contractId, BigInteger memberId) {
    FundManager fundManager = (FundManager)contractServices.load(contractId);
    return this.getDonationsById(fundManager, memberId);
  }

  public MemberResource getById(FundManager fundManager, BigInteger memberId) {

    Tuple3<String, BigInteger, String> memberNonListResources =
        this.getMemberNonListResources(fundManager, memberId);

    List<DonationResource> donationResources = this.getDonationsById(fundManager, memberId);

    MemberResource member = memberResourceAssembler.toResource(memberId, memberNonListResources, donationResources);

    return member;
  }

  public MemberResource getById(Integer contractId, BigInteger memberId) {
    FundManager fundManager = (FundManager)contractServices.load(contractId);

    return this.getById(fundManager, memberId);
  }

  public MemberResource getByAddress(Integer contractId, String address) {
    FundManager fundManager = (FundManager)contractServices.load(contractId);

    BigInteger memberId = this.getMemberId(fundManager, address);

    return this.getById(fundManager, memberId);
  }

  private MemberResource getAllById(FundManager fundManager, BigInteger memberId) {

    Tuple3<String, BigInteger, String> memberNonListResources =
        this.getMemberNonListResources(fundManager, memberId);

    if(memberNonListResources.getValue1().equals("0x0000000000000000000000000000000000000000")) {
      return new MemberResource();
    }

    List<DonationResource> donationResources = this.getDonationsById(fundManager, memberId);

    MemberResource member = memberResourceAssembler.toResource(memberId, memberNonListResources, donationResources);

    return member;
  }

  public List<MemberResource> getAll(Integer contractId) {

    FundManager fundManager = (FundManager)contractServices.load(contractId);

    Integer totalMembers = 0;
    List<MemberResource> memberResourceList = new ArrayList<MemberResource>();
    try {

      totalMembers = fundManager.totalMembers().send().intValue();
      System.out.println(totalMembers);
    } catch (Exception e) {
      e.printStackTrace();
    }
    for(Integer i = 0; i< totalMembers; i++) {
      MemberResource memberResource = this.getAllById(fundManager, new BigInteger(i.toString()));
      if(memberResource.getMemberAddress() != null) {
        memberResourceList.add(memberResource);
      }
    }
    return memberResourceList;
  }

  public TransactionReceipt remove(MemberRemoveDTO memberRemoveDTO) {
    FundManager fundManager = (FundManager)contractServices.load(memberRemoveDTO.getContractId());
    TransactionReceipt transactionReceipt = null;
    try {
      transactionReceipt = fundManager.removeMember(memberRemoveDTO.getMemberId()).send();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return transactionReceipt;
  }



  public DonationResource donate(DonationDTO donationDTO) {
    FundManager fundManager = (FundManager)contractServices.load(donationDTO.getContractId());
    TransactionReceipt transactionReceipt = null;
    Integer g = 0;
    try {
      transactionReceipt = fundManager.updateDonation(donationDTO.getEventId(), donationDTO.getAmount()).send();
      g+=transactionReceipt.getGasUsed().intValue();
        transactionReceipt = fundManager.donate(donationDTO.getAmount()).send();
      g+=transactionReceipt.getGasUsed().intValue();

    } catch (Exception e) {
      e.printStackTrace();
    }
    DonationResource donationResource = new DonationResource()
        .setAmount(donationDTO.getAmount())
        .setEventId(donationDTO.getEventId());
    donationResource.setBlockNumber(transactionReceipt.getBlockNumber().toString())
        .setStatus(transactionReceipt.getStatus())
        .setFrom(transactionReceipt.getFrom())
        .setTo(transactionReceipt.getTo())
        .setExpenses(g * (PropertiesConfig.LOAD_GAS_PRICE.intValue()));
    return donationResource;
  }

}
