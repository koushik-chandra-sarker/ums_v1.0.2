package bd.edu.seu.ums.Service;

import bd.edu.seu.ums.Entity.Semester;
import bd.edu.seu.ums.Exception.MyMadeException;
import bd.edu.seu.ums.Repository.SemesterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SemesterService {
    @Autowired
    private SemesterRepository semesterRepository;

    public List<Semester> getAllSemester(){
        return (List<Semester>) semesterRepository.findAll();
    }

    public Optional<Semester> getSemester(String id){
        return semesterRepository.findById(id);
    }

    public void addSemester(Semester semester){
        semester.setId(semester.getSemester()+"-" + semester.getProgramme().getCode());

        semesterRepository.save(semester);


    }

    public void updateSemester(Semester semester){
        semesterRepository.save(semester);
    }

    public void deleteSemester(String id){
        semesterRepository.deleteById(id);
    }
}
