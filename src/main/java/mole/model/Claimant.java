package mole.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Claimant {
    @Id
    @GeneratedValue
    private long claimantId;

    private String firstName, lastName;
    private Date dob, doa;
}
