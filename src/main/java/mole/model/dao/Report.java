package mole.model.dao;


import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
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
    private Timestamp completeDate;
    private Date dueDate;
    private Timestamp sentDate;

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
