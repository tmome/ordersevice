package sample.orderservice.domain.user.exception;

import sample.orderservice.global.support.code.ErrorCode;
import sample.orderservice.global.support.exception.BusinessException;

public class SignUpValidationException extends BusinessException {
  public SignUpValidationException() {
    super(ErrorCode.SIGN_UP_FAIL);
  }
}
