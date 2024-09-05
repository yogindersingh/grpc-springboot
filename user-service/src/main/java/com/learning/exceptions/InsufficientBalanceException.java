package com.learning.exceptions;


public class InsufficientBalanceException extends RuntimeException {

  private static String message = "User with id %s has insufficient balance";

  public InsufficientBalanceException(Integer userId) {
    super(getTransformedErrorCode(userId));
  }

  private static String getTransformedErrorCode(Integer userId) {
    return String.format(message, userId);
  }


}
