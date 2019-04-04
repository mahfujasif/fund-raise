package com.asif.ethereum.services;

import com.asif.ethereum.common.Constant;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AuthenticationServices {

  private final HttpServletRequest httpServletRequest;

  public Integer getCurrentUserId() {
    System.out.println(LocalDateTime.now() + ": current user "+Integer.parseInt(httpServletRequest.getHeader(Constant.WALLET_ID)));
    return Integer.parseInt(httpServletRequest.getHeader(Constant.WALLET_ID));
  }

}
