package com.delta.exception;

/**
 * @author: ACE.CHIU
 * @create: 2022-06-30
 */

public class SystemDataExistsException extends AbstractDeltaException {
  private static final long serialVersionUID = -5375236475871460303L;
  public static final String ERROR_CODE = "20101";

  public SystemDataExistsException(String message) {
    super(message);
  }

  @Override
  public String getErrorCode() {
    return ERROR_CODE;
  }
}