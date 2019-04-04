package com.asif.ethereum.modules.wrappers.controller;

import com.asif.ethereum.modules.wrappers.services.WrapperServices;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class WrapperControllers {

  private final WrapperServices wrapperServices;

  @RequestMapping(value = "v1/contracts/wrappers", method = RequestMethod.GET)
  public ResponseEntity<Map<String, Integer>> wrappers() {
    Map<String, Integer> wrappers = wrapperServices.wrappers();
    return new ResponseEntity<Map<String, Integer>>(wrappers, HttpStatus.OK);
  }

}
