
use hotel_booking;
	
create table if not exists Hotel 

(hotel_No		smallint unsigned not null, 
hotelName		varchar (30), 
city			varchar (15),

primary key 		(hotel_No)) engine=innodb;

create table if not exists Room 

(roomNo			int unsigned not null, 
hotel_No		smallint unsigned not null,
type			varchar (15), 
price			float (4,2),

primary key		(roomNo),
foreign key		(hotel_No)references Hotel(hotel_No)) engine=innodb;
		
create table if not exists Booking 

(hotel_No		smallint unsigned not null, 
guestNo			int unsigned not null, 
dateFrom		varchar (15), 
dateTo			varchar (15), 
roomNo			int unsigned not null,

primary key		(guestNo),
foreign key		(roomNo) references Room(roomNo)) engine=innodb;
