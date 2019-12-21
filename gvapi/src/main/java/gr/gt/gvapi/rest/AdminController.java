package gr.gt.gvapi.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import gr.gt.gvapi.entity.CouchDBEntity;
import gr.gt.gvapi.service.CouchDBService;

@RestController
@RequestMapping(path = "/admin")
public class AdminController {

    @Autowired
    private CouchDBService couchDBService;

    @GetMapping(value = "/couchdb", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> couchdb() {

        CouchDBEntity ce = couchDBService.find("4newsgr");
        System.out.println(ce.getHashtag_list());

        return ResponseEntity.ok().build();
    }
}
