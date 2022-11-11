package com.nss.kofekaknado.web;

import com.nss.kofekaknado.domain.Users;
import com.nss.kofekaknado.service.UserService;
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
    public Users createUser(@RequestBody Users users) {
        log.info("createUser() - started");
        Users created = userService.create(users);
        log.info("createUser() - ended,new id = {}", created.getId());
        return created;
    }

    @GetMapping("/signIn")
    @ResponseStatus(HttpStatus.OK)
    public Users login(@RequestBody String phoneNumber) {
        log.info("login() - started, phone number to check = {}", phoneNumber);
        Users login = userService.login(phoneNumber);
        log.info("login() - ended");
        return login;
    }


    @PatchMapping("/delete/{number}`")
    @ResponseStatus(HttpStatus.OK)
    public Users removeByPhoneNumber(@PathVariable String number) {
        log.info("removeByPhoneNumber() - started, number = {}", number);
        Users deleted = userService.removeByPhoneNumber(number);
        log.info("removeByPhoneNumber() - ended, id = {}", deleted.getId());
        return deleted;
    }

    @GetMapping("/test")
    public List<Users> testUser() {
        return userService.getAll();
    }


}
