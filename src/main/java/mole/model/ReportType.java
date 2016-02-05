package mole.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class ReportType {
    @Id
    @GeneratedValue
    private long reportTypeId;

    private String code, longName;
}