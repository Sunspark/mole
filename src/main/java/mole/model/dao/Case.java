package mole.model.dao;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.OffsetDateTime;

@Entity
@Table(name = "cases")
public class Case {
    @Id
    @GeneratedValue
    private Long caseId;

    private Long agencyId;
    private Long claimantId;
    private String agencyRefCode;

    private Long createdBy;
    private Long modifiedBy;
    private OffsetDateTime created;
    private OffsetDateTime modified;
    private Boolean deleted = false;

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
