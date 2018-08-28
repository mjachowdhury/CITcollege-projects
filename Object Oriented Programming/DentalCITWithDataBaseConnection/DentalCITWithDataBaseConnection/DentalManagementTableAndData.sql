-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: dental
-- ------------------------------------------------------
-- Server version	5.7.17-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `dentists`
--

DROP TABLE IF EXISTS `dentists`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dentists` (
  `userName` varchar(20) NOT NULL,
  `userAddress` varchar(20) DEFAULT NULL,
  `userPassword` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`userName`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dentists`
--

LOCK TABLES `dentists` WRITE;
/*!40000 ALTER TABLE `dentists` DISABLE KEYS */;
INSERT INTO `dentists` VALUES 
('dirmuaid','Wilton','123456'),
('mohammed','Cork','alom');
/*!40000 ALTER TABLE `dentists` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `invoices`
--

DROP TABLE IF EXISTS `invoices`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;

CREATE TABLE `invoices` (
  `invoiceNumber` int(11) NOT NULL AUTO_INCREMENT,
  `invoiceAmount` double DEFAULT NULL,
  `invoiceDate` varchar(45) DEFAULT NULL,
  `invoicePaid` tinyint(4) DEFAULT NULL,
  `invoicePatient` int(11) DEFAULT NULL,
  PRIMARY KEY (`invoiceNumber`),
  UNIQUE KEY `invoiceNumber_UNIQUE` (`invoiceNumber`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `invoices`
--

LOCK TABLES `invoices` WRITE;
/*!40000 ALTER TABLE `invoices` DISABLE KEYS */;
INSERT INTO `invoices` VALUES 
(1,0,'2018-04-25 22:00:03.573',0,1),
(2,0,'2018-04-25 22:02:55.188',1,2),
(3,0,'2018-04-30 17:32:05.259',0,3),
(4,0,'2018-04-30 17:32:40.37',0,4);
/*!40000 ALTER TABLE `invoices` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `patients`
--

DROP TABLE IF EXISTS `patients`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `patients` (
  `patientNumber` int(11) NOT NULL AUTO_INCREMENT,
  `patientName` varchar(45) DEFAULT NULL,
  `patientAddress` varchar(45) DEFAULT NULL,
  `patientDentist` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`patientNumber`),
  UNIQUE KEY `patientNumber_UNIQUE` (`patientNumber`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `patients`
--

LOCK TABLES `patients` WRITE;
/*!40000 ALTER TABLE `patients` DISABLE KEYS */;
INSERT INTO `patients` VALUES 
(1,'Mohammed','Cork','mohammed'),
(2,'Jahangir','Blarney','mohammed'),
(3,'Dirmuaid','Wilton','dirmuaid'),
(4,'Denis','Douglas','dirmuaid');
/*!40000 ALTER TABLE `patients` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `payments`
--

DROP TABLE IF EXISTS `payments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `payments` (
  `paymentNumber` int(11) NOT NULL AUTO_INCREMENT,
  `paymentAmount` double DEFAULT NULL,
  `paymentDate` varchar(45) DEFAULT NULL,
  `paymentInvoice` int(11) DEFAULT NULL,
  PRIMARY KEY (`paymentNumber`),
  UNIQUE KEY `paymentNumber_UNIQUE` (`paymentNumber`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payments`
--

LOCK TABLES `payments` WRITE;
/*!40000 ALTER TABLE `payments` DISABLE KEYS */;
INSERT INTO `payments` VALUES 
(1,50,'Wed Apr 25 22:01:15 BST 2018',1),
(2,80,'Wed Apr 25 22:03:13 BST 2018',2),
(3,205,'Mon Apr 30 17:32:26 BST 2018',3),
(4,50,'Mon Apr 30 17:32:57 BST 2018',4);
/*!40000 ALTER TABLE `payments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `procedures`
--

DROP TABLE IF EXISTS `procedures`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `procedures` (
  `procedureNumber` int(11) NOT NULL AUTO_INCREMENT,
  `procedureName` varchar(45) DEFAULT NULL,
  `procedureCost` double DEFAULT NULL,
  `procedureInvoice` int(11) DEFAULT NULL,
  PRIMARY KEY (`procedureNumber`),
  UNIQUE KEY `procedureNumber_UNIQUE` (`procedureNumber`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `procedures`
--

LOCK TABLES `procedures` WRITE;
/*!40000 ALTER TABLE `procedures` DISABLE KEYS */;
INSERT INTO `procedures` VALUES 
(1,'Cleaning & Polishing',55,1),
(2,'Fillings',35,1),
(3,'Crowns',80,2),
(4,'Comprehensive Dental Exams',205.5,3),
(5,'Cosmetic Dentistry',110,4);
/*!40000 ALTER TABLE `procedures` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `proceduretypes`
--

DROP TABLE IF EXISTS `proceduretypes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `proceduretypes` (
  `procedureTypeNumber` int(11) NOT NULL AUTO_INCREMENT,
  `typeName` varchar(45) DEFAULT NULL,
  `typeCost` double DEFAULT NULL,
  PRIMARY KEY (`procedureTypeNumber`),
  UNIQUE KEY `procedureTypeNumber_UNIQUE` (`procedureTypeNumber`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `proceduretypes`
--

LOCK TABLES `proceduretypes` WRITE;
/*!40000 ALTER TABLE `proceduretypes` DISABLE KEYS */;
INSERT INTO `proceduretypes` VALUES 
(1,'Cleaning & Polishing',55),
(2,'Comprehensive Dental Exams',205.5),
(3,'Fillings',35),
(4,'Sealants',25),
(5,'Crowns',80),
(6,'Cosmetic Dentistry',110),
(7,'Porcelain Veneers',45),
(8,'Athletic Mouth Guards',20),
(9,'Periodontal Treatment',125),
(10,'Dental Implants',224),
(11,'Partial dentures and dentures',105.5),
(12,'Occlusal Splints or Night Guards',134),
(13,'Bridges',95),
(14,'Extractions',34),
(15,'Root Canals',97),
(16,'Emergency Services',300),
(17,'testset',123);
/*!40000 ALTER TABLE `proceduretypes` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-04-30 17:35:34
