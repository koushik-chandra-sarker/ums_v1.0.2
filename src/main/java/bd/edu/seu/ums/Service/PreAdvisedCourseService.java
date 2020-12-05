package bd.edu.seu.ums.Service;

import bd.edu.seu.ums.Entity.Course;
import bd.edu.seu.ums.Entity.PreAdvisedCourse;
import bd.edu.seu.ums.Entity.PreAdvisedCourseId;
import bd.edu.seu.ums.Exception.MyMadeException;
import bd.edu.seu.ums.Repository.PreAdvisedCourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PreAdvisedCourseService {
    @Autowired
    private PreAdvisedCourseRepository repository;
    @Autowired
    private CourseService courseService;

    public List<PreAdvisedCourse> getAllPreAdvisedCourse() {
        return repository.findAll();
    }

    public List<PreAdvisedCourse> getAllPreAdvisedCourseBySId(String id) {
        return repository.findAllByStudentId(id);
    }

    public Optional<PreAdvisedCourse> getById(PreAdvisedCourseId id){
       return repository.findById(id);
    }

    public void addPreAdvisedCourse(PreAdvisedCourse preAdvisedCourse) {
        String c_code =preAdvisedCourse.getId().getCourse().getCode();
             try {
                Optional<PreAdvisedCourse> preAdvisedCourse1 = this.getById(preAdvisedCourse.getId());
                if (preAdvisedCourse1.isPresent()){
                    throw new MyMadeException("Course Already taken.");
                }else {
                    Optional<Course> course = courseService.getCourse(c_code);
                    if (course.isEmpty()){
                        throw new MyMadeException("Course Not Found.");
                    }else {
                        preAdvisedCourse.setCredit(course.get().getCredit());
                        repository.save(preAdvisedCourse);
                    }
                }
             }catch (Exception e){
                 throw new MyMadeException(e.getMessage());
             }
    }

    public void updatePreAdvisedCourse(PreAdvisedCourse preAdvisedCourse) {
        repository.save(preAdvisedCourse);
    }

    public void deletePreAdvisedCourse(PreAdvisedCourseId id) {
        repository.deleteById(id);
    }
}
