package bd.edu.seu.ums.Service;

import bd.edu.seu.ums.Entity.Role;
import bd.edu.seu.ums.Repository.roleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class roleService {
    @Autowired
    private roleRepository roleRepo;

    public List<Role> getAllRole(){
        return (List<Role>) roleRepo.findAll();
    }


    public Optional<Role> getRole(int roleId){
        return roleRepo.findById(roleId);
    }


    public void addRole(Role role){
        roleRepo.save(role);
    }

    public void updateRole(Role role){
        roleRepo.save(role);
    }

    public void deleteRole(int roleId){
        roleRepo.deleteById(roleId);
    }
}
