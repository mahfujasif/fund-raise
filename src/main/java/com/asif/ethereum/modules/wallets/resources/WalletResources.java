package com.asif.ethereum.modules.wallets.resources;

import java.math.BigInteger;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@Accessors(chain = true)
public class WalletResources {

  private Integer walletId;

  private String publicAddress;

  private BigInteger balance;
}
