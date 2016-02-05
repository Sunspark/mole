package mole.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Agency {
    @Id
    @GeneratedValue
    private long agencyId;

    private String name;
}

