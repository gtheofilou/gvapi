package gr.gt.gvapi.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Tweet")
public class Tweet {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    private Long userId;

    @NotNull
    private String text;

    private Double cosineSim;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Double getCosineSim() {
        return cosineSim;
    }

    public void setCosineSim(Double cosineSim) {
        this.cosineSim = cosineSim;
    }

}
