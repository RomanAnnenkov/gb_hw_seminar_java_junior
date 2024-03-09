package taskOne.coursesManager;

import taskOne.Course;

import java.util.List;

public interface CoursesManager {
    Integer create(String title, double durationInHours);
    Course read(int id);
    List<Course> readAll();
    void update(int id, String title, double durationInHours);
    void delete(int id);
}
