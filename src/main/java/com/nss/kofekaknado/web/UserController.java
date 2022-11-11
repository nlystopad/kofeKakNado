package com.nss.kofekaknado.web;

import com.nss.kofekaknado.domain.Users;
import com.nss.kofekaknado.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    @Operation(summary = "This is endpoint to create new user", description = "create request to add new user", tags = {"Users"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User was successfully created"),
            @ApiResponse(responseCode = "400", description = "User with such phone number already exist"),
            @ApiResponse(responseCode = "400", description = "Password should not be empty")
    })
    public Users createUser(@RequestBody Users users) {
        log.info("createUser() - started");
        Users created = userService.create(users);
        log.info("createUser() - ended,new id = {}", created.getId());
        return created;
    }

    @GetMapping("/signIn")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "This is endpoint to login for existed user", description = "read request to get user's data", tags = {"Users"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User was successfully logged in"),
            @ApiResponse(responseCode = "400", description = "User with such phone number doesn't exist")
    })
    public Users login(@RequestBody String phoneNumber) {
        log.info("login() - started, phone number to check = {}", phoneNumber);
        Users login = userService.login(phoneNumber);
        log.info("login() - ended");
        return login;
    }


    @PatchMapping("/delete/{number}`")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "This is endpoint to delete existed user", description = "update request to delete existed user", tags = {"Users"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User was successfully deleted"),
            @ApiResponse(responseCode = "400", description = "User with such phone number doesn't exist"),
    })
    public Users removeByPhoneNumber(@PathVariable String number) {
        log.info("removeByPhoneNumber() - started, number = {}", number);
        Users deleted = userService.removeByPhoneNumber(number);
        log.info("removeByPhoneNumber() - ended, id = {}", deleted.getId());
        return deleted;
    }

    @Operation(summary = "test endpoint", description = "test endpoint, will be deleted later")
    @GetMapping("/test")
    public List<Users> testUser() {
        return userService.getAll();
    }


}
