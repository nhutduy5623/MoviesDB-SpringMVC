package com.laptrinhweb.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.laptrinhweb.entity.SerieEntity;

public interface ISerieRepository extends JpaRepository<SerieEntity, Long>{
	SerieEntity findOneByCode(String code);
	@Query("SELECT s FROM SerieEntity s WHERE s.name LIKE %:name%")
    Page<SerieEntity> findByNamePageable(@Param("name") String name, Pageable pageable);
	@Query("SELECT COUNT(s) FROM SerieEntity s WHERE s.name LIKE %:name%")
    Integer countByName(@Param("name") String name);
	
}
