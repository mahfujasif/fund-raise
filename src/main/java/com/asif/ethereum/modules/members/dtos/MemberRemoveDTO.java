package com.asif.ethereum.modules.members.dtos;

import java.math.BigInteger;
import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MemberRemoveDTO {

  @NotNull
  private Integer contractId;

  @NotNull
  private BigInteger memberId;
}
