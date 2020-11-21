package bd.edu.seu.ums.Controller;


import bd.edu.seu.ums.Entity.School;
import bd.edu.seu.ums.Repository.ProgrammeRepository;
import bd.edu.seu.ums.Service.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("management/api/v1/schools")
public class SchoolController {

    @Autowired
    private SchoolService schoolService;
    @Autowired
    private ProgrammeRepository programmeRepository;

    @GetMapping
    public List<School> getAllSchool() {
        return schoolService.getAllSchool();
    }

    @GetMapping("{id}")
    public Optional<School> getSchool(@PathVariable int id) {
        return schoolService.getSchool(id);

    }

    @PostMapping
    public void addSchool(@RequestBody School school) {
        System.out.println(school);
        schoolService.addSchool(school);
    }

    @PutMapping
    public void updateSchool(@RequestBody School school) {
        schoolService.updateSchool(school);
    }

    @DeleteMapping("{id}")
    public void deleteSchool(@PathVariable int id) {

        int isSetNull = programmeRepository.setSchoolNull(id);
        if (isSetNull == 1) schoolService.deleteSchool(id);
    }

}
