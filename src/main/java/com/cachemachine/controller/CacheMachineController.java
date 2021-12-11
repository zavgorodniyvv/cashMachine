package com.cachemachine.controller;

import com.cachemachine.model.Cache;
import com.cachemachine.service.CacheMachineService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cache")
public class CacheMachineController {

    private final CacheMachineService cacheMachineService;

    public CacheMachineController(CacheMachineService cacheMachineService) {
        this.cacheMachineService = cacheMachineService;
    }

    @PostMapping()
    public ResponseEntity<Integer> addCacheToCacheMachine(@RequestBody List<Cache> cache){
        return new ResponseEntity<>(cacheMachineService.addCache(cache), HttpStatus.OK);
    }

    @PostMapping("/{sum}")
    public ResponseEntity<List<Cache>> getCache(int sum, @RequestBody List<Integer> billList){
        return new ResponseEntity<>(cacheMachineService.getCache(sum, billList), HttpStatus.OK);
    }
}
