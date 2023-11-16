package com.commerce.zero_cms_user_api.application;

import com.commerce.zero_cms_user_api.client.MailgunClient;
import com.commerce.zero_cms_user_api.client.mailgun.SendMailForm;
import com.commerce.zero_cms_user_api.domain.SignUpForm;
import com.commerce.zero_cms_user_api.domain.model.CustomerEntity;
import com.commerce.zero_cms_user_api.exception.CustomErrorCode;
import com.commerce.zero_cms_user_api.exception.CustomException;
import com.commerce.zero_cms_user_api.service.CustomerSignUpService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class SignUpApplication {

    private final MailgunClient mailgunClient;
    private final CustomerSignUpService customerSignUpService;


    // customer
    // customer 회원 가입
    public String customerSignUp(SignUpForm form) {
        if (customerSignUpService.isEmailExist(form.getEmail())) { // 회원 가입하려는 이메일이 이미 존재하는 경우
            // Exception
            throw new CustomException(CustomErrorCode.ALREADY_REGISTER_USER);
        }

        CustomerEntity customer = customerSignUpService.customerSignUp(form); // 회원 가입

        String randomCode = getRandomCode(); // 이메일 인증 코드
        SendMailForm sendMailForm = SendMailForm.builder() // 이메일 발송 형식
                .from("testEmail@test.com")
                .to(form.getEmail())
                .subject("회원 가입 인증 메일 입니다!")
                .text(getVerificationEmail(customer.getEmail(), customer.getName(), "customer", randomCode))
                .build();
        mailgunClient.sendEmail(sendMailForm); // 이메일 발송

        // 이메일을 보내고 성공한 경우
        customerSignUpService.changeCustomerValidationEmail(customer.getId(), randomCode); // 이메일 인증 상태 변경
        // 테스트를 위해 바로 승인으로 설정해놓은 부분
        // 나중에 제거

        return "감사합니다. 회원 가입에 성공했습니다!";
    }

    // customer 인증
    public void customerVerify(String email, String code) {
        customerSignUpService.verifyEmail(email, code);
    }



    // customer, seller 공통
    // 인증 랜덤 코드 생성
    private String getRandomCode() {
        // 12자리 문자, 숫자 조합 랜덤 코드 생성,
        return RandomStringUtils.random(12, true, true);

    }

    // 이메일 발송에 사용할 템플릿을 위한 메서드
    private String getVerificationEmail(String email, String name, String userType, String code) {
        return  "안녕하세요 " + name + "님!\n" +
                "회원 가입 인증을 위해 링크를 눌러주세요!\n\n" +
                "http://localhost:8081/signup/" + // Port: 8081
                userType +
                "verify/?email=" + email +
                "&code=" + code;

    }
}
