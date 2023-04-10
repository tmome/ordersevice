package sample.orderservice.domain.user.domain;

import java.time.LocalDateTime;
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

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "users")
public class UserEntity {
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
}
