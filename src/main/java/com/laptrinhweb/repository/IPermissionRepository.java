package com.laptrinhweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.laptrinhweb.entity.PermissionEntity;


public interface IPermissionRepository extends JpaRepository<PermissionEntity, Long>{
	PermissionEntity findOneByFunctionURL(String functionURL);
}
