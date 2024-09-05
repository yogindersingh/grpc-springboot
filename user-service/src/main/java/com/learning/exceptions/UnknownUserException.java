package com.learning.exceptions;


public class UnknownUserException extends RuntimeException {

  private static String message = "User with id : %s not found";

  public UnknownUserException(Integer userId) {
    super(getTransformedErrorCode(userId));
  }

  private static String getTransformedErrorCode(Integer userId) {
    return String.format(message, userId);
  }


}
