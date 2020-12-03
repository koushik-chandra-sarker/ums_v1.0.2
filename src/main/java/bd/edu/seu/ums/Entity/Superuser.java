package bd.edu.seu.ums.Entity;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity(name = "superuser")
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonPropertyOrder({"id", "firstName", "middleName", "lastName", "emails", "phones",
        "specialization", "description", "position", "addresses"})
public class Superuser {

    @Id
    private String id;
    private String firstName;
    private String middleName;
    private String lastName;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "superuser_id")
    private List<Email> emails;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "superuser_id")
    private List<Phone> phones;

    private String specialization;
    private String description;
    private String position;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "superuser_id")
    private List<Address> addresses;

    @OneToOne(cascade = CascadeType.ALL)
    private User user;
}
