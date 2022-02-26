package com.study.spring.app.domain;

import lombok.Data;

import java.time.LocalDateTime;

/**
 *
 * 캐싱될 데이터
 *
 */

@Data
public class CacheData {

    private String value;
    private LocalDateTime expirationDate;

}
