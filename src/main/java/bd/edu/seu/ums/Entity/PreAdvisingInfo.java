package bd.edu.seu.ums.Entity;

import bd.edu.seu.ums.Exception.MyMadeException;
import bd.edu.seu.ums.Helper.TimeConverter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Time;
import java.sql.Date;
import java.text.ParseException;

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
