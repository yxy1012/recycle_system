-- MySQL dump 10.13  Distrib 8.0.19, for Win64 (x86_64)
--
-- Host: localhost    Database: recycle_system
-- ------------------------------------------------------
-- Server version	5.7.29-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `address`
--

DROP TABLE IF EXISTS `address`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `address` (
  `Address_ID` int(11) NOT NULL AUTO_INCREMENT,
  `User_ID` int(11) DEFAULT NULL,
  `Address_Details` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `Latitude` double DEFAULT NULL,
  `Longitude` double DEFAULT NULL,
  PRIMARY KEY (`Address_ID`),
  KEY `FK_Reference_15` (`User_ID`),
  CONSTRAINT `FK_Reference_15` FOREIGN KEY (`User_ID`) REFERENCES `user` (`User_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `address`
--

LOCK TABLES `address` WRITE;
/*!40000 ALTER TABLE `address` DISABLE KEYS */;
INSERT INTO `address` VALUES (1,NULL,'浙工大屏峰校区东',NULL,NULL),(2,1,'浙工大朝晖校区综合楼',NULL,NULL),(3,1,'浙江工业大学屏峰校区西6菜鸟驿站',30.28827,120.10869);
/*!40000 ALTER TABLE `address` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `admin`
--

DROP TABLE IF EXISTS `admin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `admin` (
  `Admin_ID` int(11) NOT NULL AUTO_INCREMENT,
  `User_Name` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `Password` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`Admin_ID`),
  UNIQUE KEY `AK_Key_2` (`User_Name`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin`
--

LOCK TABLES `admin` WRITE;
/*!40000 ALTER TABLE `admin` DISABLE KEYS */;
INSERT INTO `admin` VALUES (1,'cn','654321');
/*!40000 ALTER TABLE `admin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `collector`
--

DROP TABLE IF EXISTS `collector`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `collector` (
  `Collector_Person_ID` int(11) NOT NULL AUTO_INCREMENT,
  `Site_ID` int(11) DEFAULT NULL,
  `Collector_Name` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `User_Name` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `Password` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `Phone` varchar(11) COLLATE utf8_bin DEFAULT NULL,
  `IDcard_Number` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `Photo` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `User_Status` varchar(10) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`Collector_Person_ID`),
  UNIQUE KEY `AK_Key_2` (`User_Name`),
  KEY `FK_Reference_4` (`Site_ID`),
  CONSTRAINT `FK_Reference_4` FOREIGN KEY (`Site_ID`) REFERENCES `recycle_site` (`Site_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `collector`
--

LOCK TABLES `collector` WRITE;
/*!40000 ALTER TABLE `collector` DISABLE KEYS */;
INSERT INTO `collector` VALUES (1,1,'yxy','yxy','12345678','13615780000','330106200007040000',NULL,'T');
/*!40000 ALTER TABLE `collector` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `donation`
--

DROP TABLE IF EXISTS `donation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `donation` (
  `Donate_ID` int(11) NOT NULL AUTO_INCREMENT,
  `Help_ID` int(11) DEFAULT NULL,
  `User_ID` int(11) DEFAULT NULL,
  `Collector_Person_ID` int(11) DEFAULT NULL,
  `Evaluation_ID` int(11) DEFAULT NULL,
  `Address_ID` int(11) DEFAULT NULL,
  `Scheduled_Time` datetime DEFAULT NULL,
  `Finished_Time` datetime DEFAULT NULL,
  `Donate_Detail` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`Donate_ID`),
  KEY `FK_Reference_11` (`Collector_Person_ID`),
  KEY `FK_Reference_12` (`Evaluation_ID`),
  KEY `FK_Reference_14` (`Address_ID`),
  KEY `FK_Reference_16` (`Help_ID`),
  KEY `FK_Reference_9` (`User_ID`),
  CONSTRAINT `FK_Reference_11` FOREIGN KEY (`Collector_Person_ID`) REFERENCES `collector` (`Collector_Person_ID`),
  CONSTRAINT `FK_Reference_12` FOREIGN KEY (`Evaluation_ID`) REFERENCES `evaluation` (`Evaluation_ID`),
  CONSTRAINT `FK_Reference_14` FOREIGN KEY (`Address_ID`) REFERENCES `address` (`Address_ID`),
  CONSTRAINT `FK_Reference_16` FOREIGN KEY (`Help_ID`) REFERENCES `help_the_poor` (`Help_ID`),
  CONSTRAINT `FK_Reference_9` FOREIGN KEY (`User_ID`) REFERENCES `user` (`User_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `donation`
--

LOCK TABLES `donation` WRITE;
/*!40000 ALTER TABLE `donation` DISABLE KEYS */;
INSERT INTO `donation` VALUES (1,1,1,1,2,1,'2021-01-04 12:00:00','2021-01-11 11:51:59','电脑4台'),(2,1,1,1,NULL,1,'2021-01-04 12:00:00','2021-01-22 23:03:18','书本6本');
/*!40000 ALTER TABLE `donation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `evaluation`
--

DROP TABLE IF EXISTS `evaluation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `evaluation` (
  `Evaluation_ID` int(11) NOT NULL AUTO_INCREMENT,
  `Evaluation_Details` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `Evaluation_Score` int(11) DEFAULT NULL,
  PRIMARY KEY (`Evaluation_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `evaluation`
--

LOCK TABLES `evaluation` WRITE;
/*!40000 ALTER TABLE `evaluation` DISABLE KEYS */;
INSERT INTO `evaluation` VALUES (1,'111',100),(2,'11',100),(3,'不错',100),(4,'还可以',60),(5,'还行',80),(6,'666',100);
/*!40000 ALTER TABLE `evaluation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `help_the_poor`
--

DROP TABLE IF EXISTS `help_the_poor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `help_the_poor` (
  `Help_ID` int(11) NOT NULL AUTO_INCREMENT,
  `Help_Name` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `Longitude` double DEFAULT NULL,
  `Latitude` double DEFAULT NULL,
  `Help_Detail` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `Help_Status` varchar(10) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`Help_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `help_the_poor`
--

LOCK TABLES `help_the_poor` WRITE;
/*!40000 ALTER TABLE `help_the_poor` DISABLE KEYS */;
INSERT INTO `help_the_poor` VALUES (1,'浙江省杭州市西湖区浙江工业大学屏峰校区家和东苑',120.044277,30.230821,'恩格尔系数过高','启用');
/*!40000 ALTER TABLE `help_the_poor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `item`
--

DROP TABLE IF EXISTS `item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `item` (
  `Item_ID` int(11) NOT NULL AUTO_INCREMENT,
  `Item_Type_ID` int(11) DEFAULT NULL,
  `Item_Name` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `Item_Price` double DEFAULT NULL,
  `Item_Pic` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`Item_ID`),
  KEY `FK_Reference_8` (`Item_Type_ID`),
  CONSTRAINT `FK_Reference_8` FOREIGN KEY (`Item_Type_ID`) REFERENCES `item_type` (`Item_Type_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `item`
--

LOCK TABLES `item` WRITE;
/*!40000 ALTER TABLE `item` DISABLE KEYS */;
INSERT INTO `item` VALUES (1,1,'铝',3.5,'http://localhost:8181/api/file/5yf1op.jpg'),(2,1,'不锈钢',5.5,'http://localhost:8181/api/file/c0m82t.jpg'),(3,1,'铁',6,'http://localhost:8181/api/file/g81ztu.jpg'),(5,2,'冰箱',50,'http://localhost:8181/api/file/ily7j0.jpg'),(7,3,'电池',1,'http://localhost:8181/api/file/673ixn.jpg'),(8,3,'消毒剂',0.5,'http://localhost:8181/api/file/it2kkk.jpg'),(9,4,'书本',1,'http://localhost:8181/api/file/yah2gw.jpg'),(10,4,'报纸',1,'http://localhost:8181/api/file/ib637h.jpg'),(11,2,'空调',50,'http://localhost:8181/api/file/wed20f.jpg'),(12,2,'手机',500,'http://localhost:8181/api/file/ah7m9h.jpg'),(13,2,'电视',100,'http://localhost:8181/api/file/uw1go2.jpg'),(14,2,'笔记本电脑',300,'http://localhost:8181/api/file/wi0llz.jpg'),(15,3,'灯泡',1,'http://localhost:8181/api/file/ntwcdn.jpg');
/*!40000 ALTER TABLE `item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `item_type`
--

DROP TABLE IF EXISTS `item_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `item_type` (
  `Item_Type_ID` int(11) NOT NULL AUTO_INCREMENT,
  `Item_Type_Name` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`Item_Type_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `item_type`
--

LOCK TABLES `item_type` WRITE;
/*!40000 ALTER TABLE `item_type` DISABLE KEYS */;
INSERT INTO `item_type` VALUES (1,'钢铁'),(2,'电子设备'),(3,'有毒垃圾'),(4,'废纸');
/*!40000 ALTER TABLE `item_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `recycle_order_detail`
--

DROP TABLE IF EXISTS `recycle_order_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `recycle_order_detail` (
  `Recycle_Order_Detail_ID` int(11) NOT NULL AUTO_INCREMENT,
  `Item_ID` int(11) NOT NULL,
  `Recycle_Order_ID` int(11) NOT NULL,
  `Quantity` double DEFAULT NULL,
  PRIMARY KEY (`Recycle_Order_Detail_ID`),
  KEY `FK_Reference_6` (`Recycle_Order_ID`),
  KEY `FK_Reference_7` (`Item_ID`),
  CONSTRAINT `FK_Reference_6` FOREIGN KEY (`Recycle_Order_ID`) REFERENCES `recycle_orders` (`Recycle_Order_ID`),
  CONSTRAINT `FK_Reference_7` FOREIGN KEY (`Item_ID`) REFERENCES `item` (`Item_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=57 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `recycle_order_detail`
--

LOCK TABLES `recycle_order_detail` WRITE;
/*!40000 ALTER TABLE `recycle_order_detail` DISABLE KEYS */;
INSERT INTO `recycle_order_detail` VALUES (1,1,1,5),(2,3,1,10),(3,7,1,2),(4,8,1,2),(5,1,2,5),(6,3,2,10),(7,7,2,2),(8,8,2,2),(17,1,5,0.5),(18,1,6,0.5),(19,7,6,0.5),(20,1,7,1),(21,1,8,1),(23,2,10,1),(24,2,11,1),(25,1,3,2),(26,3,3,3),(27,7,3,4),(28,8,3,5),(29,1,4,9),(30,3,4,8),(31,7,4,7),(32,8,4,6),(33,1,9,1.5),(34,5,9,50),(39,3,12,4),(40,12,12,1),(41,8,12,1),(42,9,12,5),(45,13,13,0.5),(46,14,13,1),(47,12,14,3),(48,12,15,3),(49,12,16,3),(50,12,17,3),(51,12,18,3),(52,12,19,3),(53,15,20,3),(54,11,21,3),(56,11,22,3);
/*!40000 ALTER TABLE `recycle_order_detail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `recycle_orders`
--

DROP TABLE IF EXISTS `recycle_orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `recycle_orders` (
  `Recycle_Order_ID` int(11) NOT NULL AUTO_INCREMENT,
  `Address_ID` int(11) DEFAULT NULL,
  `Collector_Person_ID` int(11) DEFAULT NULL,
  `Evaluation_ID` int(11) DEFAULT NULL,
  `User_ID` int(11) DEFAULT NULL,
  `Scheduled_Time` datetime DEFAULT NULL,
  `Finished_Time` datetime DEFAULT NULL,
  `Total_Amount` double DEFAULT NULL,
  PRIMARY KEY (`Recycle_Order_ID`),
  KEY `FK_Reference_1` (`Address_ID`),
  KEY `FK_Reference_2` (`Collector_Person_ID`),
  KEY `FK_Reference_3` (`Evaluation_ID`),
  KEY `FK_Reference_5` (`User_ID`),
  CONSTRAINT `FK_Reference_1` FOREIGN KEY (`Address_ID`) REFERENCES `address` (`Address_ID`),
  CONSTRAINT `FK_Reference_2` FOREIGN KEY (`Collector_Person_ID`) REFERENCES `collector` (`Collector_Person_ID`),
  CONSTRAINT `FK_Reference_3` FOREIGN KEY (`Evaluation_ID`) REFERENCES `evaluation` (`Evaluation_ID`),
  CONSTRAINT `FK_Reference_5` FOREIGN KEY (`User_ID`) REFERENCES `user` (`User_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `recycle_orders`
--

LOCK TABLES `recycle_orders` WRITE;
/*!40000 ALTER TABLE `recycle_orders` DISABLE KEYS */;
INSERT INTO `recycle_orders` VALUES (1,1,1,1,1,'2020-12-16 17:00:00','2020-12-16 18:00:00',NULL),(2,1,1,NULL,1,'2020-12-18 08:00:00','2020-12-18 18:00:00',NULL),(3,1,1,3,1,'2020-12-11 11:00:00','2021-01-22 22:52:16',31.5),(4,1,1,6,1,'2020-12-15 10:00:00','2021-01-22 22:56:07',89.5),(5,1,NULL,NULL,1,'2021-01-12 00:00:00',NULL,1.75),(6,1,NULL,NULL,1,'2021-01-12 00:00:00',NULL,2.25),(7,1,1,NULL,1,'2020-12-29 00:00:00',NULL,3.5),(8,1,NULL,NULL,1,'2021-01-19 00:00:00',NULL,3.5),(9,1,1,4,1,'2020-12-16 17:00:00','2021-01-27 14:05:42',2505.25),(10,1,NULL,NULL,1,'2021-01-11 00:00:00',NULL,5.5),(11,1,NULL,NULL,1,'2021-01-13 00:04:34',NULL,5.5),(12,1,1,5,1,'2020-12-16 17:00:00','2021-01-27 14:21:37',529.5),(13,1,1,NULL,1,'2020-12-16 17:00:00','2021-01-27 14:25:17',350),(14,2,NULL,NULL,1,'2021-01-31 00:34:50',NULL,NULL),(15,2,NULL,NULL,1,'2021-01-31 00:34:50',NULL,NULL),(16,2,NULL,NULL,1,'2021-01-31 00:34:50',NULL,NULL),(17,2,NULL,NULL,1,'2021-01-31 00:34:50',NULL,NULL),(18,2,NULL,NULL,1,'2021-01-31 00:34:50',NULL,NULL),(19,3,NULL,NULL,1,'2021-01-31 00:40:03',NULL,NULL),(20,3,NULL,NULL,1,'2021-01-31 00:44:27',NULL,NULL),(21,3,NULL,NULL,1,'2021-01-31 00:49:04',NULL,NULL),(22,1,1,NULL,1,'2020-12-16 17:00:00','2021-01-31 00:53:22',150);
/*!40000 ALTER TABLE `recycle_orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `recycle_site`
--

DROP TABLE IF EXISTS `recycle_site`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `recycle_site` (
  `Site_ID` int(11) NOT NULL AUTO_INCREMENT,
  `Site_Name` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `Latitude` double DEFAULT NULL,
  `Longitude` double DEFAULT NULL,
  PRIMARY KEY (`Site_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `recycle_site`
--

LOCK TABLES `recycle_site` WRITE;
/*!40000 ALTER TABLE `recycle_site` DISABLE KEYS */;
INSERT INTO `recycle_site` VALUES (1,'浙江工业大学屏峰校区',30.226134,120.038201);
/*!40000 ALTER TABLE `recycle_site` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `User_ID` int(11) NOT NULL AUTO_INCREMENT,
  `User_Name` varchar(20) COLLATE utf8_bin NOT NULL,
  `User_RealName` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `Password` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `Phone` varchar(11) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`User_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'zqy','zqy','123456','13777820000');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-03-12 12:08:43
