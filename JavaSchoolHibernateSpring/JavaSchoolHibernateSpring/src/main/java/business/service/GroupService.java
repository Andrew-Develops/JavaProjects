package business.service;

import business.dto.GroupDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import persistence.dao.GroupDAO;
import persistence.entities.Group;

import java.util.ArrayList;
import java.util.List;

@Service
public class GroupService {

    @Autowired
    GroupDAO groupDAO = new GroupDAO();


    public List<GroupDTO> getGroups() {
        List<Group> groupList = groupDAO.findAllGroups();
        List<GroupDTO> groupDTOList = new ArrayList<>();
        for (Group g : groupList) {
            GroupDTO groupDTO = new GroupDTO();
            groupDTO.setName(g.getName());
            groupDTOList.add(groupDTO);
        }
        return groupDTOList;
    }

    public List<GroupDTO> getGroupByName(String name) {
        List<Group> groupList = groupDAO.findGroupByName(name);
        List<GroupDTO> groupDTOList = new ArrayList<>();
        for (Group g : groupList) {
            GroupDTO groupDTO = new GroupDTO();
            groupDTO.setName(g.getName());
            groupDTOList.add(groupDTO);
        }
        return groupDTOList;
    }

    public void insertGroup(GroupDTO groupDTO) {
        Group group = new Group();
        group.setName(groupDTO.getName());
        groupDAO.insertGroup(group);
    }

    public Long countGroupByName(String name) {
        return groupDAO.groupCountyByName(name);
    }

}
