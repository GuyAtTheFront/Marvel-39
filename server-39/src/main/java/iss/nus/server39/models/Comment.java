package iss.nus.server39.models;

import java.time.LocalDateTime;

public class Comment {
    public String id;
    public String comment;
    public LocalDateTime timestamp = LocalDateTime.now();

    @Override
    public String toString() {
        return "Comment [id=" + id + ", comment=" + comment + ", timestamp=" + timestamp + "]";
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getComment() {
        return comment;
    }
    public void setComment(String comment) {
        this.comment = comment;
    }
    public LocalDateTime getTimestamp() {
        return timestamp;
    }
    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }



    
}
