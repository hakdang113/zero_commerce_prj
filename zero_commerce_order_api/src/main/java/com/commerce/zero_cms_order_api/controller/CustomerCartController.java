package com.commerce.zero_cms_order_api.controller;

import com.commerce.zero_cms_order_api.application.CartApplication;
import com.commerce.zero_cms_order_api.domain.form.AddProductCartForm;
import com.commerce.zero_cms_order_api.redis.Cart;
import com.commerce.zero_cms_security.JwtAuthenticationTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer/cart")
@RequiredArgsConstructor
public class CustomerCartController {

    private final CartApplication cartApplication;
    private final JwtAuthenticationTokenProvider jwtAuthenticationTokenProvider;


    @PostMapping
    public ResponseEntity<Cart> addCart(
            @RequestHeader(name = "X-AUTH-TOKEN") String token,
            @RequestBody AddProductCartForm form) {
        return ResponseEntity.ok(cartApplication.addCart(jwtAuthenticationTokenProvider.getUserVo(token).getId(), form));
    }
}
