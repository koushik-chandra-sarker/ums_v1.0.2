package bd.edu.seu.ums.Service;


import bd.edu.seu.ums.Entity.Course;
import bd.edu.seu.ums.Repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class CourseService {


    @Autowired
    private CourseRepository courseRepository;


    public List<Course> getAllCourse(){
        return (List<Course>) courseRepository.findAll();
    }

    public Optional<Course> getCourse(String code){
        return courseRepository.findById(code);
    }

    public void addCourse(Course course){
        courseRepository.save(course);
    }

    public void updateCourse(Course course){
        courseRepository.save(course);
    }

    public void deleteCourse(String code){
        courseRepository.deleteById(code);

    }
}
