package iss.nus.server39.controllers;

import java.util.List;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import iss.nus.server39.services.CommentService;
import iss.nus.server39.services.MarvelService;
import iss.nus.server39.utils.Utils;
import jakarta.json.Json;
import jakarta.json.JsonArrayBuilder;
import iss.nus.server39.models.Character;
import iss.nus.server39.models.Comment;
import iss.nus.server39.repositories.MongoRepository;


@RestController
@RequestMapping(path="/api")
public class MarvelController {

    @Autowired
    MarvelService marvelService;

    @Autowired
    CommentService commentService;
    
    @GetMapping(path="/characters", produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> test(@RequestParam String name,
                                        @RequestParam(defaultValue = "20") Integer limit, 
                                        @RequestParam(defaultValue = "0") Integer offset) {

        List<Character> characters = marvelService.getCharacters(name, limit, offset);
        
        JsonArrayBuilder arrBuilder = Json.createArrayBuilder();
        for (Character c : characters) {
            arrBuilder.add(Json.createObjectBuilder()
                            .add("id", c.getId())
                            .add("name", c.getName())
                            .build());
        }

        return ResponseEntity.ok(Json.createObjectBuilder()
                                    .add("characters", arrBuilder)
                                    .build().toString());
    }

    @GetMapping(path="character/{id}", produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getDetails(@PathVariable String id) {
        
        String detail = marvelService.getDetails(id);
        List<Comment> comments = commentService.getLatestComments();
        
        JsonArrayBuilder arrBuilder = Json.createArrayBuilder();
        comments.forEach(x -> arrBuilder.add(Utils.toJson(x)));
        
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(Json.createObjectBuilder()
                        .add("details", Utils.toJson(detail))
                        .add("comments", arrBuilder)
                        .build()
                        .toString());
    }

    @PostMapping(path="character/{id}", produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> postComment(@RequestBody String json, @PathVariable String id) {

        Comment comment = new Comment();
        comment.setId(id);
        comment.setComment(Utils.toJson(json).getString("comment"));

        commentService.insertComment(comment);

        return ResponseEntity.ok().build();
    }


    @Autowired
    MongoRepository mongoRepo;

    @GetMapping("/test")
    public ResponseEntity<String> test() {

        mongoRepo.getComments();
        return null;
    }

}
