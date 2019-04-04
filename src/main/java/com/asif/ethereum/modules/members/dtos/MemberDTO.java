package com.asif.ethereum.modules.members.dtos;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MemberDTO {

  @NotNull
  private Integer contractId;

  @NotNull
  private Integer walletId;

  @NotEmpty
  private String userName;

}
