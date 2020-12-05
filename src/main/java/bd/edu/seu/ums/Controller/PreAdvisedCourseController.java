package bd.edu.seu.ums.Controller;


import bd.edu.seu.ums.Entity.PreAdvisedCourse;
import bd.edu.seu.ums.Entity.PreAdvisedCourseId;
import bd.edu.seu.ums.Entity.StudentCourse;
import bd.edu.seu.ums.Service.PreAdvisedCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("management/api/v1/pre_advised_course")
public class PreAdvisedCourseController {
    @Autowired
    private PreAdvisedCourseService service;

    @GetMapping
    public List<PreAdvisedCourse> getAll() {
        return service.getAllPreAdvisedCourse();
    }

    @GetMapping("{id}")
    public List<PreAdvisedCourse> getAllBySId(@PathVariable String id) {
        return service.getAllPreAdvisedCourseBySId(id);
    }

    @PostMapping
    public void add(@RequestBody PreAdvisedCourse preAdvisedCourse) {
        service.addPreAdvisedCourse(preAdvisedCourse);
    }

    @PutMapping
    public void update(@RequestBody PreAdvisedCourse preAdvisedCourse) {
        service.updatePreAdvisedCourse(preAdvisedCourse);
    }

    @DeleteMapping("{course_code}/{semester_id}/{year}")
    public void delete(@PathVariable String course_code, @PathVariable String semester_id, @PathVariable int year) {
        PreAdvisedCourseId preAdvisedCourseId = new PreAdvisedCourseId();
        preAdvisedCourseId.getCourse().setCode(course_code);
        preAdvisedCourseId.getSemester().setId(semester_id);
        preAdvisedCourseId.setYear(year);
        service.deletePreAdvisedCourse(preAdvisedCourseId);

    }
}
