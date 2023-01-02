package com.dfsma.bin.userservice.service;

import com.dfsma.bin.userservice.entity.UserEntity;
import com.dfsma.bin.userservice.pojo.UserRequest;
import com.dfsma.bin.userservice.pojo.response.ResponseMessage;
import com.dfsma.bin.userservice.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Slf4j
@Service("userService")
public class UserImpl implements UserService{

    private static final Logger logger = LoggerFactory.getLogger(UserImpl.class);

    private final UserRepository userRepository;

    ResponseMessage response = new ResponseMessage();

    public UserImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public ResponseEntity<ResponseMessage> saveUser(UserRequest userRequest) throws Exception {

        ResponseEntity<ResponseMessage> httpResponse;

        logger.info("--> Inicio mapeado de request a entidad");
        UserEntity userEntity = new UserEntity(
                userRequest.getCdgUserName(),
                userRequest.getDscApellido(),
                userRequest.getDscEmail(),
                userRequest.getDscImagen(),
                userRequest.getDscNombre(),
                userRequest.getFlgActivo(),
                userRequest.getValPassword(),
                userRequest.getIdAplicacion()
        );
        logger.info("--> Fin mapeado de request a entidad");

        try {
            UserEntity existUser = userRepository.findDscEmail(userEntity.getDscEmail());
            if (existUser != null) {
                logger.info("--> El usuario ya existe");
                response.ResponseMessage(400, "El usuario ya existe.");
                httpResponse = new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            } else {
                logger.info("--> Inicio de guardado de usuario");
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
                UserEntity userEntity = new UserEntity(
                        existUser.getFchRegistro(),
                        userRequest.getCdgUserName(),
                        userRequest.getDscApellido(),
                        userRequest.getDscEmail(),
                        userRequest.getDscImagen(),
                        userRequest.getDscNombre(),
                        userRequest.getFlgActivo(),
                        userRequest.getValPassword(),
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
}
