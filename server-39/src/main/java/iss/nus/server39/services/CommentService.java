package iss.nus.server39.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import iss.nus.server39.models.Comment;
import iss.nus.server39.repositories.MongoRepository;
import iss.nus.server39.utils.Utils;

@Service
public class CommentService {

    @Autowired
    MongoRepository mongoRepo;

    public void insertComment(Comment comment) {

        mongoRepo.insertComment(Utils.toDocument(comment));

        return;
    }

    public List<Comment> getLatestComments() {
        return mongoRepo.getComments();
    }
    
}
