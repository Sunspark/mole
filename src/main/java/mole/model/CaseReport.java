package mole.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class CaseReport {
    @Id
    @GeneratedValue
    private long caseReportId;

    private long reportId;

}
