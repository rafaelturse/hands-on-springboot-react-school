-- ################################################# --

-- DROP DATABASE db_simpleschool;
CREATE DATABASE db_simpleschool;

-- ################################################# --

CREATE SCHEMA school;

-- ################################################# --

-- DROP TABLE school.user
CREATE TABLE school.tb_user (
  id bigserial NOT NULL PRIMARY KEY,
  name character varying(150),
  email character varying(100),
  password character varying(20),
  input date default now()
);

select * from tb_user

-- ################################################# --

-- DROP TABLE school.tb_school
-- TRUNCATE TABLE school.tb_school
CREATE TABLE school.tb_school (
  id bigserial NOT NULL PRIMARY KEY,
  name character varying(150)
);

insert into school.tb_school("name") values ('Roberto Monge Primary School');
insert into school.tb_school("name") values ('Lourival Gomes Machado Secondary School');

select * from school.tb_school

-- ################################################# --

-- DROP TABLE school.tb_student
CREATE TABLE school.tb_student (
  id bigserial NOT NULL PRIMARY KEY,
  id_school bigserial NOT NULL,
  name character varying(150),
  input date default now()
);

insert into school.tb_student("id_school","name") values (1,'Rafael Turse');
insert into school.tb_student("id_school","name") values (1,'Carine Ribeiro');

select * from school.tb_student

-- ################################################# --

-- DROP TABLE school.tb_subject
CREATE TABLE school.tb_subject (
  id bigserial NOT NULL PRIMARY KEY,
  name character varying(150)
);

-- ################################################# --

-- DROP TABLE school.tb_grades
CREATE TABLE school.tb_grades (
  id bigserial NOT NULL PRIMARY KEY,
  id_school bigserial NOT NULL,
  id_student bigserial NOT NULL,
  id_subject bigserial NOT NULL,
  id_user bigserial NOT NULL,
  grade_1 int,
  grade_2 int,
  grade_3 int,
  grade_4 int
);

select * from school.tb_grades

select 
	g.id as grades_id,
	g.id_user,
	s.id as student_id,
	s.name as Student,
	sc.name as School,
	g.grade_1,
	g.grade_2,
	g.grade_3,
	g.grade_4
from 
	school.tb_grades as g
inner join
	school.tb_student as s on g.id_student = s.id
inner join
	school.tb_school as sc on g.id_school = sc.id
order by 
	g.id


-- ################################################# --
