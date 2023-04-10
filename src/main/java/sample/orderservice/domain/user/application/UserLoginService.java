package sample.orderservice.domain.user.application;

import static org.postgresql.gss.MakeGSS.authenticate;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sample.orderservice.domain.user.dao.UserRepository;
import sample.orderservice.domain.user.dto.UserLoginRequest;
import sample.orderservice.domain.user.dto.UserLoginResponse;
import sample.orderservice.global.utils.security.AuthTokenInfo;
import sample.orderservice.global.utils.security.JwtTokenProvider;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class UserLoginService {

  private final AuthenticationManagerBuilder authenticationManagerBuilder;
  private final JwtTokenProvider jwtTokenProvider;

  public AuthTokenInfo login(final UserLoginRequest request) {
    return jwtTokenProvider.createToken(authentication(authenticationToken(request)));
  }

  private UsernamePasswordAuthenticationToken authenticationToken(final UserLoginRequest request) {
    return new UsernamePasswordAuthenticationToken(request.getUserId(), request.getUserPassword());
  }

  private Authentication authentication(final UsernamePasswordAuthenticationToken source) {
    return authenticationManagerBuilder.getObject()
        .authenticate(source);
  }
}
