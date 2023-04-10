package sample.orderservice.domain.user.api;

import io.swagger.v3.oas.annotations.tags.Tag;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sample.orderservice.domain.user.application.UserService;
import sample.orderservice.domain.user.domain.UserEntity;
import sample.orderservice.domain.user.dto.UserJoinRequest;

@Tag(name = "user", description = "유저 관련 샘플 API")
@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/user")
public class UserApi {

  private final UserService userService;

  @PostMapping
  private UserEntity joinUser(final @RequestBody @Valid UserJoinRequest userJoinRequest) {
    return userService.saveUser(userJoinRequest);
  }

}
