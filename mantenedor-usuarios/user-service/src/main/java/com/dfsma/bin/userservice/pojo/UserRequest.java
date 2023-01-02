package com.dfsma.bin.userservice.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
public class UserRequest {

    private Long idUsuario;
    private String cdgUserName;
    private String dscApellido;
    private String dscEmail;
    private String dscImagen;
    private String dscNombre;
    private LocalDateTime fchModificacion;
    private LocalDateTime fchRegistro;
    private String flgActivo;
    private String valPassword;
    private Long idAplicacion;
}
