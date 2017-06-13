-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: mydb
-- ------------------------------------------------------
-- Server version	5.7.18-log

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
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `userId` int(11) NOT NULL AUTO_INCREMENT,
  `firstname` varchar(25) DEFAULT NULL,
  `lastname` varchar(25) DEFAULT NULL,
  `username` varchar(25) DEFAULT NULL,
  `password` varchar(25) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `ranking` int(11) DEFAULT NULL,
  `admin` tinyint(1) DEFAULT NULL,
  `phoneNumber` bigint(20) DEFAULT NULL,
  `enabled` tinyint(1) DEFAULT NULL,
  `activationKey` varchar(50) DEFAULT NULL,
  `dateRegistered` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`userId`),
  UNIQUE KEY `username` (`username`),
  UNIQUE KEY `email` (`email`),
  UNIQUE KEY `phoneNumber` (`phoneNumber`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'Madalina','Palade','Madalina','madalina','madalina.palade@fortech.ro',0,0,761444651,1,NULL,'2017-06-06 08:19:24'),(2,'abc','abc','abc','abc','abc@abc.ro',0,0,99999999999999,0,'0ee78be8-ddcc-40f9-beb7-dc2b08478c4b','2017-06-06 08:45:36'),(3,'madass','sasa','asasa','aaa','asa@sasa.com',0,0,11111111111111111,0,'c35fe30b-d8d1-4572-873b-d9ee72246f4d','2017-06-06 08:52:48'),(4,'ujuju','kjmuiki','kiki','hhh','kiruik@dvfrd.com',0,0,55555555555555,0,'ab33cb78-e68d-41ee-ab32-30bc8d093576','2017-06-06 08:54:22'),(5,'yyyy','yyyy','yyyy','yyy','yyyy@yyy.com',0,0,666666666666,0,'545180b2-fa82-4cb7-bcaa-b738fd3ef973','2017-06-06 08:55:15'),(6,'gggg','odfhbdfb','dfbdfb','aaa','dfbdfr@dvsd.com',0,0,55555555555555555,0,'cb10d12a-8f86-40f0-9996-cd5864ba5411','2017-06-06 09:10:50'),(7,'asdf','asfvdfgb','afsvfb','asd','adfvgfd@adgf.com',0,0,7777777777,0,'96825aa3-86b3-4faf-a1bc-749166c6c51e','2017-06-06 09:14:36'),(8,'bnbbb','bbbbb','bbbbb','asa','dsfsdd@rghjn.com',0,0,8888888888888,0,'9033aece-033d-41ff-b192-3451833c6829','2017-06-06 09:15:41'),(9,'ggfgf','gfgf','gfgf','fff','gfgf@tghss.com',0,0,888888888888,0,'e49c9f94-1c4a-4b41-91e8-b104402d17e9','2017-06-06 09:17:14'),(10,'uuuu','uuuu','uuuu','uuu','uuuu@iii.com',0,0,999999999999999,0,'8d633e85-e0dc-4406-b6ad-cec533c736d5','2017-06-06 09:17:44'),(11,'ytyt','ytyt','ytyt','fff','ytytyt@yhjmk.com',0,0,22222222222,0,'9b125b18-7938-4a85-9d37-f1152b027583','2017-06-06 09:20:13'),(12,'fdfgd','fdfdf','fdfd','fdfd','fdfdf@sss.com',0,0,666677777777777777,0,'d46c8760-ec84-4215-8d0b-586c84d6123a','2017-06-06 09:26:41'),(13,'fdffdf','fdfd','fdfdsds','fdfd','fdfd@dgvfdb.com',0,0,5555555666667,0,'b3c07fc8-6a93-4237-bd12-7c253753a55d','2017-06-06 09:36:11'),(14,'hjhjh','jhjh','jhjhj','aaa','hjhjh@kjjj.om',0,0,7777777777777766,0,'6a5d04bd-7c1e-4250-9f30-2884b240b920','2017-06-06 09:39:05');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-06-13 13:23:38


-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: mydb
-- ------------------------------------------------------
-- Server version	5.7.18-log

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
-- Table structure for table `categories`
--

DROP TABLE IF EXISTS `categories`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `categories` (
  `categoryId` int(11) NOT NULL AUTO_INCREMENT,
  `parentId` int(11) DEFAULT NULL,
  `nameCategory` varchar(20) DEFAULT NULL,
  `description` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`categoryId`),
  UNIQUE KEY `nameCategory` (`nameCategory`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categories`
--

LOCK TABLES `categories` WRITE;
/*!40000 ALTER TABLE `categories` DISABLE KEYS */;
INSERT INTO `categories` VALUES (1,NULL,'',''),(2,1,'Desktop PC','Deskktop PC description'),(3,1,'PC Components','PC Components description'),(4,1,'Laptops','Laptops description'),(5,1,'Laptop accessories','Laptop accessories description'),(6,3,'CPUs','CPUs description'),(7,3,'Storage','Storage description'),(8,3,'RAM','RAM description'),(9,6,'AMD','AMD description'),(10,6,'Intel','Intel description');
/*!40000 ALTER TABLE `categories` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-06-13 13:23:38

-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: mydb
-- ------------------------------------------------------
-- Server version	5.7.18-log

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
-- Table structure for table `items`
--

DROP TABLE IF EXISTS `items`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `items` (
  `itemId` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) NOT NULL,
  `name` varchar(50) DEFAULT NULL,
  `description` varchar(500) DEFAULT NULL,
  `categories` varchar(20) DEFAULT NULL,
  `initialPrice` double DEFAULT NULL,
  `bestBid` double DEFAULT NULL,
  `yourBid` double DEFAULT NULL,
  `nrBids` int(10) DEFAULT NULL,
  `biddingStartDate` date DEFAULT NULL,
  `biddingEndDate` date DEFAULT NULL,
  `status` varchar(20) DEFAULT NULL,
  `winner` varchar(25) DEFAULT NULL,
  PRIMARY KEY (`itemId`),
  KEY `winner` (`winner`),
  KEY `userId` (`userId`),
  KEY `categories` (`categories`),
  CONSTRAINT `items_ibfk_1` FOREIGN KEY (`winner`) REFERENCES `users` (`username`),
  CONSTRAINT `items_ibfk_2` FOREIGN KEY (`userId`) REFERENCES `users` (`userId`),
  CONSTRAINT `items_ibfk_3` FOREIGN KEY (`categories`) REFERENCES `categories` (`nameCategory`)
) ENGINE=InnoDB AUTO_INCREMENT=74 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `items`
--

LOCK TABLES `items` WRITE;
/*!40000 ALTER TABLE `items` DISABLE KEYS */;
INSERT INTO `items` VALUES (1,1,'Name1','description1','Laptops',0,500.99,NULL,2,'2017-06-12','2017-06-13','open','Madalina'),(2,1,'Name2','description2','PC Components',300.77777777,400,NULL,2,'2017-06-12','2017-06-12','closed','Madalina'),(3,1,'Name3','description3','Laptops',200.233,500.2,100.1,2,'2017-06-12','2017-06-12','open','Madalina'),(4,1,'Name4','description4','PC Components',300.1,400.7,100.7,2,'2017-06-12','2017-06-12','closed','Madalina'),(5,2,'Name5','description3','Laptops',200,500.111,100.89,2,'2017-06-12','2017-06-12','open','Madalina'),(6,3,'Name6','description3','Laptops',200.9,500.11,100.009,2,'2017-06-12','2017-06-12','open','Madalina');
/*!40000 ALTER TABLE `items` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-06-13 13:23:38
