package com.asif.ethereum.modules.events.resources;

import java.math.BigInteger;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@Accessors(chain = true)
public class EventResource {

  private BigInteger creatorId;

  private BigInteger eventId;

  private String title;

  private String description;

  private BigInteger time;

  private BigInteger fund;

  private BigInteger minimum_amount;

  private BigInteger target_amount;

  private List<DonorResource> donors;

  private String status;

}
