package com.cachemachine.service;

import com.cachemachine.exception.ValidateException;
import com.cachemachine.model.Cache;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class CacheMachineService {

    @Value("${bill.pars}")
    private List<Integer> billPars;

    private int sumInCacheMachine;

    @PostConstruct
    private void init(){
        Collections.reverse(billPars);
    }

    public int addCache(List<Cache> cache) {
        isCacheDTOValid(cache);
        for(var element: cache){
            sumInCacheMachine += element.getPar() * element.getQuantity();
        }
        return sumInCacheMachine;
    }

    private void isCacheDTOValid(List<Cache> cache) {
        if(cache == null || cache.isEmpty()){
            throw new ValidateException("AddCacheDTO is null or empty");
        }
        for(var element: cache){
            if(!billPars.contains(element.getPar())){
                throw new ValidateException("AddCacheDTO is null or empty");
            }
        }
    }

    public List<Cache> getCache(int sum) {
        if(sum < 1){
            throw new ValidateException("Sum is less than 1");
        }
        if(sum > sumInCacheMachine){
            throw new ValidateException("Sum you want to get is greater than cache machine contains");
        }
        int tempSum = sum;
        var resultList = new ArrayList<Cache>();
        for(var bill: billPars){
            if(bill > tempSum){
                continue;
            }
            int  quantity = tempSum/bill;
            resultList.add(new Cache(bill, quantity));
            tempSum -= (bill * quantity);
        }
        sumInCacheMachine -= tempSum;
        return resultList;
    }

    public void removeAllCache(){
        sumInCacheMachine = 0;
    }
}
