package gr.gt.gvapi.rest;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import gr.gt.gvapi.service.StatisticsService;

@RestController
@RequestMapping(path = "/statistics")
public class StatisticsController {

    @Autowired
    private StatisticsService statisticsService;

    @GetMapping(value = "/mostRecentPerUser", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<?>> mostRecentPerUser(@RequestParam("user") String user,
            @RequestParam("dataSource") String dataSource, @RequestParam("type") String type,
            @RequestParam(value = "limit", defaultValue = "10") Integer limit) {
        return ResponseEntity.ok()
                .body(statisticsService.mostRecentPerUser(user, dataSource, type, limit));
    }
}
