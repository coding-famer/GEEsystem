-- MySQL dump 10.13  Distrib 8.0.25, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: test
-- ------------------------------------------------------
-- Server version	8.0.25

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
-- Table structure for table `college`
--

DROP TABLE IF EXISTS `college`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `college` (
  `cid` varchar(255) NOT NULL,
  `cname` varchar(50) DEFAULT NULL,
  `mid` varchar(255) NOT NULL,
  `mname` varchar(255) DEFAULT NULL,
  `entry2022` int DEFAULT NULL,
  `entry2021` int DEFAULT NULL,
  `entry2020` int DEFAULT NULL,
  `mcatid` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`cid`,`mid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `college`
--

LOCK TABLES `college` WRITE;
/*!40000 ALTER TABLE `college` DISABLE KEYS */;
INSERT INTO `college` VALUES ('10001','北京大学','025100','光华管理学院',424,419,420,'1'),('10001','北京大学','0812J3','前沿交叉学科研究院',384,383,379,'0'),('10001','北京大学','085400','软件与微电子学院',345,350,360,'0'),('10002','中国人民大学','025100','财政金融学院',404,410,404,'1'),('10002','中国人民大学','085400','电子信息',310,300,280,'0'),('10003','清华大学','025100','五道口金融学院',403,401,400,'1'),('10003','清华大学','077500','计算机科学与技术',365,369,355,'0'),('10248','上海交通大学','081200','计算机科学与技术',391,389,388,'0'),('10284','南京大学','025100','工程管理学院金融系',410,412,407,'1'),('10284','南京大学','081200','计算机科学与技术',389,387,380,'0'),('10286','东南大学','081200','计算机科学与技术',356,349,351,'0');
/*!40000 ALTER TABLE `college` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-05-04  9:57:56
