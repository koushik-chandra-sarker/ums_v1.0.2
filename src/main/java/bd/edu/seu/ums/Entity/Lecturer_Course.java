package bd.edu.seu.ums.Entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "lecturer_course")
@Data
@NoArgsConstructor
@AllArgsConstructor
//@JsonIdentityInfo(
//        generator = ObjectIdGenerators.PropertyGenerator.class,
//        property = "ssn")
public class Lecturer_Course {

    @Id
    private String id;

    @Column(nullable = false)
    private String courseCode;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Semester semester;

    @Column(nullable = false)
    private int year;

    @Column(nullable = false)
    private int section;


    @ManyToOne()
    @JoinColumn(name = "faculty_id")
    private Faculty faculty;
//
//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn
//    private Course course;

//    @OneToMany(mappedBy = "courses",fetch = FetchType.LAZY)
//    private List<Course_Student> course_students = new ArrayList<>();


}
