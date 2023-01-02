package com.dfsma.bin.userservice.repository;

import com.dfsma.bin.userservice.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("userRepository")
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    @Query(value = "SELECT * FROM USUARIO WHERE DSC_EMAIL = ?1", nativeQuery = true)
    UserEntity findDscEmail(String dscEmail);
}
