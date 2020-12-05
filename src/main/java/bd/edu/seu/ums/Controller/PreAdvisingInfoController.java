package bd.edu.seu.ums.Controller;


import bd.edu.seu.ums.Entity.PreAdvisingInfo;
import bd.edu.seu.ums.Service.PreAdvisingInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("management/api/v1/pre_advising_info")
public class PreAdvisingInfoController {
    @Autowired
    private PreAdvisingInfoService service;

    @GetMapping
    public List<PreAdvisingInfo> getAll() {
        return service.getAllPreAdvisingInfo();
    }

    @PostMapping
    public void add(@RequestBody PreAdvisingInfo preAdvisingInfo) {
        service.addPreAdvisingInfo(preAdvisingInfo);
    }

    @PutMapping
    public void update(@RequestBody PreAdvisingInfo preAdvisingInfo) {
        service.updatePreAdvisingInfo(preAdvisingInfo);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable int id) {

        service.deletePreAdvisingInfo(id);

    }
}
