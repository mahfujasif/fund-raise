package com.asif.ethereum.modules.contracts.controllers;

import com.asif.ethereum.modules.contracts.dtos.ContractDTO;
import com.asif.ethereum.modules.contracts.resources.DeployResponse;
import com.asif.ethereum.modules.contracts.services.ContractServices;
import com.asif.ethereum.modules.wallets.resources.WalletResources;
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

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ContractControllers {

  private final ContractServices contractServices;

  @RequestMapping(value = "v1/contracts/deploy", method = RequestMethod.POST)
  public ResponseEntity<DeployResponse> deploy(@RequestBody @Validated ContractDTO contractDTO) {
    DeployResponse deployResponse =
        contractServices.deploy(contractDTO);
    return new ResponseEntity<DeployResponse>(deployResponse, HttpStatus.CREATED);
  }


  @RequestMapping(value = "v1/contracts/load", method = RequestMethod.GET)
  public ResponseEntity<Object> load(@RequestParam(value = "contractId") Integer contractId) {
    Object object = contractServices
        .load(contractId);
    return new ResponseEntity<Object>(object, HttpStatus.OK);
  }

  @RequestMapping(value = "v1/contracts/{conractId}/owner", method = RequestMethod.GET)
  public ResponseEntity<WalletResources> owner(@PathVariable("conractId") final Integer conractId) {
    WalletResources walletResources = contractServices.owner(conractId);
    return new ResponseEntity<WalletResources>(walletResources, HttpStatus.OK);
  }
}
