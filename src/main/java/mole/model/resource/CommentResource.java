package mole.model.resource;

import com.fasterxml.jackson.annotation.JsonProperty;
import mole.controller.CommentController;
import mole.controller.UserController;
import mole.model.dao.Comment;
import org.springframework.hateoas.ResourceSupport;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;


public class CommentResource extends ResourceSupport {

    private final Comment comment;

    public CommentResource(Comment comment) {
        this.comment = comment;
        this.add(linkTo(methodOn(CommentController.class).getCommentById(comment.getCommentId())).withSelfRel());

        Long createdBy = comment.getCreatedBy();
        this.add(linkTo(methodOn(UserController.class).getUserById(createdBy)).withRel("createdByUser"));

        Long modifiedBy = comment.getModifiedBy();
        this.add(linkTo(methodOn(UserController.class).getUserById(modifiedBy)).withRel("modifiedByUser"));
/*
        Long caseId = comment.getCaseId();
        this.add(linkTo(methodOn(CaseController.class).getCaseById(caseId)).withRel("case"));
*/
    }

    public Comment getContent() {
        return comment;
    }
}