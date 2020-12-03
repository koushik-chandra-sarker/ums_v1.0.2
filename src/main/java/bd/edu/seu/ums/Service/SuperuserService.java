package bd.edu.seu.ums.Service;

import bd.edu.seu.ums.Entity.Phone;
import bd.edu.seu.ums.Entity.Student;
import bd.edu.seu.ums.Entity.Superuser;
import bd.edu.seu.ums.Entity.User;
import bd.edu.seu.ums.Exception.MyMadeException;
import bd.edu.seu.ums.Helper.IdGenerator;
import bd.edu.seu.ums.Repository.*;
import net.bytebuddy.implementation.bind.annotation.Super;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class SuperuserService {


    @Autowired
    private SuperuserRepository superuserRepository;
    @Autowired
    private PhoneRepository phoneRepository;
    @Autowired
    UserService userService;
    @Autowired
    private IdGenerator idGenerator;

    public List<Superuser> getAllSuperuser(){
        return (List<Superuser>) superuserRepository.findAll();
    }

    public Optional<Superuser> getSuperuser(String id){
        return superuserRepository.findById(id);
    }

    public String addSuperuser(Superuser superuser){
        Phone phone1 = null;
        if (!superuser.getPhones().isEmpty()){
            for (Phone phone: superuser.getPhones()){
                phone1 = phoneRepository.findByPhoneNo(phone.getPhoneNo());
                if (phone1 != null){
                    throw new MyMadeException("Phone '"+phone.getPhoneNo()+ "' already Exists.");
                }
            }
        }
        String id = idGenerator.RegularStudentIdGenerator();
        User user = userService.createUserObject(id,"SUPERUSER");
        superuser.setId(id);
        superuser.setUser(user);
        boolean f = false;
        try {
            superuserRepository.save(superuser);
            f=true;
        }catch(Exception e){
            idGenerator.replaceSequence(id);
            return e.toString();
        }

        return String.valueOf(f);

    }

    public void updateSuperuser(Superuser superuser){
        superuserRepository.save(superuser);
    }

    public void deleteSuperuser(String id){

        if (superuserRepository.findById(id).isEmpty()){
            throw new MyMadeException("Superuser not found of id "+id);
        }
        else superuserRepository.deleteById(id);
    }
}
