package mole.model.resource;

import mole.model.dao.CaseRecord;
import mole.controller.CaseRecordController;
import mole.controller.UserController;
import org.springframework.hateoas.ResourceSupport;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

public class CaseRecordResource extends ResourceSupport {
    private final CaseRecord caseRecord;

    public CaseRecordResource(CaseRecord caseRecord) {
        this.caseRecord = caseRecord;
        this.add(linkTo(methodOn(CaseRecordController.class).getCaseRecordById(caseRecord.getCaseRecordId())).withSelfRel());

        Long createdBy = caseRecord.getCreatedBy();
        this.add(linkTo(methodOn(UserController.class).getUserById(createdBy)).withRel("createdByUser"));

        Long modifiedBy = caseRecord.getModifiedBy();
        this.add(linkTo(methodOn(UserController.class).getUserById(modifiedBy)).withRel("modifiedByUser"));
    }

    public CaseRecord getContent() {
        return caseRecord;
    }

}
