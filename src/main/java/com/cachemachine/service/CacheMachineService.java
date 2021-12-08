package com.cachemachine.service;

import com.cachemachine.exception.ValidateException;
import com.cachemachine.model.AddCacheDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class CacheMachineService {

    @Value("${bill.pars}")
    private int[] billPars;

    private int sumInCacheMachine;

    public int addCache(List<AddCacheDTO> addCacheDTO) {
        isCacheDTOValid(addCacheDTO);
        for(var element: addCacheDTO){
            sumInCacheMachine += element.getPar() * element.getQuantity();
        }
        return sumInCacheMachine;
    }

    private void isCacheDTOValid(List<AddCacheDTO> addCacheDTO) {
        if(addCacheDTO == null || addCacheDTO.isEmpty()){
            throw new ValidateException("AddCacheDTO is null or empty");
        }
        for(var element: addCacheDTO){
            if(Arrays.binarySearch(billPars, element.getPar()) < 0){
                throw new ValidateException("AddCacheDTO is null or empty");
            }
        }
    }

    public List<AddCacheDTO> getCache(int sum) {
        return null;
    }
}
