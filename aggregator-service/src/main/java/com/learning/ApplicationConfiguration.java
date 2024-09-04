package com.learning;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.protobuf.ProtobufJsonFormatHttpMessageConverter;
import com.google.protobuf.util.JsonFormat;

@Configuration
public class ApplicationConfiguration {

  @Bean
  public ProtobufJsonFormatHttpMessageConverter protobufJsonFormatHttpMessageConverter(){
    return new ProtobufJsonFormatHttpMessageConverter(
        JsonFormat.parser().ignoringUnknownFields(),
        JsonFormat.printer().omittingInsignificantWhitespace().includingDefaultValueFields()
    );
  }


}
