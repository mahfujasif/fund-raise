package com.asif.ethereum.modules.events.services;

import com.asif.ethereum.configs.PropertiesConfig;
import com.asif.ethereum.contracts.FundManager;
import com.asif.ethereum.contracts.FundManager.EventClosedEventResponse;
import com.asif.ethereum.contracts.FundManager.EventCreatedEventResponse;
import com.asif.ethereum.modules.contracts.services.ContractServices;
import com.asif.ethereum.modules.events.Assemblers.EventResourceAssembler;
import com.asif.ethereum.modules.events.dtos.EventDTO;
import com.asif.ethereum.modules.events.resources.DonorResource;
import com.asif.ethereum.modules.events.resources.EventCloseResponses;
import com.asif.ethereum.modules.events.resources.EventCreateResponse;
import com.asif.ethereum.modules.events.resources.EventResource;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.asif.ethereum.services.CronJobServices;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.SerializationUtils;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tuples.generated.Tuple2;
import org.web3j.tuples.generated.Tuple9;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class EventServices {

  private final ContractServices contractServices;
  private final EventResourceAssembler eventResourceAssembler;
  private final CronJobServices cronJobServices;

  public EventCreateResponse create(EventDTO eventDTO) {

    FundManager fundManager = (FundManager)contractServices.load(eventDTO.getContractId());
    TransactionReceipt transactionReceipt = null;

    try {
      transactionReceipt = fundManager
          .creatEvent(eventDTO.getTitle(),
              eventDTO.getDescription(),
              eventDTO.getTime().multiply(new BigInteger("60")),
              eventDTO.getMinimum_amount(),
              eventDTO.getTarget_amount())
          .send();
    } catch (Exception e) {
      e.printStackTrace();
    }



    List<EventCreatedEventResponse> eventCreatedEventResponses = fundManager.getEventCreatedEvents(transactionReceipt);
    EventCreateResponse eventCreateResponse = new EventCreateResponse();

    try {
      String eventTitle = new String(eventCreatedEventResponses.get(0).title, "UTF-8");
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    }

    try {
      eventCreateResponse
          .setEventId(eventCreatedEventResponses.get(0).eventId)
          .setTitle(new String(eventCreatedEventResponses.get(0).title, "UTF-8"));
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    }
    eventCreateResponse
          .setStatus(transactionReceipt.getStatus())
          .setExpenses(transactionReceipt.getGasUsed().multiply(PropertiesConfig.LOAD_GAS_PRICE).intValue())
          .setTransactionHash(transactionReceipt.getTransactionHash())
          .setBlockNumber(transactionReceipt.getBlockNumber().toString())
          .setTo(transactionReceipt.getTo())
          .setFrom(transactionReceipt.getFrom());

    cronJobServices.scheduleEventTermination(
            eventDTO.getContractId(),
            eventCreateResponse.getEventId(),
            eventDTO.getTime(),
            eventDTO.getTitle()
    );

    return eventCreateResponse;
  }

  private BigInteger totalEventsCount(FundManager fundManager) {
    BigInteger eventsCount = null;
    try {
      eventsCount = fundManager.totalEvents().send();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return eventsCount;
  }

  public BigInteger totalEventsCount(Integer contractId) {
    FundManager fundManager = (FundManager)contractServices.load(contractId);
    return this.totalEventsCount(fundManager);
  }

  //public BigInteger fund

  private List<DonorResource> getDonorsById(FundManager fundManager, BigInteger eventId) {
    Integer totalDonors = null;
    Integer index;
    try {
      totalDonors = fundManager.totalEventDonars(eventId).send().intValue();
    } catch (Exception e) {
      e.printStackTrace();
    }

    List<DonorResource> donorsList = new ArrayList<>();
    Tuple2<BigInteger, BigInteger> tuple2 = null;

    for (index = 0; index < totalDonors; index++) {
      try {
        tuple2 =
            fundManager.getEventDonars(eventId, new BigInteger(index.toString())).send();
      } catch (Exception e) {
        e.printStackTrace();
      }
      DonorResource donorResource = new DonorResource().setMemberId(tuple2.getValue1())
          .setAmount(tuple2.getValue2());
      donorsList.add(donorResource);
    }
    return donorsList;
  }

  public List<DonorResource> getDonorsById(Integer contractId, BigInteger eventId) {
    FundManager fundManager = (FundManager)contractServices.load(contractId);
    return this.getDonorsById(fundManager, eventId);
  }

  private EventResource getById(FundManager fundManager, BigInteger eventId) {

    Tuple9<BigInteger, String, String, BigInteger, BigInteger, BigInteger, BigInteger, String, Boolean> tuple9 = null;

    try {
      tuple9 = fundManager.events(eventId).send();
    } catch (Exception e) {
      e.printStackTrace();
    }
    List<DonorResource> donorsList = this.getDonorsById(fundManager, eventId);
    //System.out.println(tuple9.getValue9());
    EventResource eventResource = eventResourceAssembler.toResource(tuple9, donorsList, eventId);
    return eventResource;
  }

  public EventResource getById(Integer contractId, BigInteger eventId) {
    FundManager fundManager = (FundManager)contractServices.load(contractId);

    if (eventId.intValue() >= this.totalEventsCount(fundManager).intValue()) {
      System.out.println("Exception");
    }
    return this.getById(fundManager, eventId);
  }

  public List<EventResource> getAll(Integer contractId) {
    FundManager fundManager = (FundManager)contractServices.load(contractId);
    Integer totalEvents =  this.totalEventsCount(fundManager).intValue();
    List<EventResource> eventResourcesList = new ArrayList<>();
    for(Integer i = 0; i< totalEvents; i++) {
      EventResource eventResource = this.getById(fundManager, new BigInteger(i.toString()));
      //System.out.println(eventResource.getDescription());
      eventResourcesList.add(eventResource);
    }
    return eventResourcesList;
  }

  public EventCloseResponses close(Integer contractId, BigInteger eventId) {
    FundManager fundManager = (FundManager)contractServices.load(contractId);
    if (eventId.intValue() >= this.totalEventsCount(fundManager).intValue()) {
      System.out.println("Exception");
    }
    TransactionReceipt transactionReceipt = null;
    try {
      transactionReceipt = fundManager.closeEvent(eventId).send();
    } catch (Exception e) {
      e.printStackTrace();
    }
    List<EventClosedEventResponse> eventClosedEventResponses = fundManager.getEventClosedEvents(transactionReceipt);
    EventCloseResponses eventCloseResponses = new EventCloseResponses()
        .setEventId(eventClosedEventResponses.get(0).eventId.intValue())
        .setEventStatus(new String(eventClosedEventResponses.get(0).status));
    eventCloseResponses
        .setExpenses(transactionReceipt.getGasUsed().multiply(PropertiesConfig.LOAD_GAS_PRICE).intValue())
        .setTransactionHash(transactionReceipt.getTransactionHash())
        .setBlockNumber(transactionReceipt.getBlockNumber().toString())
        .setStatus(transactionReceipt.getStatus());

    return eventCloseResponses;
  }

  public Map<String, BigInteger> balances(Integer contractId) {
    FundManager fundManager = (FundManager)contractServices.load(contractId);
    BigInteger balance = null;
    Map<String, BigInteger> balanceMap = new HashMap<>();
    try {
      balance = fundManager.contractBalance().send();
    } catch (Exception e) {
      e.printStackTrace();
    }
    balanceMap.put("total balances: ", balance);
    Integer totalEvents =  this.totalEventsCount(fundManager).intValue();
    for(Integer i = 0; i< totalEvents; i++) {
      EventResource eventResource = this.getById(fundManager, new BigInteger(i.toString()));
      balanceMap.put(eventResource.getTitle(), eventResource.getFund());
    }
    return balanceMap;
  }

}