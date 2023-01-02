package com.dfsma.bin.userservice.service;

import com.dfsma.bin.userservice.pojo.UserRequest;
import com.dfsma.bin.userservice.pojo.response.ResponseMessage;
import org.springframework.http.ResponseEntity;

public interface UserService {
    ResponseEntity<ResponseMessage> saveUser(UserRequest userRequest) throws Exception;
    ResponseEntity<ResponseMessage> updateUser(UserRequest userRequest) throws Exception;
    ResponseEntity<ResponseMessage> deleteUser(String dscEmail) throws Exception;
}
