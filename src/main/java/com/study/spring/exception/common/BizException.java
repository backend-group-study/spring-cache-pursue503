package com.study.spring.exception.common;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class BizException extends RuntimeException {

    private final String message;
    private final Integer bizCode;
    private final HttpStatus httpStatus;

    public BizException(ErrorTemplate error) {
        this.message = error.getMessage();
        this.bizCode = error.getBizCode();
        this.httpStatus = error.getHttpStatus();
    }

}
