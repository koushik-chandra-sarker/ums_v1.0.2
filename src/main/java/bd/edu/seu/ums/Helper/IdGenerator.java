package bd.edu.seu.ums.Helper;

import bd.edu.seu.ums.Entity.Campus;
import bd.edu.seu.ums.Entity.IdSeq;
import bd.edu.seu.ums.Repository.CampusRepository;
import bd.edu.seu.ums.Repository.IdSeqRepository;
import bd.edu.seu.ums.Service.IdSeqService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Optional;

@Service
public class IdGenerator {

    @Autowired
    private IdSeqRepository idSeqRepository;
    @Autowired
    private CampusRepository campusRepository;
    @Autowired
    private IdSeqService idSeqService;

    int currentYear = Calendar.getInstance().get(Calendar.YEAR);

    public String FacultyIdGenerator(){

        long id =0;
        IdSeq idSeq = idSeqRepository.findByYearAndType(currentYear, "FACULTY");
        if (idSeq!=null){
            id = Long.parseLong(idSeq.getSequence());
            id += 1;
            idSeq.setSequence(String.valueOf(id));
            idSeqRepository.save(idSeq);
        }else {
            String stringID = String.valueOf(currentYear).concat("8").concat("00001");
            IdSeq idSeq1 = new IdSeq("FACULTY",currentYear,8,stringID);
            idSeqRepository.save(idSeq1);
            return stringID;
        }

        return String.valueOf(id);
    }

    public String RegularStudentIdGenerator(){
        long id =0;
        IdSeq idSeq = idSeqRepository.findByYearAndType(currentYear,"REGULAR_STUDENT");
        if (idSeq ==null){
            String stringID = String.valueOf(currentYear).concat("000000001");
            IdSeq idSeq1 = new IdSeq("REGULAR_STUDENT",currentYear,0,stringID);
            idSeqRepository.save(idSeq1);
            return stringID;
        }
        else {
            id = Long.parseLong(idSeq.getSequence());
            id += 1;
            idSeq.setSequence(String.valueOf(id));
            idSeqRepository.save(idSeq);
        }
        return String.valueOf(id);
    }
    public String WeekEndStudentIdGenerator(){
        long id =0;
        IdSeq idSeq = idSeqRepository.findByYearAndType(currentYear,"WEEKEND_STUDENT");
        if (idSeq ==  null){
            String stringID = String.valueOf(currentYear).concat("100000001");
            IdSeq idSeq1 = new IdSeq("WEEKEND_STUDENT",currentYear,1,stringID);
            idSeqRepository.save(idSeq1);
            return stringID;
        }
        else {
            id = Long.parseLong(idSeq.getSequence());
            id += 1;
            idSeq.setSequence(String.valueOf(id));
            idSeqRepository.save(idSeq);
        }
        return String.valueOf(id);
    }

    public void replaceSequence(String id){
        int year = Integer.parseInt(id.substring(0,4));
        int flag = Integer.parseInt(String.valueOf(id.charAt(4)));
        IdSeq idSeq = idSeqRepository.findByYearAndFlag(year,flag);
        long seq = Long.parseLong(idSeq.getSequence());
        seq-=1;
        idSeq.setSequence(String.valueOf(seq));
        idSeqRepository.save(idSeq);

    }
}
