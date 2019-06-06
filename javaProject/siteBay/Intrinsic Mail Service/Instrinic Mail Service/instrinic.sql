/*
SQLyog Community Edition- MySQL GUI v6.07
Host - 5.0.67-community-nt : Database - sa
*********************************************************************
Server version : 5.0.67-community-nt
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

create database if not exists `sa`;

USE `sa`;

/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

/*Table structure for table `contact_group_items` */

DROP TABLE IF EXISTS `contact_group_items`;

CREATE TABLE `contact_group_items` (
  `ID` bigint(20) unsigned NOT NULL auto_increment,
  `USER` varchar(255) NOT NULL default '',
  `GROUP_ID` bigint(20) NOT NULL default '0',
  `CONTACT_ID` bigint(20) NOT NULL default '0',
  PRIMARY KEY  (`ID`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

/*Data for the table `contact_group_items` */

/*Table structure for table `contact_groups` */

DROP TABLE IF EXISTS `contact_groups`;

CREATE TABLE `contact_groups` (
  `ID` bigint(20) unsigned NOT NULL auto_increment,
  `USER` varchar(255) NOT NULL default '',
  `SHORT_NAME` varchar(100) default '',
  `DESCRIPTION` varchar(255) default '',
  PRIMARY KEY  (`ID`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

/*Data for the table `contact_groups` */

/*Table structure for table `contacts` */

DROP TABLE IF EXISTS `contacts`;

CREATE TABLE `contacts` (
  `ID` bigint(20) unsigned NOT NULL auto_increment,
  `USER` varchar(255) default '',
  `FIRST_NAME` varchar(100) NOT NULL default '',
  `MIDDLE_NAME` varchar(100) default '',
  `LAST_NAME` varchar(100) NOT NULL default '',
  `TITLE` varchar(50) default '',
  `SEX` varchar(10) default '',
  `GSM_NO_PRIMARY` varchar(30) default '',
  `GSM_NO_ALTERNATE` varchar(30) default '',
  `EMAIL_PRIMARY` varchar(255) default '',
  `EMAIL_ALTERNATE` varchar(255) default '',
  `WEB_PAGE` varchar(255) default '',
  `PERSONAL_NOTE` blob,
  `SPOUSE_NAME` varchar(255) default '',
  `NICKNAME` varchar(50) default '',
  `BIRTHDAY` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `ANNIVERSARY` timestamp NOT NULL default '0000-00-00 00:00:00',
  `HOME_ADDRESS` blob,
  `HOME_CITY` varchar(255) default '',
  `HOME_PROVINCE` varchar(255) default '',
  `HOME_ZIP` varchar(5) default '',
  `HOME_COUNTRY` varchar(100) default '',
  `HOME_PHONE` varchar(30) default '',
  `HOME_FAKS` varchar(30) default '',
  `WORK_COMPANY` varchar(100) default '',
  `WORK_JOB_TITLE` varchar(100) default '',
  `WORK_DEPARTMENT` varchar(100) default '',
  `WORK_OFFICE` varchar(100) default '',
  `WORK_PROFESSION` varchar(100) default '',
  `WORK_MANAGER_NAME` varchar(255) default '',
  `WORK_ASSISTANT_NAME` varchar(255) default '',
  `WORK_ADDRESS` blob,
  `WORK_CITY` varchar(255) default '',
  `WORK_PROVINCE` varchar(255) default '',
  `WORK_ZIP` varchar(5) default '',
  `WORK_COUNTRY` varchar(100) default '',
  `WORK_PHONE` varchar(30) default '',
  `WORK_FAKS` varchar(30) default '',
  PRIMARY KEY  (`ID`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

/*Data for the table `contacts` */

/*Table structure for table `email_db_items` */

DROP TABLE IF EXISTS `email_db_items`;

CREATE TABLE `email_db_items` (
  `ID` bigint(20) unsigned NOT NULL auto_increment,
  `USER` varchar(255) NOT NULL default '',
  `FOLDER_ID` bigint(20) unsigned NOT NULL default '0',
  `UNIQUE_ID` varchar(100) NOT NULL default '',
  `UNREAD` tinyint(1) default '0',
  `SIZE` bigint(20) unsigned NOT NULL default '0',
  `EMAIL` longblob,
  PRIMARY KEY  (`ID`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

/*Data for the table `email_db_items` */

/*Table structure for table `filters` */

DROP TABLE IF EXISTS `filters`;

CREATE TABLE `filters` (
  `ID` bigint(20) unsigned NOT NULL auto_increment,
  `USER` varchar(255) NOT NULL default '',
  `PORTION` varchar(100) default '''''',
  `CONDITION` varchar(30) default '''''',
  `KEYWORD` varchar(255) default '''''',
  `ACTION` varchar(30) default '''''',
  `DESTINATION` varchar(100) default '''''',
  PRIMARY KEY  (`ID`),
  KEY `USER` (`USER`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

/*Data for the table `filters` */

/*Table structure for table `folder_db_items` */

DROP TABLE IF EXISTS `folder_db_items`;

CREATE TABLE `folder_db_items` (
  `ID` bigint(20) unsigned NOT NULL auto_increment,
  `USER` varchar(255) default NULL,
  `PARENT_ID` bigint(20) default '0',
  `FOLDER_NAME` varchar(100) NOT NULL default '',
  `TYPE` int(10) unsigned NOT NULL default '4',
  PRIMARY KEY  (`ID`),
  KEY `USER` (`USER`)
) ENGINE=MyISAM AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;

/*Data for the table `folder_db_items` */

insert  into `folder_db_items`(`ID`,`USER`,`PARENT_ID`,`FOLDER_NAME`,`TYPE`) values (8,'ramu',0,'Inbox',1),(10,'ramu',0,'Sent Mail',3);

/*Table structure for table `notes` */

DROP TABLE IF EXISTS `notes`;

CREATE TABLE `notes` (
  `ID` bigint(20) unsigned NOT NULL auto_increment,
  `USER` varchar(255) NOT NULL default '',
  `FOLDER_ID` bigint(20) NOT NULL default '0',
  `NOTE` longblob,
  PRIMARY KEY  (`ID`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

/*Data for the table `notes` */

/*Table structure for table `notes_folders` */

DROP TABLE IF EXISTS `notes_folders`;

CREATE TABLE `notes_folders` (
  `ID` bigint(20) unsigned NOT NULL auto_increment,
  `USER` varchar(255) NOT NULL default '',
  `FOLDER_NAME` varchar(255) NOT NULL default '',
  PRIMARY KEY  (`ID`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

/*Data for the table `notes_folders` */

/*Table structure for table `usersettings` */

DROP TABLE IF EXISTS `usersettings`;

CREATE TABLE `usersettings` (
  `ID` bigint(20) unsigned NOT NULL auto_increment,
  `USER` varchar(255) NOT NULL default '',
  `KEYWORD` varchar(255) NOT NULL default '',
  `VALUE` varchar(255) NOT NULL default '',
  PRIMARY KEY  (`ID`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

/*Data for the table `usersettings` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
