package mole.model.entities;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.OffsetDateTime;

@Entity
public class ReportType {
    @Id
    @GeneratedValue
    private Long reportTypeId;

    private String code;
    private String longName;

    private Long createdBy;
    private Long modifiedBy;
    private OffsetDateTime created;
    private OffsetDateTime modified;
    private Boolean deleted = false;
}