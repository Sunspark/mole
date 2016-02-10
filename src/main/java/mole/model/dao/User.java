package mole.model.dao;
//https://docs.jboss.org/hibernate/stable/annotations/reference/en/html/entity.html

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "users")
@Data
public class User {

    @Id
    @GeneratedValue
    private Long userId;

    @Column(nullable = false, columnDefinition = "TINYINT DEFAULT 0")
    private Long power;
    private String email;
    private String firstName;
    private String lastName;
    @Getter(value = AccessLevel.PRIVATE) private String password;

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


    public String getFullName() {
        return firstName + " " + lastName;
    }

}
