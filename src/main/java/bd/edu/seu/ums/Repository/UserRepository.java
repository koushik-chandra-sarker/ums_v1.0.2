package bd.edu.seu.ums.Repository;


import bd.edu.seu.ums.Entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, String > {


    @Query(value = "SELECT * from user where faculty_id=?", nativeQuery = true)
    User findUserByFacultyId(int id);
    @Query(value = "SELECT * from user where student_id=?", nativeQuery = true)
    User findUserByStudentId(int id);
//
//    @Transactional
//    @Modifying(flushAutomatically = true, clearAutomatically = true)
//    @Query(value = "delete from user_role where user_id=?",nativeQuery = true)
//    int deleteUser_roleById(int id);
//
//    @Transactional
//    @Modifying(flushAutomatically = true, clearAutomatically = true)
//    @Query(value = "delete from user where id=?",nativeQuery = true)
//    int deleteUserById(int id);
}
