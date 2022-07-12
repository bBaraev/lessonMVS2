package peaksoft.service;

import peaksoft.entity.Student;

import java.util.List;

public interface StudentService {

    List<Student>getAllStudents();

    void addStudent(Student student);

    Student getStudentById(Long id );

    void updateStudent(Student student);

    void deleteStudent(Student student);

}
