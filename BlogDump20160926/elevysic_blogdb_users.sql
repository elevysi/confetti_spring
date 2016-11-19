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
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(255) NOT NULL,
  `last_name` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `active` int(1) NOT NULL DEFAULT '1',
  `created` datetime DEFAULT NULL,
  `modified` datetime DEFAULT NULL,
  `record_owner` int(11) DEFAULT NULL,
  `country` varchar(255) DEFAULT NULL,
  `town` varchar(255) DEFAULT NULL,
  `street` varchar(255) DEFAULT NULL,
  `postal_code` varchar(255) DEFAULT NULL,
  `phone_number` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (10,'Jane','Doe','jane','$2a$10$j6jJeXCYduoTXebq01LbeOitOOqWtGi35jRexRN.MpGiyMYdiib8K','jane@email.com',1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(12,'Elvis','Hatungimana','admin','$2a$10$/uXRPfSZaAERLx5aUHota.fUpgSsus6cbzAJyrl9Y2MK677PyQWci','admin@elevysi.com',1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(14,'John','Boss','john','$2a$10$yowWGLSDERIJmoQ90XKNCOfvo1eEIsBRYMEhiIWqGX3.1kZFmkZ86','john@elevysi.com',1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(15,'Jean','News','jean','$2a$10$MKHQTktR5gkk2i3cabA57O0pZAn8HdMaWMx422Lf/1CnPnbGy7kd6','news@elevysi.com',1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(16,'Lebron','James','lebron','$2a$10$OnOrzDvIR6W2FUWxhLoZJ.K31XEXLZSTz3FCQNS1hfX/F4QouZ5uu','lebron@cavaliers.com',1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(19,'Kobe','Bryant','kobe','$2a$10$pUmlqHbWs966.FG3dtkvvOyFCc6v9a0s.EmGIE37vM.wqNI4Qk7Ha','kobe@lakers.com',1,'2015-10-18 16:21:44','2015-10-18 16:21:44',NULL,NULL,NULL,NULL,NULL,NULL),(20,'Kevin','Durant','kevin','$2a$10$Qzd1BtwfAW8TDE9ovfheqO0Pu41GYI3g71hlPtDbJC.31NHN33qta','kevin@okc.com',1,'2015-10-18 16:24:17','2015-10-18 16:24:17',NULL,NULL,NULL,NULL,NULL,NULL),(21,'Steph','Curry','steph','$2a$10$2sI2UW8/1WebQh8MEgkjkOajksLauREMMBwoYryRGuJAnMDBvJ536','steph@warrioirs.com',1,'2015-11-06 21:26:48','2015-11-06 21:26:48',NULL,NULL,NULL,NULL,NULL,NULL),(22,'James','Harden','james','$2a$10$IQf1SqC1ScYk/rgSaQbHOuHbs1xdszQph9KvWByybxh8mDklaisWq','harden@houston.com',1,'2015-11-06 23:19:07','2015-11-06 23:19:07',NULL,NULL,NULL,NULL,NULL,NULL),(23,'Tony','Parker','tony','$2a$10$9VgmIS.VW97gdCv3ERHhLO9QI2ZCufQmKAN6FH.m013ssNh2jZg4y','parker@spurs.com',1,'2015-11-06 23:35:18','2015-11-06 23:35:18',NULL,NULL,NULL,NULL,NULL,NULL),(24,'Elvis','Hatungimana','elevysi','$2a$10$W3I28yoeicZC5A9M4hCTW.9AxQsYsijXllQqBr/8xJPIzSyjmpqla','elvis.hatungimana@elevysi.com',1,'2016-07-29 09:39:14','2016-07-29 09:39:14',NULL,NULL,NULL,NULL,NULL,NULL),(25,'Feeling','Someone','music','$2a$10$1Qn/SrelAh.Iy2SMlF5J3e0sZxyvjH0C8pUdM1retCuy1D.g7wq.i','fast@car.music.com',1,'2016-09-15 14:48:01','2016-09-15 14:48:01',NULL,NULL,NULL,NULL,NULL,NULL);
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

-- Dump completed on 2016-09-26 10:17:48
