package com.commerce.zero_cms_user_api.domain.form;

// 로그인을 하기 위해 아이디와 패스워드를 받을 form이 필요

import lombok.Getter;

@Getter
public class LogInForm {
    private String email;
    private String userPassword;
}
