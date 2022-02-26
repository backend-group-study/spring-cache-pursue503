package com.study.spring.app.domain;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.time.LocalDateTime;

@Slf4j
@Service
public class CacheService {

    private static final CacheData EMPTY_DATA = new CacheData();

    /**
     *
     * cacheNames
     * 메서드 호출 결과가 저장되는 캐시의 이름입니다.
     * 이름은 특정 빈 정의의 한정자 값 또는 빈 이름과 일치하는 대상 캐시(또는 캐시)를 결정하는 데 사용할 수 있습니다.
     *
     * key
     * 키를 동적으로 계산하기 위한 SpEL(Spring Expression Language) 표현식입니다.
     * 기본값은 "" 이며, 이는 사용자 정의 keyGenerator 가 구성되지 않은 경우 모든 메소드 매개변수가 키로 간주됨을 의미합니다.
     * SpEL 표현식은 다음 메타데이터를 제공하는 전용 컨텍스트에 대해 평가됩니다.
     * #root.method , #root.target 및 #root.caches 각각 method , 대상 개체 및 영향을 받는 캐시에 대한 참조입니다.
     * 메서드 이름( #root.methodName ) 및 대상 클래스( #root.targetClass )에 대한 바로 가기도 사용할 수 있습니다.
     * 메서드 인수는 인덱스로 액세스할 수 있습니다. 예를 들어 두 번째 인수는 #root.args[1] , #p1 또는 #a1 을 통해 액세스할 수 있습니다.
     * 해당 정보를 사용할 수 있는 경우 이름으로 인수에 액세스할 수도 있습니다.
     *
     *
     */
    @Cacheable(cacheNames = "myFirstCache", key = "#key")
    public CacheData getCacheData(final String key) {
        log.info("======== 캐시 데이터가 존재하지 않습니다 ========");
        return EMPTY_DATA;
    }

    @CachePut(cacheNames = "myFirstCache", key = "#key")
    public CacheData updateCacheData(final String key, final String value) {
        log.info("======== key 에 대한 캐시데이터가 업데이트 =========");
        CacheData cacheData = new CacheData();
        cacheData.setValue(value);
        cacheData.setExpirationDate(LocalDateTime.now().plusDays(1));
        return cacheData;
    }

    @CacheEvict(cacheNames = "myFirstCache" , key = "#key")
    public boolean expireCacheData(final String key) {
        log.info("캐시 데이터가 지워 질경우 메소드 실행");
        return true;
    }

    public boolean isValidation( final CacheData cacheData) {
        return !ObjectUtils.isEmpty(cacheData)
                && !ObjectUtils.isEmpty(cacheData.getExpirationDate())
                && !ObjectUtils.isEmpty(cacheData.getValue())
                && cacheData.getExpirationDate().isAfter(LocalDateTime.now());
    }

}
