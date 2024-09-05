package com.learning;

import com.learning.service.UserServiceImpl;
import com.learning.user.UserInformationRequest;
import com.learning.user.UserServiceGrpc;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(properties = {
    "grpc.server.port=-1",
    "grpc.server.in-process-name=integration-test",
    "grpc.client.user-service.address=in-process:integration-test"
})
public class UserServiceTests {

  @GrpcClient("user-service")
  private UserServiceGrpc.UserServiceBlockingStub userServiceBlockingStub;

  @Test
  public void test(){
    System.out.println(userServiceBlockingStub.getUserInformation(UserInformationRequest.newBuilder().setUserId(1).build()));
  }
}
