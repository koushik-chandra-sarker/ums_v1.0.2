package bd.edu.seu.ums.Entity;

import bd.edu.seu.ums.Enum.Gender;
import com.fasterxml.jackson.annotation.*;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
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
    @NotNull(message = "ojisjd")
    private String initial;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "faculty_id")
    private List<Email> emails;

    private String title;
    private LocalDate birthDate;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    private LocalDate joiningDate;
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
    private Programme programme;

    @ManyToOne
    private Faculty supervisor;

    @OneToMany(mappedBy = "supervisor", cascade = CascadeType.PERSIST)
//    @JoinColumn(name = "supervisor")
    private List<Faculty> faculty = new ArrayList<>();

    @OneToMany(mappedBy = "faculty",cascade = CascadeType.ALL)
    private List<Lecturer_Course> lecturer_courses = new ArrayList<>();


    @OneToOne(cascade = CascadeType.ALL)
    private User user;

}
