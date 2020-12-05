package bd.edu.seu.ums.Service;

import bd.edu.seu.ums.Entity.Campus;
import bd.edu.seu.ums.Entity.Faculty;
import bd.edu.seu.ums.Entity.OfferedCourse;
import bd.edu.seu.ums.Entity.OfferedCourseId;
import bd.edu.seu.ums.Exception.MyMadeException;
import bd.edu.seu.ums.Repository.CampusRepository;
import bd.edu.seu.ums.Repository.OfferedCourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OfferedCourseService {
    @Autowired
    private OfferedCourseRepository offeredCourseRepository;

    public List<OfferedCourse> getAllOfferedCourse(){
        return offeredCourseRepository.findAll();
    }


    public Optional<OfferedCourse> getOfferedCourse(OfferedCourseId id){
        return offeredCourseRepository.findById(id);
    }

    public void addOfferedCourse(OfferedCourse offeredCourse){
        try {
            if (offeredCourse.getId().getCourse().getCode().equals("")){
                throw new MyMadeException("id.course.code cannot be left empty.");
            }
        }catch (NullPointerException e){
            throw new MyMadeException("You must have to add id.course.code");
        }
        try {
            if (offeredCourse.getId().getSemester().getId().equals("")){
                throw new MyMadeException("id.semester.id cannot be left empty.");
            }
        }catch (NullPointerException e){
            throw new MyMadeException("You must have to add id.semester.id");
        }
        try {
            if (offeredCourse.getId().getSemester().getId().equals("")){
                throw new MyMadeException("id.semester.id cannot be left empty.");
            }
        }catch (NullPointerException e){
            throw new MyMadeException("You must have to add id.semester.id");
        }
        try {
            if (offeredCourse.getFaculty().getId().equals("")){
                throw new MyMadeException("faculty.id cannot be left empty.");
            }
        }catch (NullPointerException e){
            throw new MyMadeException("You must have to add faculty.id");
        }
        try {
             offeredCourse.setAvailableLimit(offeredCourse.getStudentLimit()); // setting AvailableLimit by studentLimit
        }catch (NullPointerException e){
            throw new MyMadeException("You must have to add studentLimit");
        }

        //section auto generated start
        if (offeredCourse.getId().getSection()==1){
            OfferedCourse offeredCourse1 = offeredCourseRepository.findByCourseAndSemesterAndYear(
                    offeredCourse.getId().getCourse().getCode(),
                    offeredCourse.getId().getSemester().getId(),
                    offeredCourse.getId().getYear()
                    );
            if (offeredCourse1!=null){
                int section = offeredCourse1.getId().getSection();
                offeredCourse.getId().setSection(section+1);
                offeredCourseRepository.save(offeredCourse);
            }else {
                offeredCourseRepository.save(offeredCourse);
            }

        }else {
            throw new MyMadeException("Please Delete id.section... it will auto generated");
        }
        //section auto generated end


    }

    public void updateOfferedCourse(OfferedCourse offeredCourse){
        offeredCourseRepository.save(offeredCourse);
    }

//    public void deleteOfferedCourse(OfferedCourseId id){
//        offeredCourseRepository.deleteById(id);
//    }
}
