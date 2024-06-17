package com.ace.exception;

/**
 * @author: ACE.CHIU
 * @create: 2022-06-30
 */
public abstract class AbstractDeltaException extends RuntimeException {

  private static final long serialVersionUID = -1704508525572788612L;

  public AbstractDeltaException() {
    super();
  }

  public AbstractDeltaException(String message) {
    super(message);
  }

  public AbstractDeltaException(String message, Throwable cause) {
    super(message, cause);
  }

  public AbstractDeltaException(Throwable cause) {
    super(cause);
  }

  public AbstractDeltaException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }

  public abstract String getErrorCode();
}