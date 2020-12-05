package bd.edu.seu.ums.Service;

import bd.edu.seu.ums.Entity.AdvisingInfo;
import bd.edu.seu.ums.Entity.PreAdvisedCourse;
import bd.edu.seu.ums.Entity.StudentCourse;
import bd.edu.seu.ums.Exception.MyMadeException;
import bd.edu.seu.ums.Repository.AdvisingInfoRepository;
import bd.edu.seu.ums.Repository.CourseRepository;
import bd.edu.seu.ums.Repository.PreAdvisedCourseRepository;
import bd.edu.seu.ums.Repository.StudentCourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentCourseService {
    @Autowired
    private StudentCourseRepository repository;

    @Autowired
    PreAdvisedCourseRepository preAdvisedCourseRepository;

    @Autowired
    AdvisingInfoRepository advisingInfoRepository;
    @Autowired
    CourseRepository courseRepository;

    public List<StudentCourse> getAllStudentCourse() {
        return (List<StudentCourse>) repository.findAll();
    }

    public Optional<StudentCourse> getStudentCourse(int id) {
        return repository.findById(id);
    }

    public void addStudentCourse(StudentCourse studentCourse) {


        Optional<StudentCourse> studentCourse1 = repository.findBySemIdAndCourseCodeAndYear(
                studentCourse.getOfferedCourse().getId().getSemester().getId(),
                studentCourse.getOfferedCourse().getId().getCourse().getCode(),
                studentCourse.getOfferedCourse().getId().getYear()
        );

        //Checking the course already taken or not
        if (studentCourse1.isPresent()){
            throw new MyMadeException("Course already taken.");
        }else {
            AdvisingInfo advisingInfo = advisingInfoRepository.findByRunning(true); // get running advising information

            //get total credit that already taken in current semester
            int creditTaken = repository.totalCreditBySemesterIdAndYear(
                    studentCourse.getOfferedCourse().getId().getSemester().getId(),
                    studentCourse.getOfferedCourse().getId().getYear());

            //get credit of this course
            int credit =  courseRepository.findCreditByCourseCode(studentCourse.getOfferedCourse().getId().getCourse().getCode());

            //Check this course is present or not on PreAdvisingCourse
            Optional<PreAdvisedCourse> preAdvisedCourse = preAdvisedCourseRepository.findByCourseCodeAndSemIdAndYearAndStudentId(
                    studentCourse.getOfferedCourse().getId().getCourse().getCode(),
                    studentCourse.getOfferedCourse().getId().getSemester().getId(),
                    studentCourse.getOfferedCourse().getId().getYear(),
                    studentCourse.getStudent().getId()
            );

            // if course is present on PreAdvisingCourse
            if (preAdvisedCourse.isPresent()){
                //Checking max credit limit
                if ((creditTaken+credit) <= advisingInfo.getMaximumCredit()){
                    try {
                        repository.save(studentCourse);
                    }catch (Exception e){
                        throw new MyMadeException(e.getMessage());
                    }
                }else {
                    throw new MyMadeException(String.format("You can't take more then %d Credit", advisingInfo.getMaximumCredit()));
                }
            }
            else {

                int extraCreditTaken = repository.totalCreditExceptPreAdvisingCourse(
                        studentCourse.getOfferedCourse().getId().getSemester().getId(),
                        studentCourse.getOfferedCourse().getId().getYear()
                );

                if ((creditTaken+credit) <= advisingInfo.getMaximumCredit() && (extraCreditTaken+credit) <= advisingInfo.getExtraCredit()){
                    try {
                        repository.save(studentCourse);
                    }catch (Exception e){
                        throw new MyMadeException(e.getMessage());
                    }
                }else {
                    throw new MyMadeException(String.format("You can't take more then %d credit except pre-advised course", advisingInfo.getExtraCredit()));
                }
            }
        }


//        List<StudentCourse> studentCourse2 = repository.findByStudentId(studentCourse.getStudent().getId());
//        long count = studentCourse2.stream().filter(sc ->
//                sc.getOfferedCourse().getId().getCourse().getCode().equals(studentCourse.getOfferedCourse().getId().getCourse().getCode()) &&
//                        sc.getOfferedCourse().getId().getSemester().getId().equals(studentCourse.getOfferedCourse().getId().getSemester().getId()) &&
//                        sc.getOfferedCourse().getId().getYear() == studentCourse.getOfferedCourse().getId().getYear()
//        ).count();
//         if (count>0){
//             throw new MyMadeException("This Course Already taken.");
//         }else {
//             try {
//                 repository.save(studentCourse);
//             }catch (Exception e){
//                 throw new MyMadeException(e.getMessage());
//             }
//
//         }

    }

    public void updateStudentCourse(StudentCourse studentCourse) {
        repository.save(studentCourse);
    }

    public void deleteStudentCourse(Integer id) {
        repository.deleteById(id);
    }
}
