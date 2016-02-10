package mole.model.dao;
//https://docs.jboss.org/hibernate/stable/annotations/reference/en/html/entity.html

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue
    private Long userId;

    @Column(nullable = false, columnDefinition = "TINYINT DEFAULT 0")
    private Long power;
    private String email;
    private String firstName;
    private String lastName;
    private String password;

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

    protected User() {}  // non-parameter constructor for the magic stuff

    public Long getUserId() {
        return userId;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Long getPower() {
        return power;
    }

    public String getPassword() {
        return password;
    }

    public Long getCreatedBy() {
        return createdBy;
    }

    public Long getModifiedBy() {
        return modifiedBy;
    }

    public Timestamp getCreated() {
        return created;
    }

    public Timestamp getModified() {
        return modified;
    }

    public Boolean isDeleted() {
        return deleted;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

}
