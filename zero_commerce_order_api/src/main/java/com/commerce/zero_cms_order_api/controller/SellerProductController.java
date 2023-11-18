package com.commerce.zero_cms_order_api.controller;

import com.commerce.zero_cms_order_api.domain.dto.ProductDto;
import com.commerce.zero_cms_order_api.domain.RegisterProductForm;
import com.commerce.zero_cms_order_api.service.ProductService;
import com.commerce.zero_cms_security.JwtAuthenticationTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


// 판매자가 상품을 관리하기 위한 컨트롤러
@RestController
@RequiredArgsConstructor
@RequestMapping("/seller/product")
public class SellerProductController {

    private final ProductService productService;
    private final JwtAuthenticationTokenProvider jwtAuthenticationTokenProvider;


    // 토큰, 등록 양식을 받아서 진행
    @PostMapping
    public ResponseEntity<ProductDto> registerProduct(@RequestHeader(name = "X-AUTH_TOKEN") String token,
                                                      @RequestBody RegisterProductForm form) {

        // return 을 위한 Dto model 필요
        return ResponseEntity.ok(ProductDto.from(productService.registerProduct(jwtAuthenticationTokenProvider.getUserVo(token).getId(), form)));


    }

}
