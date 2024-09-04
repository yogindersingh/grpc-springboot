package com.learning.service;

import com.learning.user.StockTradeRequest;
import com.learning.user.StockTradeResponse;
import com.learning.user.UserServiceGrpc;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

@Service
public class StockService {

  @GrpcClient("user-service")
  UserServiceGrpc.UserServiceBlockingStub userServiceBlockingStub;

  public StockTradeResponse getStockTrade(StockTradeRequest stockTradeRequest) {
    return userServiceBlockingStub.tradeStock(stockTradeRequest);
  }


}
