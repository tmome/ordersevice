package sample.orderservice.domain.user.api;

import io.swagger.v3.oas.annotations.tags.Tag;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sample.orderservice.domain.user.application.UserLoginService;
import sample.orderservice.domain.user.application.UserSignUpService;
import sample.orderservice.domain.user.dto.UserLoginRequest;
import sample.orderservice.domain.user.dto.UserLoginResponse;
import sample.orderservice.domain.user.dto.UserSignUpRequest;
import sample.orderservice.domain.user.dto.UserSignUpResponse;
import sample.orderservice.global.utils.security.AuthTokenInfo;

@Tag(name = "user", description = "유저 관련 샘플 API")
@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/v1/user")
public class UserApi {

  private final UserSignUpService userSignUpService;
  private final UserLoginService userLoginService;

  @PostMapping("/signUp")
  public UserSignUpResponse userSignUp(final @RequestBody @Valid UserSignUpRequest userSignUpRequest) {
    return userSignUpService.save(userSignUpRequest);
  }

  @PostMapping("/login")
  public AuthTokenInfo userLogin(final @RequestBody @Valid UserLoginRequest userLoginRequest) {
    return userLoginService.login(userLoginRequest);
  }
}
