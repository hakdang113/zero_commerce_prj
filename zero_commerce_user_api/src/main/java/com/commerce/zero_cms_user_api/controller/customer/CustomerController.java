package com.commerce.zero_cms_user_api.controller.customer;

import com.commerce.zero_cms_security.JwtAuthenticationTokenProvider;
import com.commerce.zero_cms_security.common.UserVo;
import com.commerce.zero_cms_user_api.domain.dto.CustomerDto;
import com.commerce.zero_cms_user_api.domain.model.CustomerEntity;
import com.commerce.zero_cms_user_api.exception.CustomErrorCode;
import com.commerce.zero_cms_user_api.exception.CustomException;
import com.commerce.zero_cms_user_api.service.customer.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


// 이와 같이 고객 정보를 보는 기능을 사용한다면
// 로그인 하지 않은 사용자는 접근을 할 수 없도록 하기 위해
// 토큰을 사용

@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final JwtAuthenticationTokenProvider jwtAuthenticationTokenProvider;
    private final CustomerService customerService;


    // 이미 로그인 된 상태에서,
    // 토큰을 이용하여 고객 정보를 가져옴
    // 토큰은 암호화 되어있는 상태이므로,
    @GetMapping("/getInfo")
    public ResponseEntity<CustomerDto> getInfo(@RequestHeader(name = "X-AUTH-TOKEN") String token) { // 헤더로 받음
        UserVo userVo = jwtAuthenticationTokenProvider.getUserVo(token); // 토큰을 통해서 UserVo를 가져옴
        CustomerEntity customer = customerService.findByIdAndEmail(userVo.getId(), userVo.getEmail())
                .orElseThrow(
                        () -> new CustomException(CustomErrorCode.NOT_FOUND_USER)
                );
        return ResponseEntity.ok(CustomerDto.from(customer));
    }

}
