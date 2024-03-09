package com.laptrinhweb.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.laptrinhweb.entity.RelatedPartyEntity;
import com.laptrinhweb.entity.RelatedPartyRoleEntity;

public interface IRelatedPartyRepository extends JpaRepository<RelatedPartyEntity, Long> {
	
	@Query("SELECT rp FROM RelatedPartyEntity rp WHERE rp.name LIKE %:name%")
    Page<RelatedPartyEntity> findByNamePageable(@Param("name") String name, Pageable pageable);
	
	@Query("SELECT COUNT(rp) FROM RelatedPartyEntity rp WHERE rp.name LIKE %:name%")
    Integer countByName(@Param("name") String name);

	@Query("SELECT rp FROM RelatedPartyEntity rp WHERE rp.RProle.code LIKE :roleCode")
	Page<RelatedPartyEntity> findByRelatedPartyRoleCode(@Param("roleCode") String roleCode, Pageable pageable);
	
	@Query("SELECT COUNT(rp) FROM RelatedPartyEntity rp WHERE rp.RProle.code LIKE :roleCode")
	Integer countByRelatedPartyRoleCode(@Param("roleCode") String roleCode);
}
