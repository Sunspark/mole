package mole.model.resource;

import mole.model.dao.Clinic;
import mole.controller.ClinicController;
import mole.controller.UserController;
import org.springframework.hateoas.ResourceSupport;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

public class ClinicResource extends ResourceSupport {
    private final Clinic clinic;

    public ClinicResource(Clinic clinic) {
        this.clinic = clinic;
        this.add(linkTo(methodOn(ClinicController.class).getClinicById(clinic.getClinicId())).withSelfRel());

        Long createdBy = clinic.getCreatedBy();
        this.add(linkTo(methodOn(UserController.class).getUserById(createdBy)).withRel("createdByUser"));

        Long modifiedBy = clinic.getModifiedBy();
        this.add(linkTo(methodOn(UserController.class).getUserById(modifiedBy)).withRel("modifiedByUser"));
    }

    public Clinic getContent() {
        return clinic;
    }

}
