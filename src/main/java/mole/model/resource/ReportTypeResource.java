package mole.model.resource;

import mole.model.dao.ReportType;
import mole.controller.ReportTypeController;
import mole.controller.UserController;
import org.springframework.hateoas.ResourceSupport;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

public class ReportTypeResource extends ResourceSupport {
    private final ReportType reportType;

    public ReportTypeResource(ReportType reportType) {
        this.reportType = reportType;
        this.add(linkTo(methodOn(ReportTypeController.class).getReportTypeById(reportType.getReportTypeId())).withSelfRel());

        Long createdBy = reportType.getCreatedBy();
        this.add(linkTo(methodOn(UserController.class).getUserById(createdBy)).withRel("createdByUser"));

        Long modifiedBy = reportType.getModifiedBy();
        this.add(linkTo(methodOn(UserController.class).getUserById(modifiedBy)).withRel("modifiedByUser"));
    }

    public ReportType getContent() {
        return reportType;
    }

}
