package bd.edu.seu.ums.Repository;

import bd.edu.seu.ums.Entity.Faculty;
import bd.edu.seu.ums.Entity.OfferedCourse;
import bd.edu.seu.ums.Entity.OfferedCourseId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OfferedCourseRepository extends JpaRepository<OfferedCourse, OfferedCourseId> {

    @Query(value = "select * from offered_course  where course_code=? and semester_id=? and year=? order by section desc limit 1",nativeQuery = true)
    OfferedCourse findByCourseAndSemesterAndYear(String course_code, String semester_id, int year);

}
