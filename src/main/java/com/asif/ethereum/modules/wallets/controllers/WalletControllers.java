package com.asif.ethereum.modules.wallets.controllers;

import com.asif.ethereum.modules.wallets.resources.WalletResources;
import com.asif.ethereum.modules.wallets.services.WalletServices;
import java.math.BigInteger;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class WalletControllers {

  private final WalletServices walletServices;

  @RequestMapping(value = "v1/wallets", method = RequestMethod.POST)
  public ResponseEntity<WalletResources> register() {
    WalletResources walletResources = walletServices.register();
    return new ResponseEntity<WalletResources>(walletResources, HttpStatus.CREATED);
  }


  @RequestMapping(value = "v1/wallets/{walletId}", method = RequestMethod.GET)
  public ResponseEntity<WalletResources> get(@PathVariable("walletId") Integer walletId) {
    WalletResources walletResources = walletServices.get(walletId);
    return new ResponseEntity<WalletResources>(walletResources, HttpStatus.OK);
  }
}
