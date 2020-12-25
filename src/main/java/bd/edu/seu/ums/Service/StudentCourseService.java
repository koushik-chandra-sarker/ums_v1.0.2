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



    //Advising
    public void addStudentCourse(StudentCourse studentCourse) {

        Optional<AdvisingInfo> advisingInfo = advisingInfoRepository.findByRunning(true); // get running advising information


        //Check: Advising start or not
        if (advisingInfo.isPresent()) {

            //TODO: Check prerequisites course and modify this code

            List<String> preCourseCode = courseRepository.findPreCourseCodeByCourseCode(studentCourse.getOfferedCourse().getId().getCourse().getCode());
            List<String> completedCourseCode =  repository.findCompletedCourseCodeByStudentIdAndStatus(studentCourse.getStudent().getId(),"COMPLETE");

            //Check: prerequisites course are completed or not
            if (isCourseComplete(preCourseCode,completedCourseCode)){
                Optional<StudentCourse> studentCourse1 = repository.findBySemesterIdAndCourseCodeAndYearAndStudentId(
                        studentCourse.getOfferedCourse().getId().getSemester().getId(),
                        studentCourse.getOfferedCourse().getId().getCourse().getCode(),
                        studentCourse.getOfferedCourse().getId().getYear(),
                        studentCourse.getStudent().getId()
                );
                //Checking the course already taken or not
                if (studentCourse1.isPresent()) {
                    throw new MyMadeException("Course already taken.");
                } else {

                    //get total credit that already taken in current semester
                    int creditTaken = repository.totalCreditBySemesterIdAndYearAndStudentId(
                            studentCourse.getOfferedCourse().getId().getSemester().getId(),
                            studentCourse.getOfferedCourse().getId().getYear(),
                            studentCourse.getStudent().getId()
                    );

                    //get credit of this course
                    int credit = courseRepository.findCreditByCourseCode(studentCourse.getOfferedCourse().getId().getCourse().getCode());

                    //Check this course is present or not on PreAdvisingCourse
                    Optional<PreAdvisedCourse> preAdvisedCourse = preAdvisedCourseRepository.findByCourseCodeAndSemesterIdAndYearAndStudentId(
                            studentCourse.getOfferedCourse().getId().getCourse().getCode(),
                            studentCourse.getOfferedCourse().getId().getSemester().getId(),
                            studentCourse.getOfferedCourse().getId().getYear(),
                            studentCourse.getStudent().getId()
                    );

                    // if course is present on PreAdvisingCourse
                    if (preAdvisedCourse.isPresent()) {
                        //Checking max credit limit
                        if ((creditTaken + credit) <= advisingInfo.get().getMaximumCredit()) {
                            try {
                                repository.save(studentCourse);
                            } catch (Exception e) {
                                throw new MyMadeException(e.getMessage());
                            }
                        } else {
                            throw new MyMadeException(String.format("You can't take more then %d Credit", advisingInfo.get().getMaximumCredit()));
                        }
                    } else {

                        int extraCreditTaken = repository.totalCreditExceptPreAdvisingCourse(
                                studentCourse.getOfferedCourse().getId().getSemester().getId(),
                                studentCourse.getOfferedCourse().getId().getYear(),
                                studentCourse.getStudent().getId()
                        );

                        if ((extraCreditTaken + credit) <= advisingInfo.get().getExtraCredit() && (extraCreditTaken + credit) <= advisingInfo.get().getExtraCredit()) {
                            try {
                                repository.save(studentCourse);
                            } catch (Exception e) {
                                throw new MyMadeException(e.getMessage());
                            }
                        } else {
                            throw new MyMadeException(String.format("You can't take more than %d credit except for the pre-advised course", advisingInfo.get().getExtraCredit()));
                        }
                    }
                }
            }else {
                throw new MyMadeException("First, You have to complete the prerequisite courses of this Course.");
            }


        } else {
            throw new MyMadeException("Advising not yet started.");
        }

    }

    //check preCourse are completed or not
    public boolean isCourseComplete(List<String> preCourseCode , List<String> completedStudentCoursesCode){
        boolean f = false;
        long completedCourseCount  = preCourseCode.stream().filter(completedStudentCoursesCode::contains).count();
        if (completedCourseCount==preCourseCode.size()){
            f=true;
        }
        return f;
    }

    public void updateStudentCourse(StudentCourse studentCourse) {
        Optional<StudentCourse> studentCourse1 = getStudentCourse(studentCourse.getId());
        if (studentCourse1.isPresent()){
            String dbCourseCode = studentCourse1.get().getOfferedCourse().getId().getCourse().getCode();
            String dbSemId = studentCourse1.get().getOfferedCourse().getId().getSemester().getId();
            int dbYear = studentCourse1.get().getOfferedCourse().getId().getYear();
            int dbSec = studentCourse1.get().getOfferedCourse().getId().getSection();

            String inputCourseCode = studentCourse.getOfferedCourse().getId().getCourse().getCode();
            String inputSemId = studentCourse.getOfferedCourse().getId().getSemester().getId();
            int inoutYear = studentCourse.getOfferedCourse().getId().getYear();
            int inputSec = studentCourse.getOfferedCourse().getId().getSection();

            if (dbCourseCode.equals(inputCourseCode) && dbSemId.equals(inputSemId) && dbYear == inoutYear && dbSec == inputSec){
                repository.save(studentCourse);
            }
            else throw new MyMadeException("Id & OfferedCourse.id did not matched.");

        }

    }

    public void deleteStudentCourse(Integer id) {
        repository.deleteById(id);
    }




}
