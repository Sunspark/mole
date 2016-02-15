package mole.controller;

import mole.model.resource.CommentResource;
import mole.model.repositories.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

@RestController
@RequestMapping("/Comments")
public class CommentController {
    @Autowired
    private CommentRepository commentRepository;

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public CommentResource getCommentById(@PathVariable("id") Long commentId) {
        return new CommentResource(this.commentRepository.findOne(commentId));
    }
}
