package bd.edu.seu.ums.Controller;

import bd.edu.seu.ums.Entity.Course;
import bd.edu.seu.ums.Service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("management/api/v1/courses")
public class CourseController {
    @Autowired
    private CourseService courseService;

    //Fetch all
    @GetMapping
    public List<Course> getAllCourse(){

        return courseService.getAllCourse();
    }

    //Fetch one
    @RequestMapping("{code}")
    public Optional<Course> getCourse(@PathVariable String code){
        return courseService.getCourse(code);

    }
    //Insert
   @PostMapping
    public void addCourse(@RequestBody Course course){
        courseService.addCourse(course);
    }

    //Update
    @PutMapping
    public void updateCourse(@RequestBody Course course){
        courseService.updateCourse(course);
    }
    //Delete
    @RequestMapping(method = RequestMethod.DELETE, value = "{code}")
    public void deleteCourse(@PathVariable String code){
        courseService.deleteCourse(code);
    }


}
