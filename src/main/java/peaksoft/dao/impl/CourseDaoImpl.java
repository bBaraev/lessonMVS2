package peaksoft.dao.impl;

import org.springframework.stereotype.Repository;
import peaksoft.dao.CourseDAO;
import peaksoft.entity.Course;
import peaksoft.entity.Group;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Repository
@Transactional
public class CourseDaoImpl implements CourseDAO {
    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public List<Course> getAllCourses() {
        List<Course>courses=entityManager.createQuery("from Course",Course.class).getResultList();
        Comparator<Course> comparator=((o1, o2)->(int)(o1.getId()-o2.getId()));
        courses.sort(comparator);
        return courses;
    }

    @Override
    public void addCourse(Course course) {
        entityManager.persist(course);
    }

    @Override
    public Course getCourseById(Long id) {
        return entityManager.find(Course.class, id);
    }

    @Override
    public void updateCourse(Course course) {
        entityManager.merge(course);
    }

    @Override
    public void deleteCourse(Course course) {
        entityManager.remove(entityManager.contains(course) ? course : entityManager.merge(course));
    }

    @Override
    public List<Course> findByGroupId(Long id) {
       List<Course>courses=getAllCourses();
       List<Course>courseList=new ArrayList<>();
       Course course=new Course();
       int index=1;
        for (int i = 1; i < courses.size(); i++) {
          if (courses.get(i).getGroups().get(i).getId()==id) {
              course= entityManager.find(Course.class,index);
              index++;
          }
          courseList.add(course);
        }
        return courseList;
    }
}
