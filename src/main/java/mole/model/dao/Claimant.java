package mole.model.dao;


import lombok.Data;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;

@Entity
@Table(name = "claimants")
@Data
public class Claimant {
    @Id
    @GeneratedValue
    private Long claimantId;

    private String firstName;
    private String lastName;

    private Date dob;
    private Date doa;

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
