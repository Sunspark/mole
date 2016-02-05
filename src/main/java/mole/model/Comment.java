package mole.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.OffsetDateTime;

@Entity
public class Comment {
    @Id
    @GeneratedValue
    private long commentId;

    private long caseId;
    private String comment;

    private long createdBy;
    private long modifiedBy;
    private OffsetDateTime created;
    private OffsetDateTime modified;
    private boolean deleted = false;
}
