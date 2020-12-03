package bd.edu.seu.ums.Service;

import bd.edu.seu.ums.Entity.Phone;
import bd.edu.seu.ums.Entity.Role;
import bd.edu.seu.ums.Entity.Student;
import bd.edu.seu.ums.Entity.User;
import bd.edu.seu.ums.Exception.MyMadeException;
import bd.edu.seu.ums.Helper.IdGenerator;
import bd.edu.seu.ums.Repository.StudentRepository;
import bd.edu.seu.ums.Repository.UserRepository;
import bd.edu.seu.ums.Repository.roleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class StudentService {


    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private roleRepository roleRepository;
    @Autowired
    UserService userService;
    @Autowired
    private IdGenerator idGenerator;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<Student> getAllStudent(){
        return (List<Student>) studentRepository.findAll();
    }

    public Optional<Student> getStudent(String id){
        return studentRepository.findById(id);
    }
    public Student getStudent1(String p){
        return studentRepository.findByPhones(p);
    }

    public String addStudent(Student student){
        Student student1 = null;
        if (!student.getPhones().isEmpty()){
            for (Phone phone: student.getPhones()){
                student1 = studentRepository.findByPhones(phone.getPhoneNo());
                if (student1 != null){
                    throw new MyMadeException("Phone '"+phone.getPhoneNo()+ "' already Exists.");
                }
            }
        }

        String id = "";
        try {
            if (student.getType().equals("REGULAR")) {
                id = idGenerator.RegularStudentIdGenerator();
            } else if (student.getType().equals("WEEKEND")) {
                id = idGenerator.WeekEndStudentIdGenerator();
            }else {
                throw new MyMadeException("Student type field must be 'REGULAR' or 'WEEKEND'");
            }
        }catch (NullPointerException e){
            throw new MyMadeException("Student 'type' field must be present.");

        }

        User user = userService.createUserObject(id,"STUDENT");
        student.setId(id);
        student.setUser(user);
        boolean f = false;
        try {
            studentRepository.save(student);
            f= true;
        }catch(Exception e){
            idGenerator.replaceSequence(id);
            return e.toString();
        }

        return String.valueOf(f);

    }

    public void updateStudent(Student student){
        studentRepository.save(student);
    }

    public void deleteStudent(String id){

        if (studentRepository.findById(id).isEmpty()){
            throw new MyMadeException("Student not found of id "+id);
        }
        else studentRepository.deleteById(id);
    }
}
