package com.asif.ethereum.modules.members.resources;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@Accessors(chain = true)
public class MemberResource {

  private BigInteger memberId;

  private String memberAddress;

  private LocalDateTime since;

  private String name;

  private List<DonationResource> donations;
}
