/*
SQLyog Community Edition- MySQL GUI v5.23 RC2
Host - 5.0.45-community-nt : Database - realtorspro
*********************************************************************
Server version : 5.0.45-community-nt
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

create database if not exists `realtorspro`;

USE `realtorspro`;

/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

/*Table structure for table `activitytracker` */

DROP TABLE IF EXISTS `activitytracker`;

CREATE TABLE `activitytracker` (
  `AdminUser` varchar(50) default NULL,
  `EmpModified` double default NULL,
  `DateModified` varchar(50) default NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

/*Data for the table `activitytracker` */

insert  into `activitytracker`(`AdminUser`,`EmpModified`,`DateModified`) values ('kalyan',34,'2000-01-01 02:03:21.1'),('kalyan',34,'2000-01-01 02:03:21.1'),('kalyan',10001,'2000-01-01 02:07:43.86'),('kalyan',121212,'2000-01-01 02:11:00.11'),('kalyan',33333,'2000-01-01 01:34:39.57'),('kalyan',33,'2000-01-01 01:36:47.98'),('kalyan',234234,'2000-01-01 01:17:32.474'),('kalyan',12345,'2000-01-01 01:19:10.155');

/*Table structure for table `appartmentdetails` */

DROP TABLE IF EXISTS `appartmentdetails`;

CREATE TABLE `appartmentdetails` (
  `AppNo` int(11) default NULL,
  `FlatNo` int(11) default NULL,
  `FlatType` varchar(50) default NULL,
  `CostPerSqFeet` decimal(18,0) default NULL,
  `Area` int(11) default NULL,
  `Status` varchar(50) default NULL,
  `SoldTo` varchar(50) default NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

/*Data for the table `appartmentdetails` */

insert  into `appartmentdetails`(`AppNo`,`FlatNo`,`FlatType`,`CostPerSqFeet`,`Area`,`Status`,`SoldTo`) values (1,101,'2bhk','2000',1000,'Sold','Raj Malhotra'),(1,102,'1bhk','2000',700,'Sold','Veera Reddy');

/*Table structure for table `appartmentmaster` */

DROP TABLE IF EXISTS `appartmentmaster`;

CREATE TABLE `appartmentmaster` (
  `AppNo` int(11) default NULL,
  `PlotNo` int(11) default NULL,
  `AppName` varchar(50) default NULL,
  `Address` varchar(50) default NULL,
  `NoFlats` int(11) default NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

/*Data for the table `appartmentmaster` */

insert  into `appartmentmaster`(`AppNo`,`PlotNo`,`AppName`,`Address`,`NoFlats`) values (1,99,'Sai Heights','Kukatpally,Hyd',50),(33,5,'don','don',100),(22,5,'SRHeights','Mothinagar',35),(1,1001,'Surya Enclave','S R Nagar',8);

/*Table structure for table `empmaster` */

DROP TABLE IF EXISTS `empmaster`;

CREATE TABLE `empmaster` (
  `EMPName` varchar(255) default NULL,
  `EMPNO` double default NULL,
  `MailID` varchar(255) default NULL,
  `CurrentLocation` varchar(255) default NULL,
  `JoiningDate` datetime default NULL,
  `Role` varchar(255) default NULL,
  `PrimarySkillset` varchar(255) default NULL,
  `Remarks` varchar(255) default NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

/*Data for the table `empmaster` */

insert  into `empmaster`(`EMPName`,`EMPNO`,`MailID`,`CurrentLocation`,`JoiningDate`,`Role`,`PrimarySkillset`,`Remarks`) values ('jjj',33,'jajj@jfas.com','Bangalore','2223-10-22 00:00:00','General Manager','MBA','ashdfsd'),('rrrrr',34,'Name@Company.com','Pune','2000-02-20 00:00:00','Accounts Manager','MBA','testing;rrr;RRR;Added on Sat Aug 26 10:32:20 UTC+0530 2006'),('ttttt',2234,'Name@Company.com','Pune','2000-02-20 00:00:00','Sales Manager','MBA','Added on Sat Aug 26 10:32:20 UTC+0530 2006'),('Kalyan',10001,'kals@gmail.com','Hyderabad','1222-11-11 00:00:00','Sr Accountant','CA,ICWA','Testing;kalyan edited'),('Pranavi',10002,'Pranavi@gmail.com','Hyderabad','1222-11-11 00:00:00','Accountant','MA','asdfasdfsdf;kalyan edited'),('Sitaram',20002,'Sitaram@gmail.com','Bangalore','1222-11-11 00:00:00','Accountant','Mcom','kalyan edited'),('John',21211,'Name@Company.com','Bangalore','2006-09-03 00:00:00','PA','MBA','Added on Sun Sep 3 15:35:15 UTC+0530 2006'),('Sri',22222,'Sri@gmail.com','Pune','1222-11-11 00:00:00','Steno','Bcom','kalyan edited'),('Ramu',23456,'Ramu@yahoo.com','Hyderabad','2006-07-23 00:00:00','Sales Representative','BA','Added on Sat Jan 1 00:13:28 UTC+0530 2000'),('kalyansri',33333,'NA','Hyderabad','1930-02-11 00:00:00','Sales Representative','BA','asdfa;test;'),('Raj',121212,'Raj@Raj.com','Bangalore','2000-01-01 00:00:00','Security','BA','added new entry'),('asdfasdf',234234,'Name@Company.com','Pune','2000-02-20 00:00:00','CEO','MA','edited by me;Added on Sat Aug 26 10:32:20 UTC+0530 2006'),('ram',432423,'fasdf','Bangalore','2344-10-03 00:00:00','General Manager','MBA','status modified;Remarks'),('xxxxx',2322234,'Name@Company.com','Pune','2000-02-20 00:00:00','Clerk','BA','Added on Sat Aug 26 10:32:20 UTC+0530 2006'),('Raju',12345,'Raju@gmail.com','Hyderabad','2001-01-08 00:00:00','Accounts Manager','Bcom','edited by kalyan');

/*Table structure for table `empmasterbkup` */

DROP TABLE IF EXISTS `empmasterbkup`;

CREATE TABLE `empmasterbkup` (
  `EMPName` varchar(255) default NULL,
  `EMPNO` double default NULL,
  `MailID` varchar(255) default NULL,
  `CurrentLocation` varchar(255) default NULL,
  `JoiningDate` datetime default NULL,
  `BenchStartDate` datetime default NULL,
  `BenchAge` varchar(255) default NULL,
  `Role` varchar(255) default NULL,
  `EntryAs` varchar(255) default NULL,
  `Domain` varchar(255) default NULL,
  `PrimarySkillset` varchar(255) default NULL,
  `SecondarySkillset` varchar(255) default NULL,
  `VisaType` varchar(255) default NULL,
  `Status` varchar(255) default NULL,
  `Remarks` varchar(255) default NULL,
  `DateAllocated` varchar(50) default NULL,
  `ProjectBlocked` varchar(50) default NULL,
  `PreviousStatus` varchar(50) default NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

/*Data for the table `empmasterbkup` */

insert  into `empmasterbkup`(`EMPName`,`EMPNO`,`MailID`,`CurrentLocation`,`JoiningDate`,`BenchStartDate`,`BenchAge`,`Role`,`EntryAs`,`Domain`,`PrimarySkillset`,`SecondarySkillset`,`VisaType`,`Status`,`Remarks`,`DateAllocated`,`ProjectBlocked`,`PreviousStatus`) values ('Kallu',121212,'asdf','Bangalore','1930-02-11 00:00:00','1930-02-11 00:00:00','','SCON','Fresher','Testing','NA','NA','NA','Deleted','asdfasdf;Blocked for ISIS;entry modified;status changed by kalyan;RemarksDeleted From Records on2006-08-28 22:51:57.77Status changed to Deleted From Records on2006-08-28 22:51:57.77','2000-02-02','ISIS','NA'),('rrrrr',34,'Name@Company.com','Pune','2000-02-20 00:00:00','2000-03-01 00:00:00',NULL,'SPM','Fresher','Project Management','NA','NA','NA','Deleted','dd;df;rrr;RRR;Added on Sat Aug 26 10:32:20 UTC+0530 2006Deleted From Records on2006-09-03 16:53:54.92Status changed to Deleted From Records on2006-09-03 16:53:54.92','2006-09-03','RADD','NA'),('ttttt',2234,'Name@Company.com','Pune','2000-02-20 00:00:00','2000-03-01 00:00:00',NULL,'PM','Lateral','AS/400','NA','NA','NA','Deleted','asdf;Added on Sat Aug 26 10:32:20 UTC+0530 2006Deleted From Records on2006-09-03 16:54:44.85Status changed to Deleted From Records on2006-09-03 16:54:44.85','2006-09-01','NA','NA'),('Sri',22222,'Sri@gmail.com','Pune','1222-11-11 00:00:00','1111-11-11 00:00:00','1000','SE','Fresher','Open Systems','Win Runner','Win Runner','NA','Deleted','asdf;kalyan editedDeleted From Records on2006-09-03 16:56:55.79Status changed to Deleted From Records on2006-09-03 16:56:55.79','2004-04-04','NA','Bench'),('asdfasdf',234234,'Name@Company.com','Pune','2000-02-20 00:00:00','2000-03-01 00:00:00','','CON','NA','Unix Administration','NA','NA','NA','Deleted','asdf;Added on Sat Aug 26 10:32:20 UTC+0530 2006Deleted From Records on2006-09-03 16:57:34.79Status changed to Deleted From Records on2006-09-03 16:57:34.79','5555-55-55','NA','NA'),('xxxxx',2322234,'Name@Company.com','Pune','2000-02-20 00:00:00','2000-03-01 00:00:00',NULL,'CON','NA','NA','NA','NA','NA','Allocated','asdf;Added on Sat Aug 26 10:32:20 UTC+0530 2006Deleted From Records on2006-09-03 16:58:45.31Status changed to Allocated From Records on2006-09-03 16:58:45.31','2006-06-06','NA','NA');

/*Table structure for table `installmentdetails` */

DROP TABLE IF EXISTS `installmentdetails`;

CREATE TABLE `installmentdetails` (
  `PlotNo` int(11) default NULL,
  `InstallmentNo` int(11) default NULL,
  `Balance` decimal(18,0) default NULL,
  `PaymentMade` decimal(18,0) default NULL,
  `PaymentDate` datetime default NULL,
  `ChequeNo` varchar(50) default NULL,
  `PaymentBy` varchar(50) default NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

/*Data for the table `installmentdetails` */

insert  into `installmentdetails`(`PlotNo`,`InstallmentNo`,`Balance`,`PaymentMade`,`PaymentDate`,`ChequeNo`,`PaymentBy`) values (1,1,'900','100','2000-02-01 00:00:00','99999','RamaRao'),(1,2,'800','100','2000-02-02 00:00:00','88888','RamaRao'),(1,3,'689','111','2000-01-01 00:00:00','111','RamaRao'),(4,1,'270000','10000','2000-01-01 00:00:00','89871','Rahul'),(4,2,'200000','70000','2000-01-01 00:00:00','0','Veeranna'),(4,3,'199000','1000','2000-01-01 00:00:00','0','rajev'),(99,1,'400','300','2000-01-01 00:00:00','0','Rajayyah'),(99,2,'0','400','2000-01-01 00:00:00','9999','Krishna'),(1,4,'800','100','2000-01-01 00:00:00','0','RamaRao'),(3,1,'980000','10000','2000-01-01 00:00:00','12344','RamaRao'),(3,2,'970000','10000','2009-02-17 00:00:00','0','Rajan');

/*Table structure for table `login` */

DROP TABLE IF EXISTS `login`;

CREATE TABLE `login` (
  `UserID` varchar(50) default NULL,
  `Password` varchar(50) default NULL,
  `Auth` int(11) default NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

/*Data for the table `login` */

insert  into `login`(`UserID`,`Password`,`Auth`) values ('admin','admin',0),('user','user',1);

/*Table structure for table `plotdetails` */

DROP TABLE IF EXISTS `plotdetails`;

CREATE TABLE `plotdetails` (
  `PlotNo` int(11) default NULL,
  `RoadNo` int(11) default NULL,
  `SurveyNo` int(11) default NULL,
  `Extent` int(11) default NULL,
  `SqYardCost` decimal(18,0) default NULL,
  `OtherExpences` decimal(18,0) default NULL,
  `Boundaries` varchar(50) default NULL,
  `Status` varchar(50) default NULL,
  `Facing` varchar(50) default NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

/*Data for the table `plotdetails` */

insert  into `plotdetails`(`PlotNo`,`RoadNo`,`SurveyNo`,`Extent`,`SqYardCost`,`OtherExpences`,`Boundaries`,`Status`,`Facing`) values (1,2,91,2700,'1000','0','East :Plot2;North:Plot 4;West :Road; South:Road','Sold','East'),(2,11,91,2000,'150','0','East','Sold','West'),(3,3,91,3000,'120','0','East :Plot1;North:Plot 4;West :Road; South:Road','Sold','West'),(4,3,91,1000,'250',NULL,'East :Plot1;North:Plot 4;West :Road; South:Road','Sold','West'),(5,3,91,2500,'200','0','East :Plot1;North:Plot 4;West :Road; South:Road','Reserved','East'),(6,6,66,66666,'666','6666','East Ocean','Vacant','East'),(7,7,77,700,'100','5550','77777','Reserved','East'),(99,5,33,1000,'1000','2000','xyz','Sold','East'),(1001,2,123,3000,'1900','25000','East: Main Road, North: Lake','Reserved','East');

/*Table structure for table `salesmaster` */

DROP TABLE IF EXISTS `salesmaster`;

CREATE TABLE `salesmaster` (
  `PlotNo` int(11) default NULL,
  `SaleValue` decimal(18,0) default NULL,
  `DateofSale` datetime default NULL,
  `SoldTo` varchar(50) default NULL,
  `Advance` decimal(18,0) default NULL,
  `Balance` decimal(18,0) default NULL,
  `InstallmentOption` varchar(50) default NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

/*Data for the table `salesmaster` */

insert  into `salesmaster`(`PlotNo`,`SaleValue`,`DateofSale`,`SoldTo`,`Advance`,`Balance`,`InstallmentOption`) values (1,'1000','2000-01-03 00:00:00','RamaRao','100','800','Yes'),(4,'300000','2000-01-13 00:00:00','Faheem','20000','199000','Yes'),(99,'1000','2000-01-01 00:00:00','Raj','300','0','Yes'),(3,'1000000','2000-01-04 00:00:00','Ram','10000','970000','Yes');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
