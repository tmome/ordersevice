package sample.orderservice.domain.user.domain;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "users")
public class UserEntity implements UserDetails {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Long id;

  @Column(name = "user_id", nullable = false)
  private String userId;

  @Column(name = "user_password", nullable = false)
  private String userPassword;

  @Column(name = "user_name", nullable = false)
  private String userName;

  @CreatedDate
  @Column(name = "create_date", nullable = false, updatable = false)
  private LocalDateTime createDate;

  @LastModifiedDate
  @Column(name = "update_date")
  private LocalDateTime updateDate;


  @Builder
  private UserEntity(
      String userId,
      String userPassword,
      String userName,
      LocalDateTime createDate,
      LocalDateTime updateDate
  ) {
    this.userId = userId;
    this.userPassword = userPassword;
    this.userName = userName;
    this.createDate = createDate;
    this.updateDate = updateDate;
  }

  /**
   * 계정 권한 목록 리턴
   */
  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return Collections.emptyList();
  }

  /**
   * 유저 패스워드 리턴
   */
  @Override
  public String getPassword() {
    return userPassword;
  }

  /**
   * 유저 이름 리턴
   */
  @Override
  public String getUsername() {
    return userName;
  }

  /**
   * 계정 만료 여부 리턴 (true : 만료안됨)
   */
  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  /**
   * 계정 잠금 여부 리턴 (true : 잠금 안됨)
   * @return
   */
  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  /**
   * 비밀번호 만료 여부 리턴 (true : 만료안됨)
   * @return
   */
  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  /**
   * 계정 활성화 여부 (true : 활성화)
   * @return
   */
  @Override
  public boolean isEnabled() {
    return true;
  }

}
