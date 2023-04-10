package sample.orderservice.domain.user.application;

import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sample.orderservice.domain.user.dao.UserRepository;
import sample.orderservice.domain.user.domain.UserEntity;
import sample.orderservice.domain.user.dto.UserSignUpRequest;
import sample.orderservice.domain.user.dto.UserSignUpResponse;
import sample.orderservice.domain.user.exception.SignUpValidationException;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class UserSignUpService {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  public UserSignUpResponse save(final UserSignUpRequest userSignUpRequest) {
    userSignUpValidation(userSignUpRequest.getUserId());
    final var userEntity = userRepository.save(buildForUserSingUpEntity(userSignUpRequest));

    return UserSignUpResponse.buildForUserResponse(userEntity);
  }

  private UserEntity buildForUserSingUpEntity(final UserSignUpRequest userSignUpRequest) {
    return UserEntity.builder()
        .userId(userSignUpRequest.getUserId())
        .userPassword(passwordEncoder.encode(userSignUpRequest.getUserPassword()))
        .userName(userSignUpRequest.getUserName())
        .createDate(LocalDateTime.now())
        .updateDate(LocalDateTime.now())
        .build();
  }

  @Transactional(readOnly = true)
  public void userSignUpValidation(final String userId) {
    if (userRepository.existsByUserId(userId)) {
      throw new SignUpValidationException();
    }
  }
}
