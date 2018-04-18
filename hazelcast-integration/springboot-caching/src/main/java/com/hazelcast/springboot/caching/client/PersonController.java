package com.hazelcast.springboot.caching.client;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import com.hazelcast.springboot.caching.serialization.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonController {
    private final IMap<String, Person> persons;

    @Autowired
    public PersonController(HazelcastInstance hazelcastInstance) {
        persons = hazelcastInstance.getMap("persons");
    }

    @RequestMapping(value = "/person/{name}", method = RequestMethod.POST)
    public ResponseEntity<?> createPerson(@PathVariable("name") String name) {
        persons.put(name, new Person(name));
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "/person/{name}", method = RequestMethod.GET)
    public ResponseEntity<?> getPerson(@PathVariable("name") String name) {
        Person person = persons.get(name);
        if (person == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(person);
    }

}
