package com.commerce.zero_cms_order_api.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class ApiExceptionAdvice {

    // 에러 코드, 에러 메세지 표시
    @ExceptionHandler ({CustomException.class})
    public ResponseEntity<CustomException.CustomExceptionResponse>
    exceptionHandler(HttpServletRequest request, final CustomException exception) {
        return ResponseEntity
                .status(exception.getStatus())
                .body(CustomException.CustomExceptionResponse.builder()
                        .status(exception.getStatus())
                        .code(exception.getCustomErrorCode().name())
                        .message(exception.getMessage())
                        .build());
    }
}
