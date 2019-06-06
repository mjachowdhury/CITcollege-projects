select*from Discharge

select*from Registration

create table Discharge(RegNo varchar(5) primary key,PatientName Varchar(20),AdmissionDate varchar(20),DischargeDate Varchar(15),
Diseases varchar(15),RoomCharges int,MedicineCharges int,OTCharges int,DoctorCharges int,TotalAmount int)



insert into Discharge values('PR155','Sushma Bai','12/10/09','06/11/09','TB',5000,7000,8000,1500,21500)

insert into Discharge values('PR156','Sundar Lal','12/10/09','01/11/09','SkinInfection',1000,1500,5000,1000,8500)

insert into Discharge values('PR157','Vinita Singhal','16/10/09','05/11/09','HeartProblem',5000,6500,10000,1000,22500)

insert into Discharge values('PR158','Kamal Ahuja','20/10/09','10/11/09','HeartProblem',5000,5000,10000,1000,21000)

