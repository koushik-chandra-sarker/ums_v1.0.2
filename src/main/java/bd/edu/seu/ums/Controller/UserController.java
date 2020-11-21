package bd.edu.seu.ums.Controller;


import bd.edu.seu.ums.Entity.User;
import bd.edu.seu.ums.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

//@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(path = "management/api/v1/users")
public class UserController {
    @Autowired
    private UserService userService;


    private final PasswordEncoder passwordEncoder;

    public UserController(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    //Fetch all
    @GetMapping
    public List<User> getAllUser(){

        return userService.getAllUser();
    }
    @GetMapping("fid/{id}")
    public User getUserBYFId(@PathVariable int id){
        return userService.getUserByFId(id);
    }
    @RequestMapping(path = "sid/{id}")
    public User getUserBYSId(@PathVariable int id){
        return userService.getUserBySId(id);
    }


    //Fetch one
    @GetMapping("{username}")
    public Optional<User> getUser(@PathVariable String username){
        return userService.getUser(username);

    }
//    //Insert
//    @PostMapping
//    public void addUser(@RequestBody User User){
//        String pass = User.getPassword();
//        String encryptPwd = passwordEncoder.encode(pass);
//        User.setPassword(encryptPwd);
//        userService.addUser(User);
//    }

    //Update
    @PutMapping
    public void updateUser(@RequestBody User User){
        String pass = User.getPassword();
        String encryptPwd = passwordEncoder.encode(pass);
        User.setPassword(encryptPwd);
        userService.updateUser(User);
    }

    //Delete
    @DeleteMapping("{username}")
    public void deleteUser(@PathVariable String username){

    userService.deleteUser(username);

    }
}
