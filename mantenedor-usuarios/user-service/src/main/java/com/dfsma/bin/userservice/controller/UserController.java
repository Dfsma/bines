package com.dfsma.bin.userservice.controller;

import com.dfsma.bin.userservice.pojo.UserRequest;
import com.dfsma.bin.userservice.pojo.response.ResponseMessage;
import com.dfsma.bin.userservice.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
@Slf4j
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;

    ResponseMessage response = new ResponseMessage();

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseMessage> createUser(@RequestBody UserRequest userRequest) {

        logger.info("--> Invocando el metodo de creación de usuario");
        ResponseEntity<ResponseMessage> httpResponse;

        try{
            httpResponse = userService.saveUser(userRequest);
        } catch (Exception e) {
            response.ResponseMessage(500, e.getLocalizedMessage());
            httpResponse = new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return httpResponse;
    }

}
