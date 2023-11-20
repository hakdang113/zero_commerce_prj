package com.commerce.zero_cms_user_api.controller.seller;

import com.commerce.zero_cms_security.JwtAuthenticationTokenProvider;
import com.commerce.zero_cms_security.common.UserVo;
import com.commerce.zero_cms_user_api.domain.dto.SellerDto;
import com.commerce.zero_cms_user_api.domain.model.SellerEntity;
import com.commerce.zero_cms_user_api.exception.CustomErrorCode;
import com.commerce.zero_cms_user_api.exception.CustomException;
import com.commerce.zero_cms_user_api.service.seller.SellerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/seller")
@RequiredArgsConstructor
public class SellerController{

    private final JwtAuthenticationTokenProvider jwtAuthenticationTokenProvider;
    private final SellerService sellerService;


    // 이미 로그인 된 상태에서,
    // 토큰을 이용하여 고객 정보를 가져옴
    // 토큰은 암호화 되어있는 상태이므로,
    @GetMapping("/getInfo")
    public ResponseEntity<SellerDto> getInfo(@RequestHeader(name = "X-AUTH-TOKEN") String token) {
        UserVo userVo = jwtAuthenticationTokenProvider.getUserVo(token); // 토큰을 통해서 UserVo를 가져옴
        SellerEntity seller = sellerService.findByIdAndEmail(userVo.getId(), userVo.getEmail())
                .orElseThrow(
                        () -> new CustomException(CustomErrorCode.NOT_FOUND_USER)
                );
        return ResponseEntity.ok(SellerDto.from(seller));
    }

}
