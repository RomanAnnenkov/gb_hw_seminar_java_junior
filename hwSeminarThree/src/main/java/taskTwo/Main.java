package taskTwo;

import taskTwo.personManager.PersonManager;
import taskTwo.personManager.PersonManagerHibernate;

import java.util.List;

public class Main {
    //Задание 2: Используя JPA, создайте базу данных для хранения объектов класса Person.
    // Реализуйте методы для добавления, обновления и удаления объектов Person.
    public static void main(String[] args) {
        PersonManager personManager = new PersonManagerHibernate();

        int personId = personManager.create("Ivan",20);
        System.out.println("create person with id: " + personId);

        List<Person> persons = personManager.readAllPersons();
        System.out.println(persons);

        personManager.update(1,"Roma",36);

        Person personWithIdOne = personManager.readPerson(1);
        System.out.println("person with id = 1: "+ personWithIdOne);

        personManager.remove(1);

        persons = personManager.readAllPersons();
        System.out.println(persons);

    }
}
