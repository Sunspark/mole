package mole.model.business;

import lombok.Data;
import mole.model.dao.Comment;
import mole.model.repositories.CommentRepository;
import mole.model.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

@Data
public class BusinessComment {
    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private UserRepository userRepository;

    private Long commentId;
    private Long caseId;

    public BusinessComment getById (Long commentId) {
        return this;
    }

}
