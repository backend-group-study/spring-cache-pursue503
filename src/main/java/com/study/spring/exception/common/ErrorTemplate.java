package com.study.spring.exception.common;

import org.springframework.http.HttpStatus;

public interface ErrorTemplate {
    String getMessage();
    Integer getBizCode();
    HttpStatus getHttpStatus();

}
