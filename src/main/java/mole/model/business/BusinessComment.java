package mole.model.business;

import lombok.Data;
import mole.model.dao.Comment;
import mole.model.repositories.CommentRepository;
import mole.model.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class BusinessComment {
    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private UserRepository userRepository;



}
