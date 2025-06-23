package bxnd.sori.exception.errorcode;

import bxnd.sori.exception.exception.BusinessException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum AnnounceErrorCode implements ErrorCode {
  ANNOUNCE_NOT_FOUND("ANNOUNCE_NOT_FOUND", "공지를 찾을 수 없습니다.", HttpStatus.NOT_FOUND);

  private final String code;
  private final String message;
  private final HttpStatus httpStatus;

  @Override
  public String defaultMessage() {
    return message;
  }

  @Override
  public HttpStatus defaultHttpStatus() {
    return httpStatus;
  }

  @Override
  public RuntimeException defaultException() {
    return new BusinessException(this);
  }

  @Override
  public RuntimeException defaultException(Throwable cause) {
    return new BusinessException(this, cause);
  }

  @Override
  public Exception defaultException(String message) {
    return new BusinessException(this, message);
  }
}
