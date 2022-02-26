package com.study.spring.app.web.common.response.error;

import com.study.spring.app.web.common.response.error.ErrorResponse;
import com.study.spring.exception.common.BizException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@Slf4j
@RestControllerAdvice
public class ErrorControllerAdvice {

    @ExceptionHandler(BizException.class)
    public ResponseEntity<ErrorResponse> catchBizException(BizException e) {
        return generatorErrorResponse(e.getHttpStatus(), e);
    }


    public static ResponseEntity<ErrorResponse> generatorErrorResponse(HttpStatus httpStatus, Exception e) {
        ErrorResponse errorResponseDTO = ErrorResponse.builder()
                .message(e.getMessage())
                .httpStatus(httpStatus)
                .build();
        log.info(errorResponseDTO.toString());
        return new ResponseEntity<>(errorResponseDTO, httpStatus);
    }

}
