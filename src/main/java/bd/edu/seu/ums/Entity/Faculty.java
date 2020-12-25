package bd.edu.seu.ums.Entity;

import bd.edu.seu.ums.Enum.Gender;
import com.fasterxml.jackson.annotation.*;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@Entity
@Data
/*@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")*/
@JsonPropertyOrder({"id", "firstName", "middleName", "lastName", "email", "title", "birthday",
        "gender", "joiningDate", "officeRoom", "phone", "supervisor", "lecturer", "lecturer_courses"})
public class Faculty {
    @Id
    private String id;
    private String firstName;
    private String middleName;
    private String lastName;
    @Column(unique = true,nullable = false)
    @NotNull
    private String initial;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "faculty_id")
    private List<Email> emails;

    private String title;
    private LocalDate birthDate; // YYYY-MM-DD
    @Enumerated(EnumType.STRING)
    private Gender gender;  // MALE or FEMALE or OTHER
    private LocalDate joiningDate; // YYYY-MM-DD
    private String officeRoom;
    private String bloodGroup;
    private String maritalStatus;
    private String religion;
    private String nationality;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "faculty_id")
    private List<Address> addresses;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "faculty_id", referencedColumnName = "id")
    private List<Phone> phones;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "faculty_id",referencedColumnName = "id")
    private List<Education> educations;

    @ManyToOne
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "code")
    @JsonIdentityReference(alwaysAsId = true)
    @JsonProperty("programmeCodes")
    private Programme programme;

    @ManyToOne
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    private Faculty supervisor;

    @OneToMany(mappedBy = "supervisor", cascade = CascadeType.PERSIST)
//    @JoinColumn(name = "supervisor")
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    private List<Faculty> faculty = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true,fetch = FetchType.EAGER)
    @JsonIgnore
    private User user;



    @OneToMany(mappedBy = "faculty",cascade = CascadeType.REMOVE)
    @JsonIgnore
    private Collection<OfferedCourse> offeredCourse;


}
