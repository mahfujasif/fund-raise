package com.asif.ethereum.modules.wallets.services;

import com.asif.ethereum.configs.PropertiesConfig;
import com.asif.ethereum.modules.contracts.services.ContractServices;
import com.asif.ethereum.modules.wallets.resources.WalletResources;
import com.asif.ethereum.services.AccountsServices;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.response.EthGetBalance;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class WalletServices {
  
  private final AccountsServices accountsServices;
  private final Web3j web3j;
  private final ContractServices contractServices;

  public WalletResources register(){
    BufferedReader bufferedReader = null;
    Integer walletId = 0;
    Integer maxWallets = null;
    try {
      bufferedReader = new BufferedReader(new FileReader(PropertiesConfig.USER_COUNT_FILE));
      walletId = Integer.parseInt(bufferedReader.readLine());
      maxWallets = Integer.parseInt(bufferedReader.readLine());
      bufferedReader.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
    walletId++;
    if (walletId > maxWallets) {
      System.out.println("exception");
      return new WalletResources().setWalletId(0);
    }

    BufferedWriter bufferedWriter = null;
    try {
      bufferedWriter = new BufferedWriter(new FileWriter(PropertiesConfig.USER_COUNT_FILE));
      bufferedWriter.write(walletId.toString());
      bufferedWriter.newLine();
      bufferedWriter.write(maxWallets.toString());
      bufferedWriter.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
    WalletResources walletResources = new WalletResources()
        .setWalletId(walletId)
        .setPublicAddress(accountsServices.IdAccountMap().get(walletId));
    return walletResources;
  }

  public BigInteger balance(String address) {
    EthGetBalance balance = null;
    try {
      balance = web3j.ethGetBalance(address, DefaultBlockParameterName.LATEST).send();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return balance.getBalance();
  }

  public WalletResources get(Integer id) {
    WalletResources walletResources = new WalletResources()
        .setWalletId(id)
        .setPublicAddress(accountsServices.IdAccountMap().get(id))
        .setBalance(balance(accountsServices.IdAccountMap().get(id)));
    return walletResources;

  }
}
