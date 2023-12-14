package com.laptrinhweb.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.laptrinhweb.entity.PermissionEntity;
import com.laptrinhweb.entity.RoleEntity;

public interface IRoleRepository extends JpaRepository<RoleEntity, Long> {
}
