package sample.orderservice.global.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
@EnableWebSecurity
@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

  @Value("${jwt.secret}")
  private String secret;

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.cors().disable()
        .csrf().disable()
        .formLogin().disable()
        .headers().frameOptions().disable();
  }
}
