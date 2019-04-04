package com.asif.ethereum.configs;

import java.io.IOException;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;

@Component
public class Web3jConfig {

  @Bean
  Web3j creatWeb3jBean() {
    Web3j web3j = Web3j.build(new HttpService(PropertiesConfig.WEB3J_RPC_URL));
    return web3j;
  }
}
