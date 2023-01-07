package com.dfsma.bin.roleservice.pojo;

import com.dfsma.bin.roleservice.entity.RoleEntity;
import com.dfsma.bin.roleservice.entity.UserRolesEntity;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
public class RoleUserRequest {
    private String dscEmail;
    private Set<RoleEntity> roles;
}
