package bd.edu.seu.ums.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "id_seq")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class IdSeq {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int ssn;
    private String type;
    private int year;
    private int flag;
    private String sequence;

    public IdSeq(String type, int year, int flag, String sequence) {
        this.type = type;
        this.year = year;
        this.flag = flag;
        this.sequence = sequence;
    }
}
