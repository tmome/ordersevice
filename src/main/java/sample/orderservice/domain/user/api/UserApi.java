package sample.orderservice.domain.user.api;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "user", description = "유저 관련 샘플 API")
@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/user")
public class UserApi {

}
