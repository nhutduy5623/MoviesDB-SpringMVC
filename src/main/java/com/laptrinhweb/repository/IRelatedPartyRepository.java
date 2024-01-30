package com.laptrinhweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.laptrinhweb.entity.RelatedPartyEntity;

public interface IRelatedPartyRepository extends JpaRepository<RelatedPartyEntity, Long> {
}
