package com.learning.service;

import com.learning.user.UserInformation;
import com.learning.user.UserInformationRequest;
import com.learning.user.UserServiceGrpc;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  @GrpcClient("user-service")
  private UserServiceGrpc.UserServiceBlockingStub userClient;

  public UserInformation getUserInformation(int userId) {
    var request = UserInformationRequest.newBuilder()
        .setUserId(userId)
        .build();
    return this.userClient.getUserInformation(request);
  }

}
