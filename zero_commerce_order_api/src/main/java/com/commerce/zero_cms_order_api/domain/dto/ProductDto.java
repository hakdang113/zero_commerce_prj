package com.commerce.zero_cms_order_api.domain.dto;

import com.commerce.zero_cms_order_api.domain.model.ProductEntity;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {

    private Long id;
    private String productName;
    private String description;
    private List<ProductItemDto> productItems;


    // 생성자
    public static ProductDto from(ProductEntity product) {
        List<ProductItemDto> productItems = product.getProductItemEntities()
                .stream().map(ProductItemDto::from).collect(Collectors.toList());

        return ProductDto.builder()
                .id(product.getId())
                .productName(product.getProductName())
                .description(product.getDescription())
                .productItems(productItems)
                .build();
    }


    public static ProductDto fromWithoutProductItems(ProductEntity product) {

        return ProductDto.builder()
                .id(product.getId())
                .productName(product.getProductName())
                .description(product.getDescription())
                .build();
    }
}
