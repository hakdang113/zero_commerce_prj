package com.commerce.zero_cms_user_api.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
@Slf4j

public class ExceptionController {

    @ExceptionHandler({
            CustomException.class
    })
    public ResponseEntity<ExceptionResponse> CustomRequestException(final CustomException e) {

        return ResponseEntity.badRequest()
                .body(new ExceptionResponse(e.getMessage(), e.getCustomErrorCode()));
    }


    @Getter
    @ToString
    @AllArgsConstructor
    public static class ExceptionResponse {
        private String message;
        private CustomErrorCode customErrorCode;
    }
}
