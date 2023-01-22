package com.dfsma.bin.userservice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

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

    @JoinColumn(name = "ID_USUARIO")
    private String idUsuario;


}
