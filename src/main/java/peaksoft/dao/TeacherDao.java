package peaksoft.dao;

import peaksoft.entity.Teacher;

import java.util.List;

public interface TeacherDao {
    List<Teacher>getAllTeachers();

    void addTeacher(Teacher teacher);

    Teacher getTeacherById(Long id);

    void updateTeacher(Teacher teacher);

    void deleteTeacher(Teacher teacher);
}
