package mole.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.OffsetDateTime;

@Entity
public class Report {
    @Id
    @GeneratedValue
    private long reportId;

    private long reportTypeId;
    private long userId;
    private String filepath;
    private OffsetDateTime completeDate;
    private OffsetDateTime dueDate;
    private OffsetDateTime sentDate;

    private long createdBy;
    private long modifiedBy;
    private OffsetDateTime created;
    private OffsetDateTime modified;
    private boolean deleted = false;
}
