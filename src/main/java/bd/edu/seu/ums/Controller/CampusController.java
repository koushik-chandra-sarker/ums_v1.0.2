package bd.edu.seu.ums.Controller;

import bd.edu.seu.ums.Entity.Campus;
import bd.edu.seu.ums.Entity.School;
import bd.edu.seu.ums.Repository.SchoolRepository;
import bd.edu.seu.ums.Service.CampusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.swing.plaf.IconUIResource;
import java.util.List;
import java.util.Optional;

//@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("management/api/v1/campuses")
public class CampusController {
    @Autowired
    private CampusService campusService;
    @Autowired
    private SchoolRepository schoolRepository;


    @GetMapping
    public List<Campus> getAllCampus() {
        return campusService.getAllCampus();
    }

    @GetMapping("{id}")
    public Optional<Campus> getCampus(@PathVariable int id) {
        return campusService.getCampus(id);

    }

    @PostMapping
    public void addCampus(@RequestBody Campus campus) {
        campusService.addCampus(campus);
    }

    @PutMapping
    public void updateCampus(@RequestBody Campus campus) {
        campusService.updateCampus(campus);
    }

    @DeleteMapping(value = "{id}")
    public void deleteCampus(@PathVariable int id) {

        List<School> school = schoolRepository.findByCampus_Id(id);
        if (school.isEmpty()){
            campusService.deleteCampus(id);
        }else {
            int isSetNUll = schoolRepository.setCampusNull(id);
            if (isSetNUll == 1) campusService.deleteCampus(id);
        }

    }
}
