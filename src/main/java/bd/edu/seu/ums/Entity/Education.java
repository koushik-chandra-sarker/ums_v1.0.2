package bd.edu.seu.ums.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "education")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Education {
    @Id
    private int id;
    private String degree;
    @Column(name = "\"group\"")
    private String group;
    private String roll;
    private String registrationNo;
    private String instituteName;
    private String result;
    private String board;
    private String passingYear;
}
