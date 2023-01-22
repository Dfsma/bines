package com.dfsma.bin.roleservice.controller;

import com.dfsma.bin.roleservice.pojo.RoleRequest;
import com.dfsma.bin.roleservice.pojo.RoleUserRequest;
import com.dfsma.bin.roleservice.pojo.response.ResponseMessage;
import com.dfsma.bin.roleservice.service.RoleService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/role")
@Slf4j
public class RoleController {

    private static final Logger logger = LoggerFactory.getLogger(RoleController.class);

    private final RoleService roleService;

    ResponseMessage response = new ResponseMessage();

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseMessage> createUser(@RequestBody RoleRequest roleRequest) {

        logger.info("--> Invocando el metodo de creación de de rol");
        ResponseEntity<ResponseMessage> httpResponse;

        try{
            httpResponse = roleService.save(roleRequest);
        } catch (Exception e) {
            response.ResponseMessage(500, e.getLocalizedMessage());
            httpResponse = new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return httpResponse;
    }

    @PostMapping(value = "/create/user/roles", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseMessage> createUserRoles(@RequestBody RoleUserRequest roleUserRequest) {

        logger.info("--> Invocando el metodo de asignacion de roles a un usuario");
        ResponseEntity<ResponseMessage> httpResponse;

        try{
            httpResponse = roleService.addRoleToUser(roleUserRequest);
        } catch (Exception e) {
            response.ResponseMessage(500, e.getLocalizedMessage());
            httpResponse = new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return httpResponse;
    }

}
