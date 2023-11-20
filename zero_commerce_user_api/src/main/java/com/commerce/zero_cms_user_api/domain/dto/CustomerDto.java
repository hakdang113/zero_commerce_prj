package com.commerce.zero_cms_user_api.domain.dto;

import com.commerce.zero_cms_user_api.domain.model.CustomerEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
public class CustomerDto {

    private Long id;
    private String email;

    public static CustomerDto from(CustomerEntity customer) {
        return new CustomerDto(customer.getId(), customer.getEmail());
    }


}
