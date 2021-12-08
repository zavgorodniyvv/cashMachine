package com.cachemachine.controller;

import com.cachemachine.model.AddCacheDTO;
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
    public ResponseEntity<Integer> addCacheToCacheMachine(@RequestBody List<AddCacheDTO> addCacheDTO){
        return new ResponseEntity<>(cacheMachineService.addCache(addCacheDTO), HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<AddCacheDTO>> getCache(int sum){
        return new ResponseEntity<>(cacheMachineService.getCache(sum), HttpStatus.OK);
    }
}
