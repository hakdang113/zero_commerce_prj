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
    private String name;
    private Integer price;
    private Integer count;


    // 생성자
    public static ProductItemDto from (ProductItemEntity item) {
        return ProductItemDto.builder()
                .id(item.getId())
                .name(item.getName())
                .price(item.getPrice())
                .count(item.getCount())
                .build();
    }

}