CREATE DATABASE  IF NOT EXISTS `myonlineshopdb` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `myonlineshopdb`;
-- MySQL dump 10.13  Distrib 5.6.18, for Win32 (x86)
--
-- Host: localhost    Database: myonlineshopdb
-- ------------------------------------------------------
-- Server version	5.6.19-enterprise-commercial-advanced

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
-- Table structure for table `arrival`
--

DROP TABLE IF EXISTS `arrival`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `arrival` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `GoodsId` int(11) NOT NULL,
  `UserId` int(11) NOT NULL,
  `Amount` int(11) NOT NULL,
  `Price` int(11) NOT NULL,
  `Time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`Id`),
  UNIQUE KEY `Id_UNIQUE` (`Id`),
  KEY `GoodsId_FK_idx` (`GoodsId`),
  KEY `UserId_FK_idx` (`UserId`),
  CONSTRAINT `Arrival_GoodsId_FK_Goods` FOREIGN KEY (`GoodsId`) REFERENCES `goods` (`Id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `Arrival_UserId_FK_Users` FOREIGN KEY (`UserId`) REFERENCES `users` (`Id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `arrival`
--

LOCK TABLES `arrival` WRITE;
/*!40000 ALTER TABLE `arrival` DISABLE KEYS */;
INSERT INTO `arrival` VALUES (10,61,9,25,400,'2014-08-22 08:46:05'),(11,64,9,20,250,'2014-08-25 14:18:16'),(12,65,9,15,200,'2014-08-25 14:18:52'),(13,66,9,30,500,'2014-08-25 14:19:01'),(14,67,9,20,390,'2014-08-25 14:19:32'),(15,68,9,10,320,'2014-08-26 14:19:47'),(16,62,9,10,400,'2014-08-27 14:20:02'),(17,63,9,500,12,'2014-08-28 14:20:11'),(18,69,9,25,600,'2014-08-28 14:23:27'),(19,70,9,15,190,'2014-08-29 14:20:31'),(21,65,9,13,200,'2014-08-30 14:21:57'),(22,68,9,22,300,'2014-08-30 14:22:52'),(23,70,9,33,180,'2014-08-30 14:23:08');
/*!40000 ALTER TABLE `arrival` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `brands`
--

DROP TABLE IF EXISTS `brands`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `brands` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `BrandName` varchar(45) NOT NULL,
  PRIMARY KEY (`Id`),
  UNIQUE KEY `Id_UNIQUE` (`Id`),
  UNIQUE KEY `BrandName_UNIQUE` (`BrandName`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `brands`
--

LOCK TABLES `brands` WRITE;
/*!40000 ALTER TABLE `brands` DISABLE KEYS */;
INSERT INTO `brands` VALUES (16,'Apple'),(18,'HTC'),(20,'Huawei'),(17,'LG'),(21,'Nokia');
/*!40000 ALTER TABLE `brands` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `goods`
--

DROP TABLE IF EXISTS `goods`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `goods` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `BrandId` int(11) NOT NULL,
  `Model` varchar(45) NOT NULL,
  `Price` decimal(10,0) NOT NULL,
  `AmountInStock` int(11) NOT NULL,
  PRIMARY KEY (`Id`),
  UNIQUE KEY `Id_UNIQUE` (`Id`),
  UNIQUE KEY `Model_UNIQUE` (`Model`),
  KEY `ModelId_FK_idx` (`BrandId`),
  KEY `ScreenSizeId_FK_idx` (`Model`),
  CONSTRAINT `Goods_BrandId_FK_Brands` FOREIGN KEY (`BrandId`) REFERENCES `brands` (`Id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=71 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `goods`
--

LOCK TABLES `goods` WRITE;
/*!40000 ALTER TABLE `goods` DISABLE KEYS */;
INSERT INTO `goods` VALUES (61,16,'iPhone 5S',550,0),(62,17,'G Flex',400,10),(63,17,'G3',390,498),(64,16,'iPhone 4S',350,15),(65,18,'W8',270,28),(66,18,'One M8',530,30),(67,20,'Ascend P7',430,18),(68,20,'Ascend Mate 2',395,31),(69,21,'Lumia 935',670,24),(70,21,'Lumia 635',240,46);
/*!40000 ALTER TABLE `goods` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders_sales`
--

DROP TABLE IF EXISTS `orders_sales`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `orders_sales` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `GoodsId` int(11) NOT NULL,
  `UserId` int(11) NOT NULL,
  `IsSold` tinyint(1) NOT NULL,
  `Amount` int(11) NOT NULL,
  `TimeOrdered` timestamp NULL DEFAULT NULL,
  `TimeSold` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`Id`),
  UNIQUE KEY `Id_UNIQUE` (`Id`),
  KEY `UserId_FK_idx` (`UserId`),
  KEY `GoodsId_FK_idx` (`GoodsId`),
  CONSTRAINT `Orders_Sales_GoodsId_FK_Goods` FOREIGN KEY (`GoodsId`) REFERENCES `goods` (`Id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `Orders_Sales_UserId_FK_Users` FOREIGN KEY (`UserId`) REFERENCES `users` (`Id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=78 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders_sales`
--

LOCK TABLES `orders_sales` WRITE;
/*!40000 ALTER TABLE `orders_sales` DISABLE KEYS */;
INSERT INTO `orders_sales` VALUES (36,64,9,0,1,'2014-09-02 06:21:08',NULL),(39,65,9,0,1,'2014-09-02 06:21:12',NULL),(40,68,9,0,1,'2014-09-02 06:21:13',NULL),(41,67,9,1,1,'2014-09-03 06:21:15','2014-09-03 06:21:15'),(42,66,9,0,1,'2014-09-04 06:21:17',NULL),(52,61,9,1,1,'2014-09-04 05:54:55','2014-09-04 05:54:55'),(54,61,9,1,1,'2014-09-04 05:54:56','2014-09-04 05:54:56'),(58,64,9,0,1,'2014-09-04 06:21:12',NULL),(59,68,9,1,1,'2014-09-05 15:15:10','2014-09-05 15:15:10'),(61,61,9,0,3,'2014-09-08 14:25:26',NULL),(62,64,9,1,5,'2014-09-08 14:25:28','2014-09-08 14:25:28'),(63,65,9,0,2,'2014-09-08 14:25:31',NULL),(64,66,9,0,1,'2014-09-08 14:25:34',NULL),(65,67,9,1,1,'2014-09-08 14:25:35','2014-09-08 14:25:35'),(66,68,9,0,2,'2014-09-08 14:25:38',NULL),(67,62,9,0,100,'2014-09-10 14:25:44',NULL),(68,63,9,1,1,'2014-09-11 14:27:15','2014-09-11 14:27:15'),(69,63,9,1,1,'2014-09-11 14:30:28','2014-09-11 14:30:28'),(70,69,9,1,1,'2014-09-11 14:30:43','2014-09-11 14:30:43'),(71,70,9,1,1,'2014-09-12 14:30:45','2014-09-12 14:30:45'),(72,70,9,1,1,'2014-09-12 14:30:45','2014-09-12 14:30:45'),(73,69,9,0,1,'2014-09-12 14:30:45',NULL),(74,63,9,0,1,'2014-09-12 14:30:45',NULL),(75,62,9,0,1,'2014-09-12 14:30:45',NULL),(77,61,9,1,23,'2014-09-02 11:26:34','2014-09-02 11:26:34');
/*!40000 ALTER TABLE `orders_sales` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `userroles`
--

DROP TABLE IF EXISTS `userroles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `userroles` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Role` varchar(45) NOT NULL,
  PRIMARY KEY (`Id`),
  UNIQUE KEY `Id_UNIQUE` (`Id`),
  UNIQUE KEY `Role_UNIQUE` (`Role`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `userroles`
--

LOCK TABLES `userroles` WRITE;
/*!40000 ALTER TABLE `userroles` DISABLE KEYS */;
INSERT INTO `userroles` VALUES (3,'ROLE_ADMIN'),(4,'ROLE_USER');
/*!40000 ALTER TABLE `userroles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `RoleId` int(11) NOT NULL,
  `Login` varchar(45) NOT NULL,
  `Password` varchar(255) NOT NULL,
  PRIMARY KEY (`Id`),
  UNIQUE KEY `Id_UNIQUE` (`Id`),
  UNIQUE KEY `Login_UNIQUE` (`Login`),
  KEY `RoleId_FK_idx` (`RoleId`),
  CONSTRAINT `Users_RoleId_FK_UserRoles` FOREIGN KEY (`RoleId`) REFERENCES `userroles` (`Id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (9,3,'stas','$2a$10$nzTWVV9HDH1TzAgrwyyorOn6zYIhnpBTVqVqWUf7VNFIRqT3ejQe6'),(16,3,'stas8','$2a$10$nzTWVV9HDH1TzAgrwyyorOn6zYIhnpBTVqVqWUf7VNFIRqT3ejQe6'),(18,4,'stas10','$2a$10$nzTWVV9HDH1TzAgrwyyorOn6zYIhnpBTVqVqWUf7VNFIRqT3ejQe6'),(19,4,'stas11','$2a$10$nzTWVV9HDH1TzAgrwyyorOn6zYIhnpBTVqVqWUf7VNFIRqT3ejQe6'),(20,4,'stas12','$2a$10$nzTWVV9HDH1TzAgrwyyorOn6zYIhnpBTVqVqWUf7VNFIRqT3ejQe6'),(21,4,'stas13','$2a$10$nzTWVV9HDH1TzAgrwyyorOn6zYIhnpBTVqVqWUf7VNFIRqT3ejQe6'),(22,4,'stas14','$2a$10$nzTWVV9HDH1TzAgrwyyorOn6zYIhnpBTVqVqWUf7VNFIRqT3ejQe6'),(23,4,'stas15','$2a$10$nzTWVV9HDH1TzAgrwyyorOn6zYIhnpBTVqVqWUf7VNFIRqT3ejQe6'),(24,4,'stas16','$2a$10$nzTWVV9HDH1TzAgrwyyorOn6zYIhnpBTVqVqWUf7VNFIRqT3ejQe6'),(25,4,'stas17','$2a$10$nzTWVV9HDH1TzAgrwyyorOn6zYIhnpBTVqVqWUf7VNFIRqT3ejQe6');
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

-- Dump completed on 2014-09-08  2:39:43
