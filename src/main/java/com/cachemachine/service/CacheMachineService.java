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
        Collections.sort(billPars);
        Collections.reverse(billPars);
    }

    public int addCache(List<Cache> cache) {
        isCacheValid(cache);
        for(var element: cache){
            sumInCacheMachine += element.getPar() * element.getQuantity();
        }
        return sumInCacheMachine;
    }

    private void isCacheValid(List<Cache> cache) {
        if(cache == null || cache.isEmpty()){
            throw new ValidateException("AddCacheDTO is null or empty");
        }
        for(var element: cache){
            if(!billPars.contains(element.getPar())){
                throw new ValidateException("AddCacheDTO is null or empty");
            }
        }
    }

    public List<Cache> getCache(int sum, List<Integer> billsList) {
        if(sum < 1){
            throw new ValidateException("Sum is less than 1");
        }
        if(sum > sumInCacheMachine){
            throw new ValidateException("Sum you want to get is greater than cache machine contains");
        }
        List<Integer> localBillList;
        if(billsList == null || billsList.isEmpty()){
            localBillList = this.billPars;
        }else{
            localBillList = new ArrayList<>(billsList);
            Collections.sort(localBillList);
            Collections.reverse(localBillList);
        }
        int tempSum = sum;
        var resultList = new ArrayList<Cache>();
        for(var bill: localBillList){
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
