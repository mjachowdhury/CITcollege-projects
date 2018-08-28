
Assignemnt 4
Mohammed Alom
Student No R00144214
-----------------------------------

 

	CREATE DOMAIN HotelNumbers AS HotelNumber
		CHECK(VALUE IN (SELECT hotelNo FROM Hotel));


	CREATE DOMAIN RoomType AS CHAR(1)
		CHECK(VALUE IN (‘S’, ‘F’, ‘D’));

	
	CREATE DOMAIN RoomPrice AS DECIMAL(5, 2)
		CHECK(VALUE BETWEEN 10 AND 100);


	CREATE DOMAIN RoomNumber AS VARCHAR(4)
		CHECK(VALUE BETWEEN ‘1’ AND ‘100’);


	CREATE DOMAIN GuestNumber AS CHAR(4);


create table if not exists Hotel 

	(hotel_No			HotelNumber 	not null, 

	hotelName		varchar (30), 		not null,
	
	city			varchar (15),		not null,

	primary key 		(hotel_No)) engine=innodb;




create table if not exists Room 	
	

	(roomNo			RoomNumber 		not null, 

	hotel_No		HotelNumber 	not null,

	type			RoomType		not null, 

	price			RoomPrice		not null,

	primary key		(roomNo,hotelNo),
	
	foreign key		(hotel_No)references Hotel on delete cascade on update cascade) engine=innodb;
		


create table if not exists Booking
	

	(hotel_No			HotelNumber		not null, 

	guestNo				GuestNumbers 	not null, 

	dateFrom			BookingDate		not null, 

	dateTo				BookingDate		not null,
	 
	roomNo				RoomNumber 		not null,
	
	primary key		(hotelNo,guestNo,dateFrom),

	foreign key		(hotelNo) references Hotel on delete cascade on update cascade,
	
	foreign key		(guestNo) reference Gurest on delete cascade on update cascade,

	CONSTRAINT RoomBooked
	
		CHECK (NOT EXISTS (	SELECT *
					FROM 	Booking b
					
					WHERE 	b.dateTo > Booking.dateFrom 
					
					AND	b.dateFrom < Booking.dateTo 
					
					AND	b.roomNo = Booking.roomNo 
					
					AND	b.hotelNo = Booking.hotelNo)),

		CONSTRAINT GuestBooked
		
		CHECK (NOT EXISTS (	SELECT *
					FROM 	Booking b
					
					WHERE	b.dateTo > Booking.dateFrom 
					
					AND		b.dateFrom < Booking.dateTo 
					
					AND		b.guestNo = Booking.guestNo)))engine=innodb;


create table if not exists Guest 

		(guestNo		GuestNumber		NOT NULL,
		guestName 		VARCHAR(25)		NOT NULL,
		guestAddress	VARCHAR(50)		NOT NULL) engine=innodb;

	CREATE DOMAIN GuestNumbers AS GuestNumber
		CHECK(VALUE IN (SELECT guestNo FROM Guest));
		
	CREATE DOMAIN BookingDate AS DATETIME
		CHECK(VALUE > CURRENT_DATE);



---------------------------------------------------------------------------------------------------------------------------------------------------------

Create a view containing the hotel name and the names of the guests staying at the hotel.

	CREATE VIEW HotelNameAndGuest
	
			(hotelName, guestName)
			
			AS 	SELECT h.hotelName, g.guestName
			
	  		FROM 	Hotel h, Guest g, Booking b
			
	   		WHERE 	h.hotelNo = b.hotelNo 
			
			AND 	g.guestNo = b.guestNo 
			
			AND 	b.dateFrom <= CURRENT_DATE 
			
			AND 	b.dateTo >= CURRENT_DATE;

--------------------------------------------------------------------------------------------------------------------------------------------------------

Give the users Manager and Deputy full access to these views  with the privilege to pass the access on to other users.

	GRANT ALL PRIVILEGES ON HotelData 
	TO Manager, Director WITH GRANT OPTION;

	GRANT ALL PRIVILEGES ON BookingOutToday 
	TO Manager, Director WITH GRANT OPTION;

-----------------------------------------------------------------------------------------------------------------------------------------------------------

Give the user Accounts SELECT access to these views. Now revoke the access from this user.

	GRANT SELECT ON HotelData TO Accounts;
	GRANT SELECT ON BookingOutToday TO Accounts;

	REVOKE SELECT ON HotelData FROM Accounts;
	REVOKE SELECT ON BookingOutToday FROM Accounts;




		