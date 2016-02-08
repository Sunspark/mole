package mole.model.dao;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.OffsetDateTime;

@Entity
@Table(name = "reports")
public class Report {
    @Id
    @GeneratedValue
    private Long reportId;

    private Long caseId;
    private Long reportTypeId;
    private Long userId;
    private String filepath;
    private OffsetDateTime completeDate;
    private OffsetDateTime dueDate;
    private OffsetDateTime sentDate;

    private Long createdBy;
    private Long modifiedBy;
    private OffsetDateTime created;
    private OffsetDateTime modified;
    private Boolean deleted = false;
}
