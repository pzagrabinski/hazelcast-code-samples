package com.hazelcast.springboot.caching.member;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Map;

@Component
public class CachePopulator {

    @PostConstruct
    private void populateCache() {
        HazelcastInstance client = HazelcastClient.newHazelcastClient();

        // Create a Hazelcast backed map
        IMap<Integer, String> map = client.getMap("training");

        map.put(1, "Tom");
        map.put(2, "Jim");
    }
}
