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
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@CrossOrigin("*")
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

    @PutMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseMessage> updateUser(@RequestBody UserRequest userRequest){

            logger.info("--> Invocando el metodo de actualización de usuario");
            ResponseEntity<ResponseMessage> httpResponse;

            try{
                httpResponse = userService.updateUser(userRequest);
            } catch (Exception e) {
                response.ResponseMessage(500, e.getLocalizedMessage());
                httpResponse = new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }

            return httpResponse;
    }

    @DeleteMapping(value = "/delete", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseMessage> deleteUser(@RequestBody UserRequest userRequest){

            logger.info("--> Invocando el metodo de eliminación de usuario");
            ResponseEntity<ResponseMessage> httpResponse;

            try{
                httpResponse = userService.deleteUser(userRequest.getDscEmail());
            } catch (Exception e) {
                response.ResponseMessage(500, e.getLocalizedMessage());
                httpResponse = new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }

            return httpResponse;
    }

    @GetMapping(value = "/get", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseMessage> getUserAndRoles(@RequestBody UserRequest userRequest){

            logger.info("--> Invocando el metodo de obtencion de usuario y sus roles");
            ResponseEntity<ResponseMessage> httpResponse;

            try{
                httpResponse = userService.getUserRoles(userRequest.getIdUsuario());
            } catch (Exception e) {
                response.ResponseMessage(500, e.getLocalizedMessage());
                httpResponse = new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }

            return httpResponse;
    }

    @GetMapping(value = "/get-users", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getUsers(){

        logger.info("--> Invocando el metodo de obtencion de usuarios");
        ResponseEntity<?> httpResponse;

        try{
            httpResponse = userService.getUsers();
        } catch (Exception e) {
            response.ResponseMessage(500, e.getLocalizedMessage());
            httpResponse = new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return httpResponse;
    }

}
