CREATE DATABASE  IF NOT EXISTS `blogdb` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `blogdb`;
-- MySQL dump 10.13  Distrib 5.6.13, for osx10.6 (i386)
--
-- Host: localhost    Database: blogdb
-- ------------------------------------------------------
-- Server version	5.5.33

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
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `albums`
--

LOCK TABLES `albums` WRITE;
/*!40000 ALTER TABLE `albums` DISABLE KEYS */;
INSERT INTO `albums` VALUES (4,NULL,NULL,'2015-11-05 21:13:19',NULL,'2015-11-05 21:13:19','2015-11-05 21:13:19',26,'posts'),(5,NULL,NULL,'2015-11-05 21:21:18',NULL,'2015-11-05 21:21:18','2015-11-05 21:21:18',27,'posts'),(6,NULL,NULL,'2015-11-05 21:24:11',NULL,'2015-11-05 21:24:11','2015-11-05 21:24:11',28,'posts'),(7,NULL,NULL,'2015-11-05 22:13:52',NULL,'2015-11-05 22:13:52','2015-11-05 22:13:52',29,'posts'),(8,NULL,NULL,'2015-11-05 22:18:11',NULL,'2015-11-05 22:18:11','2015-11-05 22:18:11',30,'posts'),(9,NULL,NULL,'2015-11-05 22:20:47',NULL,'2015-11-05 22:20:47','2015-11-05 22:20:47',31,'posts');
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
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comments`
--

LOCK TABLES `comments` WRITE;
/*!40000 ALTER TABLE `comments` DISABLE KEYS */;
INSERT INTO `comments` VALUES (1,NULL,NULL,NULL,NULL,NULL,NULL,'info@web.com','Elvis','A comment',NULL,NULL),(2,NULL,NULL,NULL,NULL,NULL,NULL,'info@web.com','Elvis','We dem boyz',NULL,NULL),(3,31,'posts',NULL,NULL,NULL,NULL,'info@web.com','Elvis','ada',NULL,NULL),(4,31,'posts',NULL,NULL,NULL,NULL,'lemarcus@spurs.com','Lemarchus Aldbrigde','I think SA Spurs will improve over the years. We are just getting started',NULL,NULL),(5,31,'posts',NULL,NULL,NULL,NULL,NULL,NULL,'A new one',NULL,NULL),(6,31,'posts',NULL,NULL,9,NULL,NULL,NULL,'My new comment comes here',NULL,NULL);
/*!40000 ALTER TABLE `comments` ENABLE KEYS */;
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
  `sender` int(11) NOT NULL,
  `created` datetime DEFAULT NULL,
  `modified` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `messages`
--

LOCK TABLES `messages` WRITE;
/*!40000 ALTER TABLE `messages` DISABLE KEYS */;
/*!40000 ALTER TABLE `messages` ENABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `post_categories`
--

LOCK TABLES `post_categories` WRITE;
/*!40000 ALTER TABLE `post_categories` DISABLE KEYS */;
INSERT INTO `post_categories` VALUES (1,32,1),(2,34,2),(3,34,4);
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
  `title` varchar(45) NOT NULL,
  `description` varchar(45) DEFAULT NULL,
  `user_id` int(11) NOT NULL,
  `created` datetime DEFAULT NULL,
  `modified` datetime DEFAULT NULL,
  `content` mediumtext,
  `upload_id` int(11) DEFAULT NULL,
  `type_id` int(11) DEFAULT NULL,
  `profile_id` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `uuid` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uid_UNIQUE` (`uuid`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `posts`
--

LOCK TABLES `posts` WRITE;
/*!40000 ALTER TABLE `posts` DISABLE KEYS */;
INSERT INTO `posts` VALUES (10,'Team Work',NULL,16,'2015-10-13 16:44:19','2015-10-13 16:44:19','Lebron James',NULL,NULL,4,NULL,NULL),(11,'New Post with no md',NULL,9,'2015-10-14 13:31:28','2015-10-14 13:31:28','Here goes the post in question',NULL,NULL,4,NULL,NULL),(12,'A dummy Post','Just to start out',16,'2015-10-17 20:59:44','2015-10-17 20:59:44','<p>A wonderful serenity has taken possession of my entire soul, like these sweet mornings of spring which I enjoy with my whole heart. I am alone, and feel the charm of existence in this spot, which was created for the bliss of souls like mine. I am so happy, my dear friend, so absorbed in the exquisite sense of mere tranquil existence, t<span style=\"text-decoration: underline;\">hat I neglect my talents. I should</span> be incapable of drawing a single stroke at the present moment; and yet I feel that I never was a greater artist than now. When, while the lovely valley teems with vapour around me, and the meridi<strong>an sun strikes the upper</strong> surface of the impenetrable foliage of my trees, and but a few stray gleams steal into the inner sanctuary, I throw myself down among the tall grass by the trickling stream; and, as I lie close to the earth, a thousand unknown plants are noticed by me: when I hear the buzz of the little world among the stalks, and grow familiar with the countless indescribable forms of the insects and flies, then I feel the presence of the Almighty, who formed us in his own image, and the breath</p>',NULL,NULL,NULL,NULL,NULL),(13,'Second Dummy Post','A second dummy post',16,'2015-10-17 21:05:51','2015-10-17 21:05:51','<p>Far far away, behind the word mountains, far from the countries Vokalia and Consonantia, there live the blind texts. Separated they live in Bookmarksgrove right at the coast of the Semantics, a large language ocean. A small river named Duden flows by their place and supplies it with the necessary regelialia. It is a paradisematic country, in which roasted parts of sentences fly into your mouth. Even the all-powerful Pointing has no control about the blind texts it is an almost unorthographic life One day however a small line of blind text by the name of Lorem Ipsum decided to leave for the far World of Grammar. The Big Oxmox advised her not to do so, because there were thousands of bad Commas, wild Question Marks and devious Semikoli, but the Little Blind Text didn&rsquo;t listen. She packed her seven versalia, put her initial into the belt and made herself on the way. When she reached the first hills of the Italic Mountains, she had a last view back on the skyline of her hometown Bookmarksgrove, the headline of Alphabet Village and the subline of her own road, the Line Lane. Pityful a rethoric question ran over her cheek, then</p>',NULL,NULL,4,NULL,NULL),(14,'Post avec Image','Un post avec une Image',16,'2015-10-17 22:23:59','2015-10-17 22:23:59','<p>But I must explain to you how all this mistaken idea of denouncing pleasure and praising pain was born and I will give you a complete account of the system, and expound the actual teachings of the great explorer of the truth, the master-builder of human happiness. No one rejects, dislikes, or avoids pleasure itself, because it is pleasure, but because those who do not know how to pursue pleasure rationally encounter consequences that are extremely painful. Nor again is there anyone who loves or pursues or desires to obtain pain of itself, because it is pain, but because occasionally circumstances occur in which toil and pain can procure him some great pleasure. To take a trivial example, which of us ever undertakes laborious physical exercise, except to obtain some advantage from it? But who has any right to find fault with a man who chooses to enjoy a pleasure that has no annoying consequences, or one who avoids a pain that produces no resultant pleasure? On the other hand, we denounce with righteous indignation and dislike men who are so beguiled and demoralized by the charms of pleasure of the moment, so blinded by desire, that they cannot foresee</p>',NULL,NULL,4,NULL,NULL),(15,'First UI Post','Eh bah',16,'2015-10-17 22:34:58','2015-10-17 22:34:58','<p>One morning, when Gregor Samsa woke from troubled dreams, he found himself transformed in his bed into a horrible vermin. He lay on his armour-like back, and if he lifted his head a little he could see his brown belly, slightly domed and divided by arches into stiff sections. The bedding was hardly able to cover it and seemed ready to slide off any moment. His many legs, pitifully thin compared with the size of the rest of him, waved about helplessly as he looked. \"What\'s happened to me?\" he thought. It wasn\'t a dream. His room, a proper human room although a little too small, lay peacefully between its four familiar walls. A collection of textile samples lay spread out on the table - Samsa was a travelling salesman - and above it there hung a picture that he had recently cut out of an illustrated magazine and housed in a nice, gilded frame. It showed a lady fitted out with a fur hat and fur boa who sat upright, raising a heavy fur muff that covered the whole of her lower arm towards the viewer. Gregor then turned to look out the window at the dull weather. Drops</p>',NULL,NULL,4,NULL,NULL),(16,'dw','dwd',16,'2015-10-17 22:41:22','2015-10-17 22:41:22','<p>dwdwdw</p>',NULL,NULL,4,NULL,NULL),(17,'A dummy Post','No matter',16,'2015-10-17 22:43:35','2015-10-17 22:43:35','<p>Check console</p>',NULL,NULL,4,NULL,NULL),(18,'Post with Image v2','This one is gonna work',16,'2015-10-17 22:52:51','2015-10-17 22:52:51','<p>The European languages are members of the same family. Their separate existence is a myth. For science, music, sport, etc, Europe uses the same vocabulary. The languages only differ in their grammar, their pronunciation and their most common words. Everyone realizes why a new common language would be desirable: one could refuse to pay expensive translators. To achieve this, it would be necessary to have uniform grammar, pronunciation and more common words. If several languages coalesce, the grammar of the resulting language is more simple and regular than that of the individual languages. The new common language will be more simple and regular than the existing European languages. It will be as simple as Occidental; in fact, it will be Occidental. To an English person, it will seem like simplified English, as a skeptical Cambridge friend of mine told me what Occidental is.The European languages are members of the same family. Their separate existence is a myth. For science, music, sport, etc, Europe uses the same vocabulary. The languages only differ in their grammar, their pronunciation and their most common words. Everyone realizes why a new common language would be desirable: one could refuse to pay expensive translators. To</p>',NULL,NULL,4,NULL,NULL),(19,'Mignons','How Cute',16,'2015-10-17 23:07:01','2015-10-17 23:07:01','<p>Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu, pretium quis, sem. Nulla consequat massa quis enim. Donec pede justo, fringilla vel, aliquet nec, vulputate eget, arcu. In enim justo, rhoncus ut, imperdiet a, venenatis vitae, justo. Nullam dictum felis eu pede mollis pretium. Integer tincidunt. Cras dapibus. Vivamus elementum semper nisi. Aenean vulputate eleifend tellus. Aenean leo ligula, porttitor eu, consequat vitae, eleifend ac, enim. Aliquam lorem ante, dapibus in, viverra quis, feugiat a, tellus. Phasellus viverra nulla ut metus varius laoreet. Quisque rutrum. Aenean imperdiet. Etiam ultricies nisi vel augue. Curabitur ullamcorper ultricies nisi. Nam eget dui. Etiam rhoncus. Maecenas tempus, tellus eget condimentum rhoncus, sem quam semper libero, sit amet adipiscing sem neque sed ipsum. Nam quam nunc, blandit vel, luctus pulvinar, hendrerit id, lorem. Maecenas nec odio et ante tincidunt tempus. Donec vitae sapien ut libero venenatis faucibus. Nullam quis ante. Etiam sit amet orci eget eros faucibus tincidunt. Duis leo. Sed fringilla mauris sit amet nibh. Donec sodales sagittis magna. Sed consequat, leo eget bibendum sodales, augue velit cursus nunc,</p>',NULL,NULL,4,NULL,NULL),(20,'OKC en route','my journey to the championship',20,'2015-10-18 18:16:50','2015-10-18 18:16:50','<p>Far far away, behind the word mountains, far from the countries Vokalia and Consonantia, there live the blind texts. Separated they live in Bookmarksgrove right at the coast of the Semantics, a large language ocean. A small river named Duden flows by their place and supplies it with the necessary regelialia. It is a paradisematic country, in which roasted parts of sentences fly into your mouth. Even the all-powerful Pointing has no control about the blind texts it is an almost unorthographic life One day however a small line of blind text by the name of Lorem Ipsum decided to leave for the far World of Grammar. The Big Oxmox advised her not to do so, because there were thousands of bad Commas, wild Question Marks and devious Semikoli, but the Little Blind Text didn&rsquo;t listen. She packed her seven versalia, put her initial into the belt and made herself on the way. When she reached the first hills of the Italic Mountains, she had a last view back on the skyline of her hometown Bookmarksgrove, the headline of Alphabet Village and the subline of her own road, the Line Lane. Pityful a rethoric question ran over her cheek, then she continued her way. On her way she met a copy. The copy warned the Little Blind Text, that where it came from it would have been rewritten a thousand times and everything that was left from its origin would be the word \"and\" and the Little Blind Text should turn around and return to its own, safe country. But nothing the copy said could convince her and so it didn&rsquo;t take long until a few insidious Copy Writers ambushed her, made her drunk with Longe and Parole and dragged her into their agency, where they abused her for their projects again and again. And if she hasn&rsquo;t been rewritten, then they are still using her. Far far away, behind the word mountains, far from the countries Vokalia and Consonantia, there live the blind texts. Separated they live in Bookmarksgrove right at the coast of the Semantics, a large language ocean. A small river named Duden flows by their place and supplies it with the necessary regelialia. It is a paradisematic country, in which roasted parts of sentences fly into your mouth. Even the all-powerful Pointing has no control about the blind texts it is an almost unorthographic life One day however a small line of blind text by the name of Lorem Ipsum decided to leave for the far World of Grammar. The Big Oxmox advised her not to do so, because there were thousands of bad Commas, wild Question Marks and devious Semikoli, but the Little Blind Text didn&rsquo;t listen. She packed her seven versalia, put her initial into the belt and made herself on the way. When she reached the first hills of the Italic Mountains, she had a last view back on the skyline of her hometown Bookmarksgrove, the headline of Alphabet Village and the subline of her own road, the Line Lane. Pityful a rethoric question ran over her cheek, then she continued her way. On her way she met a copy. The copy warned the Little Blind Text, that where it came from it would have been rewritten a thousand times and everything that was left from its origin would be the word \"and\" and the Little Blind Text should turn around and return to its own, safe country. But nothing the copy said could convince her and so it didn&rsquo;t take long until a few insidious Copy Writers ambushed her, made her drunk with Longe and Parole and dragged her into their agency, where they abused her for their projects again and again. And if she hasn&rsquo;t been rewritten, then they are still using her. Far far away, behind the word mountains, far from the countries Vokalia and Consonantia, there live the blind texts. Separated they live in Bookmarksgrove right at the coast of the Semantics, a large language ocean. A small river named Duden flows by their place and supplies it with the necessary regelialia. It is a paradisematic country, in which roasted parts of sentences fly into your mouth. Even the all-powerful Pointing has no control about the blind texts it is an almost unorthographic life One day however a small line of blind text by the name of Lorem Ipsum decided to leave for the far World of Grammar. The Big Oxmox advised her not to do so, because there were thousands of bad Commas, wild Question Marks and devious Semikoli, but the Little Blind Text didn&rsquo;t listen. She packed her seven versalia, put her initial into the belt and made herself on the way. When she reached the first hills of the Italic Mountains, she had a last view back on the skyline of her hometown Bookmarksgrove, the headline of Alphabet Village and the subline of her own road, the Line Lane. Pityful a rethoric question ran over her cheek, then she continued her way. On her way she met a copy. The copy warned the Little Blind Text, that where it came from it would have been rewritten a thousand times and everything that was left from its origin would be the word \"and\" and the Little Blind Text should turn around and return to its own, safe country. But nothing the copy said could convince her and so it didn&rsquo;t take long until a few insidious Copy Writers ambushed her, made her drunk with Longe and Parole and dragged her into their agency, where they abused her for their projects again and again. And if she hasn&rsquo;t been rewritten, then they are still using her.Far far away, behind the word mountains, far from the countries Vokalia and Consonantia, there live the blind texts. Separated they live in Bookmarksgrove right at the coast of the Semantics, a large language ocean. A small river named Duden flows by their place and supplies it with the necessary regelialia. It</p>',NULL,NULL,9,NULL,NULL),(21,'Album Post','Here',20,'2015-11-05 17:05:59','2015-11-05 17:05:59','<p>A wonderful serenity has taken possession of my entire soul, like these sweet mornings of spring which I enjoy with my whole heart. I am alone, and feel the charm of existence in this spot, which was created for the bliss of souls like mine. I am so happy, my dear friend, so absorbed in the exquisite sense of mere tranquil existence, that I neglect my talents. I should be incapable of drawing a single stroke at the present moment; and yet I feel that I never was a greater artist than now. When, while the lovely valley teems with vapour around me, and the meridian sun strikes the upper surface of the impenetrable foliage of my trees, and but a few stray gleams steal into the inner sanctuary, I throw myself down among the tall grass by the trickling stream; and, as I lie close to the earth, a thousand unknown plants are noticed by me: when I hear the buzz of the little world among the stalks, and grow familiar with the countless indescribable forms of the insects and flies, then I feel the presence of the Almighty, who formed us in his own image, and the breath</p>',NULL,NULL,9,2,'12fde53f-7dfa-42c8-8301-65968319a05a'),(22,'Business','As Usual',20,'2015-11-05 17:18:30','2015-11-05 17:18:30','<p>A wonderful serenity has taken possession of my entire soul, like these sweet mornings of spring which I enjoy with my whole heart. I am alone, and feel the charm of existence in this spot, which was created for the bliss of souls like mine. I am so happy, my dear friend, so absorbed in the exquisite sense of mere tranquil existence, that I neglect my talents. I should be incapable of drawing a single stroke at the present moment; and yet I feel that I never was a greater artist than now. When, while the lovely valley teems with vapour around me, and the meridian sun strikes the upper surface of the impenetrable foliage of my trees, and but a few stray gleams steal into the inner sanctuary, I throw myself down among the tall grass by the trickling stream; and, as I lie close to the earth, a thousand unknown plants are noticed by me: when I hear the buzz of the little world among the stalks, and grow familiar with the countless indescribable forms of the insects and flies, then I feel the presence of the Almighty, who formed us in his own image, and the breath</p>',NULL,NULL,9,2,'90f1475d-d389-44d5-93fc-ed6fe9671300'),(23,'Another one','I changed a lot',20,'2015-11-05 17:56:17','2015-11-05 17:56:17','<p>A wonderful serenity has taken possession of my entire soul, like these sweet mornings of spring which I enjoy with my whole heart. I am alone, and feel the charm of existence in this spot, which was created for the bliss of souls like mine. I am so happy, my dear friend, so absorbed in the exquisite sense of mere tranquil existence, that I neglect my talents. I should be incapable of drawing a single stroke at the present moment; and yet I feel that I never was a greater artist than now. When, while the lovely valley teems with vapour around me, and the meridian sun strikes the upper surface of the impenetrable foliage of my trees, and but a few stray gleams steal into the inner sanctuary, I throw myself down among the tall grass by the trickling stream; and, as I lie close to the earth, a thousand unknown plants are noticed by me: when I hear the buzz of the little world among the stalks, and grow familiar with the countless indescribable forms of the insects and flies, then I feel the presence of the Almighty, who formed us in his own image, and the breath</p>',NULL,NULL,9,2,'44f47285-9928-4c75-8f35-a97128e7e263'),(24,'Bleu','Is',20,'2015-11-05 17:58:00','2015-11-05 17:58:00','<p>A wonderful serenity has taken possession of my entire soul, like these sweet mornings of spring which I enjoy with my whole heart. I am alone, and feel the charm of existence in this spot, which was created for the bliss of souls like mine. I am so happy, my dear friend, so absorbed in the exquisite sense of mere tranquil existence, that I neglect my talents. I should be incapable of drawing a single stroke at the present moment; and yet I feel that I never was a greater artist than now. When, while the lovely valley teems with vapour around me, and the meridian sun strikes the upper surface of the impenetrable foliage of my trees, and but a few stray gleams steal into the inner sanctuary, I throw myself down among the tall grass by the trickling stream; and, as I lie close to the earth, a thousand unknown plants are noticed by me: when I hear the buzz of the little world among the stalks, and grow familiar with the countless indescribable forms of the insects and flies, then I feel the presence of the Almighty, who formed us in his own image, and the breath</p>',NULL,NULL,9,2,'3fb13d7a-33d1-4110-a18e-e5ca59f955d7'),(25,'Let a n','try me ',20,'2015-11-05 20:47:42','2015-11-05 20:47:42','<p>Imma pot a</p>',NULL,NULL,9,2,'f1fb5b31-e9e0-4683-8b20-a92c1d954354'),(26,'xaxax','xaxa',20,'2015-11-05 21:13:19','2015-11-05 21:13:19','<p>xaxaxa</p>',NULL,NULL,9,2,'8a774124-ebd1-4365-b44c-7023ef471757'),(27,'I am','Heree',20,'2015-11-05 21:21:18','2015-11-05 21:21:18','<p>Alicia Keys</p>',NULL,NULL,9,2,'681203fa-495d-4da8-bbd0-a5d4455f4458'),(28,'We','are',20,'2015-11-05 21:24:11','2015-11-05 21:24:11','<p>here</p>',NULL,NULL,9,2,'7491243c-52b2-4d3f-892b-d808a4226698'),(29,'Album Post','Aucun line',20,'2015-11-05 22:13:52','2015-11-05 22:13:52','<p>A wonderful serenity has taken possession of my entire soul, like these sweet mornings of spring which I enjoy with my whole heart. I am alone, and feel the charm of existence in this spot, which was created for the bliss of souls like mine. I am so happy, my dear friend, so absorbed in the exquisite sense of mere tranquil existence, that I neglect my talents. I should be incapable of drawing a single stroke at the present moment; and yet I feel that I never was a greater artist than now. When, while the lovely valley teems with vapour around me, and the meridian sun strikes the upper surface of the impenetrable foliage of my trees, and but a few stray gleams steal into the inner sanctuary, I throw myself down among the tall grass by the trickling stream; and, as I lie close to the earth, a thousand unknown plants are noticed by me: when I hear the buzz of the little world among the stalks, and grow familiar with the countless indescribable forms of the insects and flies, then I feel the presence of the Almighty, who formed us in his own image, and the breath</p>',NULL,NULL,9,2,'37ce393c-2044-411a-a5a7-e4a423801329'),(30,'Une recrue','Intention',20,'2015-11-05 22:18:11','2015-11-05 22:18:11','<p>But I must explain to you how all this mistaken idea of denouncing pleasure and praising pain was born and I will give you a complete account of the system, and expound the actual teachings of the great explorer of the truth, the master-builder of human happiness. No one rejects, dislikes, or avoids pleasure itself, because it is pleasure, but because those who do not know how to pursue pleasure rationally encounter consequences that are extremely painful. Nor again is there anyone who loves or pursues or desires to obtain pain of itself, because it is pain, but because occasionally</p>',NULL,NULL,9,2,'7edf64b2-0d26-4a8c-92a8-e22985b4b055'),(31,'Ola','We dem boyz',20,'2015-11-05 22:20:47','2015-11-05 22:20:47','<p>But I must explain to you how all this mistaken idea of denouncing pleasure and praising pain was born and I will give you a complete account of the system, and expound the actual teachings of the great explorer of the truth, the master-builder of human happiness. No one rejects, dislikes, or avoids pleasure itself, because it is pleasure, but because those who do not know how to pursue pleasure rationally encounter consequences that are extremely painful. Nor again is there anyone who loves or pursues or desires to obtain pain of itself, because it is pain, but because occasionally</p>',NULL,NULL,9,2,'f6fb6c02-1c66-4820-8daa-2b5df7e3d63b'),(32,'Dummy Integer','No comments',20,'2015-11-06 15:53:12','2015-11-06 15:53:12','<p>Another dummy Post here to see what happens as we go</p>',NULL,NULL,9,2,'5be42d04-1997-4645-b0be-73d531bd486e'),(33,'My awesome post','I am making a post',20,'2015-11-06 15:54:52','2015-11-06 15:54:52','<p>A new awesome post goes on here as prepared.</p>',NULL,NULL,9,2,'09da3669-18b1-4a4b-8928-5eb27a6444a3'),(34,'Several Categories','Here are some more',20,'2015-11-06 16:23:27','2015-11-06 16:23:27','<p>Li Europan lingues es membres del sam familie. Lor separat existentie es un myth. Por scientie, musica, sport etc, litot Europa usa li sam vocabular. Li lingues differe solmen in li grammatica, li pronunciation e li plu commun vocabules. Omnicos directe al desirabilite de un nov lingua franca: On refusa continuar payar custosi traductores. At solmen va esser necessi far uniform grammatica, pronunciation e plu sommun paroles. Ma quande lingues coalesce, li grammatica del resultant lingue es plu simplic e regulari quam ti del coalescent lingues. Li nov lingua franca va esser plu simplic e regulari quam li existent Europan</p>',NULL,NULL,9,2,'213523bb-2808-42d0-8e01-b6d4eee6b02e');
/*!40000 ALTER TABLE `posts` ENABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `profile_friends`
--

LOCK TABLES `profile_friends` WRITE;
/*!40000 ALTER TABLE `profile_friends` DISABLE KEYS */;
INSERT INTO `profile_friends` VALUES (1,3,1,'2015-10-09 16:05:50','2015-10-09 16:05:50',3,1,'2015-10-09 16:05:50','2015-10-09 16:05:50'),(2,3,2,'2015-10-09 16:05:50','2015-10-09 16:05:50',3,1,'2015-10-09 16:05:50','2015-10-09 16:05:50'),(10,5,4,NULL,NULL,NULL,1,NULL,NULL),(11,4,5,NULL,NULL,NULL,1,NULL,NULL);
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
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `profile_types`
--

LOCK TABLES `profile_types` WRITE;
/*!40000 ALTER TABLE `profile_types` DISABLE KEYS */;
INSERT INTO `profile_types` VALUES (1,'user',NULL,NULL,NULL);
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
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `profiles`
--

LOCK TABLES `profiles` WRITE;
/*!40000 ALTER TABLE `profiles` DISABLE KEYS */;
INSERT INTO `profiles` VALUES (1,14,NULL,NULL,1,'dummy',NULL),(2,15,'2015-10-09 16:05:50','2015-10-09 16:05:50',1,'dummy',NULL),(4,16,'2015-10-13 10:13:25','2015-10-13 10:13:25',1,'dummy',NULL),(8,19,'2015-10-18 16:21:44','2015-10-18 16:21:44',1,'kobe',NULL),(9,20,'2015-10-18 16:24:17','2015-10-18 16:24:17',1,'kevin',NULL);
/*!40000 ALTER TABLE `profiles` ENABLE KEYS */;
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
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1177 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `uploads`
--

LOCK TABLES `uploads` WRITE;
/*!40000 ALTER TABLE `uploads` DISABLE KEYS */;
INSERT INTO `uploads` VALUES (1112,NULL,NULL,'passport.jpg',117991,'image/jpeg','profilePicture',4,NULL,NULL,NULL,'mignon.png',NULL,NULL,NULL,NULL,NULL,1,NULL,NULL),(1113,NULL,NULL,'mignon.png',395011,'image/png','postImage',NULL,NULL,NULL,NULL,'mignon.png',NULL,NULL,NULL,NULL,NULL,1,NULL,NULL),(1114,NULL,NULL,'mignon.png',395011,'image/png','postImage',NULL,NULL,NULL,NULL,'mignon.png',NULL,NULL,NULL,NULL,NULL,1,NULL,NULL),(1115,NULL,NULL,'mignon.png',395011,'image/png','postImage',NULL,NULL,NULL,NULL,'mignon.png',NULL,NULL,NULL,NULL,NULL,1,NULL,NULL),(1120,NULL,NULL,'1profile.jpg',461693,'image/jpeg','profilePicture',9,NULL,NULL,NULL,'profiles/9/avatar/1profile.jpg',NULL,NULL,NULL,NULL,NULL,1,NULL,NULL),(1121,NULL,NULL,'1.jpg',336712,'image/jpeg','postImage',20,NULL,NULL,NULL,'stateless/image/1.jpg',NULL,NULL,NULL,NULL,NULL,1,NULL,NULL),(1122,NULL,NULL,'#2.png',114605,'image/png','postImage',NULL,NULL,NULL,NULL,'stateless/image/#2.png',NULL,NULL,NULL,'0d5de1fb-c0ae-428b-b4d3-27140af94bfc',NULL,1,NULL,NULL),(1123,NULL,NULL,'#2.png',114605,'image/png','postImage',NULL,NULL,NULL,NULL,'stateless/image/#2.png',NULL,NULL,NULL,'800a09d4-ea79-4d6f-a63c-b7c1b2995d8a',NULL,1,NULL,NULL),(1124,NULL,NULL,'hot',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,2,'61668a1c-642e-47c8-84b1-3069afb732c4',NULL,0,'http://www.elevysi.com',9),(1143,NULL,NULL,'https://getimage.com',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,'d443c7a8-a59a-44df-a183-3a0b8e0066a5',NULL,0,'https://getimage.com',NULL),(1144,NULL,NULL,'http://www.elevysi.com',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,'d443c7a8-a59a-44df-a183-3a0b8e0066a5',NULL,0,'http://www.elevysi.com',NULL),(1145,NULL,NULL,'http://www.tomcatexpert.com/blog/feed',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,'d443c7a8-a59a-44df-a183-3a0b8e0066a5',NULL,0,'http://www.tomcatexpert.com/blog/feed',NULL),(1146,NULL,NULL,'www.lessentiel.lu',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,'d443c7a8-a59a-44df-a183-3a0b8e0066a5',NULL,0,'www.lessentiel.lu',NULL),(1147,NULL,NULL,'http://www.elevysi.com',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,'d443c7a8-a59a-44df-a183-3a0b8e0066a5',NULL,0,'http://www.elevysi.com',9),(1148,NULL,NULL,'http://anotherone.rule.com',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,'d443c7a8-a59a-44df-a183-3a0b8e0066a5',NULL,0,'http://anotherone.rule.com',9),(1149,NULL,NULL,'1.jpg',336712,'image/jpeg','postImage',NULL,NULL,NULL,NULL,'stateless/image/1.jpg',NULL,NULL,NULL,'d443c7a8-a59a-44df-a183-3a0b8e0066a5',NULL,0,NULL,NULL),(1150,NULL,NULL,'#2.png',114605,'image/png','postImage',NULL,NULL,NULL,NULL,'stateless/image/#2.png',NULL,NULL,NULL,'d443c7a8-a59a-44df-a183-3a0b8e0066a5',NULL,0,NULL,9),(1151,NULL,NULL,'1.jpg',336712,'image/jpeg','postImage',NULL,NULL,NULL,NULL,'stateless/image/1.jpg',NULL,NULL,NULL,'d443c7a8-a59a-44df-a183-3a0b8e0066a5',NULL,0,NULL,9),(1152,NULL,NULL,'http://www.elevysi.com',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,'61668a1c-642e-47c8-84b1-3069afb732c4',NULL,0,'http://www.elevysi.com',9),(1153,NULL,NULL,'#2.png',114605,'image/png','postImage',NULL,NULL,NULL,NULL,'stateless/image/#2.png',NULL,NULL,NULL,'12fde53f-7dfa-42c8-8301-65968319a05a',NULL,1,NULL,9),(1154,NULL,NULL,'1.jpg',336712,'image/jpeg','postImage',NULL,NULL,NULL,NULL,'stateless/image/1.jpg',NULL,NULL,NULL,'12fde53f-7dfa-42c8-8301-65968319a05a',NULL,1,NULL,9),(1155,NULL,NULL,'#2.png',114605,'image/png','postImage',NULL,NULL,NULL,NULL,'stateless/image/#2.png',NULL,NULL,NULL,'749acbe8-e1e5-425a-8df3-1498ef429140',NULL,0,NULL,9),(1156,NULL,NULL,'#2.png',114605,'image/png','postImage',NULL,NULL,NULL,NULL,'stateless/image/#2.png',NULL,NULL,NULL,'90f1475d-d389-44d5-93fc-ed6fe9671300',NULL,1,NULL,9),(1157,NULL,NULL,'1.jpg',336712,'image/jpeg','postImage',NULL,NULL,NULL,NULL,'stateless/image/1.jpg',NULL,NULL,NULL,'90f1475d-d389-44d5-93fc-ed6fe9671300',NULL,1,NULL,9),(1158,NULL,NULL,'#2.png',114605,'image/png','postImage',NULL,NULL,NULL,NULL,'stateless/image/#2.png',NULL,NULL,NULL,'3fb13d7a-33d1-4110-a18e-e5ca59f955d7',NULL,1,NULL,9),(1159,NULL,NULL,'1.jpg',336712,'image/jpeg','postImage',NULL,NULL,NULL,NULL,'stateless/image/1.jpg',NULL,NULL,NULL,'3fb13d7a-33d1-4110-a18e-e5ca59f955d7',NULL,1,NULL,9),(1160,NULL,NULL,'1.jpg',336712,'image/jpeg','postImage',NULL,NULL,NULL,NULL,'stateless/image/1.jpg',NULL,NULL,NULL,'f1fb5b31-e9e0-4683-8b20-a92c1d954354',NULL,1,NULL,9),(1161,NULL,NULL,'#2.png',114605,'image/png','postImage',NULL,NULL,NULL,NULL,'stateless/image/#2.png',NULL,NULL,NULL,'f1fb5b31-e9e0-4683-8b20-a92c1d954354',NULL,1,NULL,9),(1162,NULL,NULL,'#2.png',114605,'image/png','postImage',NULL,NULL,NULL,NULL,'stateless/image/#2.png',NULL,NULL,NULL,'8a774124-ebd1-4365-b44c-7023ef471757',NULL,1,NULL,9),(1163,NULL,NULL,'1.jpg',336712,'image/jpeg','postImage',NULL,NULL,NULL,NULL,'stateless/image/1.jpg',NULL,NULL,NULL,'8a774124-ebd1-4365-b44c-7023ef471757',NULL,1,NULL,9),(1164,NULL,NULL,'1.jpg',336712,'image/jpeg','postImage',NULL,NULL,NULL,NULL,'stateless/image/1.jpg',NULL,NULL,NULL,'681203fa-495d-4da8-bbd0-a5d4455f4458',NULL,1,NULL,9),(1165,NULL,NULL,'#2.png',114605,'image/png','postImage',NULL,NULL,NULL,NULL,'stateless/image/#2.png',NULL,NULL,NULL,'681203fa-495d-4da8-bbd0-a5d4455f4458',NULL,1,NULL,9),(1166,NULL,NULL,'#2.png',114605,'image/png','postImage',NULL,NULL,NULL,NULL,'stateless/image/#2.png',NULL,NULL,NULL,'7491243c-52b2-4d3f-892b-d808a4226698',6,1,NULL,9),(1167,NULL,NULL,'1.jpg',336712,'image/jpeg','postImage',NULL,NULL,NULL,NULL,'stateless/image/1.jpg',NULL,NULL,NULL,'7491243c-52b2-4d3f-892b-d808a4226698',6,1,NULL,9),(1168,NULL,NULL,'1.jpg',336712,'image/jpeg','postImage',NULL,NULL,NULL,NULL,'stateless/image/1.jpg',NULL,NULL,NULL,'37ce393c-2044-411a-a5a7-e4a423801329',7,1,NULL,9),(1169,NULL,NULL,'2.png',114605,'image/png','postImage',NULL,NULL,NULL,NULL,'stateless/image/2.png',NULL,NULL,NULL,'37ce393c-2044-411a-a5a7-e4a423801329',7,1,NULL,9),(1170,NULL,NULL,'1 2.jpg',443738,'image/jpeg','postImage',NULL,NULL,NULL,NULL,'stateless/image/1 2.jpg',NULL,NULL,NULL,'7edf64b2-0d26-4a8c-92a8-e22985b4b055',8,1,NULL,9),(1171,NULL,NULL,'1.jpg',336712,'image/jpeg','postImage',NULL,NULL,NULL,NULL,'stateless/image/1.jpg',NULL,NULL,NULL,'7edf64b2-0d26-4a8c-92a8-e22985b4b055',8,1,NULL,9),(1172,NULL,NULL,'12.jpg',443738,'image/jpeg','postImage',NULL,NULL,NULL,NULL,'stateless/image/12.jpg',NULL,NULL,NULL,'f6fb6c02-1c66-4820-8daa-2b5df7e3d63b',9,1,NULL,9),(1173,NULL,NULL,'1.jpg',336712,'image/jpeg','postImage',NULL,NULL,NULL,NULL,'stateless/image/1.jpg',NULL,NULL,NULL,'f6fb6c02-1c66-4820-8daa-2b5df7e3d63b',9,1,NULL,9),(1174,NULL,NULL,'12.jpg',443738,'image/jpeg','postImage',NULL,NULL,NULL,NULL,'stateless/image/12.jpg',NULL,NULL,NULL,'70977706-a595-48c4-bbb1-edcd11680832',NULL,1,NULL,9),(1175,NULL,NULL,'12.jpg',443738,'image/jpeg','postImage',33,NULL,NULL,NULL,'stateless/image/12.jpg',NULL,NULL,NULL,'09da3669-18b1-4a4b-8928-5eb27a6444a3',NULL,1,NULL,9),(1176,NULL,NULL,'1profile.jpg',461693,'image/jpeg','postImage',34,NULL,NULL,NULL,'stateless/image/1profile.jpg',NULL,NULL,NULL,'213523bb-2808-42d0-8e01-b6d4eee6b02e',NULL,1,NULL,9);
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
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (10,'Jane','Doe','jane','$2a$10$j6jJeXCYduoTXebq01LbeOitOOqWtGi35jRexRN.MpGiyMYdiib8K','jane@email.com',1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(12,'Elvis','Hatungimana','admin','$2a$10$/uXRPfSZaAERLx5aUHota.fUpgSsus6cbzAJyrl9Y2MK677PyQWci','admin@elevysi.com',1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(14,'John','Boss','john','$2a$10$yowWGLSDERIJmoQ90XKNCOfvo1eEIsBRYMEhiIWqGX3.1kZFmkZ86','john@elevysi.com',1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(15,'Jean','News','jean','$2a$10$MKHQTktR5gkk2i3cabA57O0pZAn8HdMaWMx422Lf/1CnPnbGy7kd6','news@elevysi.com',1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(16,'Lebron','James','lebron','$2a$10$OnOrzDvIR6W2FUWxhLoZJ.K31XEXLZSTz3FCQNS1hfX/F4QouZ5uu','lebron@cavaliers.com',1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(19,'Kobe','Bryant','kobe','$2a$10$pUmlqHbWs966.FG3dtkvvOyFCc6v9a0s.EmGIE37vM.wqNI4Qk7Ha','kobe@lakers.com',1,'2015-10-18 16:21:44','2015-10-18 16:21:44',NULL,NULL,NULL,NULL,NULL,NULL),(20,'Kevin','Durant','kevin','$2a$10$Qzd1BtwfAW8TDE9ovfheqO0Pu41GYI3g71hlPtDbJC.31NHN33qta','kevin@okc.com',1,'2015-10-18 16:24:17','2015-10-18 16:24:17',NULL,NULL,NULL,NULL,NULL,NULL);
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
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users_roles`
--

LOCK TABLES `users_roles` WRITE;
/*!40000 ALTER TABLE `users_roles` DISABLE KEYS */;
INSERT INTO `users_roles` VALUES (2,9,'4',NULL,NULL,NULL),(3,10,'4',NULL,NULL,NULL),(5,12,'1',NULL,NULL,NULL),(6,14,'4',NULL,NULL,NULL),(7,15,'4',NULL,NULL,NULL),(8,16,'4',NULL,NULL,NULL),(9,17,'4',NULL,NULL,NULL),(10,18,'4',NULL,NULL,NULL),(11,19,'4',NULL,NULL,NULL),(12,20,'4',NULL,NULL,NULL);
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

-- Dump completed on 2015-11-06 19:45:01
