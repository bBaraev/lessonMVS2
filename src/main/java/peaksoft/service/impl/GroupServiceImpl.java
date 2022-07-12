package peaksoft.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import peaksoft.dao.CourseDAO;
import peaksoft.dao.GroupDao;
import peaksoft.entity.Course;
import peaksoft.entity.Group;
import peaksoft.service.GroupService;

import java.util.ArrayList;
import java.util.List;
@Service
public class GroupServiceImpl implements GroupService {
    private final GroupDao groupDao;
    private final CourseDAO courseDAO;
    @Autowired
    public GroupServiceImpl(GroupDao groupDao, CourseDAO courseDAO) {
        this.groupDao = groupDao;
        this.courseDAO = courseDAO;
    }

    @Override
    public List<Group> getAllGroups() {
        return groupDao.getAllGroups();
    }

    @Override
    public void addGroups(Group group) {
       groupDao.addGroups(group);
    }

//    @Override
//    public void addGroups(Group group,Long courseId) {
//        List<Course> courses = new ArrayList<>();
//        Course course = courseDAO.getCourseById(courseId);
//        courses.add(course);
//        group.setCourses(courses);
//        List<Group> groups = new ArrayList<>();
//        course.setGroups(groups);
//        groupDao.addGroups(group);
//    }

    @Override
    public Group getGroupById(Long id) {
        return groupDao.getGroupById(id);
    }

    @Override
    public void updateGroup(Group group) {
        groupDao.updateGroup(group);
    }

    @Override
    public void deleteGroup(Group group) {
        groupDao.deleteGroup(group);
    }
}
