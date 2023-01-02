package com.dfsma.bin.userservice.repository;

import com.dfsma.bin.userservice.entity.UserEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository("userRepository")
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    @Query(value = "SELECT * FROM USUARIO WHERE DSC_EMAIL = ?1", nativeQuery = true)
    UserEntity findDscEmail(String dscEmail);
    @Modifying
    @Transactional
    void deleteByDscEmail(String dscEmail);
}
