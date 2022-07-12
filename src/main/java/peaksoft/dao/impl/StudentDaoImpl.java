package peaksoft.dao.impl;

import org.springframework.stereotype.Repository;
import peaksoft.dao.StudentDao;
import peaksoft.entity.Student;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.Comparator;
import java.util.List;

@Repository
@Transactional
public class StudentDaoImpl implements StudentDao {
    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<Student> getAllStudents() {
        List<Student> students =manager.createQuery("from Student",Student.class).getResultList();
        Comparator<Student> comparator=((o1, o2)->(int)(o1.getId()-o2.getId()));
        students.sort(comparator);
        return students;
    }

    @Override
    public void addStudent(Student student) {
        manager.persist(student);
    }

    @Override
    public Student getStudentById(Long id) {
        return manager.find(Student.class,id);
    }

    @Override
    public void updateStudent(Student student) {
         manager.merge(student);
    }

    @Override
    public void deleteStudent(Student student) {
        manager.remove(manager.contains(student) ? student : manager.merge(student));
    }
}
