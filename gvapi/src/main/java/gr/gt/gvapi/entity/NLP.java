package gr.gt.gvapi.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "NLP", indexes = {@Index(columnList = "word"), @Index(columnList = "fileId")})
public class NLP {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    private Long fileId;

    @NotNull
    private String word;

    @NotNull
    private String pos;

    @NotNull
    private Integer rtf; // raw term frequency

    @NotNull
    private Integer twd; // total number of words in doc

    @NotNull
    private Float tf; // rtf/twd

    public NLP() {}

    public NLP(@NotNull Long fileId, @NotNull String word, @NotNull String pos,
            @NotNull Integer rtf, @NotNull Integer twd, @NotNull Float tf) {
        this.fileId = fileId;
        this.word = word;
        this.pos = pos;
        this.rtf = rtf;
        this.twd = twd;
        this.tf = tf;
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

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getPos() {
        return pos;
    }

    public void setPos(String pos) {
        this.pos = pos;
    }

    public Integer getRtf() {
        return rtf;
    }

    public void setRtf(Integer rtf) {
        this.rtf = rtf;
    }

    public Integer getTwd() {
        return twd;
    }

    public void setTwd(Integer twd) {
        this.twd = twd;
    }

    public Float getTf() {
        return tf;
    }

    public void setTf(Float tf) {
        this.tf = tf;
    }

}
