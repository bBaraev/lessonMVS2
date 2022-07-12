package peaksoft.dao.impl;

import org.springframework.stereotype.Repository;
import peaksoft.dao.GroupDao;
import peaksoft.dto.GroupDto;
import peaksoft.entity.Company;
import peaksoft.entity.Group;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.Comparator;
import java.util.List;
@Repository
@Transactional
public class GroupDaoImpl implements GroupDao {
    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<Group> getAllGroups() {
        List<Group> groups = manager.createQuery("from Group", Group.class).getResultList();
        Comparator<Group> comparator = ((o1, o2) -> (int) (o1.getId() - o2.getId()));
        groups.sort(comparator);
        return groups;
    }

    @Override
    public void addGroups(Group group) {
        manager.persist(group);
    }

    @Override
    public Group getGroupById(Long id) {
        return manager.find(Group.class, id);
    }

    @Override
    public void updateGroup(Group group) {
        manager.merge(group);
    }

    @Override
    public void deleteGroup(Group group) {
        manager.remove(manager.contains(group) ? group : manager.merge(group));
    }




}
