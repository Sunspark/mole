package mole.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Case {
    @Id
    @GeneratedValue
    private long caseId;

    private long agencyId,
        claimantId,
        createdBy,
        modifiedBy;
    private String agencyRefCode;
    private Date created,
        modified;
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

    public long getCreatedBy() {
        return createdBy;
    }

    public long getModifiedBy() {
        return modifiedBy;
    }

    public String getAgencyRefCode() {
        return agencyRefCode;
    }

    public Date getCreated() {
        return created;
    }

    public Date getModified() {
        return modified;
    }

    public boolean isDeleted() {
        return deleted;
    }
}
