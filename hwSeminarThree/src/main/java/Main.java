import taskOne.Person;

import java.io.*;

public class Main {
    //Задание 1: Создайте класс Person с полями name и age.
    //Реализуйте сериализацию и десериализацию этого класса в файл.

    //Задание 2: Используя JPA, создайте базу данных для хранения объектов класса Person.
    // Реализуйте методы для добавления, обновления и удаления объектов Person.
    public static void main(String[] args) {
        System.out.println("hw from seminar three");

        Person personForSerialization = new Person("Roma", 36);

        String filename = "person.bin";

        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(filename))) {
            outputStream.writeObject(personForSerialization);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(filename))) {
            Person personForDeserialization = (Person) objectInputStream.readObject();
            System.out.println(personForDeserialization);
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }
}
