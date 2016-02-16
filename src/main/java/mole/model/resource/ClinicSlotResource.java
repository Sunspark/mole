package mole.model.resource;

import mole.controller.*;
import mole.model.dao.Clinic;
import mole.model.dao.ClinicSlot;
import org.springframework.hateoas.ResourceSupport;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

public class ClinicSlotResource extends ResourceSupport {
    private final ClinicSlot clinicSlot;

    public ClinicSlotResource(ClinicSlot clinicSlot) {
        this.clinicSlot = clinicSlot;
        this.add(linkTo(methodOn(ClinicSlotController.class).getClinicSlotById(clinicSlot.getClinicSlotId())).withSelfRel());

        Long clinicId = clinicSlot.getClinicId();
        this.add(linkTo(methodOn(ClinicController.class).getClinicById(clinicId)).withRel("clinic"));

        Long agencyId = clinicSlot.getAgencyId();
        this.add(linkTo(methodOn(AgencyController.class).getAgencyById(agencyId)).withRel("agency"));

        Long clinicSlotOutcomeId = clinicSlot.getClinicSlotOutcomeId();
        this.add(linkTo(methodOn(ClinicSlotOutcomeController.class).getClinicSlotOutcomeById(clinicSlotOutcomeId)).withRel("clinicSlotOutcome"));

        Long claimantId = clinicSlot.getClaimantId();
        this.add(linkTo(methodOn(ClaimantController.class).getClaimantById(claimantId)).withRel("claimant"));

        Long createdBy = clinicSlot.getCreatedBy();
        this.add(linkTo(methodOn(UserController.class).getUserById(createdBy)).withRel("createdByUser"));

        Long modifiedBy = clinicSlot.getModifiedBy();
        this.add(linkTo(methodOn(UserController.class).getUserById(modifiedBy)).withRel("modifiedByUser"));
    }

    public ClinicSlot getContent() {
        return clinicSlot;
    }

}
