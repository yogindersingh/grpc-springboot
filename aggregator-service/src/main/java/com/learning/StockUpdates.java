package com.learning;

import com.google.protobuf.Empty;
import com.learning.listener.StockUpdateObserver;
import com.learning.stock.StockServiceGrpc;
import com.learning.user.UserServiceGrpc;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class StockUpdates implements InitializingBean {

  @Autowired
  private StockUpdateObserver stockUpdateObserver;

  @GrpcClient("stock-service")
  private StockServiceGrpc.StockServiceStub stockServiceStub;

  @Override
  public void afterPropertiesSet() throws Exception {
    stockServiceStub.withWaitForReady().getPriceUpdates(Empty.getDefaultInstance(),stockUpdateObserver);
  }

}
