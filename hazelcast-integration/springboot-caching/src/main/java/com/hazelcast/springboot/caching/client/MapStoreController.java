package com.hazelcast.springboot.caching.client;


import com.hazelcast.client.HazelcastClient;
import com.hazelcast.core.HazelcastInstance;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class MapStoreController {
    private final Map<Integer, String> map;

    public MapStoreController() {
        HazelcastInstance client = HazelcastClient.newHazelcastClient();

        // Create a Hazelcast backed map
        map = client.getMap("training");
    }

    @RequestMapping(value = "/map-store/{id}", method = RequestMethod.GET)
    public String getById(@PathVariable("id") Integer id) {
        return map.getOrDefault(id, "non-existing");
    }
}
