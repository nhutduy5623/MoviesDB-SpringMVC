package com.laptrinhweb.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.laptrinhweb.entity.WorkEntity;

public interface IWorkRepository extends JpaRepository<WorkEntity, Long>{
	WorkEntity findOneByCode(String code);
	@Modifying
    @Query("UPDATE WorkEntity w SET w.status = :newStatus WHERE w.id = :workId")
    void updateWorkStatus(@Param("workId") Long workId, @Param("newStatus") int newStatus);
	
	@Query("SELECT w FROM WorkEntity w WHERE w.name LIKE %:name%")
    Page<WorkEntity> findByNamePageable(@Param("name") String name, Pageable pageable);
	@Query("SELECT COUNT(w) FROM WorkEntity w WHERE w.name LIKE %:name%")
    Integer countByName(@Param("name") String name);
    Page<WorkEntity> findByGenre_Code(String genreCode, Pageable pageable);
	int countByGenre_Code(String genreCode);
	
}
