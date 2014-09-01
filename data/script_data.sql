CREATE DATABASE  IF NOT EXISTS `myonlineshopdb` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `myonlineshopdb`;
-- MySQL dump 10.13  Distrib 5.6.17, for Win32 (x86)
--
-- Host: localhost    Database: myonlineshopdb
-- ------------------------------------------------------
-- Server version	5.6.19

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
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `arrival`
--

LOCK TABLES `arrival` WRITE;
/*!40000 ALTER TABLE `arrival` DISABLE KEYS */;
INSERT INTO `arrival` VALUES (10,61,9,25,400,'2014-08-22 08:46:05'),(11,64,9,2,250,'2014-08-27 12:25:45'),(12,76,9,22,150,'2014-08-29 10:00:22'),(13,68,9,15,200,'2014-08-29 10:00:37'),(14,62,9,23,125,'2014-08-29 10:21:12'),(15,63,9,66,300,'2014-08-29 10:21:26'),(16,65,9,33,320,'2014-08-29 10:23:34'),(17,66,9,4,200,'2014-08-29 10:24:33'),(18,67,9,5,333,'2014-08-29 10:24:41'),(19,69,9,8,230,'2014-08-29 10:24:55');
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
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;
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
) ENGINE=InnoDB AUTO_INCREMENT=82 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `goods`
--

LOCK TABLES `goods` WRITE;
/*!40000 ALTER TABLE `goods` DISABLE KEYS */;
INSERT INTO `goods` VALUES (61,16,'iPhone 5S',550,16),(62,17,'G Flex',400,23),(63,17,'G3',390,66),(64,16,'iPhone 4S',350,1),(65,18,'W8',270,33),(66,18,'One M8',530,4),(67,20,'Ascend P7',430,5),(68,20,'Ascend Mate 2',395,15),(69,21,'Lumia 935',670,8),(70,21,'Lumia 635',240,0),(76,16,'xzc',0,22),(77,16,'sdf',0,0),(78,16,'ss',0,0),(79,16,'gdg',0,0),(80,16,'Ghf',0,0),(81,16,'gd',0,0);
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
) ENGINE=InnoDB AUTO_INCREMENT=65 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders_sales`
--

LOCK TABLES `orders_sales` WRITE;
/*!40000 ALTER TABLE `orders_sales` DISABLE KEYS */;
INSERT INTO `orders_sales` VALUES (48,61,9,1,1,'2014-08-28 11:09:58',NULL),(51,64,9,1,1,'2014-08-28 11:09:40',NULL),(52,61,9,1,1,'2014-08-28 11:09:53',NULL),(53,61,9,1,1,'2014-08-28 11:09:55',NULL),(54,61,9,1,1,'2014-08-28 11:09:46',NULL),(55,61,9,1,1,'2014-08-28 11:09:38',NULL),(56,61,9,1,1,'2014-08-28 11:09:36',NULL),(57,61,9,1,1,'2014-08-28 11:09:34',NULL),(58,61,9,1,1,'2014-08-28 11:09:31',NULL),(60,61,9,1,1,'2014-08-28 11:09:28',NULL),(62,64,9,0,1,'2014-08-28 11:41:40',NULL),(63,61,9,0,1,'2014-08-28 11:42:45',NULL),(64,64,9,0,1,'2014-08-28 11:42:50',NULL);
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
) ENGINE=InnoDB AUTO_INCREMENT=48 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (9,3,'stas','$2a$10$nzTWVV9HDH1TzAgrwyyorOn6zYIhnpBTVqVqWUf7VNFIRqT3ejQe6'),(26,3,'stas7','$2a$10$nzTWVV9HDH1TzAgrwyyorOn6zYIhnpBTVqVqWUf7VNFIRqT3ejQe6'),(27,3,'stas8','$2a$10$nzTWVV9HDH1TzAgrwyyorOn6zYIhnpBTVqVqWUf7VNFIRqT3ejQe6'),(38,3,'stacy3','$2a$10$5Nh74SrLyiDF1Gh.rJcVduZMF1sZQGi8g0fEv3KOGNQzb.f4uqy8.'),(41,3,'stacy10','$2a$10$5Nh74SrLyiDF1Gh.rJcVduZMF1sZQGi8g0fEv3KOGNQzb.f4uqy8.'),(42,3,'stac11','$2a$10$5Nh74SrLyiDF1Gh.rJcVduZMF1sZQGi8g0fEv3KOGNQzb.f4uqy8.'),(43,3,'stacy12','$2a$10$5Nh74SrLyiDF1Gh.rJcVduZMF1sZQGi8g0fEv3KOGNQzb.f4uqy8.'),(44,3,'stacy13','$2a$10$5Nh74SrLyiDF1Gh.rJcVduZMF1sZQGi8g0fEv3KOGNQzb.f4uqy8.'),(45,3,'stacy14','$2a$10$5Nh74SrLyiDF1Gh.rJcVduZMF1sZQGi8g0fEv3KOGNQzb.f4uqy8.'),(46,3,'stacy5','$2a$10$5Nh74SrLyiDF1Gh.rJcVduZMF1sZQGi8g0fEv3KOGNQzb.f4uqy8.'),(47,3,'blabla','$2a$10$vgDKzuxaWJHgUF9FxMVmc.NstRBozBV.S1Qnx3ArI4.r0iqRfNyIO');
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

-- Dump completed on 2014-09-01 11:28:35
