package gr.gt.gvapi.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import gr.gt.gvapi.entity.File;
import gr.gt.gvapi.service.FileService;

@RestController
@RequestMapping(path = "/file")
public class FileController {
	
	@Autowired
	private FileService fileService;
	
	@PostMapping("/upload")
	public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile inputFile) {
		
		File file =  new File();
		file.setName(inputFile.getOriginalFilename());
		fileService.persist(file);
		
		return ResponseEntity.ok().build();
	}

}
