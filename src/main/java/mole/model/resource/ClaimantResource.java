package mole.model.resource;

import mole.model.dao.Claimant;
import mole.controller.ClaimantController;
import mole.controller.UserController;
import org.springframework.hateoas.ResourceSupport;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

public class ClaimantResource extends ResourceSupport {
    private final Claimant claimant;

    public ClaimantResource(Claimant claimant) {
        this.claimant = claimant;
        this.add(linkTo(methodOn(ClaimantController.class).getClaimantById(claimant.getClaimantId())).withSelfRel());

        Long createdBy = claimant.getCreatedBy();
        this.add(linkTo(methodOn(UserController.class).getUserById(createdBy)).withRel("createdByUser"));

        Long modifiedBy = claimant.getModifiedBy();
        this.add(linkTo(methodOn(UserController.class).getUserById(modifiedBy)).withRel("modifiedByUser"));
    }

    public Claimant getContent() {
        return claimant;
    }

}
