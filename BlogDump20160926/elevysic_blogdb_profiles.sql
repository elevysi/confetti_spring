-- MySQL dump 10.13  Distrib 5.7.9, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: elevysic_blogdb
-- ------------------------------------------------------
-- Server version	5.5.41-log

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
-- Table structure for table `profiles`
--

DROP TABLE IF EXISTS `profiles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `profiles` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `created` datetime DEFAULT NULL,
  `modified` datetime DEFAULT NULL,
  `profile_type_id` int(11) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `description` mediumtext,
  `title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `profiles`
--

LOCK TABLES `profiles` WRITE;
/*!40000 ALTER TABLE `profiles` DISABLE KEYS */;
INSERT INTO `profiles` VALUES (1,14,NULL,NULL,1,'dummy',NULL,NULL),(2,15,'2015-10-09 16:05:50','2015-10-09 16:05:50',1,'dummy',NULL,NULL),(4,16,'2015-10-13 10:13:25','2015-10-13 10:13:25',1,'dummy',NULL,NULL),(8,19,'2015-10-18 16:21:44','2015-10-18 16:21:44',1,'kobe',NULL,NULL),(9,20,'2015-10-18 16:24:17','2016-09-24 15:02:06',1,'kevin','<p>Cool kid acting plain nope&nbsp;<img src=\"../../../js/tinymce_4.4.1/tinymce/js/tinymce/plugins/emoticons/img/smiley-kiss.gif\" alt=\"kiss\" /></p>','Kevin Durant'),(10,21,'2015-11-06 21:26:48','2015-11-06 21:26:48',1,'steph',NULL,NULL),(11,22,'2015-11-06 23:19:07','2015-11-06 23:19:07',1,'james',NULL,NULL),(12,23,'2015-11-06 23:35:18','2015-11-06 23:35:18',1,'tony',NULL,NULL),(13,12,NULL,NULL,1,'admin',NULL,NULL),(14,24,'2016-07-29 09:39:14','2016-07-29 09:39:14',1,'elevysi',NULL,NULL),(15,25,'2016-09-15 14:48:01','2016-09-15 14:48:01',1,'music',NULL,NULL),(16,20,NULL,NULL,2,'Solution','','Road to starting with the Spring MVC Framework development');
/*!40000 ALTER TABLE `profiles` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-09-26 10:17:46
