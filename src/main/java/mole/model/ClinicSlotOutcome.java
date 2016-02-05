package mole.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class ClinicSlotOutcome {
    @Id
    @GeneratedValue
    private long clinicSlotOutcomeId;

    private String code, longName;

}

