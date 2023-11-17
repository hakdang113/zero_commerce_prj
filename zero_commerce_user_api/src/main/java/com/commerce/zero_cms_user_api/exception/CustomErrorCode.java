package com.commerce.zero_cms_user_api.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;


@Getter
@RequiredArgsConstructor
public enum CustomErrorCode {

    // signup
    ALREADY_REGISTER_USER(HttpStatus.BAD_REQUEST, "이미 가입된 회원 입니다."),
    NOT_FOUND_USER(HttpStatus.BAD_REQUEST, "일치하는 회원 아이디가 없습니다."),
    ALREADY_VERIFIED(HttpStatus.BAD_REQUEST, "이미 인증이 완료된 이메일입니다."),
    INCORRECT_CODE(HttpStatus.BAD_REQUEST, "인증 코드가 다릅니다."),
    VALID_VERIFICATION_TIME_OVER(HttpStatus.BAD_REQUEST, "인증 시간이 만료 되었습니다."),

    // login
    LOGIN_FAIL(HttpStatus.BAD_REQUEST, "아이디 또는 비밀번호가 다릅니다");

    private final HttpStatus httpStatus;
    private final String errorDetail;
}
