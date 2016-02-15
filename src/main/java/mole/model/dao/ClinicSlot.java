package mole.model.dao;


import lombok.Data;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.OffsetDateTime;
import java.util.Date;

@Entity
@DynamicInsert
@Table(name = "clinic_slots")
@Data
public class ClinicSlot {
    @Id
    @GeneratedValue
    private Long clinicSlotId;

    private Long clinicId;
    private Long agencyId;
    private Long claimantId;
    private Long clinicSlotOutcomeId;
    private Timestamp datetime;

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
