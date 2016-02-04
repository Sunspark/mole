package mole.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class User {

    @Id
    @GeneratedValue
    private long userId;
    private long power, createdBy, modifiedBy;
    private String email, firstName, lastName, password;
    private Date createdDate, modifiedDate;

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

    public long getCreatedBy() {
        return createdBy;
    }

    public long getModifiedBy() {
        return modifiedBy;
    }

    public String getPassword() {
        return password;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public Date getModifiedDate() {
        return modifiedDate;
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
