package taskTwo.personManager;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import taskTwo.Person;

import java.util.ArrayList;
import java.util.List;


public class PersonManagerHibernate implements PersonManager {

    private SessionFactory sessionFactory;

    public PersonManagerHibernate() {
        try {
            sessionFactory = new Configuration()
                    .configure("hibernate.cfg.xml")
                    .addAnnotatedClass(Person.class)
                    .buildSessionFactory();

        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Integer create(String name, int age) {
        Transaction transaction = null;
        Integer personId = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            personId = (Integer) session.save(new Person(name, age));
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return personId;
    }

    @Override
    public void update(int id, String name, int age) {
        Transaction transaction = null;
        try(Session session = sessionFactory.openSession()) {
            Person person = session.get(Person.class, id);
            transaction = session.beginTransaction();
            person.setName(name);
            person.setAge(age);
            session.update(person);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public Person readPerson(int id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Person.class, id);
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Person> readAllPersons() {
        List<Person> persons = new ArrayList<>();
        try (Session session = sessionFactory.openSession()) {
            for (var element : session.createQuery("FROM Person").list()) {
                persons.add((Person) element);
            }
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return persons;
    }

    @Override
    public void remove(int id) {
        Transaction transaction = null;
        try(Session session = sessionFactory.openSession()) {
            Person person = session.get(Person.class, id);
            transaction = session.beginTransaction();
            session.remove(person);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}
