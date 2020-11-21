package bd.edu.seu.ums.Service;

import bd.edu.seu.ums.Entity.Campus;
import bd.edu.seu.ums.Repository.CampusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CampusService {
    @Autowired
    private CampusRepository campusRepository;

    public List<Campus> getAllCampus(){
        return (List<Campus>) campusRepository.findAll();
    }

    public Optional<Campus> getCampus(int id){
        return campusRepository.findById(id);
    }

    public void addCampus(Campus campus){
        campusRepository.save(campus);
    }

    public void updateCampus(Campus campus){
        campusRepository.save(campus);
    }

    public void deleteCampus(int id){
        campusRepository.deleteById(id);
    }
}
