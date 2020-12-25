package bd.edu.seu.ums.Repository;


import bd.edu.seu.ums.Entity.OfferedCourse;
import bd.edu.seu.ums.Entity.StudentCourse;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface StudentCourseRepository extends CrudRepository<StudentCourse, Integer> {

    @Query(value = "select * from student_course where student_id = ?",nativeQuery = true)
    List<StudentCourse> findByStudentId(String id);


    @Query(value = "select * from student_course where offered_course_semester_id = ? and offered_course_course_code =? and offered_course_year = ? and student_id =?",nativeQuery = true)
    Optional<StudentCourse> findBySemesterIdAndCourseCodeAndYearAndStudentId(String semester_id, String course_code, int year,String student_id);

    @Query(value = "select * from student_course where offered_course_semester_id = ? and year = ?",nativeQuery = true)
    StudentCourse findBySemIdAndYear( String semester_id, int year);

    @Query(value = "select COALESCE(SUM(c.credit),0) from student_course sc inner join course c on sc.offered_course_course_code = c.code where sc.offered_course_semester_id =? and sc.offered_course_year =? and sc.student_id",nativeQuery = true)
    Integer totalCreditBySemesterIdAndYearAndStudentId(String offered_course_semester_id, int offered_course_year,String student_id);

    //COALESCE for return 0 if any there is no credit taken
    @Query(value = "select COALESCE(SUM(c.credit),0) from student_course sc" +
            " inner join course c on sc.offered_course_course_code = c.code " +
            "left join pre_advised_course pac on sc.offered_course_course_code = pac.course_code " +
            "where pac.course_code is null and sc.offered_course_semester_id =? and sc.offered_course_year =? and sc.student_id=?",nativeQuery = true)
    Integer totalCreditExceptPreAdvisingCourse(String offered_course_semester_id, int offered_course_year,String student_id);


    @Query(value = "select offered_course_course_code from student_course where student_id = ? and status = ?",nativeQuery = true)
    List<String> findCompletedCourseCodeByStudentIdAndStatus(String id, String status);

}
