package com.dfsma.bin.roleservice.service;

import com.dfsma.bin.roleservice.pojo.RoleRequest;
import com.dfsma.bin.roleservice.pojo.RoleUserRequest;
import com.dfsma.bin.roleservice.pojo.response.ResponseMessage;
import org.springframework.http.ResponseEntity;

public class RoleServiceImpl implements RoleService {


    @Override
    public ResponseEntity<ResponseMessage> save(RoleRequest roleRequest) throws Exception {
        return null;
    }

    @Override
    public ResponseEntity<ResponseMessage> update(RoleRequest roleRequest) throws Exception {
        return null;
    }

    @Override
    public ResponseEntity<ResponseMessage> delete(String idRole) throws Exception {
        return null;
    }

    @Override
    public ResponseEntity<ResponseMessage> addRoleToUser(RoleUserRequest roleUserRequest) throws Exception {
        return null;
    }
}
