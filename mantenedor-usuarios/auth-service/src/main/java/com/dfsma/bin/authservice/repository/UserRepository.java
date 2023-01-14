package com.dfsma.bin.authservice.repository;

import com.dfsma.bin.authservice.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository("userRepository")
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    @Query(value = "SELECT * FROM USUARIO WHERE DSC_EMAIL = ?1", nativeQuery = true)
    Optional<UserEntity> findDscEmail(String dscEmail);

}
