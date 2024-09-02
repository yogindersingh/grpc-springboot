package com.learning.Util;


import com.learning.Entity.PortfolioItem;
import com.learning.Entity.User;
import com.learning.user.Holding;
import com.learning.user.UserInformation;

import java.util.List;

public class ResponseUtil {


  public static UserInformation createUserInformationResponse(User user, List<PortfolioItem> portFolioList) {
    List<Holding> portFolios = portFolioList.stream()
        .map(val -> Holding.newBuilder().setTicker(val.getTicker()).setQuantity(val.getQuantity()).build()).toList();
    return UserInformation.newBuilder().setUserId(user.getId()).setBalance(user.getBalance()).setName(user.getName())
        .addAllHoldings(portFolios).build();
  }

}
