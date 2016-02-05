package mole.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class ClinicLocation {
    @Id
    @GeneratedValue
    private long clinicLocationId;
    private String name;
}
