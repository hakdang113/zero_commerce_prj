package com.commerce.zero_cms_user_api;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class ZeroCmsUserApplication {
    public static void main(String[] args) {
        SpringApplication.run(ZeroCmsUserApplication.class);
    }
}