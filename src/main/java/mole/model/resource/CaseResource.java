package mole.model.resource;

import mole.controller.AgencyController;
import mole.controller.ClaimantController;
import mole.model.dao.Case;
import mole.controller.CaseController;
import mole.controller.UserController;
import mole.model.repositories.CaseRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.ResourceSupport;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

public class CaseResource extends ResourceSupport {
    private final Case aCase;
    private Long recordsReceivedTotal;
    private Long recordsRequiredTotal;

    @Autowired
    private CaseRecordRepository caseRecordRepository;

    public CaseResource(Case aCase) {
        this.aCase = aCase;
        this.add(linkTo(methodOn(CaseController.class).getCaseById(aCase.getCaseId())).withSelfRel());

        this.recordsReceivedTotal = 4L;
        this.recordsRequiredTotal = 5L;

        // include case record query handler
        // do a count query x2
        // countByCaseIdAndRecordStatus 3 is good, anything else is bad
        // assign values
        Long goodStatus = 3L;
        Long fish = caseRecordRepository.countByCaseIdAndRecordStatusId(aCase.getCaseId(), goodStatus);

        // include report query handler
        // get max report on this case by date
        // assign values

        Long agencyId = aCase.getAgencyId();
        this.add(linkTo(methodOn(AgencyController.class).getAgencyById(agencyId)).withRel("agency"));

        Long cliaimantId = aCase.getClaimantId();
        this.add(linkTo(methodOn(ClaimantController.class).getClaimantById(cliaimantId)).withRel("cliaimant"));

        Long createdBy = aCase.getCreatedBy();
        this.add(linkTo(methodOn(UserController.class).getUserById(createdBy)).withRel("createdByUser"));

        Long modifiedBy = aCase.getModifiedBy();
        this.add(linkTo(methodOn(UserController.class).getUserById(modifiedBy)).withRel("modifiedByUser"));
    }

    public Case getContent() {
        return aCase;
    }

    public Long getRecordsReceivedTotal() {
        return recordsReceivedTotal;
    }

    public Long getRecordsRequiredTotal() {
        return recordsRequiredTotal;
    }
}
