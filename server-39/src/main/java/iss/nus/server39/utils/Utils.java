package iss.nus.server39.utils;

import java.io.StringReader;
import java.time.LocalDateTime;
import java.time.ZoneId;

import org.bson.Document;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import iss.nus.server39.models.Character;
import iss.nus.server39.models.Comment;

public class Utils {
    
    public static JsonObject toJson(String json) {
        JsonReader jsonReader = Json.createReader(new StringReader(json));
        JsonObject data = jsonReader.readObject();
        jsonReader.close();
        return data;
    }


    public static Character toCharacter(JsonObject json) {
        Character character = new Character();

        character.setId(json.getInt("id")+"");
        character.setName(json.getString("name"));
        character.setDescription(json.getString("description"));
        character.setImageUrl("%s.%s"
                .formatted(json.getJsonObject("thumbnail").getString("path"),
                    json.getJsonObject("thumbnail").getString("extension"))
                    );

        return character;
    }

    public static JsonObject toJson(Character character) {
        return Json.createObjectBuilder()
                .add("id", character.getId())
                .add("name", character.getName())
                .add("description",  character.getDescription())
                .add("imageUrl", character.getImageUrl())
                .build();
    }

    public static Document toDocument(Comment comment) {
        return new Document()
                    .append("id", comment.getId())
                    .append("comment", comment.getComment())
                    .append("timestamp", comment.getTimestamp());
    }

    public static Comment toComment(Document doc) {
        Comment comment = new Comment();

        comment.setId(doc.getString("id"));
        comment.setComment(doc.getString("comment"));
        comment.setTimestamp(LocalDateTime.ofInstant(doc.getDate("timestamp").toInstant(), ZoneId.systemDefault()));

        return comment;
    }
}
