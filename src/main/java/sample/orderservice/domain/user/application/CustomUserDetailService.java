package sample.orderservice.domain.user.application;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import sample.orderservice.domain.user.dao.UserRepository;
import sample.orderservice.domain.user.domain.UserEntity;

@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  @Override
  public UserDetails loadUserByUsername(final String userId) throws UsernameNotFoundException {
    return userRepository.findByUserId(userId)
        .map(this::createUserDetails)
        .orElseThrow(() -> new UsernameNotFoundException("해당 유저를 찾을 수 없습니다."));
  }

  private UserDetails createUserDetails(final UserEntity userEntity) {
    return User.builder()
        .username(userEntity.getUsername())
        .password(userEntity.getPassword())
        .build();
  }
}
