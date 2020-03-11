package gr.gt.gvapi.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "GoogleResponse", indexes = @Index(columnList = "fileId"))
public class GoogleResponse {

    public enum Type {
        LABEL, OCR,
    }

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    private Long fileId;

    @NotNull
    private Type type;

    @NotNull
    @Column(length = 10000)
    private String description;

    @NotNull
    private Float score;

    private Double cosineSim;

    public GoogleResponse() {}

    public GoogleResponse(@NotNull Long fileId, @NotNull Type type, @NotNull String description,
            @NotNull Float score) {
        super();
        this.fileId = fileId;
        this.type = type;
        this.description = description;
        this.score = score;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getFileId() {
        return fileId;
    }

    public void setFileId(Long fileId) {
        this.fileId = fileId;
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

    public Double getCosineSim() {
        return cosineSim;
    }

    public void setCosineSim(Double cosineSim) {
        this.cosineSim = cosineSim;
    }

}
