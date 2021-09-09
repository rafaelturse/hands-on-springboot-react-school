package com.rafaelturse.simpleschool.model.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.rafaelturse.simpleschool.model.enumeration.SubjectEnum;

import lombok.Data;

@Data
@Entity
@Table(name = "tb_student", schema = "school")
public class GradesORM implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Enumerated(value = EnumType.ORDINAL)
	private SubjectEnum subject;

	@ManyToOne
	@JoinColumn(name = "id_school")
	private SchoolORM school;
	
	@ManyToOne
	@JoinColumn(name = "id_student")
	private StudentORM student;

	private String name;
	
	private Integer grade1;
	
	private Integer grade2;

	private Integer grade3;
	
	private Integer grade4;
	
}
