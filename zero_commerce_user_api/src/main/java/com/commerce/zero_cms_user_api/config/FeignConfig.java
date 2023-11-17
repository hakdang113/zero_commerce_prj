package com.commerce.zero_cms_user_api.config;

import feign.auth.BasicAuthRequestInterceptor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class FeignConfig {

    @Qualifier(value = "mailgun") // mailgun 전용으로 쓰기 위한 설정
    @Bean
    public BasicAuthRequestInterceptor basicAuthRequestInterceptor() {
        return new BasicAuthRequestInterceptor("api" ,"Insert Your API Key"); // 개인 API Key
    }
}
