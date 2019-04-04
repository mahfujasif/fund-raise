//package com.asif.ethereum.module.contract.services;
//
//import com.asif.ethereum.configs.PropertiesConfig;
//import com.asif.ethereum.contracts.FundManager;
//import java.io.IOException;
//import java.lang.reflect.InvocationTargetException;
//import java.lang.reflect.Method;
//import java.math.BigInteger;
//import lombok.RequiredArgsConstructor;
//import org.apache.log4j.Logger;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.web3j.crypto.Credentials;
//import org.web3j.protocol.Web3j;
//import org.web3j.protocol.core.methods.response.TransactionReceipt;
//
//@Service
//@RequiredArgsConstructor(onConstructor = @__(@Autowired))
//public class ContractServices2 {
//
//  private final Web3j web3j;
//  private final Credentials credentials;
//  final static Logger logger = Logger.getLogger(ContractServices2.class);
//
//  public TransactionReceipt deploy(Class targetClass) {
//    Object object = null;
//    Method deploy = null;
//    try {
//      deploy = targetClass.getMethod(
//          "deploy",
//          Web3j.class,
//          Credentials.class,
//          BigInteger.class,
//          BigInteger.class,
//          BigInteger.class);
//    } catch (NoSuchMethodException e) {
//      e.printStackTrace();
//    }
//
//    try {
//      object = deploy.invoke(
//          object,
//          web3j,
//          credentials,
//          PropertiesConfig.DEPLOY_GAS_PRICE,
//          PropertiesConfig.DEPLOY_GAS_LIMIT,
//          PropertiesConfig.DEPLOY_INITIAL_VALUE
//      );
//    } catch (IllegalAccessException e) {
//      e.printStackTrace();
//    } catch (InvocationTargetException e) {
//      e.printStackTrace();
//    }
//
//    logger.info("contract deployed at address: " + object.getContractAddress);
//    return object.;
//  }
//
//
//  public Object load(String contractId) {
//    Class d = FundManager.class;
//
//    FundManager fundManager = FundManager.load(
//        PropertiesConfig.CONTRACT_ADDRESS,
//        web3j,
//        credentials,
//        PropertiesConfig.LOAD_GAS_PRICE,
//        PropertiesConfig.LOAD_GAS_LIMIT
//    );
//    System.out.println("contract loaded.");
//    try {
//      System.out.println("contract validity: "+ fundManager.isValid());
//    } catch (IOException e) {
//      e.printStackTrace();
//    }
//    return fundManager;
//  }
//
//  public Object load(Integer contractId) {
//    Object object = null;
//    switch (contractId) {
//      case 101:
//        object = deploy(FundManager.class);
//        break;
//    }
//    return object;
//  }
//
//}
