package bd.edu.seu.ums.Entity;


import com.fasterxml.jackson.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@JsonPropertyOrder({ "code", "title", "credit","minCredit","type","prototype", "pre_course_code", "programme","lecturer_courses" })
public class Course implements Serializable {
    @Id
    private String code;
    private String title;
    private int credit;
    private int minCredit;
    private String type;
    private String prototype;

    @ManyToMany
    @JoinTable(
            name = "pre_course",
            joinColumns = @JoinColumn(name = "course_code"),
            inverseJoinColumns = @JoinColumn(name = "pre_course_code")
    )
    @JsonIdentityInfo(
            generator = ObjectIdGenerators.PropertyGenerator.class,
            property = "code")
    @JsonIdentityReference(alwaysAsId = true)
    @JsonProperty("pre_course_code")
    private List<Course> pre_course;



    //JsonIgnore
    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(
            name = "pre_course",
            joinColumns = @JoinColumn(name = "pre_course_code"),
            inverseJoinColumns = @JoinColumn(name = "course_code")
    )
    @JsonIgnore
    private List<Course> courses;

    @ManyToOne
    @JsonIdentityInfo(
            generator = ObjectIdGenerators.PropertyGenerator.class,
            property = "code")
    @JsonIdentityReference(alwaysAsId = true)
    @JsonProperty("programmeCode")
    private Programme programme;

    @OneToOne
    private Course alternateCourse;



}
