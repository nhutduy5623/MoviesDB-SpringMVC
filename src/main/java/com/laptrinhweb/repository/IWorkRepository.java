package com.laptrinhweb.repository;

import java.util.List;

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
	
////	Client
//	@Query("SELECT DISTINCT w FROM WorkEntity w " +
//		       "JOIN w.genre g " +
//		       "JOIN w.subGenreList s " +
//		       "WHERE g.code = :genreCode " +
//		       "AND s.code IN :subgenreCodes")
//	 Page<WorkEntity> findByGenreCodeAndSubgenreCodes(@Param("genreCode") String genreCode, @Param("subgenreCodes") List<String> subgenreCodes, Pageable pageable);
//	
//	@Query("SELECT COUNT(w) FROM WorkEntity w " +
//		       "JOIN w.genre g " +
//		       "JOIN w.subGenreList s " +
//		       "WHERE g.code = :genreCode " +
//		       "AND s.code IN :subgenreCodes")
//	long countByGenreCodeAndSubgenreCodes(@Param("genreCode") String genreCode, @Param("subgenreCodes") List<String> subgenreCodes);
//	

	@Query("SELECT COUNT(DISTINCT w) FROM WorkEntity w " +
		       "JOIN w.genre g " +
		       "JOIN w.subGenreList s " +
		       "WHERE (:searchValue IS NULL OR :searchValue = '' OR w.name LIKE CONCAT('%', :searchValue, '%')) " +
		       "AND (:genreCode IS NULL OR :genreCode = '' OR g.code = :genreCode) " +
		       "AND s.code IN :subgenreCodes")
		long countByGenreCodeAndSubgenreCodesAndNameLike(@Param("searchValue") String searchValue, @Param("genreCode") String genreCode, @Param("subgenreCodes") List<String> subgenreCodes);

		@Query("SELECT DISTINCT w FROM WorkEntity w " +
		       "JOIN w.genre g " +
		       "JOIN w.subGenreList s " +
		       "WHERE (:searchValue IS NULL OR :searchValue = '' OR w.name LIKE CONCAT('%', :searchValue, '%')) " +
		       "AND (:genreCode IS NULL OR :genreCode = '' OR g.code = :genreCode) " +
		       "AND s.code IN :subgenreCodes")
		Page<WorkEntity> findByGenreCodeAndSubgenreCodesAndNameLike(@Param("searchValue") String searchValue, @Param("genreCode") String genreCode, @Param("subgenreCodes") List<String> subgenreCodes, Pageable pageable);

		@Query("SELECT COUNT(DISTINCT w) FROM WorkEntity w " +
			       "JOIN w.genre g " +
			       "WHERE (:searchValue IS NULL OR :searchValue = '' OR w.name LIKE CONCAT('%', :searchValue, '%')) " +
			       "AND (:genreCode IS NULL OR :genreCode = '' OR g.code = :genreCode) ")
			long countByGenreCodeAndNameLike(@Param("searchValue") String searchValue, @Param("genreCode") String genreCode);

			@Query("SELECT DISTINCT w FROM WorkEntity w " +
			       "JOIN w.genre g " +
			       "WHERE (:searchValue IS NULL OR :searchValue = '' OR w.name LIKE CONCAT('%', :searchValue, '%')) " +
			       "AND (:genreCode IS NULL OR :genreCode = '' OR g.code = :genreCode) ")
			Page<WorkEntity> findByGenreCodeAndNameLike(@Param("searchValue") String searchValue, @Param("genreCode") String genreCode, Pageable pageable);

}

