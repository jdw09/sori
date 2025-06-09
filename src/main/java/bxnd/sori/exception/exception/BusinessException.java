package bxnd.sori.exception.exception;

import bxnd.sori.exception.errorcode.ErrorCode;
import lombok.Getter;
import sh4re_v2.sh4re_v2.exception.error_code.ErrorCode;

@Getter
public class BusinessException extends RuntimeException {
  private final ErrorCode errorCode;

  public BusinessException(ErrorCode errorCode) {
    super(errorCode.defaultMessage());
    this.errorCode = errorCode;
  }

  public BusinessException(ErrorCode errorCode, Throwable cause) {
    super(errorCode.defaultMessage(), cause);
    this.errorCode = errorCode;
  }

  public BusinessException(ErrorCode errorCode, String message) {
    super(message);
    this.errorCode = errorCode;
  }
}
