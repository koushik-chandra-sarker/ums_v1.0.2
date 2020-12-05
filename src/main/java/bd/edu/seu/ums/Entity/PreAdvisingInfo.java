package bd.edu.seu.ums.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Time;
import java.sql.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PreAdvisingInfo {

    @Id
    @GeneratedValue
    private int ssn;
    private Date startDate; //YYYY-MM-DD
    private Time startTime;
    private Date endDate; //YYYY-MM-DD
    private Time endTime;
    private int maximumCredit;

    @ManyToOne
    @JoinColumn
    private Semester semester;
    private int year;

    private boolean active = false;

    private boolean running = false;

}
