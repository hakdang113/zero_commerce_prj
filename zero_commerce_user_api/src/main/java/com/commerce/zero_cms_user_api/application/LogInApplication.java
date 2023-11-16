package com.commerce.zero_cms_user_api.application;

import com.commerce.zero_cms_security.JwtAuthenticationTokenProvider;
import com.commerce.zero_cms_security.common.UserType;
import com.commerce.zero_cms_user_api.domain.form.LogInForm;
import com.commerce.zero_cms_user_api.domain.model.CustomerEntity;
import com.commerce.zero_cms_user_api.exception.CustomErrorCode;
import com.commerce.zero_cms_user_api.exception.CustomException;
import com.commerce.zero_cms_user_api.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class LogInApplication {

    private final CustomerService customerService;
    private final JwtAuthenticationTokenProvider jwtAuthenticationTokenProvider;

    public String customerLogInToken(LogInForm form) {

        // 로그인 구현 진행 순서
        // 1. 로그인 가능 여부 확인
        CustomerEntity customer = customerService.findValidCustomer(form.getEmail(), form.getUserPassword())
                .orElseThrow(() -> new CustomException(CustomErrorCode.LOGIN_FAIL));

        // 2. 토큰 발행
        // 3. 토큰을 response 한다.
        return jwtAuthenticationTokenProvider.createToken(customer.getEmail(), customer.getId(), UserType.CUSTOMER);
    }
}
