package bd.edu.seu.ums.Entity;

import bd.edu.seu.ums.Enum.StudentCourseStatus;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity(name = "student_course")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentCourse {

    @Id
    @GeneratedValue
    private int id;

    @ManyToOne
    @NotNull
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    @JsonProperty("studentId")
    private Student student;

    @ManyToOne
    @NotNull
    private OfferedCourse offeredCourse;

    private double gradeAward = 0.00;
    private String gradeLatter;

    @Enumerated(EnumType.STRING)
    private StudentCourseStatus status; //COMPLETE,RUNNING,FAILED,INCOMPLETE

}
