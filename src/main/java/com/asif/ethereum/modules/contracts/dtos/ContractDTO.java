package com.asif.ethereum.modules.contracts.dtos;

import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ContractDTO {

  @NotNull
  Integer contractId;

}
