package bd.edu.seu.ums.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

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
    private Student student;

    @ManyToOne
    @NotNull
    private OfferedCourse offeredCourse;

    private double gradeAward = 0.00;
    private String gradeLatter;
    private String status;

}
