package mole.controller;

import mole.model.business.BusinessComment;
import mole.model.dao.Comment;
import mole.model.repositories.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;
import org.springframework.stereotype.Controller;

@RestController
@RequestMapping("/Comments")
public class CommentController {
    @Autowired
    private CommentRepository commentRepository;

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseEntity<Comment> getCommentById(@PathVariable("id") Long commentId) {
        Comment result = commentRepository.findOne(commentId);
        result.add(
            linkTo(methodOn(CommentController.class).getCommentById(commentId))
            .withSelfRel()
        );
        return result != null ? ResponseEntity.ok(result) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    /*
    @Autowired
    private BusinessComment businessComment;

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public BusinessComment getCommentById(@PathVariable("id") Long commentId) {
        return businessComment.getById(commentId);
    }


    */


}
