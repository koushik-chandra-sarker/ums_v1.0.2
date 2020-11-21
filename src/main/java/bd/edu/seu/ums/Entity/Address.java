package bd.edu.seu.ums.Entity;

import bd.edu.seu.ums.Enum.AddressType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Enumerated(EnumType.STRING)// PRESENT, PERMANENT
    private AddressType addressType;
    private String streetAddress;
    private String division;
    private String district;
    private String subDistrict;
    private String policeStation;
    private String postOffice;
    private String postCode;
    private String country;
    private String latitude;
    private String longitude;


//    @ManyToOne
//    private Student studentId;

}
