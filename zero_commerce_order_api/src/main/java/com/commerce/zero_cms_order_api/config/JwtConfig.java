package com.commerce.zero_cms_order_api.config;

import com.commerce.zero_cms_security.JwtAuthenticationTokenProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class JwtConfig {
    @Bean
    public JwtAuthenticationTokenProvider jwtAuthenticationTokenProvider() {
        return new JwtAuthenticationTokenProvider();
    }
}
