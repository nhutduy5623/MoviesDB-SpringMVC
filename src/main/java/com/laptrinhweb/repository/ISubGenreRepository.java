package com.laptrinhweb.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.laptrinhweb.entity.GenreEntity;
import com.laptrinhweb.entity.SubGenreEntity;

public interface ISubGenreRepository extends JpaRepository<SubGenreEntity, Long> {
	SubGenreEntity findOneByCode(String code);
	Page<SubGenreEntity> findByGenreList_Code(String genreCode, Pageable pageable);
	int countByGenreList_Code(String genreCode);
	
	@Query("SELECT sg FROM SubGenreEntity sg WHERE sg.name LIKE %:name%")
    Page<SubGenreEntity> findByNamePageable(@Param("name") String name, Pageable pageable);
	@Query("SELECT COUNT(sg) FROM SubGenreEntity sg WHERE sg.name LIKE %:name%")
    Integer countByName(@Param("name") String name);
}
