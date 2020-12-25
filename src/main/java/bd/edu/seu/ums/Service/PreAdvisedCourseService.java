package bd.edu.seu.ums.Service;

import bd.edu.seu.ums.Entity.Course;
import bd.edu.seu.ums.Entity.PreAdvisedCourse;
import bd.edu.seu.ums.Entity.PreAdvisedCourseId;
import bd.edu.seu.ums.Entity.PreAdvisingInfo;
import bd.edu.seu.ums.Exception.MyMadeException;
import bd.edu.seu.ums.Repository.PreAdvisedCourseRepository;
import bd.edu.seu.ums.Repository.PreAdvisingInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PreAdvisedCourseService {
    @Autowired
    private PreAdvisedCourseRepository repository;
    @Autowired
    private PreAdvisingInfoRepository infoRepository;
    @Autowired
    private CourseService courseService;

    public List<PreAdvisedCourse> getAllPreAdvisedCourse() {
        return repository.findAll();
    }

    public List<PreAdvisedCourse> getAllPreAdvisedCourseBySId(String id) {
        return repository.findAllByStudentId(id);
    }

    public Optional<PreAdvisedCourse> getById(PreAdvisedCourseId id) {
        return repository.findById(id);
    }

    public void addPreAdvisedCourse(PreAdvisedCourse preAdvisedCourse) {

        Optional<PreAdvisingInfo> preAdvisingInfo = infoRepository.findByRunning(true);

        int takenCredit = repository.totalCreditBySemester_IdAndYearAndStudent_Id(
                preAdvisedCourse.getId().getSemester().getId(),
                preAdvisedCourse.getId().getYear(),
                preAdvisedCourse.getStudent().getId()
        );


        //Check pre_advising is running or not
        if (preAdvisingInfo.isPresent()) {

            String course_code = preAdvisedCourse.getId().getCourse().getCode();
            //get course for credit
            Optional<Course> course = courseService.getCourse(course_code);

            if (course.isPresent()){
                //this course's credit
                int creditOfThisCourse = course.get().getCredit();

                //Checking the maximum credit limit
                if ((takenCredit + creditOfThisCourse) <= preAdvisingInfo.get().getMaximumCredit()) {
                    try {
                        //Check this course already taken or not
                        Optional<PreAdvisedCourse> preAdvisedCourse2 = this.getById(preAdvisedCourse.getId());
                        if (preAdvisedCourse2.isPresent()) {
                            throw new MyMadeException("Course Already taken.");
                        } else {
                                preAdvisedCourse.setCredit(creditOfThisCourse);
                                repository.save(preAdvisedCourse);
                        }
                    } catch (Exception e) {
                        throw new MyMadeException(e.getMessage());
                    }
                } else {
                    throw new MyMadeException(String.format("You can't take more than %d credit.", preAdvisingInfo.get().getMaximumCredit()));
                }
            }
            else {
                throw new MyMadeException("Course Not Found.");
            }

        } else {
            throw new MyMadeException("Pre advising not yet started.");
        }

    }

    public void updatePreAdvisedCourse(PreAdvisedCourse preAdvisedCourse) {
        repository.save(preAdvisedCourse);
    }

    public void deletePreAdvisedCourse(PreAdvisedCourseId id) {
        repository.deleteById(id);
    }
}
