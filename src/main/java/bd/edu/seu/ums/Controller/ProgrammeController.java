package bd.edu.seu.ums.Controller;

import bd.edu.seu.ums.Entity.Programme;
import bd.edu.seu.ums.Service.ProgrammeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("management/api/v1/programmes")
public class ProgrammeController {
    @Autowired
    private ProgrammeService programmeService;

    //Fetch all
    @GetMapping
    public List<Programme> getAllProgramme(){
        return programmeService.getAllProgramme();
    }

    //Fetch one
    @GetMapping("{code}")
    public Optional<Programme> getProgramme(@PathVariable String code){
        return programmeService.getProgramme(code);

    }
    //Insert
    @PostMapping
    public void addProgramme(@RequestBody Programme programme){
        programmeService.addProgramme(programme);
    }

    //Update
    @PutMapping
    public void updateProgramme(@RequestBody Programme programme){
        programmeService.updateProgramme(programme);
    }

    //Delete
    @DeleteMapping("{code}")
    public void deleteProgramme(@PathVariable String code){
        programmeService.deleteProgramme(code);
    }
}
