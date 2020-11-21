package bd.edu.seu.ums.Service;

import bd.edu.seu.ums.Entity.Permission;
import bd.edu.seu.ums.Repository.permissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class permissionService {
    @Autowired
    private permissionRepository permissionRepo;

    public List<Permission> getAllPermission(){
        return (List<Permission>) permissionRepo.findAll();
    }


    public Optional<Permission> getPermission(int permissionId){
        return permissionRepo.findById(permissionId);
    }


    public void addPermission(Permission permission){
        permissionRepo.save(permission);
    }

    public void updatePermission(Permission permission){
        permissionRepo.save(permission);
    }

    public void deletePermission(int permissionId){
        permissionRepo.deleteById(permissionId);
    }
}
