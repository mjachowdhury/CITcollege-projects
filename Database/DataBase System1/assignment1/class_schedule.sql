Use class_schedule;
	
create table if not exists Student

(student_id 		int unsigned not null auto_increment,	
student_name		char (25),

primary key		(student_id)) engine=innodb;

create table if not exists Qualified
 
(faculty_id		char (10) not null, 
course_id		char (10) not null, 
date_qualified		char (15),

primary key 		(faculty_id),
foreign key		(course_id) references Section (course_id)) engine=innodb;

create table if not exists Faculty 

(faculty_id		char (10) not null, 
 faculty_name		char (15),

 primary key 		(faculty_name),
foreign key		(faculty_id) references Qualified(faculty_id)) engine=innodb;

create table if not exists Section 

(section_no		int unsigned not null, 
 semester		char (10), 
 course_id		char (10) not null,

primary key		(course_id)) engine=innodb;

create table if not exists Course 

(course_id		char (10)not null, 
course_name		char (15),

primary key		(course_name),
foreign key		(course_id) references Section(course_id)) engine=innodb; 

create table if not exists Registration 

(student_id		int unsigned not null auto_increment, 
section_no		int unsigned not null, 
semester		char (10),

Primary key		(student_id)) engine=innodb;
