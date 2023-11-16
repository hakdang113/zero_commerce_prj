package com.commerce.zero_cms_user_api;


import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@ServletComponentScan
@EnableFeignClients // 이메일 전송을 위해 필요한 어노테이션
@SpringBootApplication
@RequiredArgsConstructor

// 테스트 코드 실행하기 위해 추가한 어노테이션
@EnableJpaAuditing
@EnableJpaRepositories
public class ZeroCmsUserApplication {
    public static void main(String[] args) {
        SpringApplication.run(ZeroCmsUserApplication.class);
    }
}