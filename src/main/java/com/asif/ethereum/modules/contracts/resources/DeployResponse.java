package com.asif.ethereum.modules.contracts.resources;

import com.asif.ethereum.common.TransactionReceiptResponse;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@Accessors(chain = true)
public class DeployResponse extends TransactionReceiptResponse {

  private Integer contactId;
}
