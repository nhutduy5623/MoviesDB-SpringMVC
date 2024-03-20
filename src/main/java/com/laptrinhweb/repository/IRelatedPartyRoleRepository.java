package com.laptrinhweb.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.laptrinhweb.entity.RelatedPartyRoleEntity;

public interface IRelatedPartyRoleRepository extends JpaRepository<RelatedPartyRoleEntity, Long> {
	RelatedPartyRoleEntity findOneByCode(String code);
	@Query("SELECT rpr FROM RelatedPartyRoleEntity rpr WHERE rpr.name LIKE %:name%")
    Page<RelatedPartyRoleEntity> findByNamePageable(@Param("name") String name, Pageable pageable);
	@Query("SELECT COUNT(rpr) FROM RelatedPartyRoleEntity rpr WHERE rpr.name LIKE %:name%")
    Integer countByName(@Param("name") String name);
}
