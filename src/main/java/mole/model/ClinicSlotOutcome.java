package mole.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.OffsetDateTime;

@Entity
public class ClinicSlotOutcome {
    @Id
    @GeneratedValue
    private long clinicSlotOutcomeId;

    private String code;
    private String longName;

    private long createdBy;
    private long modifiedBy;
    private OffsetDateTime created;
    private OffsetDateTime modified;
    private boolean deleted = false;

}

