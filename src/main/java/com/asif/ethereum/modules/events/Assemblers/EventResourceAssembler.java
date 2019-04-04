package com.asif.ethereum.modules.events.Assemblers;

import com.asif.ethereum.modules.events.resources.DonorResource;
import com.asif.ethereum.modules.events.resources.EventResource;
import java.math.BigInteger;
import java.util.List;
import org.springframework.stereotype.Component;
import org.web3j.tuples.generated.Tuple9;

@Component
public class EventResourceAssembler {

  public EventResource toResource(
      Tuple9<BigInteger, String, String, BigInteger, BigInteger, BigInteger, BigInteger, String, Boolean> tuple9,
      List<DonorResource> donorResources,
      BigInteger eventId) {
    return new EventResource().setCreatorId(tuple9.getValue1())
        .setTitle(tuple9.getValue2())
        .setDescription(tuple9.getValue3())
        .setTime(tuple9.getValue4())
        .setFund(tuple9.getValue5())
        .setMinimum_amount(tuple9.getValue6())
        .setTarget_amount(tuple9.getValue7())
        .setStatus(tuple9.getValue8())
        .setDonors(donorResources)
        .setEventId(eventId);
  }
}
