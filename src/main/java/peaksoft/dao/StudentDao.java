package peaksoft.dao;

import peaksoft.entity.Student;

import java.util.List;

public interface StudentDao {
    List<Student>getAllStudents();

    void addStudent(Student student);

    Student getStudentById(Long id );

    void updateStudent(Student student);

    void deleteStudent(Student student);
}
