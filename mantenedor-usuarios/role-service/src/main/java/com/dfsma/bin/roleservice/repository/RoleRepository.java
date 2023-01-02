package com.dfsma.bin.roleservice.repository;

import com.dfsma.bin.roleservice.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("roleRepository")
public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
}
