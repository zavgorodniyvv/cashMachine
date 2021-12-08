package com.cachemachine.service;

import com.cachemachine.exception.ValidateException;
import com.cachemachine.model.AddCacheDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CacheMachineServiceTest {

    @Autowired
    private CacheMachineService cacheMachineService;

    @Test
    void addCacheFalseResult() {
        assertThrows(ValidateException.class, () -> cacheMachineService.addCache(null), "Cache list should be null");
        assertThrows(ValidateException.class, () -> cacheMachineService.addCache(new LinkedList<>()), "Cache list should be empty");
        List<AddCacheDTO> incorrectList = List.of(new AddCacheDTO(101, 0));
        assertThrows(ValidateException.class, () -> cacheMachineService.addCache(incorrectList), "Cache list should not contain pars not from properties");
        List<AddCacheDTO> correctList = List.of(new AddCacheDTO(100, 0));
        assertDoesNotThrow(() -> cacheMachineService.addCache(correctList));
    }

    private static final List<List<AddCacheDTO>> addCachesArray = List.of(
            List.of(new AddCacheDTO(1, 100), new AddCacheDTO(2, 1000)),
            List.of(new AddCacheDTO(2,50 ), new AddCacheDTO(20, 20), new AddCacheDTO(500, 10), new AddCacheDTO(1000, 3)),
            List.of(new AddCacheDTO(10, 10), new AddCacheDTO(50, 1), new AddCacheDTO(5, 1))
    );
    private static final int[] expectedResults = {2100, 8500, 155};

    @Test
    void addCacheTest(){
        //todo refactor
        assertEquals(expectedResults[0], cacheMachineService.addCache(addCachesArray.get(0)));
    }

    @Test
    void getCache() {
    }
}