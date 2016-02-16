package mole.model.resource;

import mole.model.dao.ClinicLocation;
import mole.controller.ClinicLocationController;
import mole.controller.UserController;
import org.springframework.hateoas.ResourceSupport;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

public class ClinicLocationResource extends ResourceSupport {
    private final ClinicLocation clinicLocation;

    public ClinicLocationResource(ClinicLocation clinicLocation) {
        this.clinicLocation = clinicLocation;
        this.add(linkTo(methodOn(ClinicLocationController.class).getClinicLocationById(clinicLocation.getClinicLocationId())).withSelfRel());

        Long createdBy = clinicLocation.getCreatedBy();
        this.add(linkTo(methodOn(UserController.class).getUserById(createdBy)).withRel("createdByUser"));

        Long modifiedBy = clinicLocation.getModifiedBy();
        this.add(linkTo(methodOn(UserController.class).getUserById(modifiedBy)).withRel("modifiedByUser"));
    }

    public ClinicLocation getContent() {
        return clinicLocation;
    }

}
