package mole.model.resource;

import mole.model.dao.Case;
import mole.controller.CaseController;
import mole.controller.UserController;
import org.springframework.hateoas.ResourceSupport;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

public class CaseResource extends ResourceSupport {
    private final Case aCase;

    public CaseResource(Case aCase) {
        this.aCase = aCase;
        this.add(linkTo(methodOn(CaseController.class).getCaseById(aCase.getCaseId())).withSelfRel());

        Long createdBy = aCase.getCreatedBy();
        this.add(linkTo(methodOn(UserController.class).getUserById(createdBy)).withRel("createdByUser"));

        Long modifiedBy = aCase.getModifiedBy();
        this.add(linkTo(methodOn(UserController.class).getUserById(modifiedBy)).withRel("modifiedByUser"));
    }

    public Case getContent() {
        return aCase;
    }

}
