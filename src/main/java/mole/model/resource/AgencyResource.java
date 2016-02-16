package mole.model.resource;

import mole.model.dao.Agency;
import mole.controller.AgencyController;
import mole.controller.UserController;
import org.springframework.hateoas.ResourceSupport;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

public class AgencyResource extends ResourceSupport {
    private final Agency agency;

    public AgencyResource(Agency agency) {
        this.agency = agency;
        this.add(linkTo(methodOn(AgencyController.class).getAgencyById(agency.getAgencyId())).withSelfRel());

        Long createdBy = agency.getCreatedBy();
        this.add(linkTo(methodOn(UserController.class).getUserById(createdBy)).withRel("createdByUser"));

        Long modifiedBy = agency.getModifiedBy();
        this.add(linkTo(methodOn(UserController.class).getUserById(modifiedBy)).withRel("modifiedByUser"));
    }

    public Agency getContent() {
        return agency;
    }

}
