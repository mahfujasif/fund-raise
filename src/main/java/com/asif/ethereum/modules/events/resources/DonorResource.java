package com.asif.ethereum.modules.events.resources;

import java.math.BigInteger;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@Accessors(chain = true)
public class DonorResource {

  private BigInteger memberId;

  private BigInteger amount;
}
