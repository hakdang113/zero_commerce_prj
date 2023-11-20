package com.commerce.zero_cms_order_api.domain.form;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddProductCartForm {

    private Long id;
    private Long sellerId;
    private String brand;
    private String productName;
    private String description;
    private List<ProductItem> productItems;


    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ProductItem {
        private Long id;
        private String itemNameWithSize; // '상품명_사이즈'
        private Integer price; // 가격
        private Integer count; // 0 ~ 999개
        private String season; // '년도/시즌'
        private String sex; // 남성용(M), 여성용(W), 공용(MW)
        private String category; // 카테고리

    }
}
