package com.laptrinhweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.laptrinhweb.entity.PermissionEntity;


public interface IPermissionRepository extends JpaRepository<PermissionEntity, Long>{
	PermissionEntity findOneByFunctionURL(String functionURL);
	PermissionEntity findOneByCode(String code);
}
