package mole.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class ClinicSlot {
    @Id
    @GeneratedValue
    private long clinicSlotId;

    private long clinicId, agencyId, claimantId, clinicSlotOutcomeId;
    private Date datetime;

}
