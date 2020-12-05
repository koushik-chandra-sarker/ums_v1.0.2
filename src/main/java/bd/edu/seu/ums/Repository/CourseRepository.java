package bd.edu.seu.ums.Repository;

import bd.edu.seu.ums.Entity.Course;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CourseRepository extends CrudRepository<Course, String> {

    @Query(value = "select credit from course where code =?",nativeQuery = true)
    int findCreditByCourseCode(String code);
}
