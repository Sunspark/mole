package mole.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.OffsetDateTime;

@Entity
public class CaseRecord {
    @Id
    @GeneratedValue
    private long caseRecordId;

    private long caseId;
    private long recordTypeId;
    private long recordStatusId;
    private String filepath;

    private long createdBy;
    private long modifiedBy;
    private OffsetDateTime created;
    private OffsetDateTime modified;
    private boolean deleted = false;
}
