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
    private Long recordsReceivedTotal;
    private Long recordsRequiredTotal;
    private Case aCase;

    public CaseResource() {}

    public CaseResource(Case aCase) {
        this.recordsReceivedTotal = 4L;
        this.recordsRequiredTotal = 5L;

        this.aCase = aCase;
        // link to self is automatically created by assembler class

        Long goodStatus = 3L;
//        Long fish = caseRecordRepository.countByCaseIdAndRecordStatusId(aCase.getCaseId(), goodStatus);

        Long agencyId = aCase.getAgencyId();
        this.add(linkTo(methodOn(AgencyController.class).getAgencyById(agencyId)).withRel("agency"));

        Long cliaimantId = aCase.getClaimantId();
        this.add(linkTo(methodOn(ClaimantController.class).getClaimantById(cliaimantId)).withRel("cliaimant"));

        Long createdBy = aCase.getCreatedBy();
        this.add(linkTo(methodOn(UserController.class).getUserById(createdBy)).withRel("createdByUser"));

        Long modifiedBy = aCase.getModifiedBy();
        this.add(linkTo(methodOn(UserController.class).getUserById(modifiedBy)).withRel("modifiedByUser"));
    }
    /*
    @Autowired
    private CaseRecordRepository caseRecordRepository;


    public CaseResource(Case aCase) {
        this.aCase = aCase;
        this.add(linkTo(methodOn(CaseController.class).getCaseById(aCase.getCaseId())).withSelfRel());

        this.recordsReceivedTotal = 4L;
        this.recordsRequiredTotal = 5L;


        Long agencyId = aCase.getAgencyId();
        this.add(linkTo(methodOn(AgencyController.class).getAgencyById(agencyId)).withRel("agency"));

        Long cliaimantId = aCase.getClaimantId();
        this.add(linkTo(methodOn(ClaimantController.class).getClaimantById(cliaimantId)).withRel("cliaimant"));

        Long createdBy = aCase.getCreatedBy();
        this.add(linkTo(methodOn(UserController.class).getUserById(createdBy)).withRel("createdByUser"));

        Long modifiedBy = aCase.getModifiedBy();
        this.add(linkTo(methodOn(UserController.class).getUserById(modifiedBy)).withRel("modifiedByUser"));
    }
    */

    public Case getContent() {
        return aCase;
    }

    public Long getRecordsReceivedTotal() {
        return recordsReceivedTotal;
    }

    public void setRecordsReceivedTotal(Long recordsReceivedTotal) {
        this.recordsReceivedTotal = recordsReceivedTotal;
    }

    public Long getRecordsRequiredTotal() {
        return recordsRequiredTotal;
    }

    public void setRecordsRequiredTotal(Long recordsRequiredTotal) {
        this.recordsRequiredTotal = recordsRequiredTotal;
    }
}
