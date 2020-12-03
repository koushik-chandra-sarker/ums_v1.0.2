package bd.edu.seu.ums.Controller;

import bd.edu.seu.ums.Entity.Campus;
import bd.edu.seu.ums.Entity.Semester;
import bd.edu.seu.ums.Service.SemesterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

//@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("management/api/v1/semesters")
public class SemesterController {
    @Autowired
    private SemesterService semesterService;



    @GetMapping
    public List<Semester> getAllCampus() {
        return semesterService.getAllSemester();
    }

    @GetMapping("{id}")
    public Optional<Semester> getCampus(@PathVariable String id) {
        return semesterService.getSemester(id);

    }

    @PostMapping
    public void addCampus(@RequestBody Semester semester) {
        semesterService.addSemester(semester);
    }

    @PutMapping
    public void updateCampus(@RequestBody Semester semester) {
        semesterService.updateSemester(semester);
    }

//    @DeleteMapping(value = "{id}")
//    public void deleteCampus(@PathVariable int id) {
//
//        int isSetNUll = schoolRepository.setCampusNull(id);
//        if (isSetNUll==1) semesterService.deleteCampus(id);
//
//    }
}
