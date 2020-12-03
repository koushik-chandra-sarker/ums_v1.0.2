package bd.edu.seu.ums.Controller;


import bd.edu.seu.ums.Entity.StudentCourse;
import bd.edu.seu.ums.Service.StudentCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("management/api/v1/student_course")
public class StudentCourseController {
    @Autowired
    private StudentCourseService service;

    @GetMapping
    public List<StudentCourse> getAllStudentCourse() {
        return service.getAllStudentCourse();
    }

    @GetMapping("{id}")
    public Optional<StudentCourse> getStudentCourse(@PathVariable int id) {
        return service.getStudentCourse(id);
    }

    @PostMapping
    public void addStudentCourse(@RequestBody StudentCourse studentCourse) {
        service.addStudentCourse(studentCourse);
    }

    @PutMapping
    public void updateStudentCourse(@RequestBody StudentCourse studentCourse) {
        service.updateStudentCourse(studentCourse);
    }

    @DeleteMapping(value = "{id}")
    public void deleteStudentCourse(@PathVariable int id) {
    service.deleteStudentCourse(id);

    }
}
