package bd.edu.seu.ums.Entity;

import com.fasterxml.jackson.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "school")
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({"id", "name", "campusId", "programmes", "lecturers"})
public class School {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.DETACH)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    @JsonProperty("campusId")
    private Campus campus;


    @OneToMany(mappedBy = "school", cascade = CascadeType.PERSIST)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "code")
    @JsonIdentityReference(alwaysAsId = true)
    @JsonProperty("programmeCode")
    private List<Programme> programmes = new ArrayList<>();


}
