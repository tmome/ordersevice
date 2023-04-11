package sample.security.global.utils.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import sample.security.global.support.exception.UnAuthorizedException;

@Component
@Slf4j
public class JwtTokenProvider {

  private Key key;
  private static final String AUTH = "auth";
  private static final String GRANT_TYPE = "Bearer";

  public JwtTokenProvider(@Value("${jwt.secret}") final String secretKey) {
    byte[] keyBytes = Decoders.BASE64.decode(secretKey);
    this.key = Keys.hmacShaKeyFor(keyBytes);
  }

  /**
   * 토큰 생성
   */
  public AuthTokenInfo createToken(final Authentication authentication) {
    final var authorities = getAuthorities(authentication);

    return AuthTokenInfo.builder()
        .accessToken(getAccessToken(authentication, authorities))
        .refreshToken(getRefreshToken())
        .grantType(GRANT_TYPE)
        .build();
  }

  /**
   * JWT 복호화 메소드
   */
  public Authentication getAuthentication(String accessToken) {

    final var claims = parseClaims(accessToken);
    if (claims.get(AUTH) == null) {
      throw new UnAuthorizedException();
    }

    Collection<? extends GrantedAuthority> authorities =
        Arrays.stream(claims.get(AUTH).toString().split(","))
            .map(SimpleGrantedAuthority::new)
            .collect(Collectors.toList());

    final var userDetails = new User(claims.getSubject(), StringUtils.EMPTY, authorities);

    return new UsernamePasswordAuthenticationToken(userDetails, StringUtils.EMPTY, authorities);
  }

  /**
   * parse Claims Method
   */
  private Claims parseClaims(String accessToken) {
    try {
      return Jwts.parserBuilder().setSigningKey(key).build()
          .parseClaimsJws(accessToken)
          .getBody();
    } catch (ExpiredJwtException e) {
      return e.getClaims();
    }
  }

  /**
   * 권한 정보 가져오는 method
   */
  private String getAuthorities(final Authentication authentication) {
    return authentication.getAuthorities()
        .stream().map(GrantedAuthority::getAuthority)
        .collect(Collectors.joining(","));
  }

  /**
   * accessToken 생성 method
   */
  private String getAccessToken(
      final Authentication authentication,
      final String authorities
  ) {
    return Jwts.builder()
        .setSubject(authentication.getName())
        .claim(AUTH, authorities)
        .setExpiration(tokenExpireDate())
        .signWith(key, SignatureAlgorithm.HS256)
        .compact();
  }

  private String getRefreshToken() {
    return Jwts.builder()
        .setExpiration(tokenExpireDate())
        .signWith(key, SignatureAlgorithm.HS256)
        .compact();
  }

  /**
   * 만료 시간 설정
   */
  private static Date tokenExpireDate() {
    final var expireTime = new Date().getTime();
    return new Date(expireTime + 86400000);
  }

  /**
   * 유효성 검증
   */
  public boolean validateToken(final String token) {
    try {
      Jwts.parserBuilder().setSigningKey(key).build()
          .parseClaimsJws(token);
      return true;
    } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
      log.info("Invalid JWT Token", e);
    } catch (ExpiredJwtException e) {
      log.info("Expired JWT Token", e);
    } catch (UnsupportedJwtException e) {
      log.info("Unsupported JWT Token", e);
    } catch (IllegalArgumentException e) {
      log.info("JWT claims string is empty.", e);
    }
    return false;
  }
}
