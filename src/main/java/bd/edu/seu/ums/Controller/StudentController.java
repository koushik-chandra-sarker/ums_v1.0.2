package bd.edu.seu.ums.Controller;


import bd.edu.seu.ums.Entity.Phone;
import bd.edu.seu.ums.Entity.Student;
import bd.edu.seu.ums.Enum.StudentType;
import bd.edu.seu.ums.Exception.MyMadeException;
import bd.edu.seu.ums.Helper.IdGenerator;
import bd.edu.seu.ums.Service.StudentService;
import bd.edu.seu.ums.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping(path = "management/api/v1/students")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @Autowired
    private UserService userService;
    @Autowired
    private IdGenerator idGenerator;


    //Fetch all
    @GetMapping
    public List<Student> getAllStudent() {
        return studentService.getAllStudent();
    }

    //Fetch one
    @GetMapping("{id}")
    public Optional<Student> getStudent(@PathVariable String id) {
        return studentService.getStudent(id);
    }
    @GetMapping("p/{p}")
    public Student getStudent1(@PathVariable String p) {
        return studentService.getStudent1(p);
    }

    //Insert
    @PostMapping
    public String addStudent(@RequestBody Student student) {

        return studentService.addStudent(student);


    }

    //Update
    @PutMapping
    public void updateStudent(@RequestBody Student Student) {
        studentService.updateStudent(Student);
    }

    //Delete
    @DeleteMapping("{id}")
    public void deleteStudent(@PathVariable String id) {
        studentService.deleteStudent(id);
    }
}
