/*
SQLyog Community Edition- MySQL GUI v6.07
Host - 5.0.67-community-nt : Database - visa
*********************************************************************
Server version : 5.0.67-community-nt
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

create database if not exists `visa`;

USE `visa`;

/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

/*Table structure for table `applicationentry` */

DROP TABLE IF EXISTS `applicationentry`;

CREATE TABLE `applicationentry` (
  `ApplnID` varchar(50) NOT NULL,
  `EmpID` varchar(50) default NULL,
  `ExeID` varchar(50) default NULL,
  `NoOfTimeToOnsite` varchar(50) default NULL,
  `Country` varchar(50) default NULL,
  `Type` varchar(50) default NULL,
  `Enquiry` varchar(50) default NULL,
  `Interview` varchar(50) default NULL,
  `Results` varchar(50) default NULL,
  `OnsiteDeparture` varchar(50) default NULL,
  `Reports` varchar(50) default NULL,
  `OnsiteArrival` varchar(50) default NULL,
  `Date` datetime default NULL,
  PRIMARY KEY  (`ApplnID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `applicationentry` */

insert  into `applicationentry`(`ApplnID`,`EmpID`,`ExeID`,`NoOfTimeToOnsite`,`Country`,`Type`,`Enquiry`,`Interview`,`Results`,`OnsiteDeparture`,`Reports`,`OnsiteArrival`,`Date`) values ('Apln331','1111','HrEx4331','0','US','L1','yes','yes','yes','yes','yes','yes','2009-02-14 00:00:00'),('Apln332','1235','HrEx4333','0','UK','H1 visa','yes','yes','yes','yes','No','yes','2005-04-10 00:00:00'),('Apln333','1111','HrEx4333','1','UK','H1 visa','yes','yes','yes','yes','No','yes','2005-04-10 00:00:00'),('Apln334','7878','HrEx4335','0','singapore','Employment','yes','yes','yes','yes','No','yes','2005-04-22 00:00:00'),('Apln335','7879','HrEx4335','1','singapore','Employment','yes','yes','yes','yes','No','yes','2005-04-22 00:00:00'),('Apln336','1235','HrEx4334','0','aa','aa','No','No','No','No','No','No','2005-04-28 00:00:00'),('Apln337','7411','HrEx4336','0','USA','H1 visa','yes','yes','yes','yes','No','yes','2004-05-03 00:00:00'),('Apln338','7411','HrEx4336','0','AUS','Employment','yes','yes','yes','No','No','No','2004-05-05 00:00:00');

/*Table structure for table `empdetails` */

DROP TABLE IF EXISTS `empdetails`;

CREATE TABLE `empdetails` (
  `EmpID` varchar(50) NOT NULL,
  `EmpName` varchar(50) default NULL,
  `Password` varchar(50) default NULL,
  `Designation` varchar(50) default NULL,
  `PassportNo` varchar(50) default NULL,
  PRIMARY KEY  (`EmpID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `empdetails` */

insert  into `empdetails`(`EmpID`,`EmpName`,`Password`,`Designation`,`PassportNo`) values ('1235','naveen','naveen','software','F123456'),('7411','nagesh','nagesh','employee','A23444'),('7879','priya','kokk','programmer','F23456');

/*Table structure for table `enquiry` */

DROP TABLE IF EXISTS `enquiry`;

CREATE TABLE `enquiry` (
  `EnqID` varchar(50) NOT NULL,
  `ApplnID` varchar(50) default NULL,
  `Enteredby` varchar(50) default NULL,
  `Query` varchar(50) default NULL,
  `Ans` varchar(50) default NULL,
  `Date` datetime default NULL,
  PRIMARY KEY  (`EnqID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `enquiry` */

insert  into `enquiry`(`EnqID`,`ApplnID`,`Enteredby`,`Query`,`Ans`,`Date`) values ('Enq31','Apln331','HrEx4331','Q1','R1','2009-02-14 00:00:00'),('Enq32','Apln333','HrEx4333','he is good or bad?','Bad','2005-04-10 00:00:00'),('Enq33','Apln333','HrEx4333','what happend?','some problems','2005-04-10 00:00:00'),('Enq34','Apln332','HrEx4333','what is naveen position in programming side?','very good','2005-04-10 00:00:00'),('Enq35','Apln334','HrEx4335','what is the position in prog side?','good','2005-04-22 00:00:00'),('Enq36','Apln335','HrEx4335','what is the pos abt on background?','normal','2005-04-22 00:00:00'),('Enq37','Apln335','HrEx4335','what happend?','nothing she is good','2005-04-22 00:00:00'),('Enq38','Apln337','HrEx4336','What is the position abt the prog side?','Good','2004-05-03 00:00:00'),('Enq39','Apln338','HrEx4336','What abt position on Prog side?','some better','2004-05-05 00:00:00'),('Enq40','Apln338','HrEx4336','whst','dfg','2004-05-05 00:00:00');

/*Table structure for table `executive` */

DROP TABLE IF EXISTS `executive`;

CREATE TABLE `executive` (
  `ExeID` varchar(50) NOT NULL,
  `Name` varchar(50) default NULL,
  `DOb` datetime default NULL,
  `MailId` varchar(50) default NULL,
  `PhNo` varchar(50) default NULL,
  `Address` varchar(50) default NULL,
  `Date` datetime default NULL,
  PRIMARY KEY  (`ExeID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `executive` */

insert  into `executive`(`ExeID`,`Name`,`DOb`,`MailId`,`PhNo`,`Address`,`Date`) values ('HrEx4334','akil','1936-02-02 00:00:00','akil@visa.com','9849683706','hyderbad','2005-04-10 00:00:00'),('HrEx4336','madhu','1983-02-22 00:00:00','madhu@visa.com','9632587410','hyderabad','2004-05-03 00:00:00');

/*Table structure for table `interview` */

DROP TABLE IF EXISTS `interview`;

CREATE TABLE `interview` (
  `IntID` varchar(50) NOT NULL,
  `ApplnID` varchar(50) default NULL,
  `EnteredBy` varchar(50) default NULL,
  `IntDate` varchar(50) default NULL,
  `IntTime` varchar(50) default NULL,
  `Venu` varchar(50) default NULL,
  `Date` datetime default NULL,
  PRIMARY KEY  (`IntID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `interview` */

insert  into `interview`(`IntID`,`ApplnID`,`EnteredBy`,`IntDate`,`IntTime`,`Venu`,`Date`) values ('Int31','Apln331','HrEx4331','2009-3-26','2 : 28 PM','Chennai','2009-02-14 00:00:00'),('Int32','Apln333','HrEx4333','2010-4-10','2 : 24 PM','ameerpet','2005-04-10 00:00:00'),('Int33','Apln332','HrEx4333','2010-4-10','2 : 36 PM','ameerpet','2005-04-10 00:00:00'),('Int34','Apln334','HrEx4335','2010-4-22','11 : 31 AM','ameerpet','2005-04-22 00:00:00'),('Int35','Apln335','HrEx4335','2010-4-22','10 : 42 AM','ameerpet','2005-04-22 00:00:00'),('Int36','Apln337','HrEx4336','2010-5-3','2 : 46 PM','ameerpet','2004-05-03 00:00:00'),('Int37','Apln338','HrEx4336','-2-22','11 : 24 AM','Hi-Tech','2004-05-05 00:00:00');

/*Table structure for table `logindetails` */

DROP TABLE IF EXISTS `logindetails`;

CREATE TABLE `logindetails` (
  `UserName` varchar(50) NOT NULL,
  `Password` varchar(50) default NULL,
  `Category` varchar(50) default NULL,
  PRIMARY KEY  (`UserName`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `logindetails` */

insert  into `logindetails`(`UserName`,`Password`,`Category`) values ('1235','naveen','Employee'),('7411','nagesh','Employee'),('7879','kokk','Employee'),('admin','admin','Administrator'),('HrEx4334','HrEx4334','HrManager'),('HrEx4336','HrEx4336','HrManager');

/*Table structure for table `onsitearrival` */

DROP TABLE IF EXISTS `onsitearrival`;

CREATE TABLE `onsitearrival` (
  `OnSiteID` varchar(50) NOT NULL,
  `ApplnID` varchar(50) default NULL,
  `EnteredBy` varchar(50) default NULL,
  `TicketNo` varchar(50) default NULL,
  `FlightNo` varchar(50) default NULL,
  `F_From` varchar(50) default NULL,
  `Via` varchar(50) default NULL,
  `F_To` varchar(50) default NULL,
  `F_Date` varchar(50) default NULL,
  `F_Time` varchar(50) default NULL,
  `Date` datetime default NULL,
  PRIMARY KEY  (`OnSiteID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `onsitearrival` */

insert  into `onsitearrival`(`OnSiteID`,`ApplnID`,`EnteredBy`,`TicketNo`,`FlightNo`,`F_From`,`Via`,`F_To`,`F_Date`,`F_Time`,`Date`) values ('Avl31','Apln331','HrEx4331','234234','sfdasdf','Newyork','Mumbai','Hyderabad','2009-12-28','11 : 25 PM','2009-02-14 00:00:00'),('Avl32','Apln333','HrEx4333','a23','UK123','Hyderabad','New Delhi','London','2010-4-13','2 : 20 AM','2005-04-10 00:00:00'),('Avl33','Apln332','HrEx4333','h45','UK123','Hyderabad','New Delhi','London]','2010-4-13','2 : 17 AM','2005-04-10 00:00:00'),('Avl34','Apln334','HrEx4335','d7','sg123','Hyderabad','Chenni','Singapore','2010-4-24','10 : 49 AM','2005-04-22 00:00:00'),('Avl35','Apln335','HrEx4335','B5','sg123','Hyderabad','Chenni','Singapore','2010-4-24','10 : 49 AM','2005-04-22 00:00:00'),('Avl36','Apln337','HrEx4336','d2','us123','Hyderabad','Mumbai','Newyork','2010-5-5','9 : 27 AM','2004-05-03 00:00:00');

/*Table structure for table `onsitedeparture` */

DROP TABLE IF EXISTS `onsitedeparture`;

CREATE TABLE `onsitedeparture` (
  `OnSiteID` varchar(50) NOT NULL,
  `ApplnID` varchar(50) default NULL,
  `EnteredBy` varchar(50) default NULL,
  `TicketNo` varchar(50) default NULL,
  `FlightNo` varchar(50) default NULL,
  `F_From` varchar(50) default NULL,
  `Via` varchar(50) default NULL,
  `F_To` varchar(50) default NULL,
  `F_Date` varchar(50) default NULL,
  `F_Time` varchar(50) default NULL,
  `Date` datetime default NULL,
  PRIMARY KEY  (`OnSiteID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `onsitedeparture` */

insert  into `onsitedeparture`(`OnSiteID`,`ApplnID`,`EnteredBy`,`TicketNo`,`FlightNo`,`F_From`,`Via`,`F_To`,`F_Date`,`F_Time`,`Date`) values ('Dep31','Apln331','HrEx4331','3123','w23123','Hyderabad','Mumbai','Newyork','2009-4-28','11 : 28 AM','2009-02-14 00:00:00'),('Dep32','Apln333','HrEx4333','I12','Ix432','Hyderabad','New Delhi','London','2010-4-12','4 : 22 AM','2005-04-10 00:00:00'),('Dep33','Apln332','HrEx4333','I13','Ix432','Hyderabad','New Delhi','London','2010-4-12','4 : 22 AM','2005-04-10 00:00:00'),('Dep34','Apln334','HrEx4335','A4','ix232','Hyderabad','Chenni','Singapore','2010-4-24','5 : 4 AM','2005-04-22 00:00:00'),('Dep35','Apln335','HrEx4335','B4','ix232','Hyderabad','Chenni','Singapore','2010-4-24','5 : 4 AM','2005-04-22 00:00:00'),('Dep36','Apln337','HrEx4336','A2','II123','Hyderabad','Mumbai','Newyork','2010-5-4','4 : 26 AM','2004-05-03 00:00:00');

/*Table structure for table `renewal` */

DROP TABLE IF EXISTS `renewal`;

CREATE TABLE `renewal` (
  `RenID` varchar(50) default NULL,
  `ApplnID` varchar(50) default NULL,
  `Details` tinytext,
  `Status` varchar(50) default NULL,
  `Date` datetime default NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `renewal` */

insert  into `renewal`(`RenID`,`ApplnID`,`Details`,`Status`,`Date`) values ('Ren31','Apln331','Renewal not required\r\n','Read','2009-02-14 00:00:00');

/*Table structure for table `reports` */

DROP TABLE IF EXISTS `reports`;

CREATE TABLE `reports` (
  `RepID` varchar(50) NOT NULL,
  `ApplnID` varchar(50) default NULL,
  `Reports` tinytext,
  `Date` datetime default NULL,
  PRIMARY KEY  (`RepID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `reports` */

insert  into `reports`(`RepID`,`ApplnID`,`Reports`,`Date`) values ('Rep31','Apln331','ok','2009-02-14 00:00:00');

/*Table structure for table `results` */

DROP TABLE IF EXISTS `results`;

CREATE TABLE `results` (
  `ResID` varchar(50) NOT NULL,
  `ApplnID` varchar(50) default NULL,
  `EnteredBy` varchar(50) default NULL,
  `Result` tinytext,
  `Date` datetime default NULL,
  PRIMARY KEY  (`ResID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `results` */

insert  into `results`(`ResID`,`ApplnID`,`EnteredBy`,`Result`,`Date`) values ('Res31','Apln331','HrEx4331','Valid Visa','2009-02-14 00:00:00'),('Res32','Apln333','HrEx4333','Ok ','2005-04-10 00:00:00'),('Res33','Apln332','HrEx4333','ok','2005-04-10 00:00:00'),('Res34','Apln334','HrEx4335','k','2005-04-22 00:00:00'),('Res35','Apln334','HrEx4335','k','2005-04-22 00:00:00'),('Res36','Apln335','HrEx4335','k','2005-04-22 00:00:00'),('Res37','Apln337','HrEx4336','Selected','2004-05-03 00:00:00'),('Res38','Apln338','HrEx4336','selected','2004-05-05 00:00:00');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
