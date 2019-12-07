package gr.gt.gvapi.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import gr.gt.gvapi.service.NLPService;

@RestController
@RequestMapping(path = "/nlp")
public class NLPController {

    @Autowired
    private NLPService nlpService;

    @GetMapping(value = "/run", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> runNLP() {
        nlpService.run();
        return ResponseEntity.ok().build();
    }
}
