package com.hazelcast.springboot.caching.mapstore;

import com.hazelcast.core.MapStore;
import org.slf4j.Logger;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static org.slf4j.LoggerFactory.getLogger;

public class DummyMapStore implements MapStore<Integer, String> {
    private static final Logger log = getLogger(DummyMapStore.class);
    private final DummyDatabase database = new DummyDatabase();

    public void store(Integer key, String value) {
        log.info("store k: {}, v: {}", key, value);
        database.store(key, value);
    }

    public void storeAll(Map<Integer, String> entries) {
        log.info("storeAll entries: {}", entries);
        database.storeAll(entries);
    }

    public void delete(Integer key) {
        log.info("delete for key: {}",key);
        database.remove(key);
    }

    public void deleteAll(Collection<Integer> keys) {
        log.info("deleteAll for keys: {}", keys);
        for (Integer key : keys) {
            delete(key);
        }
    }

    public String load(Integer key) {
        log.info("load for key: {}", key);
        // Before returning the value we want to sleep to
        // simulate a slow database
        sleep(250);

        // Create the value and return it from the load method
        return database.select(key);
    }

    public Map<Integer, String> loadAll(Collection<Integer> keys) {
        log.info("loadAll called for keys: {}", keys);
        Map<Integer, String> result = new HashMap<>();
        result.putAll(database.loadAllByKey(keys));
        return result;
    }

    //loading all data existing in Database. Pre-populating cache
    public Iterable<Integer> loadAllKeys() {
        log.info("loadAllKeys");
        // Can be used to pre-populate known keys
        return Arrays.stream(database.selectKeys()).mapToObj(i -> i).collect(Collectors.toList());
    }

    private void sleep(long millis) {
        try {
            TimeUnit.MILLISECONDS.sleep(millis);
        } catch (InterruptedException ignore) {
        }
    }
}
