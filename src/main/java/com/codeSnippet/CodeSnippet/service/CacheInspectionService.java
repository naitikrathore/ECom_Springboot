package com.codeSnippet.CodeSnippet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class CacheInspectionService {

    @Autowired
    private CacheManager cacheManager;

    //Internally we have n't specify the memory to use so it will use inMemory concurontHasjhMap tp stoere data in Heap area of this program
    public void printCacheContent(String cacheName){
        Cache cache = cacheManager.getCache(cacheName);
        if(cache != null){
            System.out.println("Cache Content");
            System.out.println(Objects.requireNonNull(cache.getNativeCache()).toString());
        }else{
            System.out.println("No Cache Content :" + cacheName);
        }
    }

}
