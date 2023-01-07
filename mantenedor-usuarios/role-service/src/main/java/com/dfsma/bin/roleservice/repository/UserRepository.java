package com.dfsma.bin.roleservice.repository;

import com.dfsma.bin.roleservice.entity.UserEntity;
import com.dfsma.bin.roleservice.entity.UserRolesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository("userRoleRepository")
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    @Query(value = "SELECT * FROM USUARIO WHERE DSC_EMAIL = ?1", nativeQuery = true)
    UserEntity findDscEmail(String dscEmail);
}
