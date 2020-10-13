package de.v3.fakeflix.controller;

import de.v3.fakeflix.dto.LoginDataDTO;
import de.v3.fakeflix.dto.UserDTO;
import de.v3.fakeflix.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping( value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> login(@RequestBody LoginDataDTO loginData) {

        if (userService.checkData(loginData)) {
            return new ResponseEntity<>(userService.getUser(loginData),HttpStatus.OK);
        }

        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }
}
