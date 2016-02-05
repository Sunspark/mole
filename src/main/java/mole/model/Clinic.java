package mole.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Clinic {
    @Id
    @GeneratedValue
    private long clinicId;

    private long clinicLocationId;
    private Date date;
}