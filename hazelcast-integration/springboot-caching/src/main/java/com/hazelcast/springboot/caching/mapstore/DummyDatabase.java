/*
 * Copyright (c) 2008-2016, Hazelcast, Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.hazelcast.springboot.caching.mapstore;

import org.slf4j.Logger;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import static org.slf4j.LoggerFactory.getLogger;

public class DummyDatabase {
    private static final Logger log = getLogger(DummyDatabase.class);
    private final Map<Integer, String> database = new ConcurrentHashMap<>();
    public AtomicInteger selectCounter = new AtomicInteger(0);

    public DummyDatabase() {
        for (int i = 0; i < 20; i++) {
            database.put(i, "value-" + i);
        }
    }

    public String select(int key) {
        log.info("select called: {} times", selectCounter.incrementAndGet());
        return database.get(key);
    }

    public boolean remove(int key) {
        return database.remove(key) != null;
    }

    public void store(int key, String value) {
        database.put(key, value);
    }

    public int[] selectKeys() {
        return database.keySet().stream().mapToInt(i -> i).toArray();
    }

    public void storeAll(Map<Integer, String> entries) {
        database.putAll(entries);
    }

    public Map<Integer, String> loadAllByKey(Collection<Integer> keys) {
        Map<Integer, String> map = new HashMap<>();
        for (Integer key : keys) {
            map.put(key, database.get(key));
        }
        return map;
    }
}
