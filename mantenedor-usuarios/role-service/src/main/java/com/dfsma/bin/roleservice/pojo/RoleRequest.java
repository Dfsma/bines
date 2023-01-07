package com.dfsma.bin.roleservice.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
public class RoleRequest {

    private Long roleId;
    private String roleName;
    private String roleDescription;

}
