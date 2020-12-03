package bd.edu.seu.ums.Service;

import bd.edu.seu.ums.Entity.StudentCourse;
import bd.edu.seu.ums.Exception.MyMadeException;
import bd.edu.seu.ums.Repository.StudentCourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentCourseService {
    @Autowired
    private StudentCourseRepository repository;

    public List<StudentCourse> getAllStudentCourse() {
        return (List<StudentCourse>) repository.findAll();
    }

    public Optional<StudentCourse> getStudentCourse(int id) {
        return repository.findById(id);
    }

    public void addStudentCourse(StudentCourse studentCourse) {
        List<StudentCourse> studentCourse1 = repository.findByStudentId(studentCourse.getStudent().getId());

        long count = studentCourse1.stream().filter(sc ->
                sc.getOfferedCourse().getId().getCourse().getCode().equals(studentCourse.getOfferedCourse().getId().getCourse().getCode()) &&
                        sc.getOfferedCourse().getId().getSemester().getId().equals(studentCourse.getOfferedCourse().getId().getSemester().getId()) &&
                        sc.getOfferedCourse().getId().getYear() == studentCourse.getOfferedCourse().getId().getYear()
        ).count();
         if (count>0){
             throw new MyMadeException("This Course Already taken.");
         }else {
             try {
                 repository.save(studentCourse);
             }catch (Exception e){
                 throw new MyMadeException(e.getMessage());
             }

         }

    }

    public void updateStudentCourse(StudentCourse studentCourse) {
        repository.save(studentCourse);
    }

    public void deleteStudentCourse(Integer id) {
        repository.deleteById(id);
    }
}
