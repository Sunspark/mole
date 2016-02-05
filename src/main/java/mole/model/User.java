package mole.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.OffsetDateTime;

@Entity
public class User {

    @Id
    @GeneratedValue
    private long userId;
    private long power;
    private String email;
    private String firstName;
    private String lastName;
    private String password;

    private long createdBy;
    private long modifiedBy;
    private OffsetDateTime created;
    private OffsetDateTime modified;
    private boolean deleted = false;

    protected User() {}  // non-parameter constructor for the magic stuff

    // constructor for demo stuff
    public User(String firstName, String lastName){
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public long getUserId() {
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

    public long getPower() {
        return power;
    }

    public String getPassword() {
        return password;
    }

    public long getCreatedBy() {
        return createdBy;
    }

    public long getModifiedBy() {
        return modifiedBy;
    }

    public OffsetDateTime getCreated() {
        return created;
    }

    public OffsetDateTime getModified() {
        return modified;
    }

    public boolean isDeleted() {
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
