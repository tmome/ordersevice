package sample.orderservice.domain.user.application;

import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import sample.orderservice.domain.user.dao.UserRepository;
import sample.orderservice.domain.user.domain.UserEntity;
import sample.orderservice.domain.user.dto.UserJoinRequest;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  public UserEntity saveUser(final UserJoinRequest userJoinRequest) {
    return userRepository.save(buildForUserEntity(userJoinRequest));
  }

  private UserEntity buildForUserEntity(final UserJoinRequest userJoinRequest) {
    return UserEntity.builder()
        .userId(userJoinRequest.getUserId())
        .userPassword(passwordEncoder.encode(userJoinRequest.getUserPassword()))
        .userName(userJoinRequest.getUserName())
        .createDate(LocalDateTime.now())
        .updateDate(LocalDateTime.now())
        .build();
  }

}
