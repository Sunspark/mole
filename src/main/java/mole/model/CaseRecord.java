package mole.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.OffsetDateTime;

@Entity
public class CaseRecord {
    @Id
    @GeneratedValue
    private Long caseRecordId;

    private Long caseId;
    private Long recordTypeId;
    private Long recordStatusId;
    private String filepath;

    private Long createdBy;
    private Long modifiedBy;
    private OffsetDateTime created;
    private OffsetDateTime modified;
    private Boolean deleted = false;
}
