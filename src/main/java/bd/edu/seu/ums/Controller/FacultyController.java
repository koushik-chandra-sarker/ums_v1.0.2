package bd.edu.seu.ums.Controller;

import bd.edu.seu.ums.Entity.Faculty;
import bd.edu.seu.ums.Exception.MyMadeException;
import bd.edu.seu.ums.Helper.IdGenerator;
import bd.edu.seu.ums.Service.FacultyService;
import bd.edu.seu.ums.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("management/api/v1/faculties")
public class FacultyController {
    @Autowired
    private FacultyService facultyService;
    @Autowired
    private IdGenerator idGenerator;

    @Autowired
    private UserService userService;

    //Fetch all
    @GetMapping
    public List<Faculty> getAllFaculty(){
        return facultyService.getAllFaculty();
    }

    //Fetch one
    @GetMapping("{id}")
    public Optional<Faculty> getFaculty(@PathVariable String id){
        return facultyService.getFaculty(id);

    }

    //Insert
    @PostMapping
    public String addFaculty(@RequestBody Faculty faculty){
       /* List<Faculty> faculties = facultyService.getAllFaculty();
        long t = faculties.stream().filter(faculty1 -> faculty1.getInitial().equals(faculty.getInitial())).count();
        if (t>0){
            throw new MyMadeException("Initial Already Exists.. Initial Must be Unique");
        }
        String id = idGenerator.FacultyIdGenerator();
        faculty.setId(id);
        String f = facultyService.addFaculty(faculty);
        if (f!="true"){
            boolean f1 = userService.createUser(id,id,"FACULTY");

        }else {
            idGenerator.replaceSequence(id);
        }*/
        return facultyService.addFaculty(faculty);
    }


    //Update
    @PutMapping
    public void updateFaculty(@RequestBody Faculty faculty){
        facultyService.updateFaculty(faculty);
    }

    //Delete
    @DeleteMapping("{id}")
    public void deleteFaculty(@PathVariable String id){
        facultyService.deleteFaculty(id);
    }
}
