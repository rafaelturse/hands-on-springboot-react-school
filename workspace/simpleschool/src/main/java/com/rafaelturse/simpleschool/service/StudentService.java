package com.rafaelturse.simpleschool.service;

import java.util.List;
import java.util.Optional;

import com.rafaelturse.simpleschool.model.entity.StudentORM;

public interface StudentService {

	StudentORM save(StudentORM student);

	StudentORM update(StudentORM student);

	void delete(StudentORM student);

	List<StudentORM> find(StudentORM studentFilter);

	Optional<StudentORM> findById(Long id);
	
	Optional<StudentORM> findBySchoolName(String school);
}
