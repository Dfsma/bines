package com.dfsma.bin.userservice.service;

import com.dfsma.bin.userservice.entity.RoleEntity;
import com.dfsma.bin.userservice.entity.UserEntity;
import com.dfsma.bin.userservice.pojo.UserRequest;
import com.dfsma.bin.userservice.pojo.response.ResponseMessage;
import com.dfsma.bin.userservice.pojo.response.RolesResponse;
import com.dfsma.bin.userservice.pojo.response.UserResponse;
import com.dfsma.bin.userservice.repository.RoleRepository;
import com.dfsma.bin.userservice.repository.UserRepository;
import com.dfsma.bin.userservice.util.PasswordEncrypt;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service("userService")
public class UserImpl implements UserService{

    private static final Logger logger = LoggerFactory.getLogger(UserImpl.class);

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    /*PasswordEncrypt passwordEncrypt = new PasswordEncrypt();*/

    @Autowired
    PasswordEncoder encoder;
    ResponseMessage response = new ResponseMessage();

    public UserImpl(UserRepository userRepository, RoleRepository roleRepository) throws Exception {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public ResponseEntity<ResponseMessage> saveUser(UserRequest userRequest) throws Exception {

        ResponseEntity<ResponseMessage> httpResponse;

        try {
            UserEntity existUser = userRepository.findDscEmail(userRequest.getDscEmail());
            if (existUser != null) {
                logger.info("--> El usuario ya existe");
                response.ResponseMessage(400, "El usuario ya existe.");
                httpResponse = new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            } else {
                logger.info("--> Inicio de guardado de usuario");
                logger.info("--> Inicio mapeado de request a entidad");
                logger.info("--> Inicio de encriptación de contraseña");
                /*String encryptPassword = passwordEncrypt.encrypt(userRequest.getValPassword());*/
                String encryptPassword = encoder.encode(userRequest.getValPassword());
                logger.info("--> Fin de encriptación de contraseña");
                logger.info("--> Contraseña encriptada: {}", encryptPassword);
                UserEntity userEntity = new UserEntity(
                        userRequest.getCdgUserName(),
                        userRequest.getDscApellido(),
                        userRequest.getDscEmail(),
                        userRequest.getDscImagen(),
                        userRequest.getDscNombre(),
                        userRequest.getFlgActivo(),
                        encryptPassword,
                        userRequest.getIdAplicacion()
                );
                /*logger.info("--> Desencriptando contraseña: {}", passwordEncrypt.decrypt(encryptPassword));*/ //TODO: Eliminar esta linea, solo es para probar la desencriptación
                logger.info("--> Fin mapeado de request a entidad");
                userRepository.save(userEntity);
                logger.info("--> Usuario creado correctamente");
                response.ResponseMessage(200,"Usuario creado correctamente");
                httpResponse = new ResponseEntity<>(response, HttpStatus.OK);
                logger.info("--> Fin de guardado de usuario");
            }
        }catch (Exception e){
            e.printStackTrace();
            response.ResponseMessage(500, e.getMessage());
            httpResponse = new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return httpResponse;
    }

    @Override
    public ResponseEntity<ResponseMessage> updateUser(UserRequest userRequest) throws Exception {

        ResponseEntity<ResponseMessage> httpResponse;

        try {
            UserEntity existUser = userRepository.findDscEmail(userRequest.getDscEmail());
            if (existUser == null) {
                logger.info("--> El usuario a actualizar no existe");
                response.ResponseMessage(400, "El usuario a actualizar no existe.");
                httpResponse = new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            } else {
                logger.info("--> Inicio de actualización de usuario");
                logger.info("--> Inicio mapeado de request a entidad");
                logger.info("--> Inicio de encriptación de contraseña");
                String encryptPassword = encoder.encode(userRequest.getValPassword());
                logger.info("--> Fin de encriptación de contraseña");
                UserEntity userEntity = new UserEntity(
                        userRequest.getIdUsuario(),
                        existUser.getFchRegistro(),
                        userRequest.getCdgUserName(),
                        userRequest.getDscApellido(),
                        userRequest.getDscEmail(),
                        userRequest.getDscImagen(),
                        userRequest.getDscNombre(),
                        userRequest.getFlgActivo(),
                        encryptPassword,
                        userRequest.getIdAplicacion()
                );
                logger.info("--> Fin mapeado de request a entidad");
                userRepository.save(userEntity);
                logger.info("--> Usuario actualizado correctamente");
                response.ResponseMessage(200,"Usuario actualizado correctamente");
                httpResponse = new ResponseEntity<>(response, HttpStatus.OK);
                logger.info("--> Fin de actualización de usuario");
            }
        }catch (Exception e){
            e.printStackTrace();
            response.ResponseMessage(500, e.getMessage());
            httpResponse = new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return httpResponse;
    }

    @Override
    public ResponseEntity<ResponseMessage> deleteUser(String dscEmail) throws Exception {
        ResponseEntity<ResponseMessage> httpResponse;


        try {
            UserEntity existUser = userRepository.findDscEmail(dscEmail);
            if (existUser == null) {
                logger.info("--> El usuario a eliminar no existe");
                response.ResponseMessage(400, "El usuario a eliminar no existe.");
                httpResponse = new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            } else {
                logger.info("--> Inicio de eliminacion de usuario");

                userRepository.deleteByDscEmail(dscEmail);
                logger.info("--> Usuario eliminado correctamente");
                response.ResponseMessage(200,"Usuario eliminado correctamente");
                httpResponse = new ResponseEntity<>(response, HttpStatus.OK);
                logger.info("--> Fin de eliminacion de usuario");
            }
        }catch (Exception e){
            e.printStackTrace();
            response.ResponseMessage(500, e.getMessage());
            httpResponse = new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return httpResponse;
    }

    @Override
    public ResponseEntity<ResponseMessage> getUserRoles(Long idUsuario) throws Exception {
        ResponseEntity<ResponseMessage> httpResponse;
        try {
            UserEntity getUser = userRepository.findIdUser(idUsuario);
            if (getUser == null) {
                logger.info("--> El usuario a obtener no existe");
                response.ResponseMessage(400, "El usuario a obtener no existe.");
                httpResponse = new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            } else {
                logger.info("--> Inicio de obtencion de usuario");
                logger.info("--> Usuario obtenido correctamente");
                logger.info("--> Inicio de obtencion de roles");
                List<RoleEntity> roles = roleRepository.findRolesByUser(getUser.getIdUsuario());
                logger.info("--> Roles obtenidos correctamente");
                logger.info("--> Inicio de mapeado de roles a response");
                List<RolesResponse> roleResponsesList = getRolesResponses(roles);
                logger.info("--> Fin de mapeado de roles a response");
                UserResponse userResponse = new UserResponse(
                        getUser.getIdUsuario(),
                        getUser.getCdgUserName(),
                        getUser.getDscApellido(),
                        getUser.getDscEmail(),
                        getUser.getDscImagen(),
                        getUser.getDscNombre(),
                        getUser.getFchModificacion(),
                        getUser.getFchRegistro(),
                        getUser.getFlgActivo(),
                        roleResponsesList
                );

                response.ResponseMessage(200,"Usuario y sus roles obtenidos correctamente" ,userResponse);
                httpResponse = new ResponseEntity<>(response, HttpStatus.OK);
                logger.info("--> Fin de obtencion de usuario");
            }
        }catch (Exception e){
            e.printStackTrace();
            response.ResponseMessage(500, e.getMessage());
            httpResponse = new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return httpResponse;
    }

    @Override
    public ResponseEntity<?> getUsers() throws Exception {
        ResponseEntity<?> httpResponse;
        List<UserResponse> userResponseList = new ArrayList<>();
        try {
            List<UserEntity> getUser = userRepository.findAll();
            if (getUser == null) {
                logger.info("--> No existen usuarios");
                response.ResponseMessage(400, "No existen usuarios.");
                httpResponse = new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            } else {
                logger.info("--> Inicio de obtencion de usuarios");

                for (UserEntity userEntity : getUser) {
                    UserResponse userResponse = new UserResponse(
                            userEntity.getIdUsuario(),
                            userEntity.getCdgUserName(),
                            userEntity.getDscApellido(),
                            userEntity.getDscEmail(),
                            userEntity.getDscImagen(),
                            userEntity.getDscNombre(),
                            userEntity.getFchModificacion(),
                            userEntity.getFchRegistro(),
                            userEntity.getFlgActivo(),
                            null
                    );
                    userResponseList.add(userResponse);
                }

                httpResponse = new ResponseEntity<>(userResponseList, HttpStatus.OK);
                logger.info("--> Fin de obtencion de usuario");
            }
        }catch (Exception e){
            e.printStackTrace();
            response.ResponseMessage(500, e.getMessage());
            httpResponse = new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return httpResponse;
    }

    private List<RolesResponse> getRolesResponses(List<RoleEntity> roles) {
        List<RolesResponse> roleResponsesList = new ArrayList<>();
        for (RoleEntity role : roles) {
            RolesResponse rolesResponse = new RolesResponse();
            rolesResponse.setRoleId(role.getRoleId());
            rolesResponse.setRoleName(role.getRoleName());
            rolesResponse.setRoleDescription(role.getRoleDescription());
            roleResponsesList.add(rolesResponse);
        }
        return roleResponsesList;
    }
}
