package tests;

import org.example.JsonSerializer;
import org.example.Person;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PersonSerializerTest {

    @Test
    void personIsSerialized() throws IllegalAccessException {
        Person p = new Person("Ivan", "Ivanov", LocalDate.of(1997, 11, 2));
        JSONObject json = new JsonSerializer<Person>(Person.class).serialize(p);
        assertEquals("Ivan", json.get("firstName"));
        assertEquals("Ivanov", json.get("lastName"));
        assertEquals("1997-11-02", json.get("birthDate"));
    }
}