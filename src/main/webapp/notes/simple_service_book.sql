CREATE DATABASE  IF NOT EXISTS `simple_service_book` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `simple_service_book`;
-- MySQL dump 10.13  Distrib 5.6.17, for Win32 (x86)
--
-- Host: localhost    Database: simple_service_book
-- ------------------------------------------------------
-- Server version	5.5.8

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
-- Table structure for table `simple_book`
--

DROP TABLE IF EXISTS `simple_book`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `simple_book` (
  `BOOKID` int(11) NOT NULL AUTO_INCREMENT,
  `BOOKNAME` varchar(128) DEFAULT NULL,
  `PUBLISHER` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`BOOKID`),
  UNIQUE KEY `BOOKID` (`BOOKID`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `simple_book`
--

LOCK TABLES `simple_book` WRITE;
/*!40000 ALTER TABLE `simple_book` DISABLE KEYS */;
INSERT INTO `simple_book` VALUES (1,'Java Restful Web Service使用指南','cmpbook'),(2,'JSF2和RichFaces4使用指南','phei'),(10,'Java Restful Web Service实战-20438455915299','TBD'),(11,'Java Restful Web Service实战-20866342304734','TBD'),(12,'Java Restful Web Service实战-21056609452753','TBD'),(13,'Java Restful Web Service实战-23714312857868','TBD'),(14,'Java Restful Web Service实战-25972406513404','TBD'),(15,'AAA','ZZZ');
/*!40000 ALTER TABLE `simple_book` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'simple_service_book'
--

--
-- Dumping routines for database 'simple_service_book'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-04-06 14:44:01
