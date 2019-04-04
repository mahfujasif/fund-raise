package com.asif.ethereum.libs;

import com.asif.ethereum.configs.PropertiesConfig;

public class Utils {

  public static String getWalletPath(Integer userId) {
    return PropertiesConfig.BASE_WALLET_PATH+userId.toString();
  }

}
