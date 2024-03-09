package taskOne.coursesManager;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import taskOne.Course;

import java.util.List;

public class CourseManagerHibernate implements CoursesManager {
    private SessionFactory sessionFactory;

    public CourseManagerHibernate() {
        try {
            sessionFactory = new Configuration()
                    .configure()
                    .addAnnotatedClass(Course.class)
                    .buildSessionFactory();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Integer create(String title, double durationInHours) {
        Integer courseId = null;
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            courseId = (Integer) session.save(new Course(title, durationInHours));
            transaction.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return courseId;
    }

    @Override
    public Course read(int id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Course.class, id);
        }
    }

    @Override
    public List<Course> readAll() {
        try (Session session = sessionFactory.openSession()) {
            Query<Course> courseQuery = session.createQuery("from Course", Course.class);
            return courseQuery.list();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public void update(int id, String title, double durationInHours) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Course course = session.get(Course.class, id);
            course.setTitle(title);
            course.setDuration(durationInHours);
            session.update(course);
            transaction.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public void delete(int id) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.remove(session.get(Course.class, id));
            transaction.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }
}
