package bd.edu.seu.ums.Repository;

import bd.edu.seu.ums.Entity.Phone;
import bd.edu.seu.ums.Entity.Role;
import org.springframework.data.repository.CrudRepository;

public interface PhoneRepository extends CrudRepository<Phone,Integer> {

    Phone findByPhoneNo(String phoneNo);
}
