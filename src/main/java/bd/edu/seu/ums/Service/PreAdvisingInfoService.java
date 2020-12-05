package bd.edu.seu.ums.Service;

import bd.edu.seu.ums.Entity.Course;
import bd.edu.seu.ums.Entity.PreAdvisedCourse;
import bd.edu.seu.ums.Entity.PreAdvisedCourseId;
import bd.edu.seu.ums.Entity.PreAdvisingInfo;
import bd.edu.seu.ums.Exception.MyMadeException;
import bd.edu.seu.ums.Repository.PreAdvisedCourseRepository;
import bd.edu.seu.ums.Repository.PreAdvisingInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PreAdvisingInfoService {
    @Autowired
    private PreAdvisingInfoRepository repository;


    public List<PreAdvisingInfo> getAllPreAdvisingInfo() {
        return repository.findAll();
    }


    public Optional<PreAdvisingInfo> getById(int id) {
        return repository.findById(id);
    }

    public void addPreAdvisingInfo(PreAdvisingInfo PreAdvisingInfo) {
        try {
            repository.save(PreAdvisingInfo);
        } catch (Exception e) {
            throw new MyMadeException(e.getMessage());
        }
    }

    public void updatePreAdvisingInfo(PreAdvisingInfo preAdvisingInfo) {
        repository.save(preAdvisingInfo);
    }

    public void deletePreAdvisingInfo(int id) {
        repository.deleteById(id);
    }
}
