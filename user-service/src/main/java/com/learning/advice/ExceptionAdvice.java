package com.learning.advice;


import com.learning.exceptions.InsufficientBalanceException;
import com.learning.exceptions.InsufficientQuantityException;
import com.learning.exceptions.InvalidTickerException;
import com.learning.exceptions.UnknownUserException;
import io.grpc.Status;
import net.devh.boot.grpc.server.advice.GrpcAdvice;
import net.devh.boot.grpc.server.advice.GrpcExceptionHandler;

@GrpcAdvice
public class ExceptionAdvice {

  @GrpcExceptionHandler(InvalidTickerException.class)
  public Status handleTickerException(InvalidTickerException throwable) {
    return Status.INVALID_ARGUMENT.withDescription(throwable.getMessage());
  }

  @GrpcExceptionHandler(UnknownUserException.class)
  public Status handleUnknownUserException(UnknownUserException throwable) {
    return Status.NOT_FOUND.withDescription(throwable.getMessage());
  }

  @GrpcExceptionHandler({InsufficientBalanceException.class, InsufficientQuantityException.class})
  public Status handlePreConditionException(Exception throwable) {
    return Status.FAILED_PRECONDITION.withDescription(throwable.getMessage());
  }

}
