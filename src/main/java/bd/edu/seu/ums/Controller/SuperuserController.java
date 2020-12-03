package bd.edu.seu.ums.Controller;

import bd.edu.seu.ums.Entity.Faculty;
import bd.edu.seu.ums.Entity.Superuser;
import bd.edu.seu.ums.Exception.MyMadeException;
import bd.edu.seu.ums.Helper.IdGenerator;
import bd.edu.seu.ums.Service.FacultyService;
import bd.edu.seu.ums.Service.SuperuserService;
import bd.edu.seu.ums.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("management/api/v1/superusers")
public class SuperuserController {
    @Autowired
    private SuperuserService superuserService;

    //Fetch all
    @GetMapping
    public List<Superuser> getAllSuperuser(){
        return superuserService.getAllSuperuser();
    }

    //Fetch one
    @GetMapping("{id}")
    public Optional<Superuser> getSuperuser(@PathVariable String id){
        return superuserService.getSuperuser(id);

    }

    //Insert
    @PostMapping
    public String addSuperuser(@RequestBody Superuser superuser){
        return superuserService.addSuperuser(superuser);
    }


    //Update
    @PutMapping
    public void updateSuperuser(@RequestBody Superuser superuser){
        superuserService.updateSuperuser(superuser);
    }

    //Delete
    @DeleteMapping("{id}")
    public void deleteSuperuser(@PathVariable String id){
        superuserService.deleteSuperuser(id);
    }
}
