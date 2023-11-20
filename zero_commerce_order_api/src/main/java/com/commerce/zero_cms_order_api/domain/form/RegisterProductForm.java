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
public class RegisterProductForm {

    private String brand;
    private String productName;
    private String description;
    private List<RegisterProductItemForm> registerProductItems;

}
