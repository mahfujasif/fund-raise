package com.asif.ethereum.modules.members.controllers;

import com.asif.ethereum.modules.members.dtos.DonationDTO;
import com.asif.ethereum.modules.members.dtos.MemberDTO;
import com.asif.ethereum.modules.members.dtos.MemberRemoveDTO;
import com.asif.ethereum.modules.members.resources.DonationResource;
import com.asif.ethereum.modules.members.resources.MemberCreateResponce;
import com.asif.ethereum.modules.members.resources.MemberResource;
import com.asif.ethereum.modules.members.services.MemberServices;
import java.math.BigInteger;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.web3j.protocol.core.methods.response.TransactionReceipt;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MemberControllers {

  private final MemberServices memberServices;

  @RequestMapping(value = "v1/members", method = RequestMethod.POST)
  public ResponseEntity<MemberCreateResponce> add(@RequestBody @Validated MemberDTO memberDTO) {
    MemberCreateResponce member =
        memberServices.add(memberDTO);
    return new ResponseEntity<MemberCreateResponce>(member, HttpStatus.CREATED);
  }


  @RequestMapping(value = "v1/members/{memberId}", method = RequestMethod.GET)
  public ResponseEntity<MemberResource> get(
      @RequestParam(value = "contractId") Integer contractId,
      @PathVariable("memberId") BigInteger memberId) {
    MemberResource memberResource =
        memberServices.getById(contractId, memberId);
    return new ResponseEntity<MemberResource>(memberResource, HttpStatus.OK);
  }


  @RequestMapping(value = "v1/members", method = RequestMethod.GET)
  public ResponseEntity<List<MemberResource>> getAll(
      @RequestParam(value = "contractId") Integer contractId) {
    List<MemberResource> memberResource =
        memberServices.getAll(contractId);
    return new ResponseEntity<List<MemberResource>>(memberResource, HttpStatus.OK);
  }

  @RequestMapping(value = "v1/members", method = RequestMethod.DELETE)
  public ResponseEntity<TransactionReceipt> remove(
      @RequestBody @Validated MemberRemoveDTO memberRemoveDTO) {
    TransactionReceipt transactionReceipt =
        memberServices.remove(memberRemoveDTO);
    return new ResponseEntity<>(transactionReceipt, HttpStatus.NO_CONTENT);
  }

  @RequestMapping(value = "v1/members/donate", method = RequestMethod.POST)
  public ResponseEntity<DonationResource> donate(
      @RequestBody @Validated DonationDTO donationDTO ) {
    DonationResource donationResource = memberServices.donate(donationDTO);
    return new ResponseEntity<DonationResource>(donationResource, HttpStatus.CREATED);
  }

}
