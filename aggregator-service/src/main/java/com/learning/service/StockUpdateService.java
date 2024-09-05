package com.learning.service;

import com.learning.listener.StockUpdateObserver;
import com.learning.stock.StockServiceGrpc;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@Service
public class StockUpdateService {

  @Autowired
  private StockUpdateObserver stockUpdateObserver;

  SseEmitter getStockServiceEmitter() {
    return stockUpdateObserver.createEmit();
  }
}
