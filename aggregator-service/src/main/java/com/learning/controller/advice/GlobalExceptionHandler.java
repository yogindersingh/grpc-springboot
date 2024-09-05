package com.learning.controller.advice;

import io.grpc.StatusRuntimeException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(StatusRuntimeException.class)
  public ResponseEntity<String> handleStatusRuntimeException(StatusRuntimeException e) {
    return switch (e.getStatus().getCode()) {
      case INVALID_ARGUMENT, FAILED_PRECONDITION -> ResponseEntity.badRequest().body(e.getStatus().getDescription());
      case NOT_FOUND -> new ResponseEntity<>(e.getStatus().getDescription(),HttpStatus.NOT_FOUND);
      default -> ResponseEntity.internalServerError().body(e.getMessage());
    };
  }

}
