package bd.edu.seu.ums.Service;

import bd.edu.seu.ums.Entity.Faculty;
import bd.edu.seu.ums.Repository.FacultyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class FacultyService {

    @Autowired
    private FacultyRepository facultyRepository;
//    @Autowired
//    private roleRepository roleRepository;
//
//    @Autowired
//    private UserRepository userRepository;


    public List<Faculty> getAllFaculty() {
        return (List<Faculty>) facultyRepository.findAll();
    }

    public Optional<Faculty> getFaculty(String id) {
        return facultyRepository.findById(id);
    }

    public String addFaculty(Faculty faculty) {

        boolean f = false;
        try {
            facultyRepository.save(faculty);
            f = true;
        } catch (Exception e) {
           return e.getMessage();
        }
        return String.valueOf(f);
    }

    /*public boolean createFacultyUser(String username,String password,String role) {
        boolean f = false;
        Role role2 = roleRepository.findByRole(role);
        List<Role> roles = new ArrayList<>();
        if (role == null){
            roleRepository.save(new Role("FACULTY"));
            Role role1 = roleRepository.findByRole("FACULTY");
            roles.add(role1);
            User user = new User(username, password, true, roles);
            try {
                userRepository.save(user);
                f=true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else {
            roles.add(role2);
            User user = new User(username, username, true, roles);
            try {
                userRepository.save(user);
                f=true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return f;
    }*/

    public void updateFaculty(Faculty faculty) {
        facultyRepository.save(faculty);
    }

    public void deleteFaculty(String id) {
        facultyRepository.deleteById(id);
    }
}
