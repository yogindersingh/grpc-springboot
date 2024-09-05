package com.learning.tests.mockservice;

import com.google.common.util.concurrent.Uninterruptibles;
import com.google.protobuf.Empty;
import com.learning.common.Ticker;
import com.learning.stock.PriceUpdate;
import com.learning.stock.StockServiceGrpc;
import io.grpc.stub.StreamObserver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

public class StockMockService extends StockServiceGrpc.StockServiceImplBase {

    private static final Logger log = LoggerFactory.getLogger(StockMockService.class);

    @Override
    public void getPriceUpdates(Empty request, StreamObserver<PriceUpdate> responseObserver) {
        responseObserver.onNext(PriceUpdate.newBuilder().setPrice(10).setTicker(Ticker.AMAZON).build());
        responseObserver.onCompleted();
    }
}
