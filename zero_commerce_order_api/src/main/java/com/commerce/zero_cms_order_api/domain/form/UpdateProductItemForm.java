package com.commerce.zero_cms_order_api.domain.form;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateProductItemForm {

    private Long id;
    private String itemNameWithSize; // '상품명_사이즈'
    private Integer price; // 가격
    private Integer count; // 0 ~ 999개
    private String season; // '년도/시즌'
    private String sex; // 남성용(M), 여성용(W), 공용(MW)
    private String category; // 카테고리

}
