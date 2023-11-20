package com.commerce.zero_cms_order_api.domain.dto;

import com.commerce.zero_cms_order_api.domain.model.ProductItemEntity;
import lombok.*;

@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ProductItemDto {

    private Long id;
    private String itemNameWithSize; // '상품명_사이즈'
    private Integer price;
    private Integer count;
    private String season; // '년도/시즌'
    private String sex; // 남성용(M), 여성용(W), 공용(MW)
    private String category; // 카테고리


    // 생성자
    public static ProductItemDto from (ProductItemEntity item) {
        return ProductItemDto.builder()
                .id(item.getId())
                .itemNameWithSize(item.getItemNameWithSize())
                .price(item.getPrice())
                .count(item.getCount())
                .season(item.getSeason())
                .sex(item.getSex())
                .category(item.getCategory())
                .build();
    }

}