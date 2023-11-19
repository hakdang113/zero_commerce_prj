package com.commerce.zero_cms_order_api.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;


@RequiredArgsConstructor
@Getter
public enum CustomErrorCode {


    NOT_FOUND_PRODUCT (HttpStatus.BAD_REQUEST, "등록되지 않은 상품입니다."),
    NOT_FOUND_PRODUCT_ITEM(HttpStatus.BAD_REQUEST, "등록되지 않은 상품 아이템입니다."),
    SAME_ITEM_NAME_EXISTED (HttpStatus.BAD_REQUEST, "이미 존재하는 아이템 이름입니다.");

    private final HttpStatus httpStatus;
    private final String errorDetail;

}
