package com.commerce.zero_cms_user_api.domain.dto;


import com.commerce.zero_cms_user_api.domain.model.CustomerEntity;
import com.commerce.zero_cms_user_api.domain.model.SellerEntity;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
public class UpdateDto {

    private String userPassword;
    private String address;
    private String cellPhoneNumber;

    public static UpdateDto from(CustomerEntity customer) {
        return new UpdateDto(customer.getUserPassword(), customer.getAddress(), customer.getCellPhoneNumber());
    }

    public static UpdateDto from(SellerEntity seller) {
        return new UpdateDto(seller.getUserPassword(), seller.getAddress(), seller.getCellPhoneNumber());
    }
}



