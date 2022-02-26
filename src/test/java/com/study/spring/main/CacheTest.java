package com.study.spring.main;

import com.study.spring.app.domain.CacheData;
import com.study.spring.app.domain.CacheService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class CacheTest {

    @Autowired
    private CacheService cacheService;

    @Test
    public void getCacheTest() {

        final String key = "cacheDataKey";
        final String key2 = "cacheDataKey2";

        // getCache
        CacheData cacheData = cacheService.getCacheData("cacheDataKey");
        CacheData cacheData2 = cacheService.getCacheData(key2);

        if (!cacheService.isValidation(cacheData)) {
            cacheData = cacheService.updateCacheData(key, this.selectValue());
            cacheData2 = cacheService.updateCacheData(key2, this.selectValue());
        }

        System.out.println(cacheData.toString());
        System.out.println(cacheData2.toString());

        CacheData newCacheData = cacheService.getCacheData(key);
        CacheData newCacheData2 = cacheService.getCacheData(key2);

        System.out.println(newCacheData.toString());
        System.out.println(newCacheData2.toString());

        assertThat(cacheData.getValue()).isEqualTo(newCacheData.getValue());
        assertThat(cacheData.getExpirationDate()).isEqualTo(newCacheData.getExpirationDate());

    }

    private String selectValue() {
        return String.valueOf(new Random().nextInt(100));
    }

}
