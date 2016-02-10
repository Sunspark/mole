package mole.model.dao;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.OffsetDateTime;

@Entity
@Table(name = "comments")
@Data
public class Comment {
    @Id
    @GeneratedValue
    private Long commentId;

    private Long caseId;
    private String commentText;

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
