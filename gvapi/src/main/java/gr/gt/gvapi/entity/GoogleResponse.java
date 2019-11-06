package gr.gt.gvapi.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(	name = "GoogleResponse",
	indexes = @Index(columnList = "fileId")
)
public class GoogleResponse {

	@Id
	@GeneratedValue
	private Long id;
	
	@NotNull
	private Long fileId;
	
	@NotNull
	private String description;
	
	@NotNull
	private Float score;

	public GoogleResponse() {}

	public GoogleResponse(@NotNull Long fileId, @NotNull String description, @NotNull Float score) {
		super();
		this.fileId = fileId;
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
