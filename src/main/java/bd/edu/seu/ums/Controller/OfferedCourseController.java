package bd.edu.seu.ums.Controller;


import bd.edu.seu.ums.Entity.OfferedCourse;
import bd.edu.seu.ums.Repository.SchoolRepository;
import bd.edu.seu.ums.Service.OfferedCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("management/api/v1/offered_courses")
public class OfferedCourseController {
    @Autowired
    private OfferedCourseService service;
    @Autowired
    private SchoolRepository schoolRepository;


    @GetMapping
    public List<OfferedCourse> getAllOfferedCourse() {
        return service.getAllOfferedCourse();
    }

//    @GetMapping("{id}")
//    public Optional<Campus> getCampus(@PathVariable String id) {
//        return service.getAllOfferedCourse(id);
//    }

    @PostMapping
    public void addOfferedCourse(@RequestBody OfferedCourse offeredCourse) {
        service.addOfferedCourse(offeredCourse);
    }

    @PutMapping
    public void updateOfferedCourse(@RequestBody OfferedCourse offeredCourse) {
        service.updateOfferedCourse(offeredCourse);
    }

//    @DeleteMapping(value = "{id}")
//    public void deleteCampus(@PathVariable int id) {
//
//        int isSetNUll = schoolRepository.setCampusNull(id);
//        if (isSetNUll==1) service.deleteCampus(id);
//
//    }
}
