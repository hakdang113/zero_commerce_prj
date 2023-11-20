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
public class UpdateProductForm {

    private Long id;
    private String brand;
    private String productName;
    private String description;
    private List<UpdateProductItemForm> updateProductItems;

}
