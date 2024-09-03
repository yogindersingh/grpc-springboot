package com.learning.exceptions;


public class InvalidTickerException extends RuntimeException {

  private static String message = "Ticker is not valid";

  public InvalidTickerException() {
    super(message);
  }


}
