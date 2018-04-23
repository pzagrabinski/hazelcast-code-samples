package com.hazelcast.springboot.caching.serialization;

@SuppressWarnings("unused")
public class Person {

    private String name;

    private Person() {
    }

    public Person(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return String.format("Person(name=%s)", name);
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Person person = (Person) o;

        return name != null ? name.equals(person.name) : person.name == null;
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }
}