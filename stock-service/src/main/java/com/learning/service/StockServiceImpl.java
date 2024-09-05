package com.learning.service;

import com.google.protobuf.Empty;
import com.learning.common.Ticker;
import com.learning.stock.PriceUpdate;
import com.learning.stock.StockServiceGrpc;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Random;
import java.util.random.RandomGenerator;

@GrpcService
public class StockServiceImpl extends StockServiceGrpc.StockServiceImplBase {

  Logger logger = LogManager.getLogger(StockServiceImpl.class);

  @Override
  public void getPriceUpdates(Empty request, StreamObserver<PriceUpdate> responseObserver) {
    while (true) {
      responseObserver.onNext(PriceUpdate.newBuilder().setPrice(new Random().nextInt(50)+1).setTicker(
          Ticker.APPLE).build());
      responseObserver.onNext(PriceUpdate.newBuilder().setPrice(new Random().nextInt(50)+1).setTicker(
          Ticker.GOOGLE).build());
      responseObserver.onNext(PriceUpdate.newBuilder().setPrice(new Random().nextInt(50)+1).setTicker(
          Ticker.AMAZON).build());
      responseObserver.onNext(PriceUpdate.newBuilder().setPrice(new Random().nextInt(50)+1).setTicker(
          Ticker.MICROSOFT).build());
      try {
        Thread.sleep(1000);
      } catch (Exception e) {
        logger.error("Error :", e);
      }
    }
  }
}
