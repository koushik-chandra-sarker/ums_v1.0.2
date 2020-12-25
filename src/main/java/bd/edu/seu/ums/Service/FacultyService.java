package bd.edu.seu.ums.Service;

import bd.edu.seu.ums.Entity.Faculty;
import bd.edu.seu.ums.Entity.User;
import bd.edu.seu.ums.Exception.MyMadeException;
import bd.edu.seu.ums.Helper.IdGenerator;
import bd.edu.seu.ums.Repository.FacultyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class FacultyService {

    @Autowired
    private FacultyRepository facultyRepository;
    @Autowired
    private IdGenerator idGenerator;

    @Autowired
    UserService userService;
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
        List<Faculty> faculties = this.getAllFaculty();
        long t = faculties.stream().filter(faculty1 -> faculty1.getInitial().equals(faculty.getInitial())).count();
        if (t>0){
            throw new MyMadeException("Initial Already Exists.. Initial Must be Unique");
        }
        String id = idGenerator.FacultyIdGenerator();
        User user = userService.createUserObject(id, "FACULTY");
        faculty.setId(id);
        faculty.setUser(user);
        boolean f = false;
        try {
            facultyRepository.save(faculty);
            f= true;
        }catch(Exception e){
            idGenerator.replaceSequence(id);
            throw new MyMadeException(e.getMessage());
        }

        return String.valueOf(f);
    }

    public void updateFaculty(Faculty faculty) throws MyMadeException {
        String id;
        String username;
        Optional<Faculty> faculty1 = null;
        try {
            id = faculty.getId();
            faculty1 = this.getFaculty(id);
        }catch (NullPointerException e){
            throw new MyMadeException("You must have to given id.");
        }
        faculty.setUser(faculty1.get().getUser());
        try {
            facultyRepository.save(faculty);
        }
        catch (Exception e){
            throw new MyMadeException(e.getLocalizedMessage());
        }

    }

    public void deleteFaculty(String id) {
        facultyRepository.deleteById(id);
    }
}
