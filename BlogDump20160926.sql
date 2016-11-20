-- MySQL dump 10.13  Distrib 5.6.31, for debian-linux-gnu (x86_64)
--
-- Host: localhost    Database: elevysic_blogdb
-- ------------------------------------------------------
-- Server version	5.6.31-0ubuntu0.15.10.1

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
-- Table structure for table `albums`
--
USE elevysic_blogdb;
DROP TABLE IF EXISTS `albums`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `albums` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `dateofshot` datetime DEFAULT NULL,
  `place` varchar(255) DEFAULT NULL,
  `created` datetime DEFAULT NULL,
  `modified` datetime DEFAULT NULL,
  `link_id` int(11) DEFAULT NULL,
  `link_table` varchar(45) DEFAULT NULL,
  `uuid` varchar(45) DEFAULT NULL,
  `publicAlbum` int(1) DEFAULT NULL,
  `publication_id` int(11) DEFAULT NULL,
  `count_comments` int(11) DEFAULT NULL,
  `count_likes` int(11) DEFAULT NULL,
  `count_shares` int(11) DEFAULT NULL,
  `dossier_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uuid_UNIQUE` (`uuid`)
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `albums`
--

LOCK TABLES `albums` WRITE;
/*!40000 ALTER TABLE `albums` DISABLE KEYS */;
INSERT INTO `albums` VALUES (38,NULL,NULL,'2016-08-24 10:24:05',NULL,'2016-08-24 10:24:05','2016-08-24 10:24:05',NULL,'posts',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(39,NULL,NULL,'2016-08-24 10:31:58',NULL,'2016-08-24 10:31:58','2016-08-24 10:31:58',64,'postss',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(40,'First Album','Getting started',NULL,'Lux','2016-08-24 10:35:44','2016-08-24 10:35:44',14,'profiles','aa599782-3fc3-4bbd-992c-6d7307d23baf',1,38,NULL,NULL,NULL,NULL),(43,'La life','Daily life photo shares',NULL,'Luxembourg','2016-11-01 18:10:04','2016-11-01 18:10:04',14,'profiles','16d7ec30-c3fe-4fcb-be1f-07c84044bc47',1,54,NULL,NULL,NULL,9);
/*!40000 ALTER TABLE `albums` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `categories`
--

DROP TABLE IF EXISTS `categories`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `categories` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(25) NOT NULL,
  `order` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categories`
--

LOCK TABLES `categories` WRITE;
/*!40000 ALTER TABLE `categories` DISABLE KEYS */;
INSERT INTO `categories` VALUES (1,'Life Style',NULL),(2,'Technology',NULL),(3,'Money',NULL),(4,'Social',NULL),(5,'Art',NULL),(6,'Music',NULL),(7,'Movies',NULL),(8,'Design',NULL);
/*!40000 ALTER TABLE `categories` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comments`
--

DROP TABLE IF EXISTS `comments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `comments` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `item_id` int(11) DEFAULT NULL,
  `item_type` varchar(255) DEFAULT NULL,
  `created` datetime DEFAULT NULL,
  `modified` datetime DEFAULT NULL,
  `profile_id` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `anonymous_email` varchar(255) DEFAULT NULL,
  `anonymous_name` varchar(255) DEFAULT NULL,
  `message` mediumtext,
  `uuid` varchar(45) DEFAULT NULL,
  `reply_to` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=76 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comments`
--

LOCK TABLES `comments` WRITE;
/*!40000 ALTER TABLE `comments` DISABLE KEYS */;
INSERT INTO `comments` VALUES (72,64,'posts','2016-08-24 10:37:09','2016-08-24 10:37:09',14,NULL,NULL,NULL,'I will comment',NULL,NULL),(73,64,'posts','2016-08-24 10:37:19','2016-08-24 10:37:19',14,NULL,NULL,NULL,'And I will reply',NULL,72),(74,66,'posts','2016-08-28 00:08:49','2016-08-28 00:08:49',14,NULL,NULL,NULL,'Trying comments',NULL,NULL),(75,10,'plays','2016-08-28 00:54:01','2016-08-28 00:54:01',13,NULL,NULL,NULL,'I comment a play',NULL,NULL);
/*!40000 ALTER TABLE `comments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `conversations`
--

DROP TABLE IF EXISTS `conversations`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `conversations` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `initiator` int(11) DEFAULT NULL,
  `created` datetime DEFAULT NULL,
  `modified` datetime DEFAULT NULL,
  `uuid` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `conversations`
--

LOCK TABLES `conversations` WRITE;
/*!40000 ALTER TABLE `conversations` DISABLE KEYS */;
INSERT INTO `conversations` VALUES (1,NULL,'2016-08-04 20:22:42','2016-08-04 20:22:42','28ec49bf-3b5f-4982-98b4-0116873ecc9d');
/*!40000 ALTER TABLE `conversations` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `correspondents`
--

DROP TABLE IF EXISTS `correspondents`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `correspondents` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `conversation_id` int(11) NOT NULL,
  `profile_id` int(11) NOT NULL,
  `created` datetime DEFAULT NULL,
  `modified` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `correspondents`
--

LOCK TABLES `correspondents` WRITE;
/*!40000 ALTER TABLE `correspondents` DISABLE KEYS */;
INSERT INTO `correspondents` VALUES (1,1,12,'2016-08-04 17:21:16','2016-08-04 17:21:16'),(2,1,14,'2016-08-04 17:21:16','2016-08-04 17:21:16');
/*!40000 ALTER TABLE `correspondents` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dossiers`
--

DROP TABLE IF EXISTS `dossiers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dossiers` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `created` datetime DEFAULT NULL,
  `modified` datetime DEFAULT NULL,
  `profile_id` int(11) DEFAULT NULL,
  `public` int(1) DEFAULT '1',
  `featured` int(1) DEFAULT '0',
  `description` mediumtext,
  `uuid` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dossiers`
--

LOCK TABLES `dossiers` WRITE;
/*!40000 ALTER TABLE `dossiers` DISABLE KEYS */;
INSERT INTO `dossiers` VALUES (2,'Spring Persistence',NULL,'2016-11-01 13:46:37',13,1,NULL,'<p>Series about Hibernate JPA in relation to Spring MVC.</p>','19f27576-77a6-43bd-96c0-947048c31b86'),(4,'The greatest love of all','2016-09-06 23:42:08','2016-09-06 23:42:08',14,1,0,'<p>Learning to love <em>yourslef</em> is the greatest love of all</p>','99ea5793-8f1f-4271-a447-2afb31776664'),(6,'Medailles','2016-09-07 13:27:40','2016-09-07 13:27:40',14,1,0,'<p>Olympics are <em>here</em></p>','6aed78a2-bcf9-45f0-80c0-1141a8d87545'),(7,'Elementaire',NULL,'2016-09-07 13:54:52',14,1,NULL,'<p>Et c est parti</p>','1569c441-b47a-4106-96d0-5a4e8a4fdad6'),(8,'On va voir','2016-09-07 13:50:05','2016-09-07 13:50:05',14,1,0,'<p>Phonen</p>','ebef69b0-251e-4dec-8e59-b6c30126ebc4'),(9,'Public life','2016-10-16 11:23:19','2016-10-16 11:23:19',14,1,0,'<p>Stream la life</p>','f4c4f392-2655-4c57-a696-35ae5824c918');
/*!40000 ALTER TABLE `dossiers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `likes`
--

DROP TABLE IF EXISTS `likes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `likes` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `item_id` int(11) DEFAULT NULL,
  `item_type` varchar(50) DEFAULT NULL,
  `created` datetime DEFAULT NULL,
  `modified` datetime DEFAULT NULL,
  `profile_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `likes`
--

LOCK TABLES `likes` WRITE;
/*!40000 ALTER TABLE `likes` DISABLE KEYS */;
INSERT INTO `likes` VALUES (2,54,'posts',NULL,NULL,13),(4,54,'posts','2016-07-14 14:34:44','2016-07-14 14:34:44',12),(5,53,'posts','2016-07-14 16:53:11','2016-07-14 16:53:11',9),(6,57,'posts','2016-07-29 10:11:46','2016-07-29 10:11:46',14),(7,2,'plays','2016-08-01 14:10:17','2016-08-01 14:10:17',13),(8,65,'posts','2016-08-24 10:41:15','2016-08-24 10:41:15',14);
/*!40000 ALTER TABLE `likes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `list_post_types`
--

DROP TABLE IF EXISTS `list_post_types`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `list_post_types` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `description` varchar(255) NOT NULL,
  `created` datetime NOT NULL,
  `modified` datetime DEFAULT NULL,
  `record_owner` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `list_post_types`
--

LOCK TABLES `list_post_types` WRITE;
/*!40000 ALTER TABLE `list_post_types` DISABLE KEYS */;
/*!40000 ALTER TABLE `list_post_types` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `media_kinds`
--

DROP TABLE IF EXISTS `media_kinds`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `media_kinds` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `media_kinds`
--

LOCK TABLES `media_kinds` WRITE;
/*!40000 ALTER TABLE `media_kinds` DISABLE KEYS */;
INSERT INTO `media_kinds` VALUES (1,'Image'),(2,'Video'),(3,'Audio'),(4,'Other');
/*!40000 ALTER TABLE `media_kinds` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `message_recipients`
--

DROP TABLE IF EXISTS `message_recipients`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `message_recipients` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `message_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `message_recipients`
--

LOCK TABLES `message_recipients` WRITE;
/*!40000 ALTER TABLE `message_recipients` DISABLE KEYS */;
/*!40000 ALTER TABLE `message_recipients` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `messages`
--

DROP TABLE IF EXISTS `messages`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `messages` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `message` mediumtext,
  `created` datetime DEFAULT NULL,
  `modified` datetime DEFAULT NULL,
  `conversation_id` int(11) NOT NULL,
  `sender` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `messages`
--

LOCK TABLES `messages` WRITE;
/*!40000 ALTER TABLE `messages` DISABLE KEYS */;
INSERT INTO `messages` VALUES (1,'Good','2016-08-04 17:21:16','2016-08-04 17:21:16',1,14),(2,'Ola my friend','2016-08-04 19:41:35','2016-08-04 19:41:35',1,12),(3,'Roof top?','2016-08-04 19:53:27','2016-08-04 19:53:27',1,14),(4,'I know it is hard','2016-08-04 20:06:20','2016-08-04 20:06:20',1,12),(5,'you do not know shit','2016-08-04 20:06:40','2016-08-04 20:06:40',1,14),(6,'You little prank','2016-08-04 20:06:57','2016-08-04 20:06:57',1,14),(7,'Of course i do','2016-08-04 20:07:32','2016-08-04 20:07:32',1,12),(8,'Of course i do','2016-08-04 20:07:41','2016-08-04 20:07:41',1,12),(9,'check error','2016-08-04 20:12:41','2016-08-04 20:12:41',1,14),(10,'let\'s chat','2016-08-04 20:22:27','2016-08-04 20:22:27',1,12),(11,'Straight up','2016-08-04 20:22:42','2016-08-04 20:22:42',1,14);
/*!40000 ALTER TABLE `messages` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `persistent_logins`
--

DROP TABLE IF EXISTS `persistent_logins`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `persistent_logins` (
  `username` varchar(64) NOT NULL,
  `series` varchar(64) NOT NULL,
  `token` varchar(64) NOT NULL,
  `last_used` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`series`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `persistent_logins`
--

LOCK TABLES `persistent_logins` WRITE;
/*!40000 ALTER TABLE `persistent_logins` DISABLE KEYS */;
/*!40000 ALTER TABLE `persistent_logins` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `play_types`
--

DROP TABLE IF EXISTS `play_types`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `play_types` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `order` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `play_types`
--

LOCK TABLES `play_types` WRITE;
/*!40000 ALTER TABLE `play_types` DISABLE KEYS */;
INSERT INTO `play_types` VALUES (1,'Video',1),(2,'Audio',2),(3,'Image',3),(4,'Other',4);
/*!40000 ALTER TABLE `play_types` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `plays`
--

DROP TABLE IF EXISTS `plays`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `plays` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `url` varchar(255) DEFAULT NULL,
  `featured` int(1) DEFAULT '0',
  `profile_id` int(11) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `description` mediumtext,
  `created` datetime DEFAULT NULL,
  `modified` datetime DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `embeddedUrl` varchar(255) DEFAULT NULL,
  `publication_id` int(11) DEFAULT NULL,
  `count_comments` int(11) DEFAULT NULL,
  `count_likes` int(11) DEFAULT NULL,
  `count_shares` int(11) DEFAULT NULL,
  `dossier_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `plays`
--

LOCK TABLES `plays` WRITE;
/*!40000 ALTER TABLE `plays` DISABLE KEYS */;
INSERT INTO `plays` VALUES (9,'https://www.youtube.com/embed/NK_e5_fKtIo',1,14,1,'<p>Good song and good dancing</p>','2016-08-24 10:33:22','2016-08-24 10:33:22','Wicked','https://www.youtube.com/embed/NK_e5_fKtIo',37,NULL,NULL,NULL,NULL),(10,'https://www.youtube.com/embed/ZYeVF9m9wdo',NULL,13,1,'<p>When you really look at it, life is plain simple. We always act as if something was promised to us but it really is not! Everything that we are given is extra. The present as they say is just a gift, it&rsquo;s an unexpected thing that lands in our hands, we do not deserve it any better than another. Looking at it from this perspective can ease up things and bring you some peace of mind.&nbsp;</p>\r\n<p>The mind seems to have loads of barriers which really are not ones. Limits, like fears, are just an illusion, said Michael Jordan. Now that is something to take away from someone that has been tried over and over again, still stood up and raised high. I even think that MJ had more mental strength than talent. He was an elite basketball player, that is for sure, but how many other guys can claim the same if not more talent. Thing is when you play at such a high level, pressure kicks in and if you cannot handle this, your talent alone will not make you shine without some mental strength. You can tell MJ was having fun playing basketball; he would not take easy lay ups, he would always do the show with crazy wicked moves. Now that is a sign of someone having fun; it is almost as if his mind would say: &ldquo;Too easy; let me add some twist to it by raising the difficulty level! &ldquo;. It seemed as if he had not much of mental barriers, as if he played like he had nothing to lose, as if he was always saying &ldquo;Why not?&rdquo;. They say Steph Curry is playing at an arrogant level but he still does not measure up to MJ in my opinion! MJ dunked and had his god damn tongue out all the time; why your tongue out MJ? Maybe because he just wanted, he was used to it that way!? and in the end why not? He was a character. It is like the colorful Soul vs. the conventional one from the &ldquo;Better call Soul&rdquo; show. One struggles but sticks to society lines while conflicting with his true nature and his call in life. He then goes on to switch his fancy suit for flashy orange / green suits; that right there is building a character, that is doing what he likes! Same with the court guys with middle age wigs? Why? Maybe because it is funny; well I don&rsquo;t know but I am not mad about this at all!</p>\r\n<p>So why struggle with barriers which in reality are not ones? I, personally, have made the decision that whenever I feel like fear is the one factor holding me back, I would go ahead and do it just for the sake of kicking fear&rsquo;s ass. Really there is so much that could go wrong, so much out of one&rsquo;s control that everyone owes themselves some courage and do what they want for nothing is guaranteed; both good and bad are always possible outcome so do yourself a favor and believe in the better! Sometimes I watch out for motivation and Prince EA on YouTube has got some of the most uplifting words out there. He said it best with &ldquo;Courage is not the absence of fear, but the judgement that something is more important than that fear&rdquo;. You cannot let fear control your decisions, as living in fear is not living at all.</p>',NULL,'2016-11-01 13:18:46','Once in while I ask myself, what am I doing?','https://www.youtube.com/embed/ZYeVF9m9wdo',59,NULL,NULL,NULL,9),(11,'https://www.youtube.com/embed/4sSKWNV3Gvk',1,14,1,'<p>Ever thought why something you find great is so underrated? Don\'t they see? That is how I feel about this song; for me it is one of the best songs ever made, period! I remember, when it came out, we used to go mad happy when it was played in the club, singing along the lyrics and asking the DJ for a replay. But for some reason it never really kikced in with the mainstream people! Timbaland was on fire at that moment and all eyes were on him, so why didn\'t this become a classic? Plus we love Jojo, don\'t we!?</p>','2016-09-02 12:00:54','2016-09-02 12:00:54','Talkin\' about my bae','https://www.youtube.com/embed/4sSKWNV3Gvk',47,NULL,NULL,NULL,NULL),(12,'https://www.youtube.com/embed/BPwCd9vjO6E',1,14,1,'<p>Drake\'s music is sometimes best when you are down. I was listening to <strong>Nothing was the same</strong> album and thinking: When Rihanna dumps your ass, we\'re gonna have such a great album Drake! I don\'t wish it for you though, I love your music!</p>','2016-09-02 12:05:34','2016-09-02 12:05:34','Don\'t think about it too much','https://www.youtube.com/embed/BPwCd9vjO6E',48,NULL,NULL,NULL,NULL),(13,'https://www.youtube.com/embed/eqceBawW4kM',1,14,1,'<p>Still a hall famer song; it&rsquo;s very catchy, kinda wanna take you into doing a little two steps dance move. It is a cute song, nothing very outstanding but I find it so good! Definitely one of my favorites. Jaicko says he likes nice things but does not need them to be happy, kinda capture the spirit of a humble baller: yes flash it once in a while if you can but don&rsquo;t let it get to your head, you know what I mean?! Live big or live broke, you can still be happy.</p>','2016-09-04 21:54:46','2016-09-04 21:54:46','Two Piece','https://www.youtube.com/embed/eqceBawW4kM',49,NULL,NULL,NULL,NULL),(14,'https://www.youtube.com/embed/FSnAllHtG70',NULL,14,1,'<p>Nice dossier</p>',NULL,'2016-11-02 20:31:05','The sound','https://www.youtube.com/embed/FSnAllHtG70',64,NULL,NULL,NULL,8);
/*!40000 ALTER TABLE `plays` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `post_categories`
--

DROP TABLE IF EXISTS `post_categories`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `post_categories` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `post_id` int(11) NOT NULL,
  `category_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `post_categories`
--

LOCK TABLES `post_categories` WRITE;
/*!40000 ALTER TABLE `post_categories` DISABLE KEYS */;
INSERT INTO `post_categories` VALUES (1,32,1),(2,34,2),(3,34,4),(4,35,2),(5,36,1),(6,36,4),(7,38,1),(8,40,1),(9,40,3),(10,43,1),(11,43,4),(12,44,1),(13,44,2),(14,44,3),(15,45,1),(16,45,2),(17,48,1),(18,48,3),(19,51,1),(20,51,4),(21,51,7),(22,53,3),(23,56,1),(24,57,4),(25,58,2),(26,59,2),(27,60,2),(28,61,2),(29,62,2),(30,63,1),(31,64,2),(33,66,2),(36,65,2),(41,70,4),(42,67,2);
/*!40000 ALTER TABLE `post_categories` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `post_statuses`
--

DROP TABLE IF EXISTS `post_statuses`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `post_statuses` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `post_statuses`
--

LOCK TABLES `post_statuses` WRITE;
/*!40000 ALTER TABLE `post_statuses` DISABLE KEYS */;
INSERT INTO `post_statuses` VALUES (1,'Draft'),(2,'Published'),(3,'To be published');
/*!40000 ALTER TABLE `post_statuses` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `posts`
--

DROP TABLE IF EXISTS `posts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `posts` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) NOT NULL,
  `description` varchar(45) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `created` datetime DEFAULT NULL,
  `modified` datetime DEFAULT NULL,
  `content` mediumtext,
  `upload_id` int(11) DEFAULT NULL,
  `type_id` int(11) DEFAULT NULL,
  `profile_id` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `uuid` varchar(45) DEFAULT NULL,
  `publication_id` int(11) DEFAULT NULL,
  `count_comments` int(11) DEFAULT NULL,
  `count_likes` int(11) DEFAULT NULL,
  `count_shares` int(11) DEFAULT NULL,
  `dossier_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uid_UNIQUE` (`uuid`)
) ENGINE=InnoDB AUTO_INCREMENT=71 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `posts`
--

LOCK TABLES `posts` WRITE;
/*!40000 ALTER TABLE `posts` DISABLE KEYS */;
INSERT INTO `posts` VALUES (63,'Premier post pour voir','',24,'2016-08-24 10:24:05','2016-08-24 10:24:05','<p>Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu, pretium quis, sem. Nulla consequat massa quis enim. Donec pede justo, fringilla vel, aliquet nec, vulputate eget, arcu. In enim justo, rhoncus ut, imperdiet a, venenatis vitae, justo. Nullam dictum felis eu pede mollis pretium. Integer tincidunt. Cras dapibus. Vivamus elementum semper nisi. Aenean vulputate eleifend tellus. Aenean leo ligula, porttitor eu, consequat vitae, eleifend ac, enim. Aliquam lorem ante, dapibus in, viverra quis, feugiat a, tellus. Phasellus viverra nulla ut metus varius laoreet. Quisque rutrum. Aenean imperdiet. Etiam ultricies nisi vel augue. Curabitur ullamcorper ultricies nisi. Nam eget dui. Etiam rhoncus. Maecenas tempus, tellus eget condimentum rhoncus, sem quam semper libero, sit amet adipiscing sem neque sed ipsum. Nam quam nunc, blandit vel, luctus pulvinar, hendrerit id, lorem. Maecenas nec odio et ante tincidunt tempus. Donec vitae sapien ut libero venenatis faucibus. Nullam quis ante. Etiam sit amet orci eget eros faucibus tincidunt. Duis leo. Sed fringilla mauris sit amet nibh. Donec sodales sagittis magna. Sed consequat, leo eget bibendum sodales, augue velit cursus nun et voila.</p>',NULL,NULL,14,1,'e48c54e2-5d98-4eea-90d7-8ce8b9414e7a',35,NULL,NULL,NULL,NULL),(64,'Second Post','',24,'2016-08-24 10:31:58','2016-08-24 10:31:58','<p>One morning, when Gregor Samsa woke from troubled dreams, he found himself transformed in his bed into a horrible vermin. He lay on his armour-like back, and if he lifted his head a little he could see his brown belly, slightly domed and divided by arches into stiff sections. The bedding was hardly able to cover it and seemed ready to slide off any moment. His many legs, pitifully thin compared with the size of the rest of him, waved about helplessly as he looked. \"What\'s happened to me?\" he thought. It wasn\'t a dream. His room, a proper human room although a little too small, lay peacefully between its four familiar walls. A collection of textile samples lay spread out on the table - Samsa was a travelling salesman - and above it there hung a picture that he had recently cut out of an illustrated magazine and housed in a nice, gilded frame. It showed a lady fitted out with a fur hat and fur boa who sat upright, raising a heavy fur muff that covered the whole of her lower arm towards the viewer. Gregor then turned to look out the window at the dull weather. Drops</p>',NULL,NULL,14,1,'20d6d0cb-65de-4c42-ae39-50e413dd28e8',36,NULL,NULL,NULL,NULL),(65,'Code Posts','Demo some codes',24,'2016-08-25 16:14:01','2016-08-28 11:57:54','<p>Today I am going to demonstrate how we can do some coding here on the blog; keep posted loads of tutorials and goodies are coming your way. Remember, it is within everyone\'s reach.</p>\r\n<pre class=\"brush:java;auto-links:false;toolbar:false\" contenteditable=\"false\">package com.elevysi.site.entity;\r\n\r\nimport java.io.Serializable;\r\nimport java.util.List;\r\n\r\nimport javax.persistence.Entity;\r\nimport javax.persistence.FetchType;\r\nimport javax.persistence.GeneratedValue;\r\nimport javax.persistence.GenerationType;\r\nimport javax.persistence.Id;\r\nimport javax.persistence.ManyToMany;\r\nimport javax.persistence.Table;\r\n\r\n@Entity\r\n@Table(name = \"roles\")\r\npublic class Role implements Serializable{\r\n	\r\n	/**\r\n	 * \r\n	 */\r\n	private static final long serialVersionUID = -8561184637783916879L;\r\n\r\n	@Id\r\n	@GeneratedValue(strategy = GenerationType.IDENTITY)\r\n	private Integer id;\r\n	\r\n	private String name;\r\n	\r\n	\r\n	@ManyToMany(mappedBy = \"roles\", fetch = FetchType.LAZY)\r\n    private List&lt;User&gt; userRoles;\r\n	\r\n\r\n\r\n	public Integer getId() {\r\n		return id;\r\n	}\r\n\r\n\r\n	public void setId(Integer id) {\r\n		this.id = id;\r\n	}\r\n	\r\n	public String getName() {\r\n		return name;\r\n	}\r\n\r\n\r\n	public void setName(String name) {\r\n		this.name = name;\r\n	}\r\n\r\n\r\n	public List&lt;User&gt; getUserRoles() {\r\n		return userRoles;\r\n	}\r\n\r\n\r\n	public void setUserRoles(List&lt;User&gt; userRoles) {\r\n		this.userRoles = userRoles;\r\n	}\r\n\r\n\r\n	\r\n	\r\n}\r\n</pre>\r\n<p>And here I gonna show some xml</p>\r\n<pre class=\"brush:xml;auto-links:false;toolbar:false\" contenteditable=\"false\">&lt;?xml version=\"1.0\" encoding=\"UTF-8\"?&gt;\r\n&lt;beans xmlns=\"http://www.springframework.org/schema/beans\"\r\n	xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\r\n	xsi:schemaLocation=\"http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans-3.2.xsd\"&gt;\r\n\r\n	 \r\n	 &lt;beans profile=\"dev\"&gt;\r\n	 	&lt;bean id=\"dataSource\" class=\"org.springframework.jdbc.datasource.DriverManagerDataSource\"&gt;\r\n		    &lt;property name=\"driverClassName\" value=\"${db.driver}\"/&gt;\r\n		    &lt;property name=\"url\" value=\"${db.url}\"/&gt;\r\n		    &lt;property name=\"username\" value=\"${db.username}\"/&gt;\r\n		    &lt;property name=\"password\" value=\"${db.password}\"/&gt;\r\n	 	&lt;/bean&gt;\r\n	 &lt;/beans&gt;\r\n	 \r\n	\r\n \r\n \r\n&lt;/beans&gt;</pre>\r\n<p>&nbsp;</p>',NULL,NULL,14,1,'12f7fd96-08b9-4fcf-88a4-15832a7981e1',44,NULL,NULL,NULL,NULL),(66,'Road to starting with the Spring MVC Framework development','Starting up',24,'2016-08-24 13:52:28','2016-11-01 13:06:43','<p>Coming from the broader term of developer, I have set an objective to become a Web developer. On our way to lunch, a more experienced colleague of mine once told me: \"Look out for the most valuable skills required in your field and make sure you master at least 80% of them\". He is not from the web development word as me but has a technical profile within automation and electric engineering. I thought it was a good idea to follow his advice since he appeared to be successful at what he does. <br /> For me to go this way, I needed to scope my area of expertise and focus on a single aspect to which I could claim 80% of the knowledge. They say that a dream without a goal remains a dream so I made sure I could set up a goal that I could achieve and whose impact would add me the most value. <br /> With all this in mind, I then decided to go for web development as my area of expertise, given that this was my first job. Everything seemed to sum up since my whole higher education had evolved around web information systems and the first job I landed was also in the same area.<br /> The issue still was that Web development was a very gigantic area; there was no way I could claim that much amount of knowledge in it. However, I thought I already knew some notions in web development and could always improve myself. &nbsp;</p>\r\n<p>For the record, my first ever full time professional project was a CakePHP 2.x project; I was introduced to a project that had just been started. It was not so difficult to catch up as I had some guidance throughout my first steps. I had access to project code that I could just use for best practices inspiration. As I was becoming more familiar with CakePHP, I thought it was the right time to see how other frameworks performed. This was in line with my objective and could enlarge my range of knowledge. My aim was first to identify which accessible web framework would add me the most value and start learning them. Unfortunately, CakePHP did not seem to be the most value I could show out there; it seemed to be more popular for personal and small projects rather than enterprise solutions. I swallowed my disappointment and started looking out for what\'s better; .NET and JAVA emerged as the big boys&rsquo; toys. I managed to get started with .NET through ASP MVC since it seems to be leading the way. However, for a self-teaching pal like me, it\'s not that accessible since it relies on payed products (you can still get free community editions). I wanted to run my blog through the solution I picked and my hosting could not allow me to afford a setting that could accommodate such a web site.</p>\r\n<p>JAVA, on the other side, with its popular Spring framework seemed to be a more accessible alternative; I then decided to start the adventure with Spring and see how far I would go.<br /> As I was already familiar with the CakePHP framework, I expected to pick up the Spring framework, at least the MVC module, very quickly. I had also done the same for Python Django and managed to do a little trip using ASP MVC. From my learning experience, these MVC frameworks seemed to have the same underlying notions and principles and nothing seemed ever out of reach.</p>\r\n<p>The problem I first faced here was getting started; my PC was already set up for PHP development and I needed to get it ready for JAVA EE development.</p>\r\n<p>I looked through JAVA versions, set up the JDKs, put it in the PATH variables, searched for the right IDE and the most popular free Server I could get. Summarized this way, it is sounds manageable! That would be without considering confusion, lack of time, tiredness, lack of results and therefore low motivation: there was the time to pick up where I had left previous installations, after two weeks of non-related activity, having forgotten some of the details; there was the time to change the computer or the OS, changing the whole previous environment, setting up and adapting it to small incompatibilities here and there. It was a lot for a guy that has a regular full time job and some social life.</p>\r\n<p>As I like things done the right way, every time I saw something debatable, I would research alternatives. Consequently, I would always end up with loads of information that would add up to the confusion and leave me undecided. This was just the environment set up, long before I could start writing up any framework code. After I was able to get the environment set up, I then started looking for online tutorial for coding.</p>\r\n<p>I am one of these &ldquo;learn by doing&rdquo; guys; I\'d rather learn from a small but complete real life case tutorial and relate it to my own situation. However, there seem to only be chunks of projects on the internet; nothing was complete, nothing could take me from scratch to realize something not too trivial but not too advanced neither. Spring documentation is available on the official web site but I did not find it very useful at that point for a guy starting afresh. For a technology that is open source, JAVA related, one of the most popular programming languages, the resources seemed very scarce. I finally came across a YouTube playlist, teaching from scratch the development of web DVD retailing store. It was such a refreshing feeling that I re-discovered the need for information sharing; I felt a moral duty to support such actions (well I did like each of his YouTube videos :-)).</p>\r\n<p>As I was following the tutorial and implementing the system myself, I had decided to use the latest Spring versions and dependencies every time I could. Good idea! But this would open the door to inconsistencies with the tutorial, to more research, more information, more confusion and therefore me being stuck again. There seemed to be a thousand ways in which each thing could be done; I couldn&rsquo;t pick which version was the standard one or which one was the best. I would find out that the solution to my problem was not the latest standard, would look for the latest solution which would thereafter introduce new aspects and vocabulary that I had not encountered so far. Coming from CakePHP, I had never encountered such problems; there are standards that each seem to be sticking to. Spring MVC, on the other side, has got so many alternatives that it is hard for a newbie to pick the right one. It is fantasy land, everything is configurable, everything is possible! After being hit by the lack of comprehensive learning resources for a starter, I would come to face the spread of principles. I was re-discovering the &ldquo;Open Sourceness&rdquo; in the framework since several third parties collaborate to improve the product; it is not something such as ASP MVC which has a very powerful industry provider keeping everybody in the same lane.</p>\r\n<p>The most confusing things were dependencies, configurations, the data access layer and the security layer (which I will be tackling later). <br /> Dependencies were confusing because you have to know which ones one need, which versions are compatible to work together. How would a newbie know which versions are supposed to be coupled? Well endless stack overflow querying, hoping that someone would have had the same issue. Fortunately, a simple copy paste of the exception stack trace, even the most random ones, always seemed to have already happened to a stack overflow user before. Look at the answer, get exposed to new notions, new vocabulary and BAM! More confusion once again. Patience is the biggest virtue with this. Then comes web.xml, application context, dispatcher servlet, properties and configuration files. What is the difference between those, what are the best practices for dealing with application configurations? XML or JAVA class configurations? A hand from an experienced pal would have been very welcomed to reduce my confusion level.</p>\r\n<p>Configurations were also confusing because there are so many ways to write configurations, so many parameters involved of which we do not always know the meaning. As much as you would think that the Web module has the basic features, you need to configure almost everything; nothing comes out of the box, at least not as it does for CakePHP.</p>\r\n<p>The data Access Layer was the most confusing part. You need to first pick up the vocabulary. What is JPA, Hibernate, EclipseLink, ORM, DAO, Repository and how do they differ from each other. Why does this solution imply there are different implementations of JPA, I thought there was only one of them? Why so many though? Which one to choose?&nbsp; I do not think EclipseLink has to do with JPA, isn&rsquo;t Eclipse an IDE? This has got to be some plugin to ease JPA language safe typing and validation?! Hit the web for an answer and find more vocabulary and more confusion. What of persisting an entity, transient state, detach, merge, flush, transaction manager, JPQL, Typed Query, Query builder?&nbsp; Well this was just the beginning.</p>\r\n<p>Next confusion is entity associations. You might set up a lazy or an eager fetching for associated data. Since the beginning, it was clear that everything shall be lazily fetched to avoid the many exceptions that I was hit up with each time. But even by doing so, performance was not still at its best. There was the n+1 problem for which I quickly found a solution. Use JPQL to write queries and fetch data with JOINS. What I did not consider was that the JOIN method was not that performant neither. I had omitted the Cartesian product problem that results from using JOINS in SQL! So what is the solution for this? Nobody can tell; it all depends on your specifics. There are some strategies to improve performance but none of them guaranteed better performance for a systematic and blind use. Before using Spring MVC, I did not care about the JOINS Cartesian Products; everything always seemed to be performing good for my small projects; deep researching introduced me to new notions that are essential to best practices and that I can use in any SQL involved project.</p>\r\n<p>Keep in mind that I am a newbie, learning by mistake and already concerned about performance issues. This was too much but I did not give up! I have a goal and I have got to keep it going to keep the dream alive. As I am writing this post, I am still confused. However, I have come to find my best friends for this adventure in books. I now own copies of Craig Wall&rsquo;s Spring in Action and Spring Boot in Action. Spring boot would have put me on track more quickly but I never knew about it at my early stages. I started almost by the same time it was coming out to the public. I plan to improve my data access layer by also reading Hibernate in Action.&nbsp; I will hence be blogging a lot about the Spring Framework. I am starting a series of posts which will tackle aspects that I found problematic or just interesting.</p>\r\n<p>Along the confusion, the Spring Framework is a very powerful tool. There is so much to learn because it is such a rich framework which one can tailor to fit their needs. It does require more fluency in some aspects than other frameworks, but I think it is a sure value that can help master the underlying principles of web development hence easing the learning paths of other tools in this age of ever changing technology. And that is the value I found in Spring; in all these complications and me trying to solve them gave me insight on some principles that I had been ignoring for so long! All these web frameworks seem to be built from the same rock! They all rely on the same principles that are just adapted from programming language / environment to the other.</p>',NULL,NULL,14,1,'a9ba33f3-a86b-4413-a645-cf150d54fdb5',40,NULL,NULL,NULL,NULL),(67,'Spring MVC 4.x @Profile, Datasource configuration, Hibernate 5.x JPA configurations','SpringMVC 4.x profiles',24,'2016-08-25 12:20:54','2016-11-01 16:03:24','<p>Moving an application from one environment to the other can be messy. If I want to put the application in production for instance, I need to change a few settings because my development environment does not use the same settings values. Some settings are also useful for development and not appropriate for production, like the logging level. In my case, I also have base upload paths that vary from one instance to the other. <br />I have a Spring MVC 4.2.1 application that has a jdbc datasource to a mysql database. I use hibernate 5.0.1 as the JPA provider. <br />To avoid having to change over and over the settings from one environment to the other, I use application properties files and profiles configuration files. The properties file has the settings for the environment while the configuration file will be referring to the properties values.<br />I have two java classes, one for dev and one for prod. Both are declared with the <em>@Configuration</em> and <em>@Profile</em> annotations. The first indicates that the class is to be considered as a configuration file while the second makes it a profile. The profile annotation has the name of the profile that will be used later on to make it active in web.xml in the form of <em>@Profile(&ldquo;profile_name&rdquo;)</em>. Each configuration profile has a corresponding application properties file that has the right values for the concerned setting. The properties file is declared through the annotation <em>@PropertySource</em>. Below is my Production profile class:</p>\r\n<pre class=\"brush:java;auto-links:false;toolbar:false\" contenteditable=\"false\">package com.elevysi.site.profile;\r\n\r\nimport java.util.Properties;\r\n\r\nimport javax.persistence.EntityManagerFactory;\r\nimport javax.sql.DataSource;\r\n\r\nimport org.hibernate.SessionFactory;\r\nimport org.springframework.beans.factory.annotation.Autowired;\r\nimport org.springframework.context.annotation.Bean;\r\nimport org.springframework.context.annotation.ComponentScan;\r\nimport org.springframework.context.annotation.Configuration;\r\nimport org.springframework.context.annotation.Profile;\r\nimport org.springframework.context.annotation.PropertySource;\r\nimport org.springframework.core.env.Environment;\r\nimport org.springframework.jdbc.datasource.DriverManagerDataSource;\r\nimport org.springframework.orm.hibernate5.HibernateTransactionManager;\r\nimport org.springframework.orm.hibernate5.LocalSessionFactoryBean;\r\nimport org.springframework.orm.jpa.JpaTransactionManager;\r\nimport org.springframework.orm.jpa.JpaVendorAdapter;\r\nimport org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;\r\nimport org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;\r\nimport org.springframework.transaction.PlatformTransactionManager;\r\n\r\n@Profile(\"prod\")\r\n@Configuration\r\n@PropertySource(\"classpath:META-INF/application-prod.properties\")\r\npublic class ProductionProfile {\r\n	\r\n	@Autowired\r\n	private Environment environment;\r\n\r\n	\r\n	@Bean\r\n    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {\r\n	      LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();\r\n	      em.setDataSource(dataSource());\r\n	      em.setPackagesToScan(new String[] { \"com.elevysi.site.entity\" });\r\n	 \r\n	      JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();\r\n	      em.setJpaVendorAdapter(vendorAdapter);\r\n	      em.setJpaProperties(hibernateProperties());\r\n	 \r\n	      return em;\r\n    }\r\n	\r\n	@Bean\r\n	public DataSource dataSource(){\r\n		DriverManagerDataSource dataSource = new DriverManagerDataSource();\r\n	    \r\n		dataSource.setDriverClassName(environment.getRequiredProperty(\"db.driver\"));\r\n		dataSource.setUrl(environment.getRequiredProperty(\"db.url\"));\r\n		dataSource.setUsername(environment.getRequiredProperty(\"db.username\"));\r\n	    dataSource.setPassword(environment.getRequiredProperty(\"db.password\"));\r\n	    \r\n	     \r\n	    return dataSource;\r\n	}\r\n	\r\n	\r\n	 private Properties hibernateProperties() {\r\n		 \r\n	        Properties properties = new Properties();\r\n	        properties.put(\"hibernate.dialect\", environment.getRequiredProperty(\"hibernate.dialect\"));\r\n	        properties.put(\"hibernate.show_sql\", environment.getRequiredProperty(\"hibernate.show_sql\"));\r\n	        properties.put(\"hibernate.max_fetch_depth\", environment.getRequiredProperty(\"hibernate.max_fetch_depth\"));\r\n	        properties.put(\"hibernate.fetch_size\", environment.getRequiredProperty(\"hibernate.fetch_size\"));\r\n	        properties.put(\"hibernate.batch_size\", environment.getRequiredProperty(\"hibernate.batch_size\"));\r\n	        return properties;        \r\n    }\r\n	     \r\n\r\n    \r\n    @Bean\r\n    public PlatformTransactionManager transactionManager(EntityManagerFactory emf){\r\n       JpaTransactionManager transactionManager = new JpaTransactionManager();\r\n       transactionManager.setEntityManagerFactory(emf);\r\n  \r\n       return transactionManager;\r\n    }\r\n	\r\n\r\n}\r\n</pre>\r\n<p>Note that the Dev profile class has exactly the same details as the production one except for the&nbsp;<em>@PropertySource</em> and the&nbsp;<em>@</em><em>Profile(profile_name)&nbsp;</em></p>\r\n<pre class=\"brush:java;auto-links:false;toolbar:false\" contenteditable=\"false\">import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;\r\nimport org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;\r\nimport org.springframework.transaction.PlatformTransactionManager;\r\n\r\n@Profile(\"dev\")\r\n@Configuration\r\n@PropertySource(\"classpath:META-INF/application-dev.properties\")\r\npublic class DevelopmentProfile {</pre>\r\n<p>Both properties files will enclose the same properties with different values:</p>\r\n<pre class=\"brush:plain;auto-links:false;toolbar:false\" contenteditable=\"false\">#DB properties:\r\ndb.driver=com.mysql.jdbc.Driver\r\ndb.url=jdbc:mysql://localhost:3306/db_name\r\ndb.username=your_sql_username\r\ndb.password=ypur_sql_password\r\n\r\n#Hibernate Configuration:\r\nhibernate.dialect=org.hibernate.dialect.MySQL5InnoDBDialect\r\nhibernate.show_sql=false\r\nhibernate.max_fetch_depth=3\r\nhibernate.fetch_size=50\r\nhibernate.batch_size=10\r\nentitymanager.packages.to.scan=com.elevysi.site.entity</pre>\r\n<p>To recap, I have two configuration profiles:<br />&bull; <em>ProductionProfile</em> using <em>application-dev.properties</em><br />&bull; <em>DevelopmentProfile</em> using <em>application-prod.properties</em><br />To make one or several profiles active, refer to your web.xml and add the following:</p>\r\n<pre class=\"brush:xml;auto-links:false;toolbar:false\" contenteditable=\"false\">&lt;context-param&gt;\r\n	&lt;param-name&gt;spring.profiles.active&lt;/param-name&gt;\r\n	&lt;param-value&gt;dev&lt;/param-value&gt;\r\n&lt;/context-param&gt;</pre>\r\n<p>In the above code, adapt the active profile by changing the <em>&lt;param-value&gt;</em> to the profile name.</p>',NULL,NULL,14,1,'c08da2bd-764a-4621-ad2c-f8f045467946',42,NULL,NULL,NULL,2),(70,'Temps d\'utilisation des reseaux sociaux','Comment faire',NULL,'2016-09-06 20:04:25','2016-09-06 20:04:25','<p>Far far away, behind the word mountains, far from the countries Vokalia and Consonantia, there live the blind texts. Separated they live in Bookmarksgrove right at the coast of the Semantics, a large language ocean. A small river named Duden flows by their place and supplies it with the necessary regelialia. It is a paradisematic country, in which roasted parts of sentences fly into your mouth. Even the all-powerful Pointing has no control about the blind texts it is an almost unorthographic life One day however a small line of blind text by the name of Lorem Ipsum decided to leave for the far World of Grammar. The Big Oxmox advised her not to do so, because there were thousands of bad Commas, wild Question Marks and devious Semikoli, but the Little Blind Text didn&rsquo;t listen. She packed her seven versalia, put her initial into the belt and made herself on the way. When she reached the first hills of the Italic Mountains, she had a last view back on the skyline of her hometown Bookmarksgrove, the headline of Alphabet Village and the subline of her own road, the Line Lane. Pityful a rethoric question ran over her cheek, then</p>',NULL,NULL,13,2,'d8509018-ee76-4b48-9023-b9442f6dbc23',51,NULL,NULL,NULL,2);
/*!40000 ALTER TABLE `posts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `products`
--

DROP TABLE IF EXISTS `products`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `products` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) DEFAULT NULL,
  `description` mediumtext,
  `price` decimal(10,2) DEFAULT NULL,
  `sale_price` decimal(10,2) DEFAULT NULL,
  `profile_id` int(11) DEFAULT NULL,
  `created` datetime DEFAULT NULL,
  `modified` datetime DEFAULT NULL,
  `bought_by` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `products`
--

LOCK TABLES `products` WRITE;
/*!40000 ALTER TABLE `products` DISABLE KEYS */;
/*!40000 ALTER TABLE `products` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `profile_configurations`
--

DROP TABLE IF EXISTS `profile_configurations`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `profile_configurations` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `profile_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `profile_configurations`
--

LOCK TABLES `profile_configurations` WRITE;
/*!40000 ALTER TABLE `profile_configurations` DISABLE KEYS */;
/*!40000 ALTER TABLE `profile_configurations` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `profile_friends`
--

DROP TABLE IF EXISTS `profile_friends`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `profile_friends` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `profile_id` int(11) NOT NULL,
  `friend_id` int(11) NOT NULL,
  `created` datetime DEFAULT NULL,
  `modified` datetime DEFAULT NULL,
  `initiator` int(11) DEFAULT NULL,
  `accepted` int(1) DEFAULT '1',
  `request_sent_on` datetime DEFAULT NULL,
  `request_accepted_on` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `profile_friends`
--

LOCK TABLES `profile_friends` WRITE;
/*!40000 ALTER TABLE `profile_friends` DISABLE KEYS */;
INSERT INTO `profile_friends` VALUES (1,3,1,'2015-10-09 16:05:50','2015-10-09 16:05:50',3,1,'2015-10-09 16:05:50','2015-10-09 16:05:50'),(2,3,2,'2015-10-09 16:05:50','2015-10-09 16:05:50',3,1,'2015-10-09 16:05:50','2015-10-09 16:05:50'),(10,5,4,NULL,NULL,NULL,1,NULL,NULL),(11,4,5,NULL,NULL,NULL,1,NULL,NULL),(12,14,13,NULL,NULL,NULL,1,NULL,NULL),(13,12,14,NULL,NULL,NULL,1,NULL,NULL),(14,9,14,NULL,NULL,NULL,1,NULL,NULL);
/*!40000 ALTER TABLE `profile_friends` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `profile_types`
--

DROP TABLE IF EXISTS `profile_types`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `profile_types` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `created` datetime DEFAULT NULL,
  `modified` datetime DEFAULT NULL,
  `record_owner` int(11) DEFAULT NULL,
  `basic` int(1) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `profile_types`
--

LOCK TABLES `profile_types` WRITE;
/*!40000 ALTER TABLE `profile_types` DISABLE KEYS */;
INSERT INTO `profile_types` VALUES (1,'user',NULL,NULL,NULL,1),(2,'Pseudo',NULL,NULL,NULL,0),(3,'Public',NULL,NULL,NULL,0),(4,'Advert',NULL,NULL,NULL,0),(5,'Admin',NULL,NULL,NULL,0);
/*!40000 ALTER TABLE `profile_types` ENABLE KEYS */;
UNLOCK TABLES;

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
INSERT INTO `profiles` VALUES (1,14,NULL,NULL,1,'dummy',NULL,NULL),(2,15,'2015-10-09 16:05:50','2015-10-09 16:05:50',1,'dummy',NULL,NULL),(4,16,'2015-10-13 10:13:25','2015-10-13 10:13:25',1,'dummy',NULL,NULL),(8,19,'2015-10-18 16:21:44','2015-10-18 16:21:44',1,'kobe',NULL,NULL),(9,20,'2015-10-18 16:24:17','2016-10-08 23:21:38',1,'kevin','<p>Cool kid acting plain nope</p>','Kevin Durant'),(10,21,'2015-11-06 21:26:48','2015-11-06 21:26:48',1,'steph',NULL,NULL),(11,22,'2015-11-06 23:19:07','2015-11-06 23:19:07',1,'james',NULL,NULL),(12,23,'2015-11-06 23:35:18','2015-11-06 23:35:18',1,'tony',NULL,NULL),(13,12,NULL,NULL,1,'admin',NULL,NULL),(14,24,'2016-07-29 09:39:14','2016-11-02 20:30:38',1,'elevysi','<p>I am building this blog to improve on my technical vocabulary as well as share some stuff.</p>','Elevysi'),(15,25,'2016-09-15 14:48:01','2016-09-15 14:48:01',1,'music',NULL,NULL),(16,20,NULL,NULL,2,'Solution','','Road to starting with the Spring MVC Framework development');
/*!40000 ALTER TABLE `profiles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `publication_types`
--

DROP TABLE IF EXISTS `publication_types`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `publication_types` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `publication_types`
--

LOCK TABLES `publication_types` WRITE;
/*!40000 ALTER TABLE `publication_types` DISABLE KEYS */;
INSERT INTO `publication_types` VALUES (1,'post',NULL),(2,'play',NULL),(3,'album',NULL);
/*!40000 ALTER TABLE `publication_types` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `publications`
--

DROP TABLE IF EXISTS `publications`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `publications` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `profile_id` int(11) NOT NULL,
  `created` datetime DEFAULT NULL,
  `modified` datetime DEFAULT NULL,
  `public_publication` int(1) DEFAULT NULL,
  `status` int(1) DEFAULT '1',
  `featured` int(1) DEFAULT '0',
  `friendly_url` varchar(200) DEFAULT NULL,
  `type` int(2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=65 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `publications`
--

LOCK TABLES `publications` WRITE;
/*!40000 ALTER TABLE `publications` DISABLE KEYS */;
INSERT INTO `publications` VALUES (35,14,'2016-08-24 10:24:05','2016-08-24 10:24:05',1,1,0,NULL,NULL),(36,14,'2016-08-24 10:31:58','2016-08-24 10:31:58',1,1,0,NULL,NULL),(37,14,'2016-08-24 10:33:22','2016-08-24 10:33:22',1,1,0,NULL,NULL),(38,14,'2016-08-24 10:35:44','2016-10-08 11:28:22',1,1,0,NULL,NULL),(39,14,'2016-08-24 10:40:50','2016-08-24 10:40:50',1,1,0,NULL,NULL),(40,14,'2016-08-24 13:52:28','2016-10-08 22:52:35',1,1,1,NULL,NULL),(41,14,'2016-08-24 13:54:21','2016-10-08 22:45:03',1,1,1,NULL,NULL),(42,14,'2016-08-25 12:20:54','2016-11-01 16:02:45',1,1,1,NULL,NULL),(43,14,'2016-08-25 16:13:37','2016-08-25 16:13:37',1,1,0,NULL,NULL),(44,14,'2016-08-25 16:14:01','2016-08-25 16:14:01',1,1,0,NULL,NULL),(45,14,'2016-08-26 16:55:43','2016-10-07 19:51:17',1,1,0,NULL,NULL),(46,14,'2016-09-01 22:13:50','2016-10-08 22:50:38',1,1,1,NULL,NULL),(47,14,'2016-09-02 12:00:54','2016-09-02 12:00:54',1,1,NULL,NULL,NULL),(48,14,'2016-09-02 12:05:34','2016-09-02 12:05:34',1,1,NULL,NULL,NULL),(49,14,'2016-09-04 21:54:46','2016-09-04 21:54:46',1,1,NULL,NULL,NULL),(50,9,'2016-09-04 22:09:48','2016-09-04 22:09:48',1,1,NULL,NULL,NULL),(51,13,'2016-09-06 20:04:25','2016-09-06 20:04:25',1,1,NULL,NULL,NULL),(52,14,'2016-09-06 23:14:27','2016-09-06 23:14:27',1,1,NULL,NULL,NULL),(53,14,'2016-10-16 11:32:35','2016-10-16 11:32:35',1,1,NULL,NULL,NULL),(54,14,'2016-10-21 16:37:19','2016-10-21 16:37:19',1,1,NULL,NULL,NULL),(55,14,'2016-10-22 13:51:26','2016-10-22 13:51:26',1,1,NULL,NULL,NULL),(56,13,'2016-11-01 13:11:05','2016-11-01 13:11:05',1,1,NULL,NULL,NULL),(57,13,'2016-11-01 13:13:42','2016-11-01 13:13:42',1,1,NULL,NULL,NULL),(58,13,'2016-11-01 13:18:17','2016-11-01 13:18:17',1,1,NULL,NULL,NULL),(59,13,'2016-11-01 13:18:46','2016-11-01 13:18:46',1,1,NULL,NULL,NULL),(60,14,'2016-11-01 15:15:43','2016-11-01 15:15:43',1,1,NULL,NULL,NULL),(61,14,'2016-11-01 15:53:05','2016-11-01 15:53:05',1,1,NULL,NULL,NULL),(62,14,'2016-11-02 20:18:37','2016-11-02 20:18:37',1,1,NULL,NULL,NULL),(63,14,'2016-11-02 20:28:09','2016-11-02 20:28:09',1,1,NULL,NULL,NULL),(64,14,'2016-11-02 20:31:05','2016-11-02 20:31:05',1,1,NULL,NULL,NULL);
/*!40000 ALTER TABLE `publications` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `roles` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `created` datetime DEFAULT NULL,
  `modified` datetime DEFAULT NULL,
  `record_owner` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES (1,'ROLE_ADMIN',NULL,NULL,1),(2,'ROLE_MODERATOR',NULL,NULL,1),(3,'ROLE_DBADMIN',NULL,NULL,1),(4,'ROLE_USER',NULL,NULL,1);
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `shares`
--

DROP TABLE IF EXISTS `shares`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `shares` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `item_id` int(11) DEFAULT NULL,
  `item_type` varchar(50) DEFAULT NULL,
  `created` datetime DEFAULT NULL,
  `modified` datetime DEFAULT NULL,
  `profile_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shares`
--

LOCK TABLES `shares` WRITE;
/*!40000 ALTER TABLE `shares` DISABLE KEYS */;
INSERT INTO `shares` VALUES (1,54,'posts','2016-07-14 16:18:12','2016-07-14 16:18:12',13),(2,57,'posts','2016-07-29 10:11:37','2016-07-29 10:11:37',14);
/*!40000 ALTER TABLE `shares` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tags`
--

DROP TABLE IF EXISTS `tags`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tags` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `item_id` int(11) NOT NULL,
  `item_type` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tags`
--

LOCK TABLES `tags` WRITE;
/*!40000 ALTER TABLE `tags` DISABLE KEYS */;
/*!40000 ALTER TABLE `tags` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `uploads`
--

DROP TABLE IF EXISTS `uploads`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `uploads` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(45) DEFAULT NULL,
  `description` text,
  `filename` varchar(255) DEFAULT NULL,
  `filesize` int(11) DEFAULT '0',
  `filemine` varchar(45) DEFAULT 'text/plain',
  `link_table` varchar(255) DEFAULT NULL,
  `link_id` int(11) DEFAULT NULL,
  `record_owner` int(11) DEFAULT NULL,
  `created` datetime DEFAULT NULL,
  `modified` datetime DEFAULT NULL,
  `path` varchar(255) DEFAULT NULL,
  `file_identificator` varchar(45) DEFAULT NULL,
  `position` int(11) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `uuid` varchar(45) DEFAULT NULL,
  `album_id` int(11) DEFAULT NULL,
  `display` int(1) DEFAULT '1',
  `url` varchar(255) DEFAULT NULL,
  `profile_id` int(11) DEFAULT NULL,
  `keyIdentification` varchar(45) DEFAULT NULL,
  `altText` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `linkIdLinkTableIndex` (`link_id`,`link_table`)
) ENGINE=InnoDB AUTO_INCREMENT=1322 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `uploads`
--

LOCK TABLES `uploads` WRITE;
/*!40000 ALTER TABLE `uploads` DISABLE KEYS */;
INSERT INTO `uploads` VALUES (1112,NULL,NULL,'passport.jpg',117991,'image/jpeg','profilePicture',4,NULL,NULL,NULL,'mignon.png',NULL,NULL,NULL,NULL,NULL,1,NULL,NULL,NULL,NULL),(1113,NULL,NULL,'mignon.png',395011,'image/png','postImage',NULL,NULL,NULL,NULL,'mignon.png',NULL,NULL,NULL,NULL,NULL,1,NULL,NULL,NULL,NULL),(1114,NULL,NULL,'mignon.png',395011,'image/png','postImage',NULL,NULL,NULL,NULL,'mignon.png',NULL,NULL,NULL,NULL,NULL,1,NULL,NULL,NULL,NULL),(1115,NULL,NULL,'mignon.png',395011,'image/png','postImage',NULL,NULL,NULL,NULL,'mignon.png',NULL,NULL,NULL,NULL,NULL,1,NULL,NULL,NULL,NULL),(1120,NULL,NULL,'1profile.jpg',461693,'image/jpeg','profilePicture',9,NULL,NULL,'2015-11-07 15:01:19','profiles/9/avatar/1446904878965/1profile.jpg',NULL,NULL,NULL,NULL,NULL,1,NULL,NULL,'7b56bcb0-cc7c-49e0-a8fe-f49b7c0375d7','profilePicture'),(1121,NULL,NULL,'1.jpg',336712,'image/jpeg','postImage',20,NULL,NULL,NULL,'stateless/image/1.jpg',NULL,NULL,NULL,NULL,NULL,1,NULL,NULL,NULL,NULL),(1122,NULL,NULL,'#2.png',114605,'image/png','postImage',NULL,NULL,NULL,NULL,'stateless/image/#2.png',NULL,NULL,NULL,'0d5de1fb-c0ae-428b-b4d3-27140af94bfc',NULL,1,NULL,NULL,NULL,NULL),(1123,NULL,NULL,'#2.png',114605,'image/png','postImage',NULL,NULL,NULL,NULL,'stateless/image/#2.png',NULL,NULL,NULL,'800a09d4-ea79-4d6f-a63c-b7c1b2995d8a',NULL,1,NULL,NULL,NULL,NULL),(1124,NULL,NULL,'hot',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,2,'61668a1c-642e-47c8-84b1-3069afb732c4',NULL,0,'http://www.elevysi.com',9,NULL,NULL),(1143,NULL,NULL,'https://getimage.com',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,'d443c7a8-a59a-44df-a183-3a0b8e0066a5',NULL,0,'https://getimage.com',NULL,NULL,NULL),(1144,NULL,NULL,'http://www.elevysi.com',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,'d443c7a8-a59a-44df-a183-3a0b8e0066a5',NULL,0,'http://www.elevysi.com',NULL,NULL,NULL),(1145,NULL,NULL,'http://www.tomcatexpert.com/blog/feed',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,'d443c7a8-a59a-44df-a183-3a0b8e0066a5',NULL,0,'http://www.tomcatexpert.com/blog/feed',NULL,NULL,NULL),(1146,NULL,NULL,'www.lessentiel.lu',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,'d443c7a8-a59a-44df-a183-3a0b8e0066a5',NULL,0,'www.lessentiel.lu',NULL,NULL,NULL),(1147,NULL,NULL,'http://www.elevysi.com',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,'d443c7a8-a59a-44df-a183-3a0b8e0066a5',NULL,0,'http://www.elevysi.com',9,NULL,NULL),(1148,NULL,NULL,'http://anotherone.rule.com',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,'d443c7a8-a59a-44df-a183-3a0b8e0066a5',NULL,0,'http://anotherone.rule.com',9,NULL,NULL),(1149,NULL,NULL,'1.jpg',336712,'image/jpeg','postImage',NULL,NULL,NULL,NULL,'stateless/image/1.jpg',NULL,NULL,NULL,'d443c7a8-a59a-44df-a183-3a0b8e0066a5',NULL,0,NULL,NULL,NULL,NULL),(1150,NULL,NULL,'#2.png',114605,'image/png','postImage',NULL,NULL,NULL,NULL,'stateless/image/#2.png',NULL,NULL,NULL,'d443c7a8-a59a-44df-a183-3a0b8e0066a5',NULL,0,NULL,9,NULL,NULL),(1151,NULL,NULL,'1.jpg',336712,'image/jpeg','postImage',NULL,NULL,NULL,NULL,'stateless/image/1.jpg',NULL,NULL,NULL,'d443c7a8-a59a-44df-a183-3a0b8e0066a5',NULL,0,NULL,9,NULL,NULL),(1177,NULL,NULL,'3.jpg',217196,'image/jpeg','profilePicture',10,NULL,NULL,NULL,'profiles/10/avatar/3.jpg',NULL,NULL,NULL,NULL,NULL,0,NULL,NULL,'544fdc1e-262d-4df4-99d6-613de222b324',NULL),(1178,NULL,NULL,'3.jpg',217196,'image/jpeg',NULL,NULL,NULL,NULL,NULL,'/Users/elvishatungimana/Desktop/3.jpg',NULL,NULL,NULL,NULL,NULL,1,NULL,NULL,'0917bcc4-093a-4834-bc3f-753fa19339bc',NULL),(1179,NULL,NULL,'13.jpg',343446,'image/jpeg','profilePicture',12,NULL,NULL,'2016-08-02 15:57:39','profiles\\12\\avatar\\1470146259737\\13.jpg',NULL,NULL,NULL,NULL,NULL,0,NULL,NULL,'210d7eba-77fe-4983-9742-7f9ab5ea607f','profilePicture'),(1180,NULL,NULL,'1.jpg',336712,'image/jpeg','postImage',NULL,NULL,NULL,NULL,'stateless/image/1.jpg',NULL,NULL,NULL,'0cf29bed-741e-4594-814c-747b6f847fdc',10,1,NULL,12,'bd3744ff-55dc-4bf0-ab4c-98186cc4cba0',NULL),(1181,NULL,NULL,'12.jpg',443738,'image/jpeg','postImage',NULL,NULL,NULL,NULL,'stateless/image/12.jpg',NULL,NULL,NULL,'0cf29bed-741e-4594-814c-747b6f847fdc',10,1,NULL,12,'0825273d-39bc-4846-9c0d-efcb54875b6e',NULL),(1182,NULL,NULL,'12.jpg',443738,'image/jpeg','postImage',NULL,NULL,NULL,'2015-11-08 18:47:32','posts/5214f815-9d0a-40ac-86be-31523f5f098d/image/1446895896679/12.jpg',NULL,NULL,NULL,'0b5337d0-95cf-418a-9b0c-1daa4056b7aa',14,1,NULL,9,'7ab70248-816d-4c79-b170-c15bdc4fe5c7',NULL),(1183,NULL,NULL,'1profile.jpg',461693,'image/jpeg','postImage',NULL,NULL,NULL,'2015-12-11 15:03:33','posts/5214f815-9d0a-40ac-86be-31523f5f098d/image/1446895911335/1profile.jpg',NULL,NULL,NULL,'f7449e8b-7aa3-4ffe-aa1d-bac2138b04fc',11,1,NULL,9,'45919cda-bf88-4398-a752-11017146ee15',NULL),(1184,NULL,NULL,'1.jpg',336712,'image/jpeg','postImage',NULL,NULL,NULL,'2015-12-11 15:03:34','posts/c6702fb6-f7e0-4cae-980a-b2850542154a/image/1446899483168/1.jpg',NULL,NULL,NULL,'f7449e8b-7aa3-4ffe-aa1d-bac2138b04fc',12,1,NULL,9,'1837b173-4684-4d3c-ba23-dc982b897066',NULL),(1185,NULL,NULL,'12.jpg',443738,'image/jpeg','postImage',NULL,NULL,NULL,NULL,'posts/c6702fb6-f7e0-4cae-980a-b2850542154a/image/1446899637635/12.jpg',NULL,NULL,NULL,'c6702fb6-f7e0-4cae-980a-b2850542154a',12,1,NULL,9,'0753f03f-4697-4548-886d-59f8f350f9fc',NULL),(1186,NULL,NULL,'2.png',114605,'image/png','postImage',38,NULL,'2015-11-08 18:27:06','2015-11-08 18:28:14','posts/23bc7ec5-5f1d-4df2-a0de-5d176577e8df/image/1447003626455/2.png',NULL,NULL,NULL,'23bc7ec5-5f1d-4df2-a0de-5d176577e8df',NULL,1,NULL,9,'ba4433cf-1265-4f20-8fcd-c344a6ce0207',NULL),(1187,NULL,NULL,'1profile.jpg',461693,'image/jpeg','postImage',NULL,NULL,'2015-11-08 18:43:54','2015-11-08 18:45:34','posts/79e74cc0-d1a5-4699-b74e-7a29965ed67e/image/1447004634468/1profile.jpg',NULL,NULL,NULL,'2cd36c80-ab4a-492b-b64e-e42e9d493c25',13,1,NULL,9,'e87af256-d4eb-4ea8-8e99-cadd00781c7d',NULL),(1188,NULL,NULL,'3.jpg',217196,'image/jpeg','postImage',NULL,NULL,'2015-11-08 18:45:17','2015-11-08 18:47:32','posts/2cd36c80-ab4a-492b-b64e-e42e9d493c25/image/1447004717375/3.jpg',NULL,NULL,NULL,'0b5337d0-95cf-418a-9b0c-1daa4056b7aa',14,1,NULL,9,'2a8f44de-8337-44ce-a298-be88f018aac3',NULL),(1189,NULL,NULL,'1.jpg',336712,'image/jpeg','postImage',NULL,NULL,'2015-11-08 18:47:02','2015-11-08 18:47:32','posts/0b5337d0-95cf-418a-9b0c-1daa4056b7aa/image/1447004822315/1.jpg',NULL,NULL,NULL,'0b5337d0-95cf-418a-9b0c-1daa4056b7aa',14,1,NULL,9,'7cd8dd19-270a-44d8-876f-704ff94b1355',NULL),(1190,NULL,NULL,'1profile.jpg',461693,'image/jpeg','postImage',NULL,NULL,'2015-11-08 18:47:13','2015-12-11 15:05:35','posts/0b5337d0-95cf-418a-9b0c-1daa4056b7aa/image/1447004833555/1profile.jpg',NULL,NULL,NULL,'c51693c0-7b90-4312-825b-6a86f22b6039',14,1,NULL,9,'edad8ec5-e81c-4e30-9d57-d04122a8de2a',NULL),(1191,NULL,NULL,'1.jpg',336712,'image/jpeg','postImage',NULL,NULL,'2015-12-11 14:35:35','2015-12-11 15:05:35','posts/dc90f0f7-dc95-45ad-a252-b1f16013ecd5/image/1449840935161/1.jpg',NULL,NULL,NULL,'c51693c0-7b90-4312-825b-6a86f22b6039',NULL,1,NULL,9,'b55f228c-66b9-4e2a-b620-9c574f31eaf9',NULL),(1192,NULL,NULL,'1.jpg',336712,'image/jpeg','postImage',44,NULL,'2015-12-11 14:50:04','2015-12-11 15:05:14','posts/374dd7d9-8b8f-4e66-89a5-72fec3d204a0/image/1449841804716/1.jpg',NULL,NULL,NULL,'64e8d1f4-da73-4f65-bcea-4245c84a64ea',NULL,1,NULL,9,'beddbadf-2ea4-4ed1-93b4-acee7fdc4baa',NULL),(1193,NULL,NULL,'12.jpg',443738,'image/jpeg','postImage',NULL,NULL,'2015-12-11 14:51:25','2015-12-11 15:05:14','posts/9936052b-1325-4b5c-924a-7d9cc1844d72/image/1449841885036/12.jpg',NULL,NULL,NULL,'64e8d1f4-da73-4f65-bcea-4245c84a64ea',NULL,1,NULL,9,'4db06626-74cc-4475-8158-7efe6ce30489',NULL),(1194,NULL,NULL,'1.jpg',336712,'image/jpeg','postImage',NULL,NULL,'2015-12-11 14:51:31','2015-12-11 14:51:37','posts/9936052b-1325-4b5c-924a-7d9cc1844d72/image/1449841891145/1.jpg',NULL,NULL,NULL,'9936052b-1325-4b5c-924a-7d9cc1844d72',NULL,1,NULL,9,'80869acb-b018-411c-8e49-44645e121802',NULL),(1195,NULL,NULL,'1.jpg',336712,'image/jpeg','postImage',NULL,NULL,'2015-12-11 14:53:09','2015-12-11 14:53:29','posts/84013c95-da47-45e8-826f-f89611ee2954/image/1449841989456/1.jpg',NULL,NULL,NULL,'84013c95-da47-45e8-826f-f89611ee2954',NULL,1,NULL,9,'11e837aa-4fec-45f2-9b4a-b3e7c813f109',NULL),(1196,NULL,NULL,'1profile.jpg',461693,'image/jpeg','postImage',NULL,NULL,'2015-12-11 14:53:24','2015-12-11 14:53:29','posts/84013c95-da47-45e8-826f-f89611ee2954/image/1449842004012/1profile.jpg',NULL,NULL,NULL,'84013c95-da47-45e8-826f-f89611ee2954',NULL,1,NULL,9,'c93ed6f2-5c66-4685-95f9-f478de19003d',NULL),(1197,NULL,NULL,'1profile.jpg',461693,'image/jpeg','postImage',NULL,NULL,'2015-12-11 15:04:31','2015-12-11 15:04:42','posts/ab3145f5-d5db-4f59-990e-1480702b1246/image/1449842671485/1profile.jpg',NULL,NULL,NULL,'ab3145f5-d5db-4f59-990e-1480702b1246',NULL,1,NULL,9,'9f55e21d-02a8-466e-b2e2-1309683353c7',NULL),(1198,NULL,NULL,'1.jpg',336712,'image/jpeg','postImage',NULL,NULL,'2015-12-11 15:04:37','2015-12-11 15:04:42','posts/ab3145f5-d5db-4f59-990e-1480702b1246/image/1449842677492/1.jpg',NULL,NULL,NULL,'ab3145f5-d5db-4f59-990e-1480702b1246',NULL,1,NULL,9,'8f524818-1945-4e5c-b026-a29c254d89cd',NULL),(1199,NULL,NULL,'1profile.jpg',461693,'image/jpeg','postImage',NULL,NULL,'2015-12-11 15:23:43','2015-12-11 15:23:43','posts/5214f815-9d0a-40ac-86be-31523f5f098d/image/1446895911335/1profile.jpg',NULL,NULL,NULL,'89f8deeb-4707-4d4d-96a3-574367507c9f',11,1,NULL,9,'45919cda-bz88-4398-a752-11017146ee15',NULL),(1207,NULL,NULL,'1.jpg',336712,'image/jpeg','postImage',52,NULL,'2015-12-11 16:12:02','2015-12-11 16:12:02','posts/0b5337d0-95cf-418a-9b0c-1daa4056b7aa/image/1447004822315/1.jpg',NULL,NULL,NULL,'3d0548fd-0963-43ca-9a8d-e6a22fc8239d',22,1,NULL,9,'a698cf17-96a2-4488-8bfd-58edd8ccd3cb',NULL),(1208,NULL,NULL,'1profile.jpg',461693,'image/jpeg','postImage',NULL,NULL,'2015-12-11 16:12:02','2015-12-11 16:12:02','posts/0b5337d0-95cf-418a-9b0c-1daa4056b7aa/image/1447004833555/1profile.jpg',NULL,NULL,NULL,'3d0548fd-0963-43ca-9a8d-e6a22fc8239d',22,1,NULL,9,'c527ea6b-b59a-473b-bd61-50f0f27b3aa7',NULL),(1209,NULL,NULL,'12.jpg',443738,'image/jpeg','postImage',53,NULL,'2015-12-11 16:14:38','2015-12-11 16:14:44','posts/115f2075-347a-4b1e-b6de-b9f493c32754/image/1449846878898/12.jpg',NULL,NULL,NULL,'115f2075-347a-4b1e-b6de-b9f493c32754',NULL,1,NULL,9,'2466fe4d-efe4-4cde-bf69-84dd440e53e6',NULL),(1210,NULL,NULL,'1profile.jpg',461693,'image/jpeg','postImage',54,NULL,'2015-12-11 16:27:11','2015-12-11 16:27:11','posts/79e74cc0-d1a5-4699-b74e-7a29965ed67e/image/1447004634468/1profile.jpg',NULL,NULL,NULL,'4c2a8166-4ee9-48af-b6f0-a6af9dacedd8',23,1,NULL,9,'9c71637c-f528-49b9-9ecb-37885dffad6b',NULL),(1211,NULL,NULL,'3.jpg',217196,'image/jpeg','postImage',NULL,NULL,'2015-12-11 16:27:11','2015-12-11 16:27:11','posts/2cd36c80-ab4a-492b-b64e-e42e9d493c25/image/1447004717375/3.jpg',NULL,NULL,NULL,'4c2a8166-4ee9-48af-b6f0-a6af9dacedd8',23,1,NULL,9,'6a034ac5-fd5f-4de3-9964-be2f4a98bc0b',NULL),(1212,NULL,NULL,'profilethumbnail.jpg',10166,'image/jpeg','profilePicture',14,NULL,'2016-07-29 09:39:15','2016-08-26 10:54:31','profiles\\14\\avatar\\1472201671737\\profilethumbnail.jpg',NULL,NULL,NULL,NULL,NULL,0,NULL,NULL,'0fdbdbb9-ad59-4ce1-9d04-c0fd3b5ce626','profilePicture'),(1213,NULL,NULL,'IMG-20160722-WA0036.jpg',198196,'image/jpeg','postImage',57,NULL,'2016-07-29 09:58:55','2016-07-29 09:59:01','posts\\352a77ac-ff97-4378-a796-666759f2bdfb\\image\\1469779135003\\IMG-20160722-WA0036.jpg',NULL,NULL,NULL,'352a77ac-ff97-4378-a796-666759f2bdfb',NULL,1,NULL,14,'5cb0ba16-920d-48a2-990c-d9a41e5a0ddc',NULL),(1226,NULL,NULL,'profilethumbnail.jpg',10166,'image/jpeg','albums',NULL,NULL,'2016-08-02 09:27:13','2016-08-02 09:27:13','albums\\36a6ee5e-e146-482b-929c-2adb8009044f\\image\\1470122833291\\profilethumbnail.jpg',NULL,NULL,NULL,'36a6ee5e-e146-482b-929c-2adb8009044f',34,1,NULL,14,'65f7ab98-fb35-49a8-93cc-dc40f3b41a6f',NULL),(1227,NULL,NULL,'thumb.png',8665,'image/png','albums',NULL,NULL,'2016-08-02 09:27:21','2016-08-02 09:27:21','albums\\36a6ee5e-e146-482b-929c-2adb8009044f\\image\\1470122841404\\thumb.png',NULL,NULL,NULL,'36a6ee5e-e146-482b-929c-2adb8009044f',34,1,NULL,14,'5092ae62-4929-453e-88ae-c4ae89e8fae1',NULL),(1228,NULL,NULL,'Elvis 2_Ekaterina Chirkova.jpg',5022,'image/jpeg','albums',NULL,NULL,'2016-08-02 09:28:02','2016-08-02 09:28:02','albums\\36a6ee5e-e146-482b-929c-2adb8009044f\\image\\1470122882477\\Elvis 2_Ekaterina Chirkova.jpg',NULL,NULL,NULL,'36a6ee5e-e146-482b-929c-2adb8009044f',34,1,NULL,14,'34b33e41-043c-4b3d-94c5-b47d6955d8a5',NULL),(1229,NULL,NULL,'Elvis 1_Ekaterina Chirkova.jpg',4394,'image/jpeg','albums',NULL,NULL,'2016-08-02 09:28:02','2016-08-02 09:28:02','albums\\36a6ee5e-e146-482b-929c-2adb8009044f\\image\\1470122882486\\Elvis 1_Ekaterina Chirkova.jpg',NULL,NULL,NULL,'36a6ee5e-e146-482b-929c-2adb8009044f',34,1,NULL,14,'9ebfd1c0-f057-40ce-8f8b-636fd9385fbb',NULL),(1230,NULL,NULL,'17.jpg',116100,'image/jpeg','albums',NULL,NULL,'2016-08-02 10:59:41','2016-08-02 10:59:41','albums\\7d9be452-0044-458a-93f9-4c1938d7be33\\image\\1470128381585\\17.jpg',NULL,NULL,NULL,'7d9be452-0044-458a-93f9-4c1938d7be33',35,1,NULL,13,'ba10f6cc-a5a5-44b5-adf7-a7e3c26e2aad',NULL),(1231,NULL,NULL,'21.jpg',1381242,'image/jpeg','albums',NULL,NULL,'2016-08-02 10:59:41','2016-08-02 10:59:41','albums\\7d9be452-0044-458a-93f9-4c1938d7be33\\image\\1470128381613\\21.jpg',NULL,NULL,NULL,'7d9be452-0044-458a-93f9-4c1938d7be33',35,1,NULL,13,'12d97312-f4d4-47c1-8e91-4d8048615779',NULL),(1232,NULL,NULL,'19.jpg',331675,'image/jpeg','albums',NULL,NULL,'2016-08-02 10:59:41','2016-08-02 10:59:41','albums\\7d9be452-0044-458a-93f9-4c1938d7be33\\image\\1470128381584\\19.jpg',NULL,NULL,NULL,'7d9be452-0044-458a-93f9-4c1938d7be33',35,1,NULL,13,'5b0428d3-4412-4c11-b287-1c5675977d8b',NULL),(1233,NULL,NULL,'15.jpg',337120,'image/jpeg','albums',NULL,NULL,'2016-08-02 10:59:41','2016-08-02 10:59:41','albums\\7d9be452-0044-458a-93f9-4c1938d7be33\\image\\1470128381586\\15.jpg',NULL,NULL,NULL,'7d9be452-0044-458a-93f9-4c1938d7be33',35,1,NULL,13,'98b30efd-bf57-47c0-9a04-a3f190b33e7c',NULL),(1234,NULL,NULL,'16.jpg',145521,'image/jpeg','albums',NULL,NULL,'2016-08-02 10:59:41','2016-08-02 10:59:41','albums\\7d9be452-0044-458a-93f9-4c1938d7be33\\image\\1470128381582\\16.jpg',NULL,NULL,NULL,'7d9be452-0044-458a-93f9-4c1938d7be33',35,1,NULL,13,'9e77c5be-b5c8-473d-9517-ee77d3381823',NULL),(1235,NULL,NULL,'18.jpg',90444,'image/jpeg','albums',NULL,NULL,'2016-08-02 10:59:41','2016-08-02 10:59:41','albums\\7d9be452-0044-458a-93f9-4c1938d7be33\\image\\1470128381601\\18.jpg',NULL,NULL,NULL,'7d9be452-0044-458a-93f9-4c1938d7be33',35,1,NULL,13,'319a9030-9730-429c-b677-dfc11c6bb895',NULL),(1236,NULL,NULL,'14.jpg',224869,'image/jpeg','albums',NULL,NULL,'2016-08-02 10:59:41','2016-08-02 10:59:41','albums\\7d9be452-0044-458a-93f9-4c1938d7be33\\image\\1470128381720\\14.jpg',NULL,NULL,NULL,'7d9be452-0044-458a-93f9-4c1938d7be33',35,1,NULL,13,'fddfc21f-894d-455a-aad9-730bca0060c3',NULL),(1237,NULL,NULL,'25.jpg',163146,'image/jpeg','albums',NULL,NULL,'2016-08-02 10:59:41','2016-08-02 10:59:41','albums\\7d9be452-0044-458a-93f9-4c1938d7be33\\image\\1470128381703\\25.jpg',NULL,NULL,NULL,'7d9be452-0044-458a-93f9-4c1938d7be33',35,1,NULL,13,'54372450-9607-47d4-a40c-b03baaf1a75e',NULL),(1238,NULL,NULL,'13.jpg',343446,'image/jpeg','albums',NULL,NULL,'2016-08-02 10:59:41','2016-08-02 10:59:41','albums\\7d9be452-0044-458a-93f9-4c1938d7be33\\image\\1470128381716\\13.jpg',NULL,NULL,NULL,'7d9be452-0044-458a-93f9-4c1938d7be33',35,1,NULL,13,'f5033097-7445-4173-bdf7-61b46aef9ec2',NULL),(1239,NULL,NULL,'30.jpg',1319937,'image/jpeg','albums',NULL,NULL,'2016-08-02 10:59:41','2016-08-02 10:59:41','albums\\7d9be452-0044-458a-93f9-4c1938d7be33\\image\\1470128381772\\30.jpg',NULL,NULL,NULL,'7d9be452-0044-458a-93f9-4c1938d7be33',35,1,NULL,13,'021159fe-2f84-4c6e-bf9b-970e94d90427',NULL),(1240,NULL,NULL,'29.jpg',1651888,'image/jpeg','albums',NULL,NULL,'2016-08-02 10:59:41','2016-08-02 10:59:41','albums\\7d9be452-0044-458a-93f9-4c1938d7be33\\image\\1470128381783\\29.jpg',NULL,NULL,NULL,'7d9be452-0044-458a-93f9-4c1938d7be33',35,1,NULL,13,'406df801-5bce-44e5-afde-9237e94ce69c',NULL),(1241,NULL,NULL,'2.jpg',441674,'image/jpeg','albums',NULL,NULL,'2016-08-02 10:59:56','2016-08-02 10:59:56','albums\\7d9be452-0044-458a-93f9-4c1938d7be33\\image\\1470128396184\\2.jpg',NULL,NULL,NULL,'7d9be452-0044-458a-93f9-4c1938d7be33',35,1,NULL,13,'c2bda55c-f230-4b96-8f66-ad63ffb12051',NULL),(1242,NULL,NULL,'3.jpg',116100,'image/jpeg','albums',NULL,NULL,'2016-08-02 11:00:01','2016-08-02 11:00:01','albums\\7d9be452-0044-458a-93f9-4c1938d7be33\\image\\1470128401695\\3.jpg',NULL,NULL,NULL,'7d9be452-0044-458a-93f9-4c1938d7be33',35,1,NULL,13,'6c4f8fe8-5642-4233-95e1-ce8096044346',NULL),(1243,NULL,NULL,'15.jpg',337120,'image/jpeg','albums',NULL,NULL,'2016-08-02 14:47:08','2016-08-02 14:47:08','albums\\faf8ccdc-4934-4a48-a4bb-732beaaa3159\\image\\1470142028499\\15.jpg',NULL,NULL,NULL,'faf8ccdc-4934-4a48-a4bb-732beaaa3159',36,1,NULL,14,'18e6b020-cf41-44c3-9670-726d84cbcae6',NULL),(1244,NULL,NULL,'16.jpg',145521,'image/jpeg','albums',NULL,NULL,'2016-08-02 14:47:08','2016-08-02 14:47:08','albums\\faf8ccdc-4934-4a48-a4bb-732beaaa3159\\image\\1470142028500\\16.jpg',NULL,NULL,NULL,'faf8ccdc-4934-4a48-a4bb-732beaaa3159',36,1,NULL,14,'929af6a7-e921-4832-bbaa-4725a13248ad',NULL),(1245,NULL,NULL,'14.jpg',224869,'image/jpeg','albums',NULL,NULL,'2016-08-02 14:47:08','2016-08-02 14:47:08','albums\\faf8ccdc-4934-4a48-a4bb-732beaaa3159\\image\\1470142028500\\14.jpg',NULL,NULL,NULL,'faf8ccdc-4934-4a48-a4bb-732beaaa3159',36,1,NULL,14,'99f697c7-38bf-47f7-ba0f-da56e3f13021',NULL),(1246,NULL,NULL,'17.jpg',116100,'image/jpeg','albums',NULL,NULL,'2016-08-02 14:47:08','2016-08-02 14:47:08','albums\\faf8ccdc-4934-4a48-a4bb-732beaaa3159\\image\\1470142028500\\17.jpg',NULL,NULL,NULL,'faf8ccdc-4934-4a48-a4bb-732beaaa3159',36,1,NULL,14,'27fa41bd-8351-4640-a391-1ae5c9c091c8',NULL),(1247,NULL,NULL,'13.jpg',343446,'image/jpeg','albums',NULL,NULL,'2016-08-02 14:47:08','2016-08-02 14:47:08','albums\\faf8ccdc-4934-4a48-a4bb-732beaaa3159\\image\\1470142028506\\13.jpg',NULL,NULL,NULL,'faf8ccdc-4934-4a48-a4bb-732beaaa3159',36,1,NULL,14,'ea313e8f-8f6e-4ef5-af56-667102ca9964',NULL),(1248,NULL,NULL,'11.jpg',228744,'image/jpeg','albums',NULL,NULL,'2016-08-02 14:47:08','2016-08-02 14:47:08','albums\\faf8ccdc-4934-4a48-a4bb-732beaaa3159\\image\\1470142028500\\11.jpg',NULL,NULL,NULL,'faf8ccdc-4934-4a48-a4bb-732beaaa3159',36,1,NULL,14,'8b1a83f2-ee7b-4c67-b939-b313337b2530',NULL),(1249,NULL,NULL,'19.jpg',331675,'image/jpeg','albums',NULL,NULL,'2016-08-02 14:47:08','2016-08-02 14:47:08','albums\\faf8ccdc-4934-4a48-a4bb-732beaaa3159\\image\\1470142028658\\19.jpg',NULL,NULL,NULL,'faf8ccdc-4934-4a48-a4bb-732beaaa3159',36,1,NULL,14,'f41ac1e4-74a1-482d-b979-24580d751857',NULL),(1250,NULL,NULL,'1.jpg',461693,'image/jpeg','albums',NULL,NULL,'2016-08-02 14:47:08','2016-08-02 14:47:08','albums\\faf8ccdc-4934-4a48-a4bb-732beaaa3159\\image\\1470142028713\\1.jpg',NULL,NULL,NULL,'faf8ccdc-4934-4a48-a4bb-732beaaa3159',36,1,NULL,14,'8831de43-e42c-4f33-bd49-72de98000241',NULL),(1251,NULL,NULL,'18.jpg',90444,'image/jpeg','albums',NULL,NULL,'2016-08-02 14:47:08','2016-08-02 14:47:08','albums\\faf8ccdc-4934-4a48-a4bb-732beaaa3159\\image\\1470142028652\\18.jpg',NULL,NULL,NULL,'faf8ccdc-4934-4a48-a4bb-732beaaa3159',36,1,NULL,14,'504126c5-f7ac-4b44-ba09-91e5649eadc2',NULL),(1252,NULL,NULL,'7.jpg',334432,'image/jpeg','albums',NULL,NULL,'2016-08-02 14:47:08','2016-08-02 14:47:08','albums\\faf8ccdc-4934-4a48-a4bb-732beaaa3159\\image\\1470142028751\\7.jpg',NULL,NULL,NULL,'faf8ccdc-4934-4a48-a4bb-732beaaa3159',36,1,NULL,14,'0c58cb30-ddb3-4126-9c66-56ca7bd3c2f1',NULL),(1253,NULL,NULL,'8.jpg',195633,'image/jpeg','albums',NULL,NULL,'2016-08-02 14:47:08','2016-08-02 14:47:08','albums\\faf8ccdc-4934-4a48-a4bb-732beaaa3159\\image\\1470142028760\\8.jpg',NULL,NULL,NULL,'faf8ccdc-4934-4a48-a4bb-732beaaa3159',36,1,NULL,14,'42daae05-882d-4181-aa06-83e9673a79b1',NULL),(1254,NULL,NULL,'3.jpg',116100,'image/jpeg','albums',NULL,NULL,'2016-08-02 14:47:08','2016-08-02 14:47:08','albums\\faf8ccdc-4934-4a48-a4bb-732beaaa3159\\image\\1470142028710\\3.jpg',NULL,NULL,NULL,'faf8ccdc-4934-4a48-a4bb-732beaaa3159',36,1,NULL,14,'69168fcf-956d-48e9-9fdf-59ff0fd914bd',NULL),(1255,NULL,NULL,'2.jpg',441674,'image/jpeg','albums',NULL,NULL,'2016-08-02 14:47:08','2016-08-02 14:47:08','albums\\faf8ccdc-4934-4a48-a4bb-732beaaa3159\\image\\1470142028719\\2.jpg',NULL,NULL,NULL,'faf8ccdc-4934-4a48-a4bb-732beaaa3159',36,1,NULL,14,'1dd2e15b-3b16-4908-ad9f-8d05d2982c6f',NULL),(1256,NULL,NULL,'9.jpg',179372,'image/jpeg','albums',NULL,NULL,'2016-08-02 14:47:08','2016-08-02 14:47:08','albums\\faf8ccdc-4934-4a48-a4bb-732beaaa3159\\image\\1470142028796\\9.jpg',NULL,NULL,NULL,'faf8ccdc-4934-4a48-a4bb-732beaaa3159',36,1,NULL,14,'2dfa613c-5b66-4b88-89c9-4d2a2cbf0911',NULL),(1257,NULL,NULL,'30.jpg',1319937,'image/jpeg','albums',NULL,NULL,'2016-08-02 14:47:54','2016-08-02 14:47:54','albums\\1a65722e-1b69-493f-8b04-fed1410e2f9f\\image\\1470142074288\\30.jpg',NULL,NULL,NULL,'1a65722e-1b69-493f-8b04-fed1410e2f9f',37,1,NULL,14,'53b00b40-d6ef-46d9-9d4a-4b92856eaa58',NULL),(1258,NULL,NULL,'25.jpg',163146,'image/jpeg','albums',NULL,NULL,'2016-08-02 14:47:54','2016-08-02 14:47:54','albums\\1a65722e-1b69-493f-8b04-fed1410e2f9f\\image\\1470142074254\\25.jpg',NULL,NULL,NULL,'1a65722e-1b69-493f-8b04-fed1410e2f9f',37,1,NULL,14,'0282dd1a-28ba-49ea-8187-9aa1a8c649a2',NULL),(1259,NULL,NULL,'29.jpg',1651888,'image/jpeg','albums',NULL,NULL,'2016-08-02 14:47:54','2016-08-02 14:47:54','albums\\1a65722e-1b69-493f-8b04-fed1410e2f9f\\image\\1470142074303\\29.jpg',NULL,NULL,NULL,'1a65722e-1b69-493f-8b04-fed1410e2f9f',37,1,NULL,14,'a87fb40b-1a42-49b5-992b-e818e36a66c7',NULL),(1260,NULL,NULL,'21.jpg',1381242,'image/jpeg','albums',NULL,NULL,'2016-08-02 14:47:54','2016-08-02 14:47:54','albums\\1a65722e-1b69-493f-8b04-fed1410e2f9f\\image\\1470142074325\\21.jpg',NULL,NULL,NULL,'1a65722e-1b69-493f-8b04-fed1410e2f9f',37,1,NULL,14,'47134ffc-1e33-4f39-9939-9eb29b45a404',NULL),(1261,NULL,NULL,'img1.jpg',137682,'image/jpeg','albums',NULL,NULL,'2016-08-02 14:48:20','2016-08-02 14:48:20','albums\\1a65722e-1b69-493f-8b04-fed1410e2f9f\\image\\1470142100795\\img1.jpg',NULL,NULL,NULL,'1a65722e-1b69-493f-8b04-fed1410e2f9f',37,1,NULL,14,'a0cc3acf-1dd6-4b26-95fb-6d0a414679a9',NULL),(1262,NULL,NULL,'img4.jpg',366900,'image/jpeg','albums',NULL,NULL,'2016-08-02 14:48:20','2016-08-02 14:48:20','albums\\1a65722e-1b69-493f-8b04-fed1410e2f9f\\image\\1470142100781\\img4.jpg',NULL,NULL,NULL,'1a65722e-1b69-493f-8b04-fed1410e2f9f',37,1,NULL,14,'9e0fa0d2-0773-4e6a-970f-24a0f64c8d53',NULL),(1263,NULL,NULL,'imac2.png',251362,'image/png','albums',NULL,NULL,'2016-08-02 14:48:20','2016-08-02 14:48:20','albums\\1a65722e-1b69-493f-8b04-fed1410e2f9f\\image\\1470142100788\\imac2.png',NULL,NULL,NULL,'1a65722e-1b69-493f-8b04-fed1410e2f9f',37,1,NULL,14,'c502f5be-cdf2-4eef-b01e-885e4902a90f',NULL),(1264,NULL,NULL,'img2.jpg',236010,'image/jpeg','albums',NULL,NULL,'2016-08-02 14:48:20','2016-08-02 14:48:20','albums\\1a65722e-1b69-493f-8b04-fed1410e2f9f\\image\\1470142100795\\img2.jpg',NULL,NULL,NULL,'1a65722e-1b69-493f-8b04-fed1410e2f9f',37,1,NULL,14,'7fe53d9d-3bc3-4618-8b30-53b124004da3',NULL),(1265,NULL,NULL,'Elvis 2_Ekaterina Chirkova.jpg',5022,'image/jpeg','profilePicture',NULL,NULL,'2016-08-03 11:07:27','2016-08-03 11:07:28','profiles\\13\\avatar\\1470215247995\\Elvis 2_Ekaterina Chirkova.jpg',NULL,NULL,NULL,NULL,NULL,0,NULL,NULL,'c92b5fb7-e063-41ca-9167-1cfd745bf8f9','profilePicture'),(1266,NULL,NULL,'Spring.jpg',26075,'image/jpeg','postImage',NULL,NULL,'2016-08-22 16:20:12','2016-08-22 16:20:18','posts\\38e3f7f0-df55-41c4-ac9c-84c2d1396a0a\\image\\1471875612042\\Spring.jpg',NULL,NULL,NULL,'38e3f7f0-df55-41c4-ac9c-84c2d1396a0a',NULL,1,NULL,14,'cc109aac-e04e-4a36-bc4e-705d062ed838',NULL),(1267,NULL,NULL,'Spring.jpg',26075,'image/jpeg','postImage',NULL,NULL,'2016-08-22 17:33:12','2016-08-22 17:33:16','posts\\e07d72aa-e61e-42da-9791-acacad411cab\\image\\1471879992870\\Spring.jpg',NULL,NULL,NULL,'e07d72aa-e61e-42da-9791-acacad411cab',NULL,1,NULL,14,'82cb9a64-1fde-434b-ae69-9f370d1d6ffe',NULL),(1268,NULL,NULL,'Spring.jpg',26075,'image/jpeg','postImage',59,NULL,'2016-08-22 17:36:28','2016-08-22 17:36:28','posts\\38e3f7f0-df55-41c4-ac9c-84c2d1396a0a\\image\\1471875612042\\Spring.jpg',NULL,NULL,NULL,'2776b365-3b80-43f8-8023-3f637a7b6a35',NULL,1,NULL,14,'bd5f3afc-3b80-4132-8b7c-84d521cb3a11',NULL),(1269,NULL,NULL,'17.jpg',116100,'image/jpeg','albums',NULL,NULL,'2016-08-24 10:24:01','2016-08-24 10:24:01','albums\\faf8ccdc-4934-4a48-a4bb-732beaaa3159\\image\\1470142028500\\17.jpg',NULL,NULL,NULL,'e48c54e2-5d98-4eea-90d7-8ce8b9414e7a',36,1,NULL,14,'f82027e7-dab1-4e6c-903e-3c0d2e8f7e18',NULL),(1270,NULL,NULL,'7.jpg',334432,'image/jpeg','albums',NULL,NULL,'2016-08-24 10:24:01','2016-08-24 10:24:01','albums\\faf8ccdc-4934-4a48-a4bb-732beaaa3159\\image\\1470142028751\\7.jpg',NULL,NULL,NULL,'e48c54e2-5d98-4eea-90d7-8ce8b9414e7a',36,1,NULL,14,'9f7c25c5-d3a4-4dc8-9a26-8f858cbebf96',NULL),(1271,NULL,NULL,'13.jpg',343446,'image/jpeg','posts',64,NULL,'2016-08-24 10:31:53','2016-08-24 10:31:53','albums\\faf8ccdc-4934-4a48-a4bb-732beaaa3159\\image\\1470142028506\\13.jpg',NULL,NULL,NULL,'20d6d0cb-65de-4c42-ae39-50e413dd28e8',39,1,NULL,14,'f62ca7dd-038d-490a-952a-5dcd807cce3a',NULL),(1272,NULL,NULL,'11.jpg',228744,'image/jpeg','albums',NULL,NULL,'2016-08-24 10:31:53','2016-08-24 10:31:53','albums\\faf8ccdc-4934-4a48-a4bb-732beaaa3159\\image\\1470142028500\\11.jpg',NULL,NULL,NULL,'20d6d0cb-65de-4c42-ae39-50e413dd28e8',39,1,NULL,14,'6a83629b-5a9b-404e-8eb1-a8228c30c126',NULL),(1273,NULL,NULL,'2.jpg',441674,'image/jpeg','albums',NULL,NULL,'2016-08-24 10:35:31','2016-08-24 10:35:31','albums\\aa599782-3fc3-4bbd-992c-6d7307d23baf\\image\\1472027731540\\2.jpg',NULL,NULL,NULL,'aa599782-3fc3-4bbd-992c-6d7307d23baf',40,1,NULL,14,'ecb4d8a1-c86e-491f-9033-b3372aef2f7d',NULL),(1274,NULL,NULL,'17.jpg',116100,'image/jpeg','albums',NULL,NULL,'2016-08-24 10:35:36','2016-08-24 10:35:36','albums\\aa599782-3fc3-4bbd-992c-6d7307d23baf\\image\\1472027736655\\17.jpg',NULL,NULL,NULL,'aa599782-3fc3-4bbd-992c-6d7307d23baf',40,1,NULL,14,'8d7e3290-b7d1-45cb-ba45-204af319b8fa',NULL),(1275,NULL,NULL,'25.jpg',163146,'image/jpeg','albums',NULL,NULL,'2016-08-24 10:35:41','2016-08-24 10:35:41','albums\\aa599782-3fc3-4bbd-992c-6d7307d23baf\\image\\1472027741372\\25.jpg',NULL,NULL,NULL,'aa599782-3fc3-4bbd-992c-6d7307d23baf',40,1,NULL,14,'024a9368-6b5d-4835-917b-71483780e87c',NULL),(1276,NULL,NULL,'Spring.jpg',26075,'image/jpeg','postImagess',2,NULL,'2016-08-24 13:51:40','2016-11-01 12:31:13','posts/a9ba33f3-a86b-4413-a645-cf150d54fdb5/image/1472039500633/Spring.jpg',NULL,NULL,NULL,'a9ba33f3-a86b-4413-a645-cf150d54fdb5',NULL,0,NULL,14,'147f106c-3fe4-4804-bf73-19312fd916ad',NULL),(1277,NULL,NULL,'Profiles.png',27413,'image/png','postImagess',66,NULL,'2016-08-25 12:20:45','2016-11-05 13:40:29','posts\\c08da2bd-764a-4621-ad2c-f8f045467946\\image\\1472120445811\\Profiles.png',NULL,NULL,NULL,'c08da2bd-764a-4621-ad2c-f8f045467946',NULL,0,NULL,14,'8fa74a91-103c-4aaf-a5da-fafa3859baba',NULL),(1278,NULL,NULL,'Spring.jpg',26075,'image/jpeg','postImage',NULL,NULL,'2016-08-25 21:26:58','2016-11-01 12:31:13','posts\\38e3f7f0-df55-41c4-ac9c-84c2d1396a0a\\image\\1471875612042\\Spring.jpg',NULL,NULL,NULL,'a9ba33f3-a86b-4413-a645-cf150d54fdb5',NULL,0,NULL,14,'ba3d4d1b-2e2a-4308-ac97-4b3c9436b8a2',NULL),(1279,NULL,NULL,'Elvis 2_Ekaterina Chirkova.jpg',5022,'image/jpeg','posts',68,NULL,'2016-08-26 16:54:18','2016-08-26 17:06:49','albums\\36a6ee5e-e146-482b-929c-2adb8009044f\\image\\1470122882477\\Elvis 2_Ekaterina Chirkova.jpg',NULL,NULL,NULL,'6232d65b-da41-475f-a0c7-4672b059cd46',34,0,NULL,14,'6e328284-d92b-4b63-8524-e6be86810dd4',NULL),(1280,NULL,NULL,'Elvis 2_Ekaterina Chirkova.jpg',5022,'image/jpeg','posts',68,NULL,'2016-08-26 16:58:17','2016-08-26 17:06:58','albums\\36a6ee5e-e146-482b-929c-2adb8009044f\\image\\1470122882477\\Elvis 2_Ekaterina Chirkova.jpg',NULL,NULL,NULL,'6232d65b-da41-475f-a0c7-4672b059cd46',34,1,NULL,14,'6ffc9365-a432-4a0c-8600-d9a29f214e7f',NULL),(1281,NULL,NULL,'Elvis 1_Ekaterina Chirkova.jpg',4394,'image/jpeg','posts',68,NULL,'2016-08-26 16:58:17','2016-08-26 17:06:58','albums\\36a6ee5e-e146-482b-929c-2adb8009044f\\image\\1470122882486\\Elvis 1_Ekaterina Chirkova.jpg',NULL,NULL,NULL,'6232d65b-da41-475f-a0c7-4672b059cd46',34,1,NULL,14,'43d48053-42f5-4284-90b0-b5420bd68cbf',NULL),(1282,NULL,NULL,'15.jpg',337120,'image/jpeg','albums',NULL,NULL,'2016-09-01 22:13:31','2016-09-01 22:13:31','albums\\60de7114-0f29-42bf-9afb-61334febad12\\image\\1472760811489\\15.jpg',NULL,NULL,NULL,'60de7114-0f29-42bf-9afb-61334febad12',41,1,NULL,14,'011e15c6-1be5-4853-95e6-050579d0b088',NULL),(1283,NULL,NULL,'2.jpg',441674,'image/jpeg','albums',NULL,NULL,'2016-09-01 22:13:40','2016-09-01 22:13:40','albums\\60de7114-0f29-42bf-9afb-61334febad12\\image\\1472760820576\\2.jpg',NULL,NULL,NULL,'60de7114-0f29-42bf-9afb-61334febad12',41,1,NULL,14,'bab17604-2b93-479b-9ec1-452759a912ff',NULL),(1284,NULL,NULL,'14.jpg',224869,'image/jpeg','albums',NULL,NULL,'2016-09-01 22:13:47','2016-09-01 22:13:47','albums\\60de7114-0f29-42bf-9afb-61334febad12\\image\\1472760827655\\14.jpg',NULL,NULL,NULL,'60de7114-0f29-42bf-9afb-61334febad12',41,1,NULL,14,'f40ed631-9647-40e4-ac1d-10ef9219f4a7',NULL),(1285,NULL,NULL,'1.jpg',336712,'image/jpeg','posts',69,NULL,'2016-09-04 22:10:08','2016-09-04 22:10:08','posts/dc90f0f7-dc95-45ad-a252-b1f16013ecd5/image/1449840935161/1.jpg',NULL,NULL,NULL,'730fbbf3-3384-4456-bb89-33b77d814c39',NULL,1,NULL,9,'482a9ab2-a1f8-42d7-8d2b-824dc1b24dc1',NULL),(1286,NULL,NULL,'17.jpg',116100,'image/jpeg','posts',NULL,NULL,'2016-09-06 22:13:29','2016-09-06 22:13:29','albums\\7d9be452-0044-458a-93f9-4c1938d7be33\\image\\1470128381585\\17.jpg',NULL,NULL,NULL,'66017d10-bcfb-4a84-a03c-29ce48fcba32',35,1,NULL,13,'e94dcbab-eeee-4f47-96e1-5b98c29e290e',NULL),(1287,NULL,NULL,'21.jpg',1381242,'image/jpeg','posts',NULL,NULL,'2016-09-06 22:20:50','2016-09-06 22:20:50','albums\\7d9be452-0044-458a-93f9-4c1938d7be33\\image\\1470128381613\\21.jpg',NULL,NULL,NULL,'5c0565e6-f13e-4f52-928f-59bcfe2ce120',35,1,NULL,13,'d9ddbcaa-d074-4c2d-9898-27573fc1e414',NULL),(1288,NULL,NULL,'16.jpg',145521,'image/jpeg','posts',71,NULL,'2016-09-06 23:13:52','2016-09-06 23:25:22','albums\\faf8ccdc-4934-4a48-a4bb-732beaaa3159\\image\\1470142028500\\16.jpg',NULL,NULL,NULL,'346ff2fd-c9fb-4703-a536-12d3545a0e89',NULL,1,NULL,14,'344068b3-ed96-4287-bdc9-aa327847e2c6',NULL),(1289,NULL,NULL,'14.jpg',224869,'image/jpeg','posts',71,NULL,'2016-09-06 23:13:53','2016-09-06 23:25:22','albums\\faf8ccdc-4934-4a48-a4bb-732beaaa3159\\image\\1470142028500\\14.jpg',NULL,NULL,NULL,'346ff2fd-c9fb-4703-a536-12d3545a0e89',NULL,1,NULL,14,'40751c59-5c39-4647-bfa3-deec0e485094',NULL),(1290,NULL,NULL,'7.jpg',334432,'image/jpeg','posts',NULL,NULL,'2016-09-06 23:26:13','2016-09-06 23:26:13','albums\\faf8ccdc-4934-4a48-a4bb-732beaaa3159\\image\\1470142028751\\7.jpg',NULL,NULL,NULL,'4e914a14-2e4c-47b7-b441-020bae095f90',NULL,1,NULL,14,'d1fd782d-4bdb-4b5b-8e23-cb503502a022',NULL),(1291,NULL,NULL,'15.jpg',337120,'image/jpeg','dossiers',NULL,NULL,'2016-09-06 23:30:02','2016-09-06 23:30:02','albums\\faf8ccdc-4934-4a48-a4bb-732beaaa3159\\image\\1470142028499\\15.jpg',NULL,NULL,NULL,'d8f5248e-d29a-4599-a629-2baf1c5d9e15',NULL,1,NULL,14,'3f99bfcd-41f7-428e-aa44-927dbc3051a8',NULL),(1292,NULL,NULL,'15.jpg',337120,'image/jpeg','dossiers',NULL,NULL,'2016-09-06 23:34:06','2016-09-06 23:34:06','albums\\faf8ccdc-4934-4a48-a4bb-732beaaa3159\\image\\1470142028499\\15.jpg',NULL,NULL,NULL,'d2d8d26a-e46c-4032-b4eb-44550bbd93b6',NULL,1,NULL,14,'4735ec2f-c460-48ec-9217-c1ec97a9e92a',NULL),(1293,NULL,NULL,'15.jpg',337120,'image/jpeg','dossiers',4,NULL,'2016-09-06 23:42:02','2016-09-06 23:42:02','albums\\faf8ccdc-4934-4a48-a4bb-732beaaa3159\\image\\1470142028499\\15.jpg',NULL,NULL,NULL,'99ea5793-8f1f-4271-a447-2afb31776664',NULL,1,NULL,14,'45b7cc81-4352-4da7-bf68-882a7a0b7b62',NULL),(1294,NULL,NULL,'14.jpg',224869,'image/jpeg','dossiers',NULL,NULL,'2016-09-07 13:17:33','2016-09-07 13:17:33','albums\\faf8ccdc-4934-4a48-a4bb-732beaaa3159\\image\\1470142028500\\14.jpg',NULL,NULL,NULL,'d8fbbaeb-5b8b-42c3-8c6f-b6f471ed7b1b',NULL,1,NULL,14,'1d44ac1b-f033-4a70-af30-bbd45ded8a40',NULL),(1295,NULL,NULL,'17.jpg',116100,'image/jpeg','dossiers',NULL,NULL,'2016-09-07 13:17:33','2016-09-07 13:17:33','albums\\faf8ccdc-4934-4a48-a4bb-732beaaa3159\\image\\1470142028500\\17.jpg',NULL,NULL,NULL,'d8fbbaeb-5b8b-42c3-8c6f-b6f471ed7b1b',NULL,1,NULL,14,'5ce466da-8ec8-40e5-a371-a273bbb3dc53',NULL),(1296,NULL,NULL,'15.jpg',337120,'image/jpeg','dossiers',NULL,NULL,'2016-09-07 13:27:18','2016-09-07 13:27:18','albums\\faf8ccdc-4934-4a48-a4bb-732beaaa3159\\image\\1470142028499\\15.jpg',NULL,NULL,NULL,'6aed78a2-bcf9-45f0-80c0-1141a8d87545',NULL,1,NULL,14,'425f4e72-52d1-4a85-9caa-bd211db47efb',NULL),(1297,NULL,NULL,'17.jpg',116100,'image/jpeg','dossiers',NULL,NULL,'2016-09-07 13:27:18','2016-09-07 13:27:18','albums\\faf8ccdc-4934-4a48-a4bb-732beaaa3159\\image\\1470142028500\\17.jpg',NULL,NULL,NULL,'6aed78a2-bcf9-45f0-80c0-1141a8d87545',NULL,1,NULL,14,'0834ae90-db36-44b0-a5ad-efb84a556679',NULL),(1298,NULL,NULL,'11.jpg',228744,'image/jpeg','dossiers',7,NULL,'2016-09-07 13:33:46','2016-09-07 13:33:46','albums\\faf8ccdc-4934-4a48-a4bb-732beaaa3159\\image\\1470142028500\\11.jpg',NULL,NULL,NULL,'1569c441-b47a-4106-96d0-5a4e8a4fdad6',NULL,1,NULL,14,'8cf3955c-3336-4a63-acf5-5052a410547d',NULL),(1299,NULL,NULL,'19.jpg',331675,'image/jpeg','dossiers',7,NULL,'2016-09-07 13:33:46','2016-09-07 13:33:46','albums\\faf8ccdc-4934-4a48-a4bb-732beaaa3159\\image\\1470142028658\\19.jpg',NULL,NULL,NULL,'1569c441-b47a-4106-96d0-5a4e8a4fdad6',NULL,1,NULL,14,'3c5b527e-a663-4be3-8761-c57f5cb01805',NULL),(1300,NULL,NULL,'15.jpg',337120,'image/jpeg','dossiers',8,NULL,'2016-09-07 13:49:52','2016-09-07 13:49:52','albums\\faf8ccdc-4934-4a48-a4bb-732beaaa3159\\image\\1470142028499\\15.jpg',NULL,NULL,NULL,'ebef69b0-251e-4dec-8e59-b6c30126ebc4',NULL,1,NULL,14,'954613c0-a9d9-41d4-81f0-9e5506d30c5d',NULL),(1301,NULL,NULL,NULL,NULL,NULL,'profilePicture',15,NULL,'2016-09-15 14:48:01','2016-09-15 14:48:01','img32-md.jpg',NULL,NULL,NULL,NULL,NULL,0,NULL,NULL,NULL,NULL),(1302,NULL,NULL,'DSCN2478.jpg',3507998,'image/jpeg','albums',NULL,NULL,'2016-10-21 16:22:04','2016-10-21 16:22:04','albums\\\\image\\1477059724753\\DSCN2478.jpg',NULL,NULL,NULL,'',NULL,1,NULL,14,'f40ee301-72f7-4e56-9db0-d7b0319d0448',NULL),(1303,NULL,NULL,'DSCN2478.jpg',3507998,'image/jpeg','albums',NULL,NULL,'2016-10-21 16:22:49','2016-10-21 16:22:49','albums\\\\image\\1477059769310\\DSCN2478.jpg',NULL,NULL,NULL,'',NULL,1,NULL,14,'214be922-cb8a-44f8-bb33-1a614790e74a',NULL),(1304,NULL,NULL,'DSCN2478.jpg',3507998,'image/jpeg','albums',NULL,NULL,'2016-10-21 16:25:10','2016-10-21 16:25:10','albums\\\\image\\1477059910606\\DSCN2478.jpg',NULL,NULL,NULL,'',NULL,1,NULL,14,'23c01da4-debc-4190-98e6-a547ab237000',NULL),(1305,NULL,NULL,'DSCN2478.jpg',3507998,'image/jpeg','albums',NULL,NULL,'2016-10-21 16:28:39','2016-10-21 16:28:39','albums\\\\image\\1477060119644\\DSCN2478.jpg',NULL,NULL,NULL,'',NULL,1,NULL,14,'5bbf401b-0ede-4333-98e8-33b9e50dd926',NULL),(1306,NULL,NULL,'DSCN2478.jpg',3507998,'image/jpeg','albums',NULL,NULL,'2016-10-21 16:29:26','2016-10-21 16:29:26','albums\\\\image\\1477060166849\\DSCN2478.jpg',NULL,NULL,NULL,'',NULL,1,NULL,14,'52d1d284-e6c3-4f11-a0b1-56e80cc1d145',NULL),(1307,NULL,NULL,'DSCN2478.jpg',3507998,'image/jpeg','albums',NULL,NULL,'2016-10-21 16:37:16','2016-10-21 16:37:16','albums\\16d7ec30-c3fe-4fcb-be1f-07c84044bc47\\image\\1477060636621\\DSCN2478.jpg',NULL,NULL,NULL,'16d7ec30-c3fe-4fcb-be1f-07c84044bc47',43,1,NULL,14,'f984cbd5-79b6-4589-a443-8f4294036871',NULL),(1308,NULL,NULL,'DSCN2292.jpg',3497773,'image/jpeg','albums',NULL,NULL,'2016-10-22 13:29:32','2016-10-22 13:29:32','albums\\\\image\\1477135772902\\DSCN2292.jpg',NULL,NULL,NULL,'',NULL,1,NULL,14,'12e61e7c-21cd-4a04-b59c-90ad07ce667e',NULL),(1309,NULL,NULL,'DSCN2292.jpg',3497773,'image/jpeg','albums',NULL,NULL,'2016-10-22 13:47:47','2016-10-22 13:47:47','albums\\\\image\\1477136867079\\DSCN2292.jpg',NULL,NULL,NULL,'',NULL,1,NULL,14,'e1a141bc-6299-43b0-b7f0-9cf411e6e6c9',NULL),(1310,NULL,NULL,'DSCN2292.jpg',3497773,'image/jpeg','albums',NULL,NULL,'2016-10-22 13:51:22','2016-10-22 13:51:22','albums\\b05d3964-0abe-45bf-9fe6-d5232eb1664a\\image\\1477137082577\\DSCN2292.jpg',NULL,NULL,NULL,'b05d3964-0abe-45bf-9fe6-d5232eb1664a',NULL,1,NULL,14,'96640455-a767-41b2-b586-fdabb5f483ea',NULL),(1311,NULL,NULL,'DSCN2292.jpg',3497773,'image/jpeg','albums',NULL,NULL,'2016-10-22 16:59:22','2016-10-22 16:59:22','albums\\\\image\\1477148362625\\DSCN2292.jpg',NULL,NULL,NULL,'',NULL,1,NULL,14,'30f52581-7166-46f4-b680-ce250d35a3f9',NULL),(1312,NULL,NULL,'DSCN2292.jpg',3497773,'image/jpeg','albums',NULL,NULL,'2016-10-22 18:02:09','2016-10-22 18:02:09','albums\\\\image\\1477152129536\\DSCN2292.jpg',NULL,NULL,NULL,'',NULL,1,NULL,14,'6acb6aa6-f6b1-4837-bb22-f47197a056c7',NULL),(1313,NULL,NULL,'DSCN2292.jpg',3497773,'image/jpeg','albums',NULL,NULL,'2016-10-22 18:06:23','2016-10-22 18:06:23','albums\\16d7ec30-c3fe-4fcb-be1f-07c84044bc47\\image\\1477152383139\\DSCN2292.jpg',NULL,NULL,NULL,'16d7ec30-c3fe-4fcb-be1f-07c84044bc47',43,1,NULL,14,'4d145a50-9dd5-48a2-bfe8-3b8793e5704c',NULL),(1314,NULL,NULL,'DSCN2293.jpg',3432688,'image/jpeg','albums',NULL,NULL,'2016-10-22 18:07:08','2016-10-22 18:07:08','albums\\16d7ec30-c3fe-4fcb-be1f-07c84044bc47\\image\\1477152428072\\DSCN2293.jpg',NULL,NULL,NULL,'16d7ec30-c3fe-4fcb-be1f-07c84044bc47',43,1,NULL,14,'70fd2913-02d6-4255-87df-25889aac4ab5',NULL),(1315,NULL,NULL,'DSCN2294.jpg',3608710,'image/jpeg','albums',NULL,NULL,'2016-10-22 18:07:13','2016-10-22 18:07:13','albums\\16d7ec30-c3fe-4fcb-be1f-07c84044bc47\\image\\1477152433020\\DSCN2294.jpg',NULL,NULL,NULL,'16d7ec30-c3fe-4fcb-be1f-07c84044bc47',43,1,NULL,14,'b4dd594b-5aff-4e71-ac17-e33b26de8934',NULL),(1316,NULL,NULL,'DSCN2295.jpg',3050559,'image/jpeg','albums',NULL,NULL,'2016-10-22 18:07:16','2016-10-22 18:07:16','albums\\16d7ec30-c3fe-4fcb-be1f-07c84044bc47\\image\\1477152436803\\DSCN2295.jpg',NULL,NULL,NULL,'16d7ec30-c3fe-4fcb-be1f-07c84044bc47',43,1,NULL,14,'fb8ccd44-1835-4b10-b67e-b8edc0cc15e9',NULL),(1317,NULL,NULL,'spring-logo-horizontal.png',3374,'image/png','posts',66,NULL,'2016-11-01 11:52:50','2016-11-01 12:31:29','posts/a9ba33f3-a86b-4413-a645-cf150d54fdb5/image/1477997570048/spring-logo-horizontal.png',NULL,NULL,NULL,'a9ba33f3-a86b-4413-a645-cf150d54fdb5',NULL,1,NULL,14,'e8beb156-16a5-49ea-b456-ca009b6927dd',NULL),(1318,NULL,NULL,'JPAHibernate.jpg',104902,'image/jpeg','dossiers',2,NULL,'2016-11-01 13:46:06','2016-11-01 13:46:10','posts/19f27576-77a6-43bd-96c0-947048c31b86/image/1478004366252/JPAHibernate.jpg',NULL,NULL,NULL,'19f27576-77a6-43bd-96c0-947048c31b86',NULL,1,NULL,13,'2a85290e-4a8e-4023-a5c6-fc710851117b',NULL),(1319,NULL,NULL,'spring-logo-horizontal.png',3374,'image/png','dossiers',10,NULL,'2016-11-01 15:59:58','2016-11-01 15:59:58','posts/a9ba33f3-a86b-4413-a645-cf150d54fdb5/image/1477997570048/spring-logo-horizontal.png',NULL,NULL,NULL,'77ec41aa-69c8-411c-ab14-f3dec513bf8a',NULL,1,NULL,14,'45d3afae-c05c-4df3-bc43-a3eb407ac74a',NULL),(1320,NULL,NULL,'JPAHibernate.jpg',104902,'image/jpeg','posts',67,NULL,'2016-11-01 16:03:13','2016-11-05 13:40:29','posts/19f27576-77a6-43bd-96c0-947048c31b86/image/1478004366252/JPAHibernate.jpg',NULL,NULL,NULL,'c08da2bd-764a-4621-ad2c-f8f045467946',NULL,0,NULL,13,'43671ea7-fbed-4dea-9271-028f9ca0091e',NULL),(1321,NULL,NULL,'JPAHibernate.jpg',104902,'image/jpeg','albums',NULL,NULL,'2016-11-01 18:10:01','2016-11-01 18:10:01','albums/16d7ec30-c3fe-4fcb-be1f-07c84044bc47/image/1478020201138/JPAHibernate.jpg',NULL,NULL,NULL,'16d7ec30-c3fe-4fcb-be1f-07c84044bc47',43,1,NULL,14,'bf08175e-1b59-461c-9936-7656b50a1c1c',NULL);
/*!40000 ALTER TABLE `uploads` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `uploads_items`
--

DROP TABLE IF EXISTS `uploads_items`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `uploads_items` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `upload_id` int(11) NOT NULL,
  `link_id` int(11) NOT NULL,
  `link_table` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `uploads_items`
--

LOCK TABLES `uploads_items` WRITE;
/*!40000 ALTER TABLE `uploads_items` DISABLE KEYS */;
/*!40000 ALTER TABLE `uploads_items` ENABLE KEYS */;
UNLOCK TABLES;

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
INSERT INTO `users` VALUES (10,'Jane','Doe','jane','$2a$10$j6jJeXCYduoTXebq01LbeOitOOqWtGi35jRexRN.MpGiyMYdiib8K','jane@email.com',1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(12,'Elvis','Hatungimana','admin','$2a$10$u0NNlIty4Utl46EhM1YTJOLyYAJFzVVckYMVCtVpWfy69rU07n.0q','admin@elevysi.com',1,NULL,'2016-10-15 20:20:43',NULL,NULL,NULL,NULL,NULL,NULL),(14,'John','Boss','john','$2a$10$yowWGLSDERIJmoQ90XKNCOfvo1eEIsBRYMEhiIWqGX3.1kZFmkZ86','john@elevysi.com',1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(15,'Jean','News','jean','$2a$10$MKHQTktR5gkk2i3cabA57O0pZAn8HdMaWMx422Lf/1CnPnbGy7kd6','news@elevysi.com',1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(16,'Lebron','James','lebron','$2a$10$OnOrzDvIR6W2FUWxhLoZJ.K31XEXLZSTz3FCQNS1hfX/F4QouZ5uu','lebron@cavaliers.com',1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(19,'Kobe','Bryant','kobe','$2a$10$pUmlqHbWs966.FG3dtkvvOyFCc6v9a0s.EmGIE37vM.wqNI4Qk7Ha','kobe@lakers.com',1,'2015-10-18 16:21:44','2015-10-18 16:21:44',NULL,NULL,NULL,NULL,NULL,NULL),(20,'Kevin','Durant','kevin','$2a$10$tyrNMYqNZ1kD757rTrHzwOI9H0EKLDgsa2rA5bbssfNiUZ7x5M6bq','kevin@okc.com',1,'2015-10-18 16:24:17','2016-10-15 20:19:50',NULL,NULL,NULL,NULL,NULL,NULL),(21,'Steph','Curry','steph','$2a$10$2sI2UW8/1WebQh8MEgkjkOajksLauREMMBwoYryRGuJAnMDBvJ536','steph@warrioirs.com',1,'2015-11-06 21:26:48','2015-11-06 21:26:48',NULL,NULL,NULL,NULL,NULL,NULL),(22,'James','Harden','james','$2a$10$IQf1SqC1ScYk/rgSaQbHOuHbs1xdszQph9KvWByybxh8mDklaisWq','harden@houston.com',1,'2015-11-06 23:19:07','2015-11-06 23:19:07',NULL,NULL,NULL,NULL,NULL,NULL),(23,'Tony','Parker','tony','$2a$10$9VgmIS.VW97gdCv3ERHhLO9QI2ZCufQmKAN6FH.m013ssNh2jZg4y','parker@spurs.com',1,'2015-11-06 23:35:18','2015-11-06 23:35:18',NULL,NULL,NULL,NULL,NULL,NULL),(24,'Elvis','Hatungimana','elevysi','$2a$10$W3I28yoeicZC5A9M4hCTW.9AxQsYsijXllQqBr/8xJPIzSyjmpqla','elvis.hatungimana@elevysi.com',1,'2016-07-29 09:39:14','2016-07-29 09:39:14',NULL,NULL,NULL,NULL,NULL,NULL),(25,'Feeling','Someone','music','$2a$10$1Qn/SrelAh.Iy2SMlF5J3e0sZxyvjH0C8pUdM1retCuy1D.g7wq.i','fast@car.music.com',1,'2016-09-15 14:48:01','2016-09-15 14:48:01',NULL,NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users_roles`
--

DROP TABLE IF EXISTS `users_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users_roles` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `role_id` varchar(45) NOT NULL,
  `created` datetime DEFAULT NULL,
  `modified` datetime DEFAULT NULL,
  `record_owner` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users_roles`
--

LOCK TABLES `users_roles` WRITE;
/*!40000 ALTER TABLE `users_roles` DISABLE KEYS */;
INSERT INTO `users_roles` VALUES (2,9,'4',NULL,NULL,NULL),(3,10,'4',NULL,NULL,NULL),(5,12,'1',NULL,NULL,NULL),(6,14,'4',NULL,NULL,NULL),(7,15,'4',NULL,NULL,NULL),(8,16,'4',NULL,NULL,NULL),(9,17,'4',NULL,NULL,NULL),(10,18,'4',NULL,NULL,NULL),(11,19,'4',NULL,NULL,NULL),(12,20,'4',NULL,NULL,NULL),(13,21,'4',NULL,NULL,NULL),(14,22,'4',NULL,NULL,NULL),(15,23,'4',NULL,NULL,NULL),(16,12,'4',NULL,NULL,NULL),(17,24,'4',NULL,NULL,NULL),(18,25,'4',NULL,NULL,NULL);
/*!40000 ALTER TABLE `users_roles` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-11-19  9:02:30
