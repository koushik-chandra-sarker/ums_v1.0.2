package bd.edu.seu.ums.Controller;

import bd.edu.seu.ums.Entity.Permission;
import bd.edu.seu.ums.Service.permissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("management/api/v1/permissions")
public class permissionController {

    @Autowired
    private permissionService permissionService;

    @GetMapping
    public List<Permission> getAllUser(){
        return permissionService.getAllPermission();
    }

    @GetMapping("{id}")
    public Optional<Permission> getPermission(@PathVariable int id){
        return permissionService.getPermission(id);

    }

    @PostMapping(path = "add")
    public void addPermission(@RequestBody Permission permission){
        permissionService.addPermission(permission);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "update")
    public void updatePermission(@RequestBody Permission permission){
        permissionService.updatePermission(permission);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "delete/{id}")
    public void deletePermission(@PathVariable int id){
        permissionService.deletePermission(id);

    }

}
