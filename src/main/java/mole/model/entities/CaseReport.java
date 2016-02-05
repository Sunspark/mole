package mole.model.entities;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.OffsetDateTime;

@Entity
public class CaseReport {
    @Id
    @GeneratedValue
    private Long caseReportId;

    private Long reportId;

    private Long createdBy;
    private Long modifiedBy;
    private OffsetDateTime created;
    private OffsetDateTime modified;
    private Boolean deleted = false;

}
