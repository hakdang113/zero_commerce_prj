package com.commerce.zero_cms_user_api.domain.form;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter

// 테스트 코드 작성을 위해 추가한 어노테이션
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignUpForm {
    private String userId;
    private String userPassword;
    private String name;
    private String email;
    private String cellPhoneNumber;
    private String address;
    private LocalDate birthDate;
}
