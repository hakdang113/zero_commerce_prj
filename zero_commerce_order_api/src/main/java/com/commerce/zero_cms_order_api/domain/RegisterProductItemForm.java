package com.commerce.zero_cms_order_api.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterProductItemForm {

    private Long productId;
    private String name;
    private Integer price;
    private Integer count;
}
