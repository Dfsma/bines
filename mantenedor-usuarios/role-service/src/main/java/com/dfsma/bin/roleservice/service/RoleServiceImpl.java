package com.dfsma.bin.roleservice.service;

import com.dfsma.bin.roleservice.entity.RoleEntity;
import com.dfsma.bin.roleservice.entity.UserEntity;
import com.dfsma.bin.roleservice.entity.UserRolesEntity;
import com.dfsma.bin.roleservice.pojo.RoleRequest;
import com.dfsma.bin.roleservice.pojo.RoleUserRequest;
import com.dfsma.bin.roleservice.pojo.response.ResponseMessage;
import com.dfsma.bin.roleservice.repository.RoleRepository;
import com.dfsma.bin.roleservice.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service("roleService")
public class RoleServiceImpl implements RoleService {

    private static final Logger logger = LoggerFactory.getLogger(RoleServiceImpl.class);

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    ResponseMessage response = new ResponseMessage();

    public RoleServiceImpl(RoleRepository roleRepository, UserRepository userRepository) throws Exception {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
    }
    @Override
    public ResponseEntity<ResponseMessage> save(RoleRequest roleRequest) throws Exception {
        ResponseEntity<ResponseMessage> httpResponse;

        try {
            RoleEntity existRole = roleRepository.findByRoleName(roleRequest.getRoleName());
            if (existRole != null) {
                logger.info("--> El rol ya existe");
                response.ResponseMessage(400, "El rol ya existe.");
                httpResponse = new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            } else {
                logger.info("--> Inicio de guardado de rol");
                logger.info("--> Inicio mapeado de request a entidad");

                RoleEntity roleEntity = new RoleEntity(
                        roleRequest.getRoleName(),
                        roleRequest.getRoleDescription()
                );
                logger.info("--> Fin mapeado de request a entidad");
                roleRepository.save(roleEntity);
                logger.info("--> Rol creado correctamente");
                response.ResponseMessage(200,"Rol creado correctamente");
                httpResponse = new ResponseEntity<>(response, HttpStatus.OK);
                logger.info("--> Fin de guardado de rol");
            }
        }catch (Exception e){
            e.printStackTrace();
            response.ResponseMessage(500, e.getMessage());
            httpResponse = new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return httpResponse;
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
        ResponseEntity<ResponseMessage> httpResponse;

        try {

            logger.info("--> Inicio de agregado de roles a un usuario");
            logger.info("--> Inicio mapeado de request a entidad");

            UserEntity searchedUser = userRepository.findDscEmail(roleUserRequest.getDscEmail());


            UserEntity userEntity = new UserEntity(
                    roleUserRequest.getIdUser(),
                    searchedUser.getFchRegistro(),
                    searchedUser.getCdgUserName(),
                    searchedUser.getDscApellido(),
                    searchedUser.getDscEmail(),
                    searchedUser.getDscImagen(),
                    searchedUser.getDscNombre(),
                    searchedUser.getFlgActivo(),
                    searchedUser.getValPassword(),
                    searchedUser.getIdAplicacion(),
                    roleUserRequest.getRoles()
            );

            logger.info("--> Fin mapeado de request a entidad");
            userRepository.save(userEntity);
            logger.info("--> Asignacion de roles a usuario creado correctamente");
            response.ResponseMessage(200,"Roles asignados correctamente");
            httpResponse = new ResponseEntity<>(response, HttpStatus.OK);
            logger.info("--> Fin de asignacion de roles");

        }catch (Exception e){
            e.printStackTrace();
            response.ResponseMessage(500, e.getMessage());
            httpResponse = new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return httpResponse;
    }
}
