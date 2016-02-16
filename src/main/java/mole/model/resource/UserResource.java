package mole.model.resource;

import mole.controller.UserController;
import mole.model.dao.User;
import org.springframework.hateoas.ResourceSupport;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;



public class UserResource extends ResourceSupport {
    private final User user;

    public UserResource(User user) {
        this.user = user;
        this.add(linkTo(methodOn(UserController.class).getUserById(user.getUserId())).withSelfRel());

        Long createdBy = user.getCreatedBy();
        this.add(linkTo(methodOn(UserController.class).getUserById(createdBy)).withRel("createdByUser"));

        Long modifiedBy = user.getModifiedBy();
        this.add(linkTo(methodOn(UserController.class).getUserById(modifiedBy)).withRel("modifiedByUser"));

    }

    public User getContent() {
        return user;
    }

}
