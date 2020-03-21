package gr.gt.gvapi.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import gr.gt.gvapi.dto.GoogleApiDto;
import gr.gt.gvapi.dto.UserDto;
import gr.gt.gvapi.entity.CouchDBEntity;
import gr.gt.gvapi.entity.File;
import gr.gt.gvapi.service.CouchDBService;
import gr.gt.gvapi.service.FileService;
import gr.gt.gvapi.service.GoogleApiService;
import gr.gt.gvapi.service.TweetService;
import gr.gt.gvapi.service.UserService;
import gr.gt.gvapi.service.W2VService;
import gr.gt.gvapi.utils.ThematicCategories;

@RestController
@RequestMapping(path = "/admin")
public class AdminController {

    @Autowired
    private CouchDBService couchDBService;

    @Autowired
    private UserService userService;

    @Autowired
    private FileService fileService;

    @Autowired
    private TweetService tweetService;

    @Autowired
    private GoogleApiService googleApiService;

    @Autowired
    private W2VService w2vService;

    @GetMapping(value = "/setTags", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> setTags() {

        userService.setTags();
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/setParty", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> setParty() {

        userService.setParty();
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/saveFinalDescription", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveFinalDescription() {

        googleApiService.saveFinalDescription();
        return ResponseEntity.ok().build();
    }

    // word2vec with labels
    @GetMapping(value = "/w2v-labels", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> w2vLabels(@RequestParam Integer clusters,
            @RequestParam Integer iterations) {

        w2vService.run(clusters, iterations);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/w2v-ocr-labels-sim", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> w2vOCRLabelsSimilarity() {

        w2vService.w2vOCRLabelsSimilarity();
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/w2v-ocr-tweets-sim", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> w2vOCRTweetsSimilarity() {

        w2vService.w2vOCRTweetsSimilarity();
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/couchdb", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> couchdb() {

        CouchDBEntity ce = couchDBService.find("4newsgr");
        System.out.println(ce.getHashtag_list());

        return ResponseEntity.ok().build();
    }

    // Gerasimos users
    @GetMapping(value = "/addUsers", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addUsers() {

        Map<String, List<String>> usersMap = ThematicCategories.getCategoriesMap();

        for (String name : usersMap.keySet()) {
            UserDto userDto = new UserDto();
            userDto.setName(name);
            userService.add(userDto);
        }

        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/handleFilesGoogle", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> handleFilesGoogle() {

        // int thread_num = 20;

        List<String> skipList = new ArrayList<String>();
        skipList.add("avramopoulos");

        Map<String, List<String>> usersMap = ThematicCategories.getCategoriesMap();

        for (String name : usersMap.keySet()) {
            if (skipList.contains(name))
                continue;

            List<File> fileList = new ArrayList<>();

            List<String> categories = usersMap.get(name);

            // run only politics
            if (!categories.contains("Politics"))
                continue;


            System.out.println("::-------------------------------");
            System.out.println("RUNNING " + name);
            System.out.println("::-------------------------------");
            CouchDBEntity ce = couchDBService.find(name);
            List<String> mediaList =
                    ce.getMedia_list().size() > 500 ? ce.getMedia_list().subList(0, 500)
                            : ce.getMedia_list();
            fileList = fileService.downloadFiles(mediaList, name);
            // tweetService.saveTweet(ce.getTweet_list(), name);
            List<Long> fileIds = fileList.stream().map(File::getId).collect(Collectors.toList());
            GoogleApiDto ga = new GoogleApiDto();
            ga.setIdList(fileIds);
            googleApiService.sendListToGoogle(ga);
        }

        System.out.println("Saving Tweets");
        // tweetService.saveTweet(ce.getTweet_list(), name);

        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/saveTweets", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveTweets() {

        Map<String, List<String>> usersMap = ThematicCategories.getCategoriesMap();

        for (String name : usersMap.keySet()) {


            List<String> categories = usersMap.get(name);

            // run only politics
            if (!categories.contains("Politics"))
                continue;

            System.out.println("::-------------------------------");
            System.out.println("RUNNING " + name);
            System.out.println("::-------------------------------");

            CouchDBEntity ce = couchDBService.find(name);

            System.out.println("Saving Tweets");
            tweetService.saveTweet(ce.getTweet_list(), name);
        }

        return ResponseEntity.ok().build();
    }
}
