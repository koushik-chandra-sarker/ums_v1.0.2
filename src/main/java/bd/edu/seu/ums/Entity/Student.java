package bd.edu.seu.ums.Entity;

import bd.edu.seu.ums.Entity.*;
import bd.edu.seu.ums.Enum.Gender;
import bd.edu.seu.ums.Enum.StudentType;
import com.fasterxml.jackson.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
/*@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")*/
@JsonPropertyOrder({"id", "firstName", "middleName", "lastName", "emails", "phones", "birthDate",
        "gender", "yearEnrolled", "bloodGroup", "maritalStatus", "religion", "nationality", "title",
        "addresses", "fatherName", "fatherOccupation", "fatherPhoneNo", "motherName", "motherOccupation",
        "motherPhoneNo", "educations", "programme", "Reg_courses", "user"})
public class Student {

    @Id
    private String id;
    private String firstName;
    private String middleName;
    private String lastName;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "student_id")
    private List<Email> emails;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "student_id")
    private List<Phone> phones = new ArrayList<>();

    private LocalDate birthDate;

    @Enumerated(EnumType.STRING)
    private Gender gender;
    private int yearEnrolled;
    private String bloodGroup;
    private String maritalStatus;
    private String religion;
    private String nationality;

//    @Enumerated(EnumType.STRING) // REGULAR,WEEKEND
//    private StudentType type;
    private String type;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "student_id")
    private List<Address> addresses;

    private String fatherName;
    private String fatherOccupation;
    private String fatherPhoneNo;

    private String motherName;
    private String motherOccupation;
    private String motherPhoneNo;


    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "student_id")
    private List<Education> educations;



    @ManyToOne
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "code")
    @JsonIdentityReference(alwaysAsId = true)
    @JsonProperty("programmeCode")
    private Programme programme;

/*
    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Course_Student> Reg_courses = new ArrayList<>();
*/
    @OneToOne(cascade = CascadeType.ALL,orphanRemoval = true,fetch = FetchType.EAGER)
    @JsonIgnore
    private User user;




}
