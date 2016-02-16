package mole.model.resource;

import mole.controller.CaseController;
import mole.controller.ReportTypeController;
import mole.model.dao.Report;
import mole.controller.ReportController;
import mole.controller.UserController;
import org.springframework.hateoas.ResourceSupport;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

public class ReportResource extends ResourceSupport {
    private final Report report;

    public ReportResource(Report report) {
        this.report = report;
        this.add(linkTo(methodOn(ReportController.class).getReportById(report.getReportId())).withSelfRel());

        Long caseId = report.getCaseId();
        this.add(linkTo(methodOn(CaseController.class).getCaseById(caseId)).withRel("case"));

        Long reportTypeId = report.getReportTypeId();
        this.add(linkTo(methodOn(ReportTypeController.class).getReportTypeById(reportTypeId)).withRel("reportType"));

        Long userId = report.getUserId();
        this.add(linkTo(methodOn(UserController.class).getUserById(userId)).withRel("user"));

        Long createdBy = report.getCreatedBy();
        this.add(linkTo(methodOn(UserController.class).getUserById(createdBy)).withRel("createdByUser"));

        Long modifiedBy = report.getModifiedBy();
        this.add(linkTo(methodOn(UserController.class).getUserById(modifiedBy)).withRel("modifiedByUser"));
    }

    public Report getContent() {
        return report;
    }

}
