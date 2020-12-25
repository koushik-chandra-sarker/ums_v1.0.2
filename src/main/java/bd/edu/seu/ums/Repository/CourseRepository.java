package bd.edu.seu.ums.Repository;

import bd.edu.seu.ums.Entity.Course;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CourseRepository extends CrudRepository<Course, String> {

    @Query(value = "select credit from course where code =?",nativeQuery = true)
    Integer findCreditByCourseCode(String code);

    @Query(value = "select pre_course_code from pre_course where course_code=?",nativeQuery = true)
    List<String> findPreCourseCodeByCourseCode(String code);


}
