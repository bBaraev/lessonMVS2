package peaksoft.dao;

import peaksoft.entity.Course;

import java.util.List;

public interface CourseDAO {
    List<Course>getAllCourses( );

    void addCourse(Course course);

    Course getCourseById(Long id );

    void  updateCourse(Course course);

    void deleteCourse(Course course);

    List<Course>findByGroupId(Long id);

}
