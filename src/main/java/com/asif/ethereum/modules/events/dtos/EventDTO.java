package com.asif.ethereum.modules.events.dtos;

import java.math.BigInteger;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EventDTO {

  @NotNull
  private Integer contractId;

  @NotEmpty
  private String title;

  @NotEmpty
  private String description;

  @NotNull
  private BigInteger time;

  @NotNull
  private BigInteger minimum_amount;

  @NotNull
  private BigInteger target_amount;

}
