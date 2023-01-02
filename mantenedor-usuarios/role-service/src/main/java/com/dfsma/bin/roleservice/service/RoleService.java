package com.dfsma.bin.roleservice.service;

import com.dfsma.bin.roleservice.pojo.RoleRequest;
import com.dfsma.bin.roleservice.pojo.RoleUserRequest;
import com.dfsma.bin.roleservice.pojo.response.ResponseMessage;
import org.springframework.http.ResponseEntity;

public interface RoleService {
    ResponseEntity<ResponseMessage> save(RoleRequest roleRequest) throws Exception;
    ResponseEntity<ResponseMessage> update(RoleRequest roleRequest) throws Exception;
    ResponseEntity<ResponseMessage> delete(String idRole) throws Exception;
    ResponseEntity<ResponseMessage> addRoleToUser(RoleUserRequest roleUserRequest) throws Exception;
}
