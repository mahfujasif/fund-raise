package com.asif.ethereum.modules.events.controllers;

import com.asif.ethereum.modules.events.dtos.EventDTO;
import com.asif.ethereum.modules.events.resources.DonorResource;
import com.asif.ethereum.modules.events.resources.EventCloseResponses;
import com.asif.ethereum.modules.events.resources.EventCreateResponse;
import com.asif.ethereum.modules.events.resources.EventResource;
import com.asif.ethereum.modules.events.services.EventServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

@RestController
public class EventControllers {

  @Autowired
  EventServices eventServices;

  @RequestMapping(value = "v1/events", method = RequestMethod.POST)
  public ResponseEntity<EventCreateResponse> create(@RequestBody @Validated EventDTO eventDTO) {
    EventCreateResponse eventCreateResponse = eventServices.create(eventDTO);
    return new ResponseEntity<EventCreateResponse>(eventCreateResponse, HttpStatus.CREATED);
  }

  @RequestMapping(value = "v1/events/{eventId}", method = RequestMethod.GET)
  public ResponseEntity<EventResource> get(
      @PathVariable("eventId") final BigInteger eventId,
      @RequestParam("contractId") final Integer contractId) {
    EventResource eventResource = eventServices.getById(contractId, eventId);
    return new ResponseEntity<EventResource>(eventResource, HttpStatus.OK);
  }

  @RequestMapping(value = "v1/events", method = RequestMethod.GET)
  public ResponseEntity<List<EventResource>> getAll(
      @RequestParam("contractId") final Integer contractId) {
    List<EventResource> eventResourceList = eventServices.getAll(contractId);
    return new ResponseEntity<List<EventResource>>(eventResourceList, HttpStatus.OK);
  }

  @RequestMapping(value = "v1/events/{eventId}/donors", method = RequestMethod.GET)
  public ResponseEntity<List<DonorResource>> getDonors(
      @PathVariable("eventId") final BigInteger eventId,
      @RequestParam("contractId") final Integer contractId) {
    List<DonorResource> donorResourceList = eventServices.getDonorsById(
        contractId,
        eventId);
    return new ResponseEntity<List<DonorResource>>(donorResourceList, HttpStatus.OK);
  }

  @RequestMapping(value = "v1/events", method = RequestMethod.DELETE)
  public ResponseEntity<EventCloseResponses> close(
      @RequestParam("contractId") final Integer contractId,
      @RequestParam("eventId") final BigInteger eventId) {
    System.out.println("closing event...  "+contractId +" "+eventId);
    EventCloseResponses eventCloseResponses = eventServices.close(
        contractId,
        eventId);
    return new ResponseEntity<EventCloseResponses>(eventCloseResponses, HttpStatus.OK);
  }

  @RequestMapping(value = "v1/events/balances", method = RequestMethod.GET)
  public ResponseEntity<Map<String, BigInteger>> balances(
      @RequestParam("contractId") final Integer contractId) {
    Map<String, BigInteger> balanceMap = eventServices.balances(contractId);
    return new ResponseEntity<Map<String, BigInteger>>(balanceMap, HttpStatus.OK);
  }

}
