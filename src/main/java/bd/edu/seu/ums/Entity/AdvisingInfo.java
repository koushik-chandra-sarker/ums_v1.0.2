package bd.edu.seu.ums.Entity;

import bd.edu.seu.ums.Exception.MyMadeException;
import bd.edu.seu.ums.Helper.TimeConverter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.sql.Time;
import java.text.ParseException;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdvisingInfo {

    @Id
    @GeneratedValue
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

    public void setStartTime(String startTime) {

        try {
            this.startTime = Time.valueOf(TimeConverter.convert24h(startTime));
        } catch (ParseException e) {
            throw new MyMadeException(e.getMessage());
        }
    }

    public void setEndTime(String endTime) {
        try {
            this.endTime = Time.valueOf(TimeConverter.convert24h(endTime));
        } catch (ParseException e) {
            throw new MyMadeException(e.getMessage());
        }
    }
}
