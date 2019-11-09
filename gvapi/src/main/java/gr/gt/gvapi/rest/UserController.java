package gr.gt.gvapi.rest;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import gr.gt.gvapi.dto.UserDto;
import gr.gt.gvapi.entity.User;
import gr.gt.gvapi.service.UserService;

@RestController
@RequestMapping(path = "/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "/add", produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> send(@RequestBody UserDto userDto) {
        if (StringUtils.hasText(userDto.getName())) {
            User u = userService.add(userDto);
            return ResponseEntity.ok().body(new UserDto(u.getId(), u.getName()));
        }
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/get", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<UserDto>> getGoogleResponse() {
        return ResponseEntity.ok().body(userService.getUsers().stream()
                .map(x -> new UserDto(x.getId(), x.getName())).collect(Collectors.toList()));
    }
}
