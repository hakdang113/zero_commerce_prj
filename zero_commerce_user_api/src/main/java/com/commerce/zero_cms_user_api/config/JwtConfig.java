package com.commerce.zero_cms_user_api.config;


import com.commerce.zero_cms_security.JwtAuthenticationTokenProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// 모듈을 가져와 연결한 것이므로,
// Bean이 자동으로 생성되지 않음
@Configuration
public class JwtConfig {

    @Bean
    public JwtAuthenticationTokenProvider jwtAuthenticationTokenProvider() {
        return new JwtAuthenticationTokenProvider();
    }
}
