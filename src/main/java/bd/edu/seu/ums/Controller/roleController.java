package bd.edu.seu.ums.Controller;

import bd.edu.seu.ums.Entity.Role;
import bd.edu.seu.ums.Service.roleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("management/api/v1/roles")
public class roleController {

    @Autowired
    private roleService roleService;

    @GetMapping
    public List<Role> getAllUser(){
        return roleService.getAllRole();
    }
    @GetMapping("{id}")

    public Optional getRole(@PathVariable int id){
        return roleService.getRole(id);

    }

    @PostMapping(path = "add")
    public void addRole(@RequestBody Role role){
        roleService.addRole(role);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "update")
    public void updateRole(@RequestBody Role role){
        roleService.updateRole(role);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "delete/{id}")
    public void deleteRole(@PathVariable int id){
        roleService.deleteRole(id);

    }

}
