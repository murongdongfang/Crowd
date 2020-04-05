package com.whpu.crowd.exception;

/**
 *@author xxh
 *@date 2020/3/17
 *@discription: crowdfunding05-common-util
 */
public class LoginFailedException extends RuntimeException{


  public LoginFailedException() {
    super();
  }

  public LoginFailedException(String message) {
    super(message);
  }

  public LoginFailedException(String message, Throwable cause) {
    super(message, cause);
  }

  public LoginFailedException(Throwable cause) {
    super(cause);
  }

  protected LoginFailedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
