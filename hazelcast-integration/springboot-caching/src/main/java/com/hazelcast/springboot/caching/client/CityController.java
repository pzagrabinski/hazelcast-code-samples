package com.hazelcast.springboot.caching.client;

import com.hazelcast.core.HazelcastInstance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

import static java.lang.String.format;
import static java.lang.System.nanoTime;

@RestController
public class CityController {
    private static final Logger LOGGER = LoggerFactory.getLogger(CityController.class);
    private final HazelcastInstance hazelcastInstance;
    private final IDummyBean dummy;

    @Autowired
    public CityController(HazelcastInstance hazelcastInstance, IDummyBean dummy) {
        this.hazelcastInstance = hazelcastInstance;
        this.dummy = dummy;
    }

    @RequestMapping("/city")
    public String getCity() {
        String logFormat = "%s call took %d millis with result: %s";
        long start1 = nanoTime();
        String city = dummy.getCity();
        long end1 = nanoTime();
        LOGGER.info(format(logFormat, "Rest", TimeUnit.NANOSECONDS.toMillis(end1 - start1), city));
        return city;
    }

    @RequestMapping(value = "city/{city}", method = RequestMethod.GET)
    public String setCity(@PathVariable String city) {
        return dummy.setCity(city);
    }
}
