package mole.model.resource;

import mole.model.dao.ClinicSlotOutcome;
import mole.controller.ClinicSlotOutcomeController;
import mole.controller.UserController;
import org.springframework.hateoas.ResourceSupport;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

public class ClinicSlotOutcomeResource extends ResourceSupport {
    private final ClinicSlotOutcome clinicSlotOutcome;

    public ClinicSlotOutcomeResource(ClinicSlotOutcome clinicSlotOutcome) {
        this.clinicSlotOutcome = clinicSlotOutcome;
        this.add(linkTo(methodOn(ClinicSlotOutcomeController.class).getClinicSlotOutcomeById(clinicSlotOutcome.getClinicSlotOutcomeId())).withSelfRel());

        Long createdBy = clinicSlotOutcome.getCreatedBy();
        this.add(linkTo(methodOn(UserController.class).getUserById(createdBy)).withRel("createdByUser"));

        Long modifiedBy = clinicSlotOutcome.getModifiedBy();
        this.add(linkTo(methodOn(UserController.class).getUserById(modifiedBy)).withRel("modifiedByUser"));
    }

    public ClinicSlotOutcome getContent() {
        return clinicSlotOutcome;
    }

}
