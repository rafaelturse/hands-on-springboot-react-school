package com.rafaelturse.simpleschool.service;

import java.util.List;
import java.util.Optional;

import com.rafaelturse.simpleschool.model.entity.SchoolORM;

public interface SchoolService {

	SchoolORM save(SchoolORM school);

	SchoolORM update(SchoolORM school);

	void delete(SchoolORM school);

	List<SchoolORM> find(SchoolORM schoolFilter);

	Optional<SchoolORM> findById(Long id);

	Optional<SchoolORM> findByName(String name);
}
