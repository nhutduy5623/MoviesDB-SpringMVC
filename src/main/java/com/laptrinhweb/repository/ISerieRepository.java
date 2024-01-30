package com.laptrinhweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.laptrinhweb.entity.GenreEntity;
import com.laptrinhweb.entity.SerieEntity;

public interface ISerieRepository extends JpaRepository<SerieEntity, Long>{
	GenreEntity findOneByCode(String code);
}
