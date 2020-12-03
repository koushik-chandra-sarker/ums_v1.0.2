package bd.edu.seu.ums.Repository;

import bd.edu.seu.ums.Entity.Campus;
import bd.edu.seu.ums.Entity.Student;
import bd.edu.seu.ums.Entity.StudentCourse;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface StudentCourseRepository extends CrudRepository<StudentCourse, Integer> {

    @Query(value = "select * from student_course where student_id = ?",nativeQuery = true)
    List<StudentCourse> findByStudentId(String id);

}
