package com.learning.service;

import com.learning.service.handler.UserHandler;
import com.learning.user.StockTradeRequest;
import com.learning.user.StockTradeResponse;
import com.learning.user.UserInformation;
import com.learning.user.UserInformationRequest;
import com.learning.user.UserServiceGrpc;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;

@GrpcService
public class UserServiceImpl extends UserServiceGrpc.UserServiceImplBase {

  @Autowired
  UserHandler userHandler;

  @Override
  public void getUserInformation(UserInformationRequest request, StreamObserver<UserInformation> responseObserver) {
    responseObserver.onNext(userHandler.getUserInformation(request.getUserId()));
    responseObserver.onCompleted();
  }

  @Override
  public void tradeStock(StockTradeRequest request, StreamObserver<StockTradeResponse> responseObserver) {
    responseObserver.onNext(null);
    responseObserver.onCompleted();
  }
}
