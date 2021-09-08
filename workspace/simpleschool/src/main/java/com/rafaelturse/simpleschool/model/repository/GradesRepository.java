package com.rafaelturse.simpleschool.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rafaelturse.simpleschool.model.entity.GradesORM;

public interface GradesRepository extends JpaRepository<GradesORM, Long> {

}
