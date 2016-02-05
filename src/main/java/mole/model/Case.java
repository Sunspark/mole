package mole.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.OffsetDateTime;

@Entity
public class Case {
    @Id
    @GeneratedValue
    private long caseId;

    private long agencyId;
    private long claimantId;
    private String agencyRefCode;

    private long createdBy;
    private long modifiedBy;
    private OffsetDateTime created;
    private OffsetDateTime modified;
    private boolean deleted = false;

    protected Case() {}  // non-parameter constructor for the magic stuff

    public long getCaseId() {
        return caseId;
    }

    public long getAgencyId() {
        return agencyId;
    }

    public long getClaimantId() {
        return claimantId;
    }

    public String getAgencyRefCode() {
        return agencyRefCode;
    }

    public long getCreatedBy() {
        return createdBy;
    }

    public long getModifiedBy() {
        return modifiedBy;
    }

    public OffsetDateTime getCreated() {
        return created;
    }

    public OffsetDateTime getModified() {
        return modified;
    }

    public boolean isDeleted() {
        return deleted;
    }
}
