package com.learning.exceptions;


public class InsufficientQuantityException extends RuntimeException {

  private static String message = "User with id %s has insufficient quantity";

  public InsufficientQuantityException(Integer userId) {
    super(getTransformedErrorCode(userId));
  }

  private static String getTransformedErrorCode(Integer userId) {
    return String.format(message, userId);
  }


}
