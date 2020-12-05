package bd.edu.seu.ums.Entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PreAdvisedCourse {

    @EmbeddedId private PreAdvisedCourseId id; //course-code, semester_id, year

    private int credit;

    @ManyToOne
    @JoinColumn
    @NotNull
    private Student student;
}
