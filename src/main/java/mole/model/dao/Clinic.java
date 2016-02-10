package mole.model.dao;


import lombok.Data;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.OffsetDateTime;

@Entity
@Table(name = "clinics")
@Data
public class Clinic {
    @Id
    @GeneratedValue
    private Long clinicId;

    private Long clinicLocationId;
    private Date clinicDate;

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