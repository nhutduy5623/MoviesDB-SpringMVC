package com.laptrinhweb.repository;

import java.util.List;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.laptrinhweb.entity.ViewerVoteDetailEntity;

@Repository
public interface ViewerVoteDetailRepository extends JpaRepository<ViewerVoteDetailEntity, Long> {
	@Query("SELECT vv FROM ViewerVoteDetailEntity vv WHERE vv.work.code = :workCode AND vv.user.id = :userId")
	public ViewerVoteDetailEntity findByWorkCodeAndUser(@Param("userId")long userId, @Param("workCode")String workCode);

	@Query("SELECT vv FROM ViewerVoteDetailEntity vv WHERE vv.work.code = :workCode ORDER BY vv.modifiedDate DESC")
	public List<ViewerVoteDetailEntity> findByWorkCode(@Param("workCode")String workCode);
	
	@Query("SELECT vv FROM ViewerVoteDetailEntity vv WHERE vv.work.code = :workCode ORDER BY vv.modifiedDate DESC")
    Page<ViewerVoteDetailEntity> findByWorkCodePageable(@Param("workCode") String workCode, Pageable pageable);

	@Query("SELECT vv FROM ViewerVoteDetailEntity vv WHERE vv.work.code = :workCode AND vv.user.id <> :userId ORDER BY vv.modifiedDate DESC")
    Page<ViewerVoteDetailEntity> findByWorkCodeNotUserPageable(@Param("workCode") String workCode, @Param("userId")long userId, Pageable pageable);
	
	@Query("SELECT COUNT(vv) FROM ViewerVoteDetailEntity vv WHERE vv.work.code = :workCode")
	public long countByWorkCode(@Param("workCode")String workCode);
	
	@Query("SELECT SUM(vv.score)/COUNT(vv) FROM ViewerVoteDetailEntity vv WHERE vv.work.code = :workCode")
	public float scoreAverageByWorkCode(@Param("workCode")String workCode);
	
	@Query("SELECT vv FROM ViewerVoteDetailEntity vv WHERE vv.user.id = :userId ORDER BY vv.modifiedDate DESC")
    Page<ViewerVoteDetailEntity> findByUserPageable(@Param("userId") long userId, Pageable pageable);
	
	@Query("SELECT COUNT(vv) FROM ViewerVoteDetailEntity vv WHERE vv.user.id = :userId")
	public long countByUser(@Param("userId")long userId);
}