package com.rafaelturse.simpleschool.model.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rafaelturse.simpleschool.model.entity.SchoolORM;

public interface SchoolRepository extends JpaRepository<SchoolORM, Long> {

	Optional<SchoolORM> findByName(String name);
}
