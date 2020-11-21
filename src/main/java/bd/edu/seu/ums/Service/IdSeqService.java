package bd.edu.seu.ums.Service;

import bd.edu.seu.ums.Entity.IdSeq;
import bd.edu.seu.ums.Repository.IdSeqRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IdSeqService {
    @Autowired
    private IdSeqRepository idSeqRepository;



    public IdSeq getIdSeq(int year, String type){
        return idSeqRepository.findByYearAndType(year,type);
    }

}
