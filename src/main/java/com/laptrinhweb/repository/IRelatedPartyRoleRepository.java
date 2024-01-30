package com.laptrinhweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.laptrinhweb.entity.RelatedPartyRoleEntity;

public interface IRelatedPartyRoleRepository extends JpaRepository<RelatedPartyRoleEntity, Long> {
}
