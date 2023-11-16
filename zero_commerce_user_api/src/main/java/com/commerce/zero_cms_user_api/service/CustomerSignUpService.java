package com.commerce.zero_cms_user_api.service;

import com.commerce.zero_cms_user_api.domain.SignUpForm;
import com.commerce.zero_cms_user_api.domain.model.CustomerEntity;
import com.commerce.zero_cms_user_api.domain.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service // 서비스 코드임을 알려주기 위해 사용하는 어노테이션
@RequiredArgsConstructor
public class CustomerSignUpService {

    private final CustomerRepository customerRepository;


    // 고객 회원 가입
    // SignUpForm 을 받음
    public CustomerEntity customerSignUp(SignUpForm form) {
        return customerRepository.save(CustomerEntity.from(form)); // form에서 받아온 정보를 repository에 저장
    }


}
