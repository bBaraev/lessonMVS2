package peaksoft.service;

import peaksoft.entity.Group;

import java.util.List;

public interface GroupService {

    List<Group> getAllGroups();

    void addGroups(Group group);

    Group getGroupById(Long id);

    void updateGroup(Group group);

    void deleteGroup(Group group);

}
