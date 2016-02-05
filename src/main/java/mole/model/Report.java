package mole.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Report {
    @Id
    @GeneratedValue
    private long reportId;

    private long reportTypeId, userId;
    private String filepath;
    private Date completeDate, dueDate, sentDate;
}
