package mole.model.entities;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.OffsetDateTime;

@Entity
public class Clinic {
    @Id
    @GeneratedValue
    private Long clinicId;

    private Long clinicLocationId;
    private OffsetDateTime date;

    private Long createdBy;
    private Long modifiedBy;
    private OffsetDateTime created;
    private OffsetDateTime modified;
    private Boolean deleted = false;
}