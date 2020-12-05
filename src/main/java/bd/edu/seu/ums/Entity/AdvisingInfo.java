package bd.edu.seu.ums.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.sql.Time;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdvisingInfo {

    @Id
    private int ssn;

    @Column(nullable = false)
    private Date startDate; //YYYY-MM-DD

    @Column(nullable = false)
    private Time startTime;

    @Column(nullable = false)
    private Date endDate; //YYYY-MM-DD

    @Column(nullable = false)
    private Time endTime;

    @Column(nullable = false)
    private int maximumCredit; // Students will be able to take credit for this length.

    @Column(nullable = false)
    private int extraCredit;//Students will be able to take credit for this length except pre-advising courses.

    @Column(nullable = false)
    private int minRequiredCredit;

    @Column(nullable = false)
    private int maxRequiredCredit;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Semester semester;

    @Column(nullable = false)
    private int year;

    @Column(nullable = false)
    private boolean active = false;

    @Column(unique = true,nullable = false)
    private boolean running = false;

}
