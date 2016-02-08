package mole.model.dao;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.OffsetDateTime;

@Entity
@Table(name = "clinic_slot_outcomes")
public class ClinicSlotOutcome {
    @Id
    @GeneratedValue
    private Long clinicSlotOutcomeId;

    private String code;
    private String longName;

    private Long createdBy;
    private Long modifiedBy;
    private OffsetDateTime created;
    private OffsetDateTime modified;
    private Boolean deleted = false;

}

