package bd.edu.seu.ums.Controller;


import bd.edu.seu.ums.Entity.AdvisingInfo;
import bd.edu.seu.ums.Entity.AdvisingInfo;
import bd.edu.seu.ums.Service.AdvisingInfoService;
import bd.edu.seu.ums.Service.AdvisingInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("management/api/v1/advising_info")
public class AdvisingInfoController {
    @Autowired
    private AdvisingInfoService service;

    @GetMapping
    public List<AdvisingInfo> getAll() {
        return service.getAllAdvisingInfo();
    }

    @PostMapping
    public void add(@RequestBody AdvisingInfo advisingInfo) {
        service.addAdvisingInfo(advisingInfo);
    }

    @PutMapping
    public void update(@RequestBody AdvisingInfo advisingInfo) {
        service.updateAdvisingInfo(advisingInfo);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable int id) {

        service.deleteAdvisingInfo(id);

    }
}
