package com.asif.ethereum.modules.contracts.services;

import com.asif.ethereum.configs.PropertiesConfig;
import com.asif.ethereum.contracts.FundManager;
import com.asif.ethereum.libs.Utils;
import com.asif.ethereum.modules.contracts.dtos.ContractDTO;
import com.asif.ethereum.modules.contracts.resources.DeployResponse;
import com.asif.ethereum.modules.wallets.resources.WalletResources;
import com.asif.ethereum.services.AccountsServices;
import com.asif.ethereum.services.AuthenticationServices;
import java.io.IOException;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.web3j.crypto.CipherException;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ContractServices {

  private final Web3j web3j;
  private final AccountsServices accountsServices;
  private final AuthenticationServices authenticationServices;

  public Credentials getCredentials() {
    Credentials credentials = null;
    try {
      credentials = WalletUtils.loadCredentials(
          PropertiesConfig.PASSWORD,
          Utils.getWalletPath(authenticationServices.getCurrentUserId())
      );
    } catch (IOException | CipherException e) {
      e.printStackTrace();

    }
    return credentials;
  }

  private DeployResponse deployFundManager() {
    //logger.info("Deploying fund manager contract.");
    FundManager fundManager = null;
    try {
      fundManager = FundManager.deploy(
          web3j,
          this.getCredentials(),
          PropertiesConfig.DEPLOY_GAS_PRICE,
          PropertiesConfig.DEPLOY_GAS_LIMIT,
          PropertiesConfig.DEPLOY_INITIAL_VALUE
      ).send();
    } catch (Exception e) {
      e.printStackTrace();
    }
    //logger.info("Fund manager contract deployed at address " + fundManager.getContractAddress());
    DeployResponse deployResponse = new DeployResponse()
        .setContactId(101);
    deployResponse.setTransactionHash(fundManager.getTransactionReceipt().get().getTransactionHash())
        .setBlockNumber(fundManager.getTransactionReceipt().get().getBlockNumber().toString())
        .setContractAddress(fundManager.getTransactionReceipt().get().getContractAddress())
        .setStatus(fundManager.getTransactionReceipt().get().getStatus())
        .setExpenses(Integer.parseInt(fundManager.getTransactionReceipt().get().getGasUsed()
            .multiply(PropertiesConfig.DEPLOY_GAS_PRICE).toString()));
    return deployResponse;
  }

  public DeployResponse deploy(ContractDTO contractDTO) {
    DeployResponse deployResponse = null;
    switch (contractDTO.getContractId()) {
      case 101:
        deployResponse = deployFundManager();
        break;
    }
    return deployResponse;
  }


  private FundManager loadFundManager() {

    FundManager fundManager = null;
    fundManager = FundManager.load(
        PropertiesConfig.CONTRACT_FUNDMANAGER_ADDRESS,
        web3j,
        this.getCredentials(),
        PropertiesConfig.LOAD_GAS_PRICE,
        PropertiesConfig.LOAD_GAS_LIMIT
    );
    fundManager.getContractBinary();
    return fundManager;
  }

  public Object load(Integer contractId) {
    Object object = null;
    switch (contractId) {
      case 101:
        object = loadFundManager();
        break;
    }
    return object;
  }

  public Object load(ContractDTO contractDTO) {
    return load(contractDTO.getContractId());
  }

  public WalletResources owner(Integer contractId) {
    String address = null;
    switch (contractId) {
      case 101:
        FundManager fundManager = loadFundManager();
        try {
          address = fundManager.owner().send();
        } catch (Exception e) {
          e.printStackTrace();
        }
        break;
    }
    Map<String, Integer> acoountIdMap = accountsServices.accountIdMap();
    return new WalletResources().setWalletId(acoountIdMap.get(address)).setPublicAddress(address);
  }

}
