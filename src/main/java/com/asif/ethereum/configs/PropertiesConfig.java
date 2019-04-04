package com.asif.ethereum.configs;

import java.math.BigInteger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:config.properties")
public class PropertiesConfig {

  public static String WEB3J_RPC_URL;
  public static String CONTRACT_FUNDMANAGER_ADDRESS;
  public static BigInteger DEPLOY_GAS_PRICE;
  public static BigInteger DEPLOY_GAS_LIMIT;
  public static BigInteger DEPLOY_INITIAL_VALUE;
  public static BigInteger LOAD_GAS_PRICE;
  public static BigInteger LOAD_GAS_LIMIT;
  public static String PASSWORD;
  public static String BASE_WALLET_PATH;
  public static String USER_COUNT_FILE;

  @Value("${web3j.rpc.url}")
  public void setWeb3jRpcUrl(String value) {
    WEB3J_RPC_URL = value;
  }

  @Value("${contract.fundmanager.address}")
  public void setContractFundmanagerAddress(String value) {
    CONTRACT_FUNDMANAGER_ADDRESS = value;
  }

  @Value("${deploy.gas.price}")
  public void setDeployGasPrice(BigInteger value) {
    DEPLOY_GAS_PRICE = value;
  }

  @Value("${deploy.gas.limit}")
  public void setDeployGasLimit(BigInteger value) {
    DEPLOY_GAS_LIMIT = value;
  }

  @Value("${deploy.initial.value}")
  public void setDeployInitialValue(BigInteger value) {
    DEPLOY_INITIAL_VALUE = value;
  }

  @Value("${load.gas.price}")
  public void setLoadGasPrice(BigInteger value) {
    LOAD_GAS_PRICE = value;
  }

  @Value("${load.gas.limit}")
  public void setLoadGasLimit(BigInteger value) {
    LOAD_GAS_LIMIT = value;
  }

  @Value("${password}")
  public void setPASSWORD(String value) {
    PASSWORD = value;
  }

  @Value("${wallet.path}")
  public void setBaseWalletPath(String value) {
    BASE_WALLET_PATH = value;
  }

  @Value("${user.count.file}")
  public void setUserCountFile(String value) {
    USER_COUNT_FILE = value;
  }

}

