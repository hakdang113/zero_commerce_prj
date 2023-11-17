package com.commerce.zero_cms_order_api.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;


@RequiredArgsConstructor
@Getter
public enum CustomErrorCode {


    ALREADY_REGISTER_USER(HttpStatus.BAD_REQUEST, "이미 가입된 회원 입니다.");

    private final HttpStatus httpStatus;
    private final String errorDetail;

}
