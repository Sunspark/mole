package mole.model.dao;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.OffsetDateTime;
import java.util.Date;

@Entity
@Table(name = "clinic_slots")
public class ClinicSlot {
    @Id
    @GeneratedValue
    private Long clinicSlotId;

    private Long clinicId;
    private Long agencyId;
    private Long claimantId;
    private Long clinicSlotOutcomeId;
    private OffsetDateTime datetime;

    private Long createdBy;
    private Long modifiedBy;
    private OffsetDateTime created;
    private OffsetDateTime modified;
    private Boolean deleted = false;
}
