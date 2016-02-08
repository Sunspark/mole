package mole.model.dao;

import javax.persistence.*;
import java.time.OffsetDateTime;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue
    private Long userId;

    private Long power;
    private String email;
    private String firstName;
    private String lastName;
    private String password;

    private Long createdBy;
    private Long modifiedBy;
    private OffsetDateTime created;
    private OffsetDateTime modified;
    private Boolean deleted = false;

    protected User() {}  // non-parameter constructor for the magic stuff

    // constructor for demo stuff
    public User(String firstName, String lastName){
        this.firstName = firstName;
        this.lastName = lastName;
    }

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

    public OffsetDateTime getCreated() {
        return created;
    }

    public OffsetDateTime getModified() {
        return modified;
    }

    public Boolean isDeleted() {
        return deleted;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    // override toString to make it 'pretty'
    @Override
    public String toString() {
        return String.format(
            "User [id=%d, firstName='%s', lastName='%s']",
            userId, firstName, lastName
        );
    }
}
