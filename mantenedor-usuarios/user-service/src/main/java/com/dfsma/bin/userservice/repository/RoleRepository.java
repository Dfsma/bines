package com.dfsma.bin.userservice.repository;

import com.dfsma.bin.userservice.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("roleRepository")
public interface RoleRepository extends JpaRepository<RoleEntity, Long> {

    @Query(value = "SELECT R.* FROM ROLE R INNER JOIN USER_ROLES UR ON UR.DSC_EMAIL = ?1 AND UR.ROLE_ID = R.ROLE_ID", nativeQuery = true)
    List<RoleEntity> findRolesByUser(String dscEmail);

}
