package peaksoft.service;

import peaksoft.entity.Teacher;

import java.util.List;

public interface TeacherService {

    List<Teacher>getAllTeachers();

    void addTeacher(Teacher teacher);

    Teacher getTeacherById(Long id);

    void updateTeacher(Teacher teacher);

    void deleteTeacher(Teacher teacher);
}
