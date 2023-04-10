package sample.orderservice.domain.user.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springdoc.api.annotations.ParameterObject;

@Schema(description = "유저 회원가입 정보 모델")
@ParameterObject
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class UserSignUpRequest {

  @NotBlank
  @Schema(description = "유저 아이디", example = "test", required = true)
  private String userId;

  @NotBlank
  @Schema(description = "유저 패스워드", example = "testPassword", required = true)
  private String userPassword;

  @NotBlank
  @Schema(description = "유저 이름", example = "testName", required = true)
  private String userName;

}
