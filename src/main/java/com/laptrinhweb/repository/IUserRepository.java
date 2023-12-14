package com.laptrinhweb.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.laptrinhweb.entity.UserEntity;
@Repository
public interface IUserRepository extends JpaRepository<UserEntity, Long> {
	UserEntity findOneByEmail(String email);
	UserEntity getOne(Long id);
	@Query("SELECT u FROM UserEntity u " +
		       "JOIN u.roleList ru " +
		       "JOIN ru.permissionList p " +
		       "WHERE u.id = :userId AND p.functionURL = :functionURL")
	Optional<UserEntity> findUserAuthorization(@Param("userId") Long userId, @Param("functionURL") String functionURL);
}
