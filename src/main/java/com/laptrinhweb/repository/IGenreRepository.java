package com.laptrinhweb.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.laptrinhweb.entity.GenreEntity;

@Repository
public interface IGenreRepository extends JpaRepository<GenreEntity, Long>{
	GenreEntity findOneByCode(String code);
	@Query("SELECT g FROM GenreEntity g WHERE g.name LIKE %:name%")
    Page<GenreEntity> findByNamePageable(@Param("name") String name, Pageable pageable);
	@Query("SELECT COUNT(g) FROM GenreEntity g WHERE g.name LIKE %:name%")
    Integer countByName(@Param("name") String name);
}
