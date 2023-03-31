package iss.nus.server39.models;

public class Character {
    private String id;
    private String name;
    private String description;
    private String imageUrl;

    @Override
    public String toString() {
        return "Character [id=" + id + ", name=" + name + ", description=" + description + ", imageUrl=" + imageUrl
                + "]";
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getImageUrl() {
        return imageUrl;
    }
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    
}
