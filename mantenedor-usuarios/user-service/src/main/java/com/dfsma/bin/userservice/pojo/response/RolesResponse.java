package com.dfsma.bin.userservice.pojo.response;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;


@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class RolesResponse {
    Long roleId;
    String roleName;
    String roleDescription;
}
