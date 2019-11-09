package gr.gt.gvapi.dto;

import gr.gt.gvapi.entity.GoogleResponse.Type;

public class GoogleResponseDto {

    private Type type;
    private String description;
    private Float score;

    public GoogleResponseDto(Type type, String description, Float score) {
        super();
        this.type = type;
        this.description = description;
        this.score = score;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Float getScore() {
        return score;
    }

    public void setScore(Float score) {
        this.score = score;
    }

}
