package sample.security.domain.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springdoc.api.annotations.ParameterObject;
import sample.security.domain.user.domain.UserEntity;

@Schema(description = "유저 회원가입 정보 반환 모델")
@ParameterObject
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class UserSignUpResponse {

  private String userId;
  private String userName;

  public static UserSignUpResponse buildForUserResponse(final UserEntity userEntity) {
    return UserSignUpResponse.builder()
        .userId(userEntity.getUserId())
        .userName(userEntity.getUsername())
        .build();
  }
}
