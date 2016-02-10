package mole.controller;

import mole.model.dao.Comment;
import mole.model.repositories.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Comments")
public class CommentController {
    @Autowired
    private CommentRepository commentRepository;

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseEntity<Comment> getCommentById(@PathVariable("id") Long commentId) {
        Comment result = commentRepository.findOne(commentId);
        return result != null ? ResponseEntity.ok(result) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
