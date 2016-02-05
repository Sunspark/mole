package mole.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.OffsetDateTime;
import java.util.Date;

@Entity
public class ClinicSlot {
    @Id
    @GeneratedValue
    private long clinicSlotId;

    private long clinicId;
    private long agencyId;
    private long claimantId;
    private long clinicSlotOutcomeId;
    private OffsetDateTime datetime;

    private long createdBy;
    private long modifiedBy;
    private OffsetDateTime created;
    private OffsetDateTime modified;
    private boolean deleted = false;
}
