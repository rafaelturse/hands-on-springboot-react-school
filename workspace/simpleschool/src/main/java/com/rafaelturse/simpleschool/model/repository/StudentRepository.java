package com.rafaelturse.simpleschool.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rafaelturse.simpleschool.model.entity.StudentORM;

public interface StudentRepository extends JpaRepository<StudentORM, Long> {

}
