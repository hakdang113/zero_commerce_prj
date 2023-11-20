package com.commerce.zero_cms_user_api.domain.form;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateForm {

    private String userPassword;
    private String cellPhoneNumber;
    private String address;

}
