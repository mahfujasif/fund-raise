package com.asif.ethereum.modules.members.resources;

import com.asif.ethereum.common.TransactionReceiptResponse;
import java.math.BigInteger;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@Accessors(chain = true)
public class DonationResource extends TransactionReceiptResponse {

  private BigInteger eventId;

  private BigInteger amount;
}
