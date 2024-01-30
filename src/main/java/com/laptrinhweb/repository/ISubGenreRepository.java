package com.laptrinhweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.laptrinhweb.entity.SubGenreEntity;

public interface ISubGenreRepository extends JpaRepository<SubGenreEntity, Long> {
	SubGenreEntity findOneByCode(String code);
}
