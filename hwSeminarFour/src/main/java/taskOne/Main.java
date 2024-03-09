package taskOne;

import taskOne.coursesManager.CourseManagerHibernate;
import taskOne.coursesManager.CoursesManager;

public class Main {

    //Создайте базу данных (например, SchoolDB).
    //В этой базе данных создайте таблицу Courses с полями id (ключ), title, и duration.
    //Настройте Hibernate для работы с вашей базой данных.
    //Создайте Java-класс Course, соответствующий таблице Courses, с необходимыми аннотациями Hibernate.
    //Используя Hibernate, напишите код для вставки, чтения, обновления и удаления данных в таблице Courses.
    //Убедитесь, что каждая операция выполняется в отдельной транзакции.
    public static void main(String[] args) {
        System.out.println("hw seminar four");
        CoursesManager coursesManager = new CourseManagerHibernate();

        Integer introId = coursesManager.create("intro",1.5);
        coursesManager.create("docker", 10.0);
        System.out.println(coursesManager.readAll());

        coursesManager.update(introId,"intro ver2", 2.0);
        System.out.println(coursesManager.readAll());

        for (Course course: coursesManager.readAll()) {
            coursesManager.delete(course.getId());
        }
        System.out.println(coursesManager.readAll());
    }
}
