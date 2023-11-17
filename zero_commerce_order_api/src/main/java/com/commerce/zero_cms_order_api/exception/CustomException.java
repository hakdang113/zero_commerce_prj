package com.commerce.zero_cms_order_api.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;


@Getter
public class CustomException extends RuntimeException {

    private final CustomErrorCode customErrorCode;
    private final int status;
    private static final ObjectMapper objMapper = new ObjectMapper();

    public CustomException(CustomErrorCode customErrorCode) {
        super(customErrorCode.getErrorDetail());
        this.customErrorCode = customErrorCode;
        this.status = customErrorCode.getHttpStatus().value();
    }
}
