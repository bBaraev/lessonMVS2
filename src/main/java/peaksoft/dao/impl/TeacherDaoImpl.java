package peaksoft.dao.impl;

import org.springframework.stereotype.Repository;
import peaksoft.dao.TeacherDao;
import peaksoft.entity.Teacher;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.Comparator;
import java.util.List;

@Repository
@Transactional
public class TeacherDaoImpl implements TeacherDao {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<Teacher> getAllTeachers() {
        List<Teacher>teachers=manager.createQuery("from Teacher",Teacher.class).getResultList();
        Comparator<Teacher> comparator=((o1, o2) -> (int) (o1.getId()-o2.getId()));
        teachers.sort(comparator);
        return teachers;
    }

    @Override
    public void addTeacher(Teacher teacher) {
        manager.persist(teacher);
    }

    @Override
    public Teacher getTeacherById(Long id) {
        return manager.find(Teacher.class,id);
    }

    @Override
    public void updateTeacher(Teacher teacher) {
        manager.merge(teacher);
    }

    @Override
    public void deleteTeacher(Teacher teacher) {
        manager.remove(manager.contains(teacher) ? teacher : manager.merge(teacher));
    }
}
