package com.hazelcast.springboot.caching.member;


import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import com.hazelcast.springboot.caching.serialization.Person;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class KryoSerializationTest extends IntegrationBaseTest {


    @Autowired
    HazelcastInstance hazelcastInstance;


    @Test
    public void shouldSerializeAndDeserializeUsingKryo() {
        //given
        final IMap<String, Person> persons = hazelcastInstance.getMap("persons");

        //when
        persons.put("name-1", new Person("name-1"));

        //then
        Person person = persons.get("name-1");
        assertThat(person).isEqualTo(new Person("name-1"));

    }


}