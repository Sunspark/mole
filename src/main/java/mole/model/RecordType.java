package mole.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class RecordType {
    @Id
    @GeneratedValue
    private long recordTypeId;

    private String code, longName;
}
