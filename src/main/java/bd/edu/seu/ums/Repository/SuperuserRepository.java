package bd.edu.seu.ums.Repository;

import bd.edu.seu.ums.Entity.Student;
import bd.edu.seu.ums.Entity.Superuser;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface SuperuserRepository extends CrudRepository<Superuser, String> {

//    @Query(value = "select s from student s where s.",nativeQuery = true)
//    Student findByPhones(List<Phone> phones);
    @Query(value = "SELECT * from superuser inner JOIN phone p on superuser.id = p.superuser_id where p.phone_no = ?;",nativeQuery = true)
    Superuser findByPhones(String phones);
}
