package sample.orderservice.global.utils.security;

import lombok.Builder;

@Builder
public class AuthTokenInfo {

  private String accessToken;
  private String refreshToken;
  private String grantType;
}
