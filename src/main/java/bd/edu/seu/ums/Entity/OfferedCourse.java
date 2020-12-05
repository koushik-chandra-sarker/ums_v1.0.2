package bd.edu.seu.ums.Entity;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
//@IdClass(OfferedCourseId.class)
public class OfferedCourse {

//    @Id
//    @ManyToOne
//    @JoinColumn(name = "course_code",referencedColumnName = "code")
//    private Course course;
//
//    @Id
//    @ManyToOne
//    @JoinColumn(name = "semester_id",referencedColumnName = "id")
//    private Semester semester;
//
//    @Id
//    private int year;
//    @Id
//    private int section;

    @EmbeddedId OfferedCourseId id;

    @ManyToOne
//    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
//    @JsonIdentityReference(alwaysAsId = true)
    @JoinColumn(nullable = false)
    private Faculty faculty;

    @NotNull
    private int studentLimit;
    private int availableLimit;

}
