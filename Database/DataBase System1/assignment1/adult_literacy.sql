use adult_literacy;

create table if not exists Tutor 

(tutor_id 		char (15) not null, 
 Create_date		char (15), 
 Status			char (10),
Primary key		(tutor_id)) engine=innodb;  


create table if not exists Student 
(student_id		char (15), 
 score			float (3.2),
primary key 		(student_id)) engine=innodb;

create table if not exists Match_History 

(match_id		char (10) not null, 
 tutor_id		char (15) not null, 
 student_id		char (15), 
 start_date		char (15), 
 end_date		char (15),
primary key		(match_id),
foreign key		(tutor_id) references Tutor(tutor_id)) engine=innodb;



