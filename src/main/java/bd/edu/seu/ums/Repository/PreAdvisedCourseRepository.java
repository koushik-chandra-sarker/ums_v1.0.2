package bd.edu.seu.ums.Repository;


import bd.edu.seu.ums.Entity.PreAdvisedCourse;
import bd.edu.seu.ums.Entity.PreAdvisedCourseId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PreAdvisedCourseRepository extends JpaRepository<PreAdvisedCourse, PreAdvisedCourseId> {

    @Query(value = "select * from pre_advised_course  where student_id=?",nativeQuery = true)
    List<PreAdvisedCourse> findAllByStudentId(String student_id);

    @Query(value = "select * from pre_advised_course  where course_code =? and semester_id =? and year =? and student_id=?",nativeQuery = true)
    Optional<PreAdvisedCourse> findByCourseCodeAndSemIdAndYearAndStudentId(String course_code, String semester_id, int year, String student_id);

}
