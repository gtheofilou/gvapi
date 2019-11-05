package gr.gt.gvapi.dto;

public class FileDto {

	private Long id;
	private String name;
	private Boolean sent;
	

	public FileDto(Long id, String name, Boolean sent) {
		this.id = id;
		this.name = name;
		this.sent = sent;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Boolean getSent() {
		return sent;
	}
}
