package com.dfsma.bin.roleservice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Set;

@Slf4j
@Getter
@Setter
@Entity
@Table(name = "USER_ROLES")
public class UserRolesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ROLE_ID")
    private Long userRoleId;

    @Column(name = "ROLE_ID")
    private Long roleId;

    @JoinColumn(name = "DSC_EMAIL")
    private String dscEmail;


}
