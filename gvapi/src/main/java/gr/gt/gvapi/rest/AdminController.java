package gr.gt.gvapi.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.google.common.collect.Lists;
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

    // word2vec with labels
    @GetMapping(value = "/w2v-labels", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> w2vLabels() {

        w2vService.run();
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

        int thread_num = 20;

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

        // String name = "4newsgr";
        // String name = "actorbrianwhite";
        // CouchDBEntity ce = couchDBService.find(name);
        //
        // List<File> fileList = new ArrayList<>();
        // fileList = fileService.downloadFiles(ce.getMedia_list(), name);
        // List<Long> fileIds = fileList.stream().map(File::getId).collect(Collectors.toList());
        // GoogleApiDto ga = new GoogleApiDto();
        // ga.setIdList(fileIds);
        // googleApiService.sendListToGoogle(ga);

        // ---------------------------------------------------------------
        // ExecutorService executorService = Executors.newFixedThreadPool(thread_num);
        // List<List<String>> chunksList = Lists.partition(ce.getMedia_list(), thread_num);
        // System.out.println("List size: " + ce.getMedia_list().size());
        //
        // for (int i = 0; i < thread_num; i++) {
        // int jj = i;
        // executorService.submit(() -> {
        // System.out.println("Chunk size" + chunksList.get(jj).size());
        // List<File> fileList = new ArrayList<>();
        // fileList = fileService.downloadFiles(chunksList.get(jj), name);
        //
        // List<Long> fileIds =
        // fileList.stream().map(File::getId).collect(Collectors.toList());
        // GoogleApiDto ga = new GoogleApiDto();
        // ga.setIdList(fileIds);
        // googleApiService.sendListToGoogle(ga);
        // });
        // }
        // ---------------------------------------------------------------

        System.out.println("Saving Tweets");
        // tweetService.saveTweet(ce.getTweet_list(), name);

        // List<Long> fileIds = fileService.getFileListNotSent().stream().map(File::getId)
        // .collect(Collectors.toList());


        return ResponseEntity.ok().build();
    }
}
