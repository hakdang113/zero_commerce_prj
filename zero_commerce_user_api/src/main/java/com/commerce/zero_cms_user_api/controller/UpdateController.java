package com.commerce.zero_cms_user_api.controller;

import com.commerce.zero_cms_security.JwtAuthenticationTokenProvider;
import com.commerce.zero_cms_user_api.domain.dto.UpdateDto;
import com.commerce.zero_cms_user_api.domain.form.UpdateForm;
import com.commerce.zero_cms_user_api.service.customer.CustomerUpdateService;
import com.commerce.zero_cms_user_api.service.seller.SellerUpdateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/update")
public class UpdateController {

    private final CustomerUpdateService customerUpdateService;
    private final SellerUpdateService sellerUpdateService;
    private final JwtAuthenticationTokenProvider jwtAuthenticationTokenProvider;

    // 수정
    // 고객
    @PutMapping("/customer")
    public ResponseEntity<UpdateDto> updateCustomer(@RequestHeader (name = "X-AUTH-TOKEN") String token,
                                                    @RequestBody UpdateForm form) {
        return ResponseEntity.ok(UpdateDto.from(customerUpdateService.updateCustomer(
                jwtAuthenticationTokenProvider.getUserVo(token).getEmail(), form)));
    }

    // 판매자
    @PutMapping("/seller")
    public ResponseEntity<UpdateDto> updateSeller(@RequestHeader (name = "X-AUTH-TOKEN") String token,
                                                    @RequestBody UpdateForm form) {
        return ResponseEntity.ok(UpdateDto.from(sellerUpdateService.updateSeller(
                jwtAuthenticationTokenProvider.getUserVo(token).getEmail(), form)));
    }
}
