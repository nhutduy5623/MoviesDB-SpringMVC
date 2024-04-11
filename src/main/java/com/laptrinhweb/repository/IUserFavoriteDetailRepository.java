package com.laptrinhweb.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.laptrinhweb.entity.UserEntity;
import com.laptrinhweb.entity.UserFavoriteDetailEntity;
import com.laptrinhweb.entity.WorkEntity;

@Repository
public interface IUserFavoriteDetailRepository extends JpaRepository<UserFavoriteDetailEntity, Long> {

	UserFavoriteDetailEntity getOne(Long id);

	@Query("SELECT uf FROM UserFavoriteDetailEntity uf WHERE uf.work = :workEntity AND uf.user = :userEntity")
	UserFavoriteDetailEntity findByWorkAndUser(@Param("workEntity") WorkEntity workEntity,
			@Param("userEntity") UserEntity userEntity);

	@Query("SELECT uf FROM UserFavoriteDetailEntity uf JOIN uf.user u WHERE u.code = :userCode")
	Page<UserFavoriteDetailEntity> findByUserCode(@Param("userCode") String userCode, Pageable pageable);

	@Query("SELECT uf FROM UserFavoriteDetailEntity uf JOIN uf.user u WHERE u.code = :userCode "
			+ "AND (:genreCode IS NULL OR uf.work.genre.code = :genreCode)")
	Page<UserFavoriteDetailEntity> findByUserCodeAndGenreCode(@Param("userCode") String userCode,
			@Param("genreCode") String genreCode, Pageable pageable);
	
    @Query("SELECT COUNT(uf) FROM UserFavoriteDetailEntity uf JOIN uf.user u WHERE u.code = :userCode " +
            "AND (:genreCode IS NULL OR uf.work.genre.code = :genreCode)")
    Integer countByUserCodeAndGenreCode(@Param("userCode") String userCode,
                                       @Param("genreCode") String genreCode);
    
    @Modifying
    @Query("DELETE FROM UserFavoriteDetailEntity uf WHERE uf.work = :workEntity AND uf.user = :userEntity")
    void deleteByWorkAndUser(@Param("workEntity") WorkEntity workEntity, @Param("userEntity") UserEntity userEntity);
}
