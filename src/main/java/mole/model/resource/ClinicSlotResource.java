package mole.model.resource;

import mole.model.dao.ClinicSlot;
import mole.controller.ClinicSlotController;
import mole.controller.UserController;
import org.springframework.hateoas.ResourceSupport;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

public class ClinicSlotResource extends ResourceSupport {
    private final ClinicSlot clinicSlot;

    public ClinicSlotResource(ClinicSlot clinicSlot) {
        this.clinicSlot = clinicSlot;
        this.add(linkTo(methodOn(ClinicSlotController.class).getClinicSlotById(clinicSlot.getClinicSlotId())).withSelfRel());

        Long createdBy = clinicSlot.getCreatedBy();
        this.add(linkTo(methodOn(UserController.class).getUserById(createdBy)).withRel("createdByUser"));

        Long modifiedBy = clinicSlot.getModifiedBy();
        this.add(linkTo(methodOn(UserController.class).getUserById(modifiedBy)).withRel("modifiedByUser"));
    }

    public ClinicSlot getContent() {
        return clinicSlot;
    }

}
