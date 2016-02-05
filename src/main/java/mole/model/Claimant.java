package mole.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.OffsetDateTime;

@Entity
public class Claimant {
    @Id
    @GeneratedValue
    private long claimantId;

    private String firstName;
    private String lastName;
    private OffsetDateTime dob;
    private OffsetDateTime doa;

    private long createdBy;
    private long modifiedBy;
    private OffsetDateTime created;
    private OffsetDateTime modified;
    private boolean deleted = false;
}
