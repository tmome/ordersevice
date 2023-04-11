package sample.security.global.utils.security;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class AuthTokenInfo {

  private String accessToken;
  private String refreshToken;
  private String grantType;
}
