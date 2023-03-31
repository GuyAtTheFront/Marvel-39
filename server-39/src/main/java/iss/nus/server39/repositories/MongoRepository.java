package iss.nus.server39.repositories;

import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import iss.nus.server39.models.Comment;
import iss.nus.server39.utils.Utils;

@Repository
public class MongoRepository {

    @Autowired
    MongoTemplate mongoTemplate;

    public void insertComment(Document doc) {
        mongoTemplate.insert(doc, "comments");
        return;
    }

    public List<Comment> getComments() {
        // db.comments.find({}).sort({timestamp: -1}).limit(10);

        Query query = Query.query(new Criteria())
        .with(Sort.by(Sort.Direction.DESC, "timestamp"))
        .limit(10);

        query.fields()
        .exclude("_id");

        List<Document> docs = mongoTemplate.find(query, Document.class, "comments");

        System.out.println(Utils.toComment(docs.get(0)));

        List<Comment> comments = docs.stream().map(x -> Utils.toComment(x)).toList();

        return comments;
    }
    
}
