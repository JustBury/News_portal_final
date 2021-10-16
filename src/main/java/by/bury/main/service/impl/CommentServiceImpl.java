package by.bury.main.service.impl;

import by.bury.main.dao.CommentDAO;
import by.bury.main.entity.Comment;
import by.bury.main.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service

public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentDAO commentDAO;

    @Override
    @Transactional
    public void saveComment(Comment comment) {
        commentDAO.saveComment(comment);
    }

    @Override
    @Transactional
    public List<Comment> getComments(int idNews) {
        return commentDAO.getComments(idNews);
    }

    @Override
    @Transactional
    public void deleteComment(int id) {
        commentDAO.deleteComment(id);
    }
}
