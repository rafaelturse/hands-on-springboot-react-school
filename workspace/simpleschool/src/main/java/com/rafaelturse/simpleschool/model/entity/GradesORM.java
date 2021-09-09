package com.rafaelturse.simpleschool.model.entity;

import java.io.Serializable;

import javax.persistence.Column;
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

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "tb_grades", schema = "school")
public class GradesORM implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Enumerated(value = EnumType.ORDINAL)
	@Column(name = "id_subject")
	private SubjectEnum subject;
	
	@ManyToOne
	@JoinColumn(name = "id_school")
	private SchoolORM school;

	@ManyToOne
	@JoinColumn(name = "id_student")
	private StudentORM student;

	@ManyToOne
	@JoinColumn(name = "id_user")
	private UserORM user;

	@Column(name = "grade_1")
	private Integer grade1;

	@Column(name = "grade_2")
	private Integer grade2;

	@Column(name = "grade_3")
	private Integer grade3;

	@Column(name = "grade_4")
	private Integer grade4;

}
