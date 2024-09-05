package com.learning.configuration;

import net.devh.boot.grpc.client.channelfactory.GrpcChannelConfigurer;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.protobuf.ProtobufJsonFormatHttpMessageConverter;
import com.google.protobuf.util.JsonFormat;

import java.util.concurrent.Executors;

@Configuration
public class ApplicationConfiguration {

  @Bean
  public ProtobufJsonFormatHttpMessageConverter protobufJsonFormatHttpMessageConverter(){
    return new ProtobufJsonFormatHttpMessageConverter(
        JsonFormat.parser().ignoringUnknownFields(),
        JsonFormat.printer().omittingInsignificantWhitespace().includingDefaultValueFields()
    );
  }

  @Bean
  public GrpcChannelConfigurer channelConfigurer(){
    return (channelBuilder, name) -> {
      System.out.println("channel builder "+ name);
      //channelBuilder.executor(Executors.newCachedThreadPool());
    };
  }

}
