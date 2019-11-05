package gr.gt.gvapi.rest;

import java.net.MalformedURLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import gr.gt.gvapi.dto.FileDto;
import gr.gt.gvapi.dto.GoogleApiDto;
import gr.gt.gvapi.entity.File;
import gr.gt.gvapi.service.GoogleApiService;

@RestController
@RequestMapping(path = "/googleapi")
public class GoogleApiController {
	
	@Autowired
	private GoogleApiService googleApiService;
	
	@PostMapping(value="/send", produces=MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> send(@RequestBody GoogleApiDto googleApiDto) throws MalformedURLException {
		File file = googleApiService.sendToGoogle(googleApiDto);
		return ResponseEntity.ok().body(new FileDto(file.getId(), file.getName(), file.getSent()));
	}

}
