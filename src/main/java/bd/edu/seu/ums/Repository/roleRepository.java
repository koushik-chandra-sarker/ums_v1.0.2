package bd.edu.seu.ums.Repository;

import bd.edu.seu.ums.Entity.Role;
import org.springframework.data.repository.CrudRepository;

public interface roleRepository extends CrudRepository<Role,Integer> {
    Role findByRole(String role);
}
