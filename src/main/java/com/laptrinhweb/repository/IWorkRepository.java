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
	
	@Query("SELECT DISTINCT w FROM WorkEntity w " +
           "JOIN w.RelatedPartyDetailList rpd " +
           "JOIN rpd.relatedParty rp " +
           "WHERE rp.code = :relatedPartyCode")
	List<WorkEntity> findByRelatedPartyCode(@Param("relatedPartyCode") String relatedPartyCode);

	@Query("SELECT w FROM WorkEntity w ORDER BY w.score DESC")
	List<WorkEntity> findTopByScore(Pageable pageable);

	@Query("SELECT w FROM WorkEntity w ORDER BY w.voteCount DESC")
	List<WorkEntity> findTopByVoteCount(Pageable pageable);
	
	@Query("SELECT w FROM WorkEntity w ORDER BY w.relatedDate DESC")
	List<WorkEntity> findTopByRelatedDate(Pageable pageable);
	
//	@Query("SELECT w FROM WorkEntity w WHERE w.code = :serieCode")
//    List<WorkEntity> findBySerieCode(@Param("serieCode") String serieCode);
	
    @Query("SELECT s.workList FROM SerieEntity s WHERE s.code = :serieCode")
    List<WorkEntity> findBySerieCode(@Param("serieCode") String serieCode);
}

