package com.asif.ethereum.modules.events.resources;

import com.asif.ethereum.common.TransactionReceiptResponse;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@Accessors(chain = true)
public class EventCloseResponses extends TransactionReceiptResponse {

  Integer eventId;

  String eventStatus;

}
