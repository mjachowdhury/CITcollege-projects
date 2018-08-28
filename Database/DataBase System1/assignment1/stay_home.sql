Use stay_home;

create table if not exists branch 

(branchNo		int unsigned not null,
Street 			char (10), 
City 			char (15),
State			char (15), 
zipCode			char (10), 
mgrStaffNo		int unsigned not null,

primary key 		(branchNo)) engine =innodb; 

create table if not exists Staff 

(staffNo		int unsigned not null,
 name			char (15),
 position		char (15),
 salary			float (5.2), 
 branchNo		int unsigned not null,

 primary key		(staffNo)) engine =innodb;
	

create table if not exists video 

(catalogNo		int unsigned not null, 
Title			char (10), 
Category		char (10), 
dailyRental		char (10), 
price			float(2.2), 
directorNo		int unsigned not null,

primary key		(catalogNo)) engine=innodb;

create table if not exists director 

(directorNo 		int unsigned not null, 
directorName		char (20),
primary key		(directorNo)) engine= innodb; 

create table if not exists actor 

(actorNo		smallint unsigned not null auto_increment, 
actorName		char (20),

primary key		(actorNo)) engine =innodb;

create table if not exists role 

(actorNo		smallint unsigned not null auto_increment, 
catalogNo		int unsigned not null, 
characters		char (15),

primary key		(actorNo)) engine=innodb; 
		

create table if not exists members 

(memberNo		smallint unsigned not null auto_increment, 
fName			char (15),
lName			char (15), 
address			char (20),

primary key		(memberNo)) engine =innodb;

create table if not exists registration 

(branchNo		int unsigned not null, 
memberNo		smallint unsigned not null auto_increment, 
staffNo			int unsigned not null,
dateJoined		char (20),

primary key		(staffNo),
foreign key		(memberNo)references members(memberNo))
			engine =innodb; 
	
create table if not exists RentalAgreement 

(rentalNo		smallint unsigned not null, 
dateOut			char (20), 
dateReturn		char (20), 
memberNo		smallint unsigned not null auto_increment, 
videoNo			smallint unsigned not null,

primary key		(videoNo),
foreign key	 	(memberNo)references registration(memberNo)) engine=innodb; 

create table if not exists VideoForRent 

(videoNo		smallint unsigned not null,
 available		varchar (10), 
 catalogNo		int unsigned not null, 
 branchNo		int unsigned not null,

 primary key		(videoNo),
 foreign key		(catalogNo)references video(catalogNo)) engine=innodb;

