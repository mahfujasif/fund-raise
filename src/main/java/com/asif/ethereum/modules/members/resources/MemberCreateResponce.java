package com.asif.ethereum.modules.members.resources;

import java.math.BigInteger;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@Accessors(chain = true)
public class MemberCreateResponce {

  private BigInteger memberId;

  private String memberAddress;

  private String name;
}
