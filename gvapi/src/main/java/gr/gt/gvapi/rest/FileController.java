package gr.gt.gvapi.rest;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import gr.gt.gvapi.dto.FileDto;
import gr.gt.gvapi.entity.File;
import gr.gt.gvapi.service.FileService;

@RestController
@RequestMapping(path = "/file")
public class FileController {

	@Autowired
	private FileService fileService;

	@PostMapping("/upload")
	public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile inputFile) throws IOException {
		File file = fileService.saveFile(inputFile);
		return ResponseEntity.ok().body(new FileDto(file.getId(), file.getName(), file.getSent()));
	}
	
	@GetMapping("/download/{fileName:.+}")
	public ResponseEntity<?> downLoadFile(@PathVariable String fileName) {
		Resource resource = fileService.loadFile(fileName);
		return ResponseEntity.ok().contentType(MediaType.APPLICATION_OCTET_STREAM).body(resource);
	}
	@GetMapping(value="/getFileList", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<File>> getFileList() throws IOException {
		return ResponseEntity.ok().body(fileService.getFileList());
	}

}
