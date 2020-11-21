package bd.edu.seu.ums.Service;

import bd.edu.seu.ums.Entity.Programme;
import bd.edu.seu.ums.Repository.ProgrammeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProgrammeService {
    @Autowired
    private ProgrammeRepository programmeRepository;

    public List<Programme> getAllProgramme(){
        return (List<Programme>) programmeRepository.findAll();
    }

    public Optional<Programme> getProgramme(String code){
        return programmeRepository.findById(code);
    }

    public void addProgramme(Programme programme){
        programmeRepository.save(programme);
    }

    public void updateProgramme(Programme programme){
        programmeRepository.save(programme);
    }

    public void deleteProgramme(String code){
        programmeRepository.deleteById(code);
    }
}
