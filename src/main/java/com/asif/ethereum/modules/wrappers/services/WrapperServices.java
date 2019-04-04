package com.asif.ethereum.modules.wrappers.services;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__())
public class WrapperServices {

  public Map<String, Integer> wrappers() {
    Map<String, Integer> contractIdMap = new HashMap<>();
    BufferedReader bufferedReader = null;
    String wrapper = null;
    String contract = null;
    Integer contractId = 0;

    try {
      bufferedReader = new BufferedReader(
          new FileReader("/home/mahfuj/workspace/fundraise/src/main/resources/wrappersIdMap"));
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    try {
      while ((wrapper = bufferedReader.readLine()) != null) {
        contractId = Integer.valueOf(wrapper.substring(wrapper.length()-3, wrapper.length()));
        contract = wrapper.substring(0, wrapper.length() - 4);
        contractIdMap.put(contract, contractId);
      }
      bufferedReader.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return contractIdMap;
  }
}
