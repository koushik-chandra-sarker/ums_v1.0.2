package bd.edu.seu.ums.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.Year;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class PreAdvisedCourseId implements Serializable {
    @ManyToOne
    @NotNull
    private Course course = new Course();
    @ManyToOne
    @NotNull
    private Semester semester = new Semester();
    @Column(nullable = false)
    private int year = Year.now().getValue();

}
