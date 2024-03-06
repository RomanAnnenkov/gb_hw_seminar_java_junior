package taskTwo.personManager;

import taskTwo.Person;

import java.util.List;

public interface PersonManager {
    Integer create(String name, int age);
    void update(int id, String name, int age);
    Person readPerson(int id);
    List<Person> readAllPersons();
    void remove(int id);
}
