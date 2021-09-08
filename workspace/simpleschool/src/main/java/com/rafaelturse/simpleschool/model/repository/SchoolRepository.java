package com.rafaelturse.simpleschool.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rafaelturse.simpleschool.model.entity.SchoolORM;

public interface SchoolRepository extends JpaRepository<SchoolORM, Long> {

}
