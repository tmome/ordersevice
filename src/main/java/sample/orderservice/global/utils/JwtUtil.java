package sample.orderservice.global.utils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.security.Key;

public class JwtUtil {

  private Key key;

  public JwtUtil(final String secretKey) {
    this.key = Keys.hmacShaKeyFor(secretKey.getBytes());
  }

  public String createToken(final Long id, final String name) {
    return Jwts.builder()
        .claim("usedId", id)
        .claim("name", name)
        .signWith(key, SignatureAlgorithm.HS256)
        .compact();
  }
}
