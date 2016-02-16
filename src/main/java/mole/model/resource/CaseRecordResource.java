package mole.model.resource;

import mole.controller.*;
import mole.model.dao.CaseRecord;
import org.springframework.hateoas.ResourceSupport;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

public class CaseRecordResource extends ResourceSupport {
    private final CaseRecord caseRecord;

    public CaseRecordResource(CaseRecord caseRecord) {
        this.caseRecord = caseRecord;
        this.add(linkTo(methodOn(CaseRecordController.class).getCaseRecordById(caseRecord.getCaseRecordId())).withSelfRel());

        Long caseId = caseRecord.getCaseId();
        this.add(linkTo(methodOn(CaseController.class).getCaseById(caseId)).withRel("case"));

        Long recordTypeId = caseRecord.getRecordTypeId();
        this.add(linkTo(methodOn(RecordTypeController.class).getRecordTypeById(recordTypeId)).withRel("recordType"));

        Long recordStatusId = caseRecord.getRecordStatusId();
        this.add(linkTo(methodOn(RecordStatusController.class).getRecordStatusById(recordStatusId)).withRel("recordStatus"));

        Long createdBy = caseRecord.getCreatedBy();
        this.add(linkTo(methodOn(UserController.class).getUserById(createdBy)).withRel("createdByUser"));

        Long modifiedBy = caseRecord.getModifiedBy();
        this.add(linkTo(methodOn(UserController.class).getUserById(modifiedBy)).withRel("modifiedByUser"));
    }

    public CaseRecord getContent() {
        return caseRecord;
    }

}
