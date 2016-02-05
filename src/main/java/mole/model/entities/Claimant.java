package mole.model.entities;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.OffsetDateTime;

@Entity
public class Claimant {
    @Id
    @GeneratedValue
    private Long claimantId;

    private String firstName;
    private String lastName;
    private OffsetDateTime dob;
    private OffsetDateTime doa;

    private Long createdBy;
    private Long modifiedBy;
    private OffsetDateTime created;
    private OffsetDateTime modified;
    private Boolean deleted = false;
}
