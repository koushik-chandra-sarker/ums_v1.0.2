package bd.edu.seu.ums.Service;

import bd.edu.seu.ums.Entity.AdvisingInfo;
import bd.edu.seu.ums.Exception.MyMadeException;
import bd.edu.seu.ums.Repository.AdvisingInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdvisingInfoService {
    @Autowired
    private AdvisingInfoRepository repository;


    public List<AdvisingInfo> getAllAdvisingInfo() {
        return repository.findAll();
    }


    public Optional<AdvisingInfo> getById(int id) {
        return repository.findById(id);
    }

    

    public void addAdvisingInfo(AdvisingInfo advisingInfo) {
        try {
            repository.save(advisingInfo);
        } catch (Exception e) {
            throw new MyMadeException(e.getMessage());
        }
    }

    public void updateAdvisingInfo(AdvisingInfo advisingInfo) {
        repository.save(advisingInfo);
    }

    public void deleteAdvisingInfo(int id) {
        repository.deleteById(id);
    }
}
