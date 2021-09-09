package com.rafaelturse.simpleschool.service.imp;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rafaelturse.simpleschool.model.entity.StudentORM;
import com.rafaelturse.simpleschool.model.repository.StudentRepository;
import com.rafaelturse.simpleschool.service.StudentService;

@Service
public class StudentServiceImp implements StudentService {

	@Autowired
	private StudentRepository repository;

	public StudentServiceImp(StudentRepository repository) {
		super();
		this.repository = repository;
	}

	@Override
	@Transactional
	public StudentORM save(StudentORM student) {
		return repository.save(student);
	}

	@Override
	@Transactional
	public StudentORM update(StudentORM student) {
		Objects.requireNonNull(student.getId());
		return repository.save(student);
	}

	@Override
	@Transactional
	public void delete(StudentORM student) {
		Objects.requireNonNull(student.getId());
		repository.delete(student);
	}

	@Override
	@Transactional(readOnly = true)
	public List<StudentORM> find(StudentORM studentFilter) {
		Example example = Example.of(studentFilter,
				ExampleMatcher.matching().withIgnoreCase().withStringMatcher(StringMatcher.CONTAINING));

		return repository.findAll(example);
	}

	public Optional<StudentORM> findById(Long id) {
		return repository.findById(id);
	}
}
