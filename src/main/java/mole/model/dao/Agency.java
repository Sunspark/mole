package mole.model.dao;


import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "agencies")
@Data
public class Agency {
    @Id
    @GeneratedValue
    private Long agencyId;

    private String name;

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

