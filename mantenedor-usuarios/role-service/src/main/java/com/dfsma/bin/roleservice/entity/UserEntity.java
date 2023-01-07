package com.dfsma.bin.roleservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Slf4j
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "USUARIO")
public class UserEntity implements Serializable {


    private static final long serialVersionUID = 1L;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "DSC_EMAIL"),
            inverseJoinColumns = @JoinColumn(name = "ROLE_ID"))
    private Set<RoleEntity> roles = new HashSet<>();

    @Id
    @Column(name = "DSC_EMAIL")
    private String dscEmail;

    @Column(name = "ID_USUARIO")
    private Long idUsuario;

    @Column(name = "CDG_USERNAME")
    private String cdgUserName;

    @Column(name = "DSC_APELLIDO")
    private String dscApellido;

    @Column(name = "DSC_IMAGEN")
    private String dscImagen;

    @Column(name = "DSC_NOMBRE")
    private String dscNombre;

    @Column(name = "FCH_MODIFICACION")
    private LocalDateTime fchModificacion;

    @Column(name = "FCH_REGISTRO")
    private LocalDateTime fchRegistro;

    @Column(name = "FLG_ACTIVO")
    private String flgActivo;

    @Column(name = "VAL_PASSWORD")
    private String valPassword;

    @Column(name = "ID_APLICACION")
    private Long idAplicacion;

    @Column(name = "DSC_URL_PASS")
    private String dscUrlPass;

    @PrePersist
    public void prePersistFechaCreacion() {
        log.info("--> Seteando fecha de creacion: {}", LocalDateTime.now());
        fchRegistro = LocalDateTime.now();
    }
    @PreUpdate
    public void preUpdateFechaModificacion() {
        log.info("--> Seteando fecha de modificacion: {}", LocalDateTime.now());
        fchModificacion = LocalDateTime.now();
    }

    public UserEntity(LocalDateTime fchRegistro, String cdgUserName, String dscApellido, String dscEmail, String dscImagen, String dscNombre, String flgActivo, String valPassword, Long idAplicacion, Set<RoleEntity> roles) {
        this.fchRegistro = fchRegistro;
        this.cdgUserName = cdgUserName;
        this.dscApellido = dscApellido;
        this.dscEmail = dscEmail;
        this.dscImagen = dscImagen;
        this.dscNombre = dscNombre;
        this.flgActivo = flgActivo;
        this.valPassword = valPassword;
        this.idAplicacion = idAplicacion;
        this.roles = roles;
    }
}
