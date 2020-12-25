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
public class OfferedCourseId implements Serializable {

    @ManyToOne
    private Course course = new Course();
    @ManyToOne
    private Semester semester;
    @Column(nullable = false)
    private int year = Year.now().getValue();
    @Column(columnDefinition = "integer default 1")
    private int section = 1;

}
