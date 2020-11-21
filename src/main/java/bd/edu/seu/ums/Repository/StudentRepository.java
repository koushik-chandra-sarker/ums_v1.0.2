package bd.edu.seu.ums.Repository;

import bd.edu.seu.ums.Entity.Phone;
import bd.edu.seu.ums.Entity.Student;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface StudentRepository extends CrudRepository<Student, String> {

//    @Query(value = "select s from student s where s.",nativeQuery = true)
//    Student findByPhones(List<Phone> phones);
    @Query(value = "SELECT * from student inner JOIN phone p on student.id = p.student_id where p.phone_no = ?;",nativeQuery = true)
    Student findByPhones(String phones);
}
