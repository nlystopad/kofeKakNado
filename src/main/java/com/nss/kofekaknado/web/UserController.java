package com.nss.kofekaknado.web;

import com.nss.kofekaknado.model.domain.Users;
import com.nss.kofekaknado.model.dto.UserCredsDto;
import com.nss.kofekaknado.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
public class UserController {
    private final UserService userService;
    private final AuthenticationManager authenticationManager;

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
    public ResponseEntity<Users> login(@RequestBody UserCredsDto creds) {
        log.info("login() - started");
        try {
            Authentication authentication = authenticationManager.
                    authenticate(new UsernamePasswordAuthenticationToken(creds.getPhoneNumber(), creds.getPassword()));

            Users user = (Users) authentication.getPrincipal();
            log.info("login() - BadCredentialsException");
            return ResponseEntity.ok()
                    .header(HttpHeaders.AUTHORIZATION)
                    .body(user);
        } catch (BadCredentialsException exception) {
            log.info("login() - BadCredentialsException");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
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
