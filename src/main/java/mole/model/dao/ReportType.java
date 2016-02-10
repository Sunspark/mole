package mole.model.dao;


import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.OffsetDateTime;

@Entity
@Table(name = "report_types")
@Data
public class ReportType {
    @Id
    @GeneratedValue
    private Long reportTypeId;

    private String code;
    private String longName;

    @Column(nullable = false)
    private Long createdBy;
    @Column(nullable = false)
    private Long modifiedBy;
    @Column(nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp created;
    @Column(nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp modified;
    @Column(nullable = false, columnDefinition = "BOOLEAN DEFAULT FALSE")
    private Boolean deleted = false;
}