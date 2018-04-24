package com.hazelcast.springboot.caching.member;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class CachePopulator {

    @PostConstruct
    private void populateCache() {

    }
}
