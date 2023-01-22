package com.dfsma.bin.userservice.pojo.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserResponse {

    private Long idUsuario;
    private String cdgUserName;
    private String dscApellido;
    private String dscEmail;
    private String dscImagen;
    private String dscNombre;
    @JsonFormat(pattern="dd-MM-yyyy HH:mm:ss")
    private LocalDateTime fchModificacion;
    @JsonFormat(pattern="dd-MM-yyyy HH:mm:ss")
    private LocalDateTime fchRegistro;
    private String flgActivo;

    private List<RolesResponse> roles;

    public UserResponse(Long idUsuario, String cdgUserName, String dscApellido, String dscEmail, String dscImagen, String dscNombre, LocalDateTime fchModificacion, LocalDateTime fchRegistro, String flgActivo, List<RolesResponse> roles) {
        this.idUsuario = idUsuario;
        this.cdgUserName = cdgUserName;
        this.dscApellido = dscApellido;
        this.dscEmail = dscEmail;
        this.dscImagen = dscImagen;
        this.dscNombre = dscNombre;
        this.fchModificacion = fchModificacion;
        this.fchRegistro = fchRegistro;
        this.flgActivo = flgActivo;
        this.roles = roles;
    }
}
