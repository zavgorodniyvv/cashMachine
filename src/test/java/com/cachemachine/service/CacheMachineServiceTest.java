package com.cachemachine.service;

import com.cachemachine.exception.ValidateException;
import com.cachemachine.model.Cache;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CacheMachineServiceTest {

    @Autowired
    private CacheMachineService cacheMachineService;

    @BeforeEach
    void setUp() {
        cacheMachineService.removeAllCache();
    }

    @Test
    void addCacheFalseResult() {
        assertThrows(ValidateException.class, () -> cacheMachineService.addCache(null), "Cache list should be null");
        assertThrows(ValidateException.class, () -> cacheMachineService.addCache(new LinkedList<>()), "Cache list should be empty");
        List<Cache> incorrectList = List.of(new Cache(101, 0));
        assertThrows(ValidateException.class, () -> cacheMachineService.addCache(incorrectList), "Cache list should not contain pars not from properties");
        List<Cache> correctList = List.of(new Cache(100, 0));
        assertDoesNotThrow(() -> cacheMachineService.addCache(correctList));
    }

    private static final List<List<Cache>> addCachesArray = List.of(
            List.of(new Cache(1, 100), new Cache(2, 1000)),
            List.of(new Cache(2,50 ), new Cache(20, 20), new Cache(500, 10), new Cache(1000, 3)),
            List.of(new Cache(10, 10), new Cache(50, 1), new Cache(5, 1))
    );
    private static final int[] expectedResults = {2100, 8500, 155};

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2})
    void addCacheTest(int i){
        assertEquals(expectedResults[i], cacheMachineService.addCache(addCachesArray.get(i)));
    }

    @Test
    void getCacheValidation(){
        assertThrows(ValidateException.class, () -> cacheMachineService.getCache(-1));
        assertThrows(ValidateException.class, () -> cacheMachineService.getCache(0));
        cacheMachineService.addCache(List.of(new Cache(1000, 1)));
        assertThrows(ValidateException.class, () -> cacheMachineService.getCache(1001));
        assertDoesNotThrow(() -> cacheMachineService.getCache(200));
    }

    private static final List<List<Cache>> expectedCache = List.of(
            List.of(new Cache(1, 1)),
            List.of(new Cache(1000, 1), new Cache(2, 1)),
            List.of(new Cache(200, 1), new Cache(20, 1), new Cache(10, 1), new Cache(5,1)),
            List.of(new Cache(1000, 1), new Cache(200, 1), new Cache(100, 1), new Cache(20, 2), new Cache(2, 1), new Cache(1,1 ))
    );

    private static final List<Integer> cache = List.of(
            1,
            1002,
            235,
            1343
    );

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 3})
    void getCache(int i) {
        cacheMachineService.addCache(List.of(new Cache(1000, 1000)));
        assertEquals(expectedCache.get(i), cacheMachineService.getCache(cache.get(i)));
    }
}