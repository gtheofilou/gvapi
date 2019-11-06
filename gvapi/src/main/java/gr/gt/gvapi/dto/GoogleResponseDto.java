package gr.gt.gvapi.dto;

public class GoogleResponseDto {
	
	private String description;
	private Float score;
	
	public GoogleResponseDto(String description, Float score) {
		super();
		this.description = description;
		this.score = score;
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
