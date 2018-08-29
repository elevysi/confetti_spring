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
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `plays`
--

LOCK TABLES `plays` WRITE;
/*!40000 ALTER TABLE `plays` DISABLE KEYS */;
INSERT INTO `plays` VALUES (9,'https://www.youtube.com/embed/NK_e5_fKtIo',1,14,1,'<p>Good song and good dancing</p>','2016-08-24 10:33:22','2016-08-24 10:33:22','Wicked','https://www.youtube.com/embed/NK_e5_fKtIo',37,NULL,NULL,NULL,NULL),(10,'https://www.youtube.com/embed/ZYeVF9m9wdo',1,14,1,'<p>When you really look at it, life is plain simple. We always act as if something was promised to us but it really is not! Everything that we are given is extra. The present as they say is just a gift, it&rsquo;s an unexpected thing that lands in our hands, we do not deserve it any better than another. Looking at it from this perspective can ease up things and bring you some peace of mind.&nbsp;</p>\r\n<p>The mind seems to have loads of barriers which really are not ones. Limits, like fears, are just an illusion, said Michael Jordan. Now that is something to take away from someone that has been tried over and over again, still stood up and raised high. I even think that MJ had more mental strength than talent. He was an elite basketball player, that is for sure, but how many other guys can claim the same if not more talent. Thing is when you play at such a high level, pressure kicks in and if you cannot handle this, your talent alone will not make you shine without some mental strength. You can tell MJ was having fun playing basketball; he would not take easy lay ups, he would always do the show with crazy wicked moves. Now that is a sign of someone having fun; it is almost as if his mind would say: &ldquo;Too easy; let me add some twist to it by raising the difficulty level! &ldquo;. It seemed as if he had not much of mental barriers, as if he played like he had nothing to lose, as if he was always saying &ldquo;Why not?&rdquo;. They say Steph Curry is playing at an arrogant level but he still does not measure up to MJ in my opinion! MJ dunked and had his god damn tongue out all the time; why your tongue out MJ? Maybe because he just wanted, he was used to it that way!? and in the end why not? He was a character. It is like the colorful Soul vs. the conventional one from the &ldquo;Better call Soul&rdquo; show. One struggles but sticks to society lines while conflicting with his true nature and his call in life. He then goes on to switch his fancy suit for flashy orange / green suits; that right there is building a character, that is doing what he likes! Same with the court guys with middle age wigs? Why? Maybe because it is funny; well I don&rsquo;t know but I am not mad about this at all!</p>\r\n<p>So why struggle with barriers which in reality are not ones? I, personally, have made the decision that whenever I feel like fear is the one factor holding me back, I would go ahead and do it just for the sake of kicking fear&rsquo;s ass. Really there is so much that could go wrong, so much out of one&rsquo;s control that everyone owes themselves some courage and do what they want for nothing is guaranteed; both good and bad are always possible outcome so do yourself a favor and believe in the better! Sometimes I watch out for motivation and Prince EA on YouTube has got some of the most uplifting words out there. He said it best with &ldquo;Courage is not the absence of fear, but the judgement that something is more important than that fear&rdquo;. You cannot let fear control your decisions, as living in fear is not living at all.</p>','2016-08-24 13:54:21','2016-08-24 13:54:21','Once in while I ask myself, what am I doing?','https://www.youtube.com/embed/ZYeVF9m9wdo',41,NULL,NULL,NULL,NULL),(11,'https://www.youtube.com/embed/4sSKWNV3Gvk',1,14,1,'<p>Ever thought why something you find great is so underrated? Don\'t they see? That is how I feel about this song; for me it is one of the best songs ever made, period! I remember, when it came out, we used to go mad happy when it was played in the club, singing along the lyrics and asking the DJ for a replay. But for some reason it never really kikced in with the mainstream people! Timbaland was on fire at that moment and all eyes were on him, so why didn\'t this become a classic? Plus we love Jojo, don\'t we!?</p>','2016-09-02 12:00:54','2016-09-02 12:00:54','Talkin\' about my bae','https://www.youtube.com/embed/4sSKWNV3Gvk',47,NULL,NULL,NULL,NULL),(12,'https://www.youtube.com/embed/BPwCd9vjO6E',1,14,1,'<p>Drake\'s music is sometimes best when you are down. I was listening to <strong>Nothing was the same</strong> album and thinking: When Rihanna dumps your ass, we\'re gonna have such a great album Drake! I don\'t wish it for you though, I love your music!</p>','2016-09-02 12:05:34','2016-09-02 12:05:34','Don\'t think about it too much','https://www.youtube.com/embed/BPwCd9vjO6E',48,NULL,NULL,NULL,NULL),(13,'https://www.youtube.com/embed/eqceBawW4kM',1,14,1,'<p>Still a hall famer song; it&rsquo;s very catchy, kinda wanna take you into doing a little two steps dance move. It is a cute song, nothing very outstanding but I find it so good! Definitely one of my favorites. Jaicko says he likes nice things but does not need them to be happy, kinda capture the spirit of a humble baller: yes flash it once in a while if you can but don&rsquo;t let it get to your head, you know what I mean?! Live big or live broke, you can still be happy.</p>','2016-09-04 21:54:46','2016-09-04 21:54:46','Two Piece','https://www.youtube.com/embed/eqceBawW4kM',49,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `plays` ENABLE KEYS */;
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
