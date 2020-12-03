package bd.edu.seu.ums.Repository;

import bd.edu.seu.ums.Entity.Semester;
import bd.edu.seu.ums.Entity.Student;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface SemesterRepository extends CrudRepository<Semester, String> {

}
