package com.rafaelturse.simpleschool.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rafaelturse.simpleschool.model.entity.UserORM;

public interface UserRepository extends JpaRepository<UserORM, Long> {

	boolean existsByEmail(String email);
}
