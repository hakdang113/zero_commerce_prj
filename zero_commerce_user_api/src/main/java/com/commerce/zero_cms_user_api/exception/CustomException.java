package com.commerce.zero_cms_user_api.exception;

import lombok.Getter;


@Getter
public class CustomException extends RuntimeException {

    private final CustomErrorCode customErrorCode;

    public CustomException(CustomErrorCode customErrorCode) {
        super(customErrorCode.getErrorDetail());
        this.customErrorCode = customErrorCode;
    }

}
