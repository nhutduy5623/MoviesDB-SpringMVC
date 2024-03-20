package com.laptrinhweb.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.laptrinhweb.entity.RelatedPartyWorkDetailEntity;

public interface IRelatedPartyWorkDetailRepository extends JpaRepository<RelatedPartyWorkDetailEntity, Long> {
	  @Query("SELECT rpwd FROM RelatedPartyWorkDetailEntity rpwd WHERE rpwd.work.id = :workId")
	  List<RelatedPartyWorkDetailEntity> findRelatedPartyDetailsByWorkId(@Param("workId") Long workId);
	
}
	