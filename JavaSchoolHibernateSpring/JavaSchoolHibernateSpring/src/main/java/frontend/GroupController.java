package frontend;

import business.dto.GroupDTO;
import business.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import persistence.entities.Group;

import javax.validation.Valid;
import java.util.List;

@RestController
public class GroupController {

    @Autowired
    GroupService groupService;

    //afisam toate grupele din DB
    @GetMapping(path = "/findAllGroups")
    public ResponseEntity<List<GroupDTO>> findAllGroups() {
        List<GroupDTO> groupDTOList = groupService.getGroups();
        if (groupDTOList.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(groupDTOList);
        }
    }

    //afisam grupa in functie de nume
    @GetMapping(path = "/findGroupByName")
    public ResponseEntity<List<GroupDTO>> findGroupByName(String name) {
        List<GroupDTO> groupDTOList = groupService.getGroupByName(name);
        if (groupDTOList.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(groupDTOList);
        }
    }

    //inseram o grupa in DB
    @PostMapping(path = "/insertGroup")
    public String insertGroup(@RequestBody @Valid GroupDTO groupDTO) {
        Long numberOfGroups = groupService.countGroupByName(groupDTO.getName());
        if (numberOfGroups == 0) {
            groupService.insertGroup(groupDTO);
            return "Grupa inserata cu succes";
        } else {
            return "Grupa deja existenta";
        }
    }
}
