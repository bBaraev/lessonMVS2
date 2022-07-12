package peaksoft.dao;

import peaksoft.dto.GroupDto;
import peaksoft.entity.Group;

import java.util.List;

public interface GroupDao {

   List<Group>getAllGroups();

   void addGroups(Group group);

   Group getGroupById(Long id);

   void updateGroup(Group group);

   void deleteGroup(Group group);



}
