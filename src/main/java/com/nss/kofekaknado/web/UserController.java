package com.nss.kofekaknado.web;

import com.nss.kofekaknado.domain.Users;
import com.nss.kofekaknado.service.UserService;
import com.nss.kofekaknado.utils.dto.UserCredsDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
public class UserController {
    private final UserService userService;

    @PostMapping("/signUp")
    @ResponseStatus(HttpStatus.CREATED)
    public Users createUser(@RequestBody Users users){
        log.info("createUser() - started");
        userService.create(users);
        log.info("createUser() - ended");
        
    }

    @GetMapping("/signIn")
    @ResponseStatus(HttpStatus.OK)
    public Users login() {
        log.info("login() - started");
        log.info("login() - ended");
    }


    @PatchMapping("/delete/{number}`")
    @ResponseStatus(HttpStatus.OK)
    public Users removeByPhoneNumber(@PathVariable String number) {

        userService.removeByPhoneNumber(number);

    }

    @GetMapping("/test")
    public List<Users> testUser(){
        return userService.getAll();
    }


}
