create table if not exists admissions(
id serial not null primary key,
departments varchar(100) not null,
faculty varchar(100) not null,
start_date date not null,
end_date date not null,
alloted int not null,
filled int not null,
remaining int not null);

CREATE TABLE if not exists school_articles (
  id serial NOT NULL primary key,
  author varchar(255) DEFAULT NULL,
  content_title varchar(255) DEFAULT NULL,
  department varchar(255) DEFAULT NULL,
  description text,
  profession varchar(255) DEFAULT NULL,
  published_date varchar(255) DEFAULT NULL,
  title varchar(255) DEFAULT NULL
);

create table if not exists courses(
id serial not null primary key,
course_name varchar(100) not null,
course_duration int not null,
department_id bigint not null,
department_name varchar(50) not null,
tutors varchar(100) not null,
resources TEXT not null
);

create table if not exists feedback(
id serial not null primary key,
name varchar(100) not null,
email varchar(100) not null,
subject varchar(100),
feedback_message TEXT not null
);

--ALTER TABLE `soujanyaexp`.`feedback` 
--CHANGE COLUMN `feedback_msg` `feedback_message` LONGTEXT NOT NULL ;
--drop table if exists userroles;
--drop table if exists roles;
--drop table if exists school_users;
--alter table `school_users` add column `deleteuser` tinyint not null;
create table if not exists school_users(
id serial not null primary key,
firstname varchar(255) not null,
lastname varchar(255) not null,
email varchar(255) not null,
phonenumber varchar(255) not null,
userid varchar(255) not null,
designation varchar(255) not null,
password varchar(255) not null,
checkterms boolean not null,
inactive boolean not null,
deleteuser boolean not null
);

--alter table student change column firstname first_name varchar(255) not null;
--alter table student change column lastname last_name varchar(255) not null;
--drop table if exists student;
create table if not exists student(
id serial not null primary key,
first_name varchar(255) not null,
last_name varchar(255) not null,
parentname varchar(255) not null,
phonenumber varchar(255) not null unique,
student_id varchar(255) not null unique,
sclass varchar(255) not null,
grade varchar(255) not null
);

create table if not exists roles(
id serial not null primary key,
rolename varchar(45) not null unique);

--ALTER TABLE `soujanyaexp`.`roles` 
--CHANGE COLUMN `role` `rolename` varchar(45) NOT NULL ;

CREATE TABLE if not exists userroles (
  userid serial NOT NULL,
  roleid serial NOT NULL,
  primary key (userid, roleid),
    FOREIGN KEY (userid)
    REFERENCES school_users (id),
    FOREIGN KEY (roleid)
    REFERENCES roles (id)
);
    
    
--alter table `admin` add column `inactive` tinyint not null;
--alter table `admin` add column `deleteadmin` tinyint not null; 
create table if not exists admin(
id serial not null primary key,
first_name varchar(255) not null,
last_name varchar(255) not null,
email varchar(255) not null,
phonenumber varchar(255) not null,
admin_id varchar(255) not null,
password varchar(255) not null,
role varchar(255) not null,
designation varchar(255) not null,
education text not null,
work_experience text not null,
inactive boolean not null,
deleteadmin boolean not null
);

--alter table `parent` add column `inactive` tinyint not null;
--alter table `parent` add column `deleteparent` tinyint not null;
create table if not exists parent(
id serial not null primary key,
first_name varchar(255) not null,
last_name varchar(255) not null,
email varchar(255) not null,
phonenumber varchar(255) not null,
parent_id varchar(255) not null,
student_name varchar(255) not null,
studentid varchar(255) not null,
password varchar(255) not null,
role varchar(255) not null,
inactive boolean not null,
deleteparent boolean not null
);

--alter table `teacher` add column `inactive` tinyint not null;
--alter table `teacher` add column `deleteteacher` tinyint not null;
create table if not exists teacher(
id serial not null primary key,
first_name varchar(255) not null,
last_name varchar(255) not null,
email varchar(255) not null,
phonenumber varchar(255) not null,
teacher_id varchar(255) not null,
password varchar(255) not null,
role varchar(255) not null,
designation varchar(255) not null,
education text not null,
work_experience text not null,
inactive boolean not null,
deleteteacher boolean not null
);

--alter table `guest_user` add column `inactive` tinyint not null;
--alter table `guest_user` add column `deleteuser` tinyint not null;
create table if not exists guest_user(
id serial not null primary key,
first_name varchar(255) not null,
last_name varchar(255) not null,
email varchar(255) not null,
phonenumber varchar(255) not null,
user_id varchar(255) not null,
password varchar(255) not null,
role varchar(255) not null,
profession varchar(255) not null,
education text not null,
description text not null,
inactive boolean not null,
deleteuser boolean not null
);