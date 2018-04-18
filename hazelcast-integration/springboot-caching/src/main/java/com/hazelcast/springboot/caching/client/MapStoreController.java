package com.hazelcast.springboot.caching.client;


import com.hazelcast.core.HazelcastInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class MapStoreController {
    private final Map<Integer, String> map;

    @Autowired
    public MapStoreController(HazelcastInstance hazelcastInstance) {
        map = hazelcastInstance.getMap("training");
    }

    @RequestMapping(value = "/map-store/{id}", method = RequestMethod.GET)
    public String getById(@PathVariable("id") Integer id) {
        return map.getOrDefault(id, "non-existing");
    }
}
