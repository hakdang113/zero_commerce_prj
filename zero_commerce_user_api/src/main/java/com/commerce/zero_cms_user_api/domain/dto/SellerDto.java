package com.commerce.zero_cms_user_api.domain.dto;

import com.commerce.zero_cms_user_api.domain.model.SellerEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SellerDto {

    private Long id;
    private String email;

    public static SellerDto from(SellerEntity seller) {
        return new SellerDto(seller.getId(), seller.getEmail());
    }

}