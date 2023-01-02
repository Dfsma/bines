package com.dfsma.bin.userservice.service;

import com.dfsma.bin.userservice.entity.UserEntity;
import com.dfsma.bin.userservice.pojo.UserRequest;
import com.dfsma.bin.userservice.pojo.response.ResponseMessage;
import com.dfsma.bin.userservice.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Example;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service("userService")
public class UserImpl implements UserService{

    private static final Logger logger = LoggerFactory.getLogger(UserImpl.class);

    @Autowired
    @Qualifier("userRepository")
    private UserRepository userRepository;

    ResponseMessage response = new ResponseMessage();

    @Override
    public ResponseEntity<ResponseMessage> saveUser(UserRequest userRequest) throws Exception {

        ResponseEntity<ResponseMessage> httpResponse;

        logger.info("--> Inicio mapeado de request a entidad");
        UserEntity userEntity = new UserEntity(
                userRequest.getIdUsuario(),
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
}
