package com.hazelcast.springboot.caching.member;


import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class TrainingDataSetTest extends IntegrationBaseTest {


    @Autowired
    HazelcastInstance hazelcastInstance;


    @Test
    public void shouldGetElementsFromDBColdStart() {
        //given
        IMap<Integer, String> training = hazelcastInstance.getMap("training");

        //then
        assertThat(training.get(3)).isEqualTo("value-3");
        assertThat(training.get(4)).isEqualTo("value-4");

        assertThat(training.get(100_00)).isEqualTo(null);
    }

    @Test
    public void shouldGetElementsFromTrainingSetForInjectedViaIMap() {
        //given
        IMap<Integer, String> training = hazelcastInstance.getMap("training");

        //then
        assertThat(training.get(1)).isEqualTo("Tom");
        assertThat(training.get(2)).isEqualTo("Jim");

        assertThat(training.get(100_00)).isEqualTo(null);
    }
}