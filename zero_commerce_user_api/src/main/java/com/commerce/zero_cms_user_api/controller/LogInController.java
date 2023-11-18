package com.commerce.zero_cms_user_api.controller;

import com.commerce.zero_cms_user_api.application.LogInApplication;
import com.commerce.zero_cms_user_api.domain.form.LogInForm;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/logIn")
public class LogInController {

    // 로그인을 하기 위해 아이디와 패스워드를 받을 form이 필요
    // 로그인 처리를 위한 토큰 필요

    private final LogInApplication logInApplication;

    // customer
    @PostMapping("/customer")
    public ResponseEntity<String> logInCustomer(@RequestBody LogInForm form) {
        return ResponseEntity.ok(logInApplication.customerLogInToken(form));
    }

    //seller
    @PostMapping("/seller")
    public ResponseEntity<String> logInSeller(@RequestBody LogInForm form) {
        return ResponseEntity.ok(logInApplication.sellerLogInToken(form));
    }

}