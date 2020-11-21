package bd.edu.seu.ums.Entity;

import com.fasterxml.jackson.annotation.*;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@JsonPropertyOrder({ "code", "title", "label", "length", "school","faculties", "students", "courses" })
public class Programme {

    @Id
    private String code;
    private String title;
    private String label;
    private int length;

    @ManyToOne
    private School school;

    @OneToMany(mappedBy = "programme",fetch = FetchType.EAGER)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    @JsonProperty("facultyIds")
    private List<Faculty> faculties = new ArrayList<>();

    @OneToMany(mappedBy = "programme", cascade = CascadeType.ALL)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    @JsonProperty("studentIds")
    private List<Student> students = new ArrayList<>();

    @OneToMany(cascade = CascadeType.PERSIST,mappedBy = "programme" )
    private List<Course> courses = new ArrayList<>();


}
