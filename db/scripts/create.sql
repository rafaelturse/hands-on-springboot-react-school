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

-- ################################################# --

-- DROP TABLE school.school
CREATE TABLE school.school (
  id bigserial NOT NULL PRIMARY KEY,
  name character varying(150)
);

-- ################################################# --

-- DROP TABLE school.tb_student
CREATE TABLE school.tb_student (
  id bigserial NOT NULL PRIMARY KEY,
  id_school bigserial NOT NULL,
  name character varying(150),
  input date default now()
);

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
  id_student bigserial NOT NULL,
  id_subject bigserial NOT NULL,
  grade_1 int,
  grade_2 int,
  grade_3 int,
  grade_4 int
);

-- ################################################# --
