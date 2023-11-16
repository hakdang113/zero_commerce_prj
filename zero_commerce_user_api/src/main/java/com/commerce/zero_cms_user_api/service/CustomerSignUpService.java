package com.commerce.zero_cms_user_api.service;

import com.commerce.zero_cms_user_api.domain.form.SignUpForm;
import com.commerce.zero_cms_user_api.domain.model.CustomerEntity;
import com.commerce.zero_cms_user_api.domain.repository.CustomerRepository;
import com.commerce.zero_cms_user_api.exception.CustomErrorCode;
import com.commerce.zero_cms_user_api.exception.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Locale;
import java.util.Optional;


@Service // 서비스 코드임을 알려주기 위해 사용하는 어노테이션
@RequiredArgsConstructor
public class CustomerSignUpService {

    private final CustomerRepository customerRepository;


    // 고객 회원 가입
    // SignUpForm 을 받음
    public CustomerEntity customerSignUp(SignUpForm form) {
        return customerRepository.save(CustomerEntity.from(form)); // form에서 받아온 정보를 repository에 저장
    }

    // 이메일 중복을 확인하기 위한 메서드
    public boolean isEmailExist(String email) {
        return customerRepository.findByEmail(email.toLowerCase(Locale.ROOT)) // 소문자로 변환
                .isPresent();
        // 이메일이 존재하면 true 리턴
    }

    // 고객 회원 가입 이메일 인증 진행 메서드
    @Transactional // 변경 사항이 있으면 자동으로 저장해주는 어노테이션
    public LocalDateTime changeCustomerValidationEmail(Long customerId, String verificationCode) {
        Optional<CustomerEntity> customerEntityOptional = customerRepository.findById(customerId);

        if (customerEntityOptional.isPresent()) {
            CustomerEntity customer = customerEntityOptional.get();
            customer.setVerificationCode(verificationCode);
            customer.setVerifyExpiredTime(LocalDateTime.now().plusDays(1)); // 현재 시간 기준 하루 뒤 인증 유효 시간 만료
            return customer.getVerifyExpiredTime();
        }

        // Exception
        throw new CustomException(CustomErrorCode.NOT_FOUND_USER);
    }

    @Transactional
    // 고객 이메일 인증 확인 메서드
    public void verifyEmail(String email, String code) {
        CustomerEntity customer = customerRepository.findByEmail(email) // 데이터베이스에서 email을 찾음
                .orElseThrow(() -> new CustomException(CustomErrorCode.NOT_FOUND_USER));

        if (customer.isVerify()) {
            // 고객 이메일 인증이 이미 되었다면
            // 예외 처리
            throw new CustomException(CustomErrorCode.ALREADY_VERIFIED);
        } else if (!customer.getVerificationCode().equals(code)) {
            // 인증 코드가 서로 다르면
            // 예외 처리
            throw new CustomException(CustomErrorCode.INCORRECT_CODE);
        } else if (customer.getVerifyExpiredTime().isBefore(LocalDateTime.now())) {
            // 인증 만료 시간이 현재 시간보다 이전이면
            // 예외 처리
            throw new CustomException(CustomErrorCode.VALID_VERIFICATION_TIME_OVER);
        }

        // 정상적으로 인증 되었다면
        customer.setVerify(true);
    }
}
