package com.laptrinhweb.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.laptrinhweb.entity.PermissionEntity;


public interface IPermissionRepository extends JpaRepository<PermissionEntity, Long>{
	PermissionEntity findOneByFunctionURL(String functionURL);
	PermissionEntity findOneByCode(String code);
	@Query("SELECT p FROM PermissionEntity p WHERE p.name LIKE %:name%")
    Page<PermissionEntity> findByNamePageable(@Param("name") String name, Pageable pageable);
	@Query("SELECT COUNT(p) FROM PermissionEntity p WHERE p.name LIKE %:name%")
    Integer countByName(@Param("name") String name);
}

