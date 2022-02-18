create table if not exists admissions(
id bigint not null primary key auto_increment,
departments varchar(100) not null,
faculty varchar(100) not null,
start_date date not null,
end_date date not null,
alloted int not null,
filled int not null,
remaining int not null);

CREATE TABLE if not exists `school_articles` (
  `id` bigint NOT NULL,
  `author` varchar(255) DEFAULT NULL,
  `content_title` varchar(255) DEFAULT NULL,
  `department` varchar(255) DEFAULT NULL,
  `description` longtext,
  `profession` varchar(255) DEFAULT NULL,
  `published_date` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

create table if not exists courses(
id bigint not null primary key auto_increment,
course_name varchar(100) not null,
course_duration int not null,
department_id Long not null,
department_name varchar(50) not null,
tutors varchar(100) not null,
resources LONGTEXT not null
);

create table if not exists feedback(
id bigint not null primary key auto_increment,
name varchar(100) not null,
email varchar(100) not null,
subject varchar(100),
feedback_message LONGTEXT not null
);

--ALTER TABLE `soujanyaexp`.`feedback` 
--CHANGE COLUMN `feedback_msg` `feedback_message` LONGTEXT NOT NULL ;
--drop table if exists userroles;
--drop table if exists school_users;
--alter table `school_users` add column `deleteuser` tinyint not null;
create table if not exists school_users(
id bigint not null primary key auto_increment,
firstname varchar(255) not null,
lastname varchar(255) not null,
email varchar(255) not null,
phonenumber varchar(255) not null,
userid varchar(255) not null,
designation varchar(255) not null,
password varchar(255) not null,
checkterms tinyint not null,
inactive tinyint not null,
deleteuser tinyint not null
);

--alter table student change column firstname first_name varchar(255) not null;
--alter table student change column lastname last_name varchar(255) not null;
--drop table if exists student;
create table if not exists student(
id bigint not null primary key auto_increment,
first_name varchar(255) not null,
last_name varchar(255) not null,
parentname varchar(255) not null,
phonenumber varchar(255) not null unique,
student_id varchar(255) not null unique,
sclass varchar(255) not null,
grade varchar(255) not null
);

create table if not exists roles(
id bigint not null primary key auto_increment,
role varchar(45) not null unique);

--ALTER TABLE `soujanyaexp`.`roles` 
--CHANGE COLUMN `role` `rolename` varchar(45) NOT NULL ;

CREATE TABLE if not exists `soujanyaexp`.`userroles` (
  `userid` BIGINT NOT NULL,
  `roleid` BIGINT NOT NULL,
  INDEX `userid_fk_idx` (`userid` ASC) VISIBLE,
  INDEX `roleid_fk_idx` (`roleid` ASC) VISIBLE,
  CONSTRAINT `userid_fk`
    FOREIGN KEY (`userid`)
    REFERENCES `soujanyaexp`.`school_users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `roleid_fk`
    FOREIGN KEY (`roleid`)
    REFERENCES `soujanyaexp`.`roles` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
    
    
--alter table `admin` add column `inactive` tinyint not null;
--alter table `admin` add column `deleteadmin` tinyint not null; 
create table if not exists admin(
id bigint not null primary key auto_increment,
first_name varchar(255) not null,
last_name varchar(255) not null,
email varchar(255) not null,
phonenumber varchar(255) not null,
admin_id varchar(255) not null,
password varchar(255) not null,
role varchar(255) not null,
designation varchar(255) not null,
education longtext not null,
work_experience longtext not null,
inactive tinyint not null,
deleteadmin tinyint not null
);

--alter table `parent` add column `inactive` tinyint not null;
--alter table `parent` add column `deleteparent` tinyint not null;
create table if not exists parent(
id bigint not null primary key auto_increment,
first_name varchar(255) not null,
last_name varchar(255) not null,
email varchar(255) not null,
phonenumber varchar(255) not null,
parent_id varchar(255) not null,
student_name varchar(255) not null,
studentid varchar(255) not null,
password varchar(255) not null,
role varchar(255) not null,
inactive tinyint not null,
deleteparent tinyint not null
);

--alter table `teacher` add column `inactive` tinyint not null;
--alter table `teacher` add column `deleteteacher` tinyint not null;
create table if not exists teacher(
id bigint not null primary key auto_increment,
first_name varchar(255) not null,
last_name varchar(255) not null,
email varchar(255) not null,
phonenumber varchar(255) not null,
teacher_id varchar(255) not null,
password varchar(255) not null,
role varchar(255) not null,
designation varchar(255) not null,
education longtext not null,
work_experience longtext not null,
inactive tinyint not null,
deleteteacher tinyint not null
);

--alter table `guest_user` add column `inactive` tinyint not null;
--alter table `guest_user` add column `deleteuser` tinyint not null;
create table if not exists guest_user(
id bigint not null primary key auto_increment,
first_name varchar(255) not null,
last_name varchar(255) not null,
email varchar(255) not null,
phonenumber varchar(255) not null,
user_id varchar(255) not null,
password varchar(255) not null,
role varchar(255) not null,
profession varchar(255) not null,
education longtext not null,
description longtext not null,
inactive tinyint not null,
deleteuser tinyint not null
);