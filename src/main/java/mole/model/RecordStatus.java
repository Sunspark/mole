package mole.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class RecordStatus {
    @Id
    @GeneratedValue
    private long recordStatusId;

    private String code, longName;

}
