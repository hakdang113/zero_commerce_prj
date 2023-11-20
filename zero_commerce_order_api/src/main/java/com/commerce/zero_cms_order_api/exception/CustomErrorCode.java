package com.commerce.zero_cms_order_api.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;


@RequiredArgsConstructor
@Getter
public enum CustomErrorCode {


    // 상품 관리
    NOT_FOUND_PRODUCT (HttpStatus.BAD_REQUEST, "등록되지 않은 상품입니다."),
    NOT_FOUND_PRODUCT_ITEM(HttpStatus.BAD_REQUEST, "등록되지 않은 상품 아이템입니다."),
    SAME_ITEM_NAME_EXISTED (HttpStatus.BAD_REQUEST, "이미 존재하는 아이템 이름입니다."),


    // 장바구니
    ADD_CART_FAILED(HttpStatus.BAD_REQUEST, "장바구니에 상품을 추가할 수 없습니다."),
    NOT_ENOUGH_ITEM_COUNT(HttpStatus.BAD_REQUEST, "장바구니에 추가할 상품의 수량이 부족합니다.");

    private final HttpStatus httpStatus;
    private final String errorDetail;

}
