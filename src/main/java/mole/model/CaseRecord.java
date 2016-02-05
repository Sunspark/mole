package mole.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class CaseRecord {
    @Id
    @GeneratedValue
    private long caseRecordId;

    private long caseId, recordTypeId, recordStatusId;
    private String filepath;
}
