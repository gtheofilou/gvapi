package gr.gt.gvapi.rest;

import java.net.MalformedURLException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import gr.gt.gvapi.dto.FileDto;
import gr.gt.gvapi.dto.GoogleApiDto;
import gr.gt.gvapi.dto.GoogleResponseDto;
import gr.gt.gvapi.entity.File;
import gr.gt.gvapi.service.GoogleApiService;

@RestController
@RequestMapping(path = "/googleapi")
public class GoogleApiController {

    @Autowired
    private GoogleApiService googleApiService;

    @PostMapping(value = "/send", produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> send(@RequestBody GoogleApiDto googleApiDto)
            throws MalformedURLException {
        List<File> fileList = googleApiService.sendListToGoogle(googleApiDto);
        return ResponseEntity.ok()
                .body(fileList.stream().map(x -> new FileDto(x.getId(), x.getName(), x.getSent())));
    }

    @GetMapping(value = "/get/{fileId:.+}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getGoogleResponse(@PathVariable Long fileId) {
        return ResponseEntity.ok()
                .body(googleApiService.getGoogleResponse(fileId).stream().map(
                        x -> new GoogleResponseDto(x.getType(), x.getDescription(), x.getScore()))
                        .collect(Collectors.groupingBy(GoogleResponseDto::getType)));
    }

}
