package sample.security.global.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@OpenAPIDefinition
@Profile("!prod")
public class SpringdocConfig {

	@Bean
	public OpenAPI openAPI() {
		return new OpenAPI().info(info());
	}

  public Info info() {
    return new Info()
        .title("간편 로그인 어플리케이션")
        .description("간편 로그인 어플리케이션 샘플 코드 입니다.")
        .version("v1")
        .contact(new Contact()
            .email("effortsof@gmail.com")
            .name("CHEON HYUN SEUNG")
            .url("https://github.com/tmome"));
  }
}
