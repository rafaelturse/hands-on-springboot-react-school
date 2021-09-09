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

import com.rafaelturse.simpleschool.model.entity.SchoolORM;
import com.rafaelturse.simpleschool.model.repository.SchoolRepository;
import com.rafaelturse.simpleschool.service.SchoolService;

@SuppressWarnings({ "unchecked", "rawtypes" })
@Service
public class SchoolServiceImp implements SchoolService {

	@Autowired
	private SchoolRepository repository;

	public SchoolServiceImp(SchoolRepository repository) {
		super();
		this.repository = repository;
	}

	@Override
	@Transactional
	public SchoolORM save(SchoolORM school) {
		return repository.save(school);
	}

	@Override
	@Transactional
	public SchoolORM update(SchoolORM school) {
		Objects.requireNonNull(school.getId());
		return repository.save(school);
	}

	@Override
	@Transactional
	public void delete(SchoolORM school) {
		Objects.requireNonNull(school.getId());
		repository.delete(school);
	}

	@Override
	@Transactional(readOnly = true)
	public List<SchoolORM> find(SchoolORM schoolFilter) {
		Example example = Example.of(schoolFilter,
				ExampleMatcher.matching().withIgnoreCase().withStringMatcher(StringMatcher.CONTAINING));

		return repository.findAll(example);
	}

	public Optional<SchoolORM> findById(Long id) {
		return repository.findById(id);
	}

	public Optional<SchoolORM> findByName(String name) {
		return repository.findByName(name);
	}
}
