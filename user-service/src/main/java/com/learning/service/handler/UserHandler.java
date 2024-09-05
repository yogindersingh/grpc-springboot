package com.learning.service.handler;

import com.learning.Entity.PortfolioItem;
import com.learning.Entity.User;
import com.learning.Util.ResponseUtil;
import com.learning.exceptions.UnknownUserException;
import com.learning.repository.PortFolioRepository;
import com.learning.repository.UserRepository;
import com.learning.user.Holding;
import com.learning.user.UserInformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserHandler {

  @Autowired
  UserRepository userRepository;

  @Autowired
  PortFolioRepository portFolioRepository;

  public UserInformation getUserInformation(Integer userId) {
    User user = userRepository.findById(userId).orElseThrow(() -> new UnknownUserException(userId));
    List<PortfolioItem> portFolioList = portFolioRepository.findAllByUserId(userId);
    return ResponseUtil.createUserInformationResponse(user, portFolioList);
  }

}
