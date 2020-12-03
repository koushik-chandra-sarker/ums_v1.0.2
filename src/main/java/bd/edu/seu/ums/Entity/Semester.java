package bd.edu.seu.ums.Entity;


import com.fasterxml.jackson.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name = "semester")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Semester {

    @Id
    private String id;

    @Column(nullable = false)
    private String semester;

    @ManyToOne
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "code")
    @JsonIdentityReference(alwaysAsId = true)
    @JsonProperty("programmeCode")
    @JoinColumn(name = "programme_code",nullable = false)
    private Programme programme;

}
