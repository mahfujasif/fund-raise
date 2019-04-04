package com.asif.ethereum.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor(onConstructor = @__())
public class AccountsServices {

  public Map<String, Integer> accountIdMap() {
    Map<String, Integer> accountIdMap = new HashMap<>();
    BufferedReader bufferedReader = null;
    String line = null;
    try {
      bufferedReader = new BufferedReader(new FileReader("/home/mahfuj/Geth/acoountIdMap"));
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    try {
      while((line=bufferedReader.readLine())!=null) {
        String[] splited = line.split("\\s+");
        String address = splited[0];
        Integer id = Integer.parseInt(splited[1]);
        accountIdMap.put(address, id);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    return accountIdMap;
  }

  public Map<Integer, String> IdAccountMap() {
    Map<Integer, String> accountIdMap = new HashMap<>();
    BufferedReader bufferedReader = null;
    String line = null;
    try {
      bufferedReader = new BufferedReader(new FileReader("/home/mahfuj/Geth/acoountIdMap"));
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    try {
      while((line=bufferedReader.readLine())!=null) {
        String[] splited = line.split("\\s+");
        String address = splited[0];
        Integer id = Integer.parseInt(splited[1]);
        accountIdMap.put(id, address);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    return accountIdMap;
  }

}
