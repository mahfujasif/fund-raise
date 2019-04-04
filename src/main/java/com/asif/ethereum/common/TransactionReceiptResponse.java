package com.asif.ethereum.common;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@Accessors(chain = true)
public class TransactionReceiptResponse {


  private String transactionHash;

  private String blockNumber;

  private Integer expenses;

  private String contractAddress;

  private String status;

  private String from;

  private String to;
}
