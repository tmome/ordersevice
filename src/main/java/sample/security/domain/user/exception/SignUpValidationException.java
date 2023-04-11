package sample.security.domain.user.exception;

import sample.security.global.support.code.ErrorCode;
import sample.security.global.support.exception.BusinessException;

public class SignUpValidationException extends BusinessException {
  public SignUpValidationException() {
    super(ErrorCode.SIGN_UP_FAIL);
  }
}
