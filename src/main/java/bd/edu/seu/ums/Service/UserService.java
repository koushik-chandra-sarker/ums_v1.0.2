package bd.edu.seu.ums.Service;

import bd.edu.seu.ums.Entity.Role;
import bd.edu.seu.ums.Entity.User;
import bd.edu.seu.ums.Repository.UserRepository;
import bd.edu.seu.ums.Repository.roleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private roleRepository roleRepository;
    @Autowired
    private IdSeqService idSeqService;

    @Autowired
    private PasswordEncoder passwordEncoder;


    public List<User> getAllUser(){
        return (List<User>) userRepository.findAll();
    }


    public Optional<User> getUser(String username){

        Optional<User> users = userRepository.findById(username);
        System.out.println(users);
        return userRepository.findById(username);
    }

    public User getUserByFId(int id){
        return userRepository.findUserByFacultyId(id);
    }
    public User getUserBySId(int id){
        return userRepository.findUserByStudentId(id);
    }

    public User createUserObject(String id, String role){
        User user = null;
        Role role2 = roleRepository.findByRole(role);
        List<Role> roles = new ArrayList<>();
        if (role2 == null){
            roleRepository.save(new Role(role));
            Role role1 = roleRepository.findByRole(role);
            roles.add(role1);
            user = new User(id, passwordEncoder.encode(id), true, roles);
        }
        else {
            roles.add(role2);
            user = new User(id, passwordEncoder.encode(id), true, roles);
        }
        return user;
    }

    public boolean createUser(String username,String password,String role) {

//        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
//        IdSeq idSeq = idSeqService.getIdSeq(currentYear, "FACULTY");



        boolean f = false;
        Role role2 = roleRepository.findByRole(role);
        List<Role> roles = new ArrayList<>();
        if (role2 == null){
            roleRepository.save(new Role(role));
            Role role1 = roleRepository.findByRole(role);
            roles.add(role1);

            User user = new User(username, passwordEncoder.encode(password), true, roles);
            try {
                userRepository.save(user);
                f=true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else {
            roles.add(role2);
            User user = new User(username, passwordEncoder.encode(password), true, roles);
            try {
                userRepository.save(user);
                f=true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return f;
    }


    public void updateUser(User user){
        userRepository.save(user);
    }

    public void deleteUser(String username){
        userRepository.deleteById(username);
    }
}
