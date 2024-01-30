package com.laptrinhweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.laptrinhweb.entity.GenreEntity;

@Repository
public interface IGenreRepository extends JpaRepository<GenreEntity, Long>{
	GenreEntity findOneByCode(String code);
}
