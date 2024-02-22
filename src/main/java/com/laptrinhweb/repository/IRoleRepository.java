package com.laptrinhweb.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.laptrinhweb.entity.RoleEntity;

public interface IRoleRepository extends JpaRepository<RoleEntity, Long> {
	RoleEntity findOneByCode(String code);
	@Query("SELECT r FROM RoleEntity r WHERE r.name LIKE %:name%")
    Page<RoleEntity> findByNamePageable(@Param("name") String name, Pageable pageable);
	@Query("SELECT COUNT(r) FROM RoleEntity r WHERE r.name LIKE %:name%")
    Integer countByName(@Param("name") String name);

}
