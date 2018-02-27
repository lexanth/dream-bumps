-- MySQL dump 10.16  Distrib 10.2.12-MariaDB, for Linux (x86_64)
--
-- Host: localhost    Database: dreambumps
-- ------------------------------------------------------
-- Server version	10.2.12-MariaDB

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
-- Table structure for table `DATABASECHANGELOG`
--

DROP TABLE IF EXISTS `DATABASECHANGELOG`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `DATABASECHANGELOG` (
  `ID` varchar(255) NOT NULL,
  `AUTHOR` varchar(255) NOT NULL,
  `FILENAME` varchar(255) NOT NULL,
  `DATEEXECUTED` datetime NOT NULL,
  `ORDEREXECUTED` int(11) NOT NULL,
  `EXECTYPE` varchar(10) NOT NULL,
  `MD5SUM` varchar(35) DEFAULT NULL,
  `DESCRIPTION` varchar(255) DEFAULT NULL,
  `COMMENTS` varchar(255) DEFAULT NULL,
  `TAG` varchar(255) DEFAULT NULL,
  `LIQUIBASE` varchar(20) DEFAULT NULL,
  `CONTEXTS` varchar(255) DEFAULT NULL,
  `LABELS` varchar(255) DEFAULT NULL,
  `DEPLOYMENT_ID` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `DATABASECHANGELOG`
--

LOCK TABLES `DATABASECHANGELOG` WRITE;
/*!40000 ALTER TABLE `DATABASECHANGELOG` DISABLE KEYS */;
INSERT INTO `DATABASECHANGELOG` VALUES ('00000000000001','jhipster','classpath:config/liquibase/changelog/00000000000000_initial_schema.xml','2018-02-26 22:18:47',1,'EXECUTED','7:bff2851968d816961bf81ed1f81332da','createTable tableName=jhi_user; createIndex indexName=idx_user_login, tableName=jhi_user; createIndex indexName=idx_user_email, tableName=jhi_user; createTable tableName=jhi_authority; createTable tableName=jhi_user_authority; addPrimaryKey tableN...','',NULL,'3.5.3',NULL,NULL,'9683526525'),('20170305151015-1','jhipster','classpath:config/liquibase/changelog/20170305151015_added_entity_Crew.xml','2018-02-26 22:18:47',2,'EXECUTED','7:24efd7275c6aa62e9bc2965d2c538382','createTable tableName=crew','',NULL,'3.5.3',NULL,NULL,'9683526525'),('20170305151234-1','jhipster','classpath:config/liquibase/changelog/20170305151234_added_entity_CrewMember.xml','2018-02-26 22:18:47',3,'EXECUTED','7:b3b3f37d2fd78414eb4275a5d49f8cda','createTable tableName=crew_member','',NULL,'3.5.3',NULL,NULL,'9683526525'),('20170305151406-1','jhipster','classpath:config/liquibase/changelog/20170305151406_added_entity_CrewPriceHistory.xml','2018-02-26 22:18:47',4,'EXECUTED','7:51d77b1a186d89cef546afa01c4226b8','createTable tableName=crew_price_history; dropDefaultValue columnName=date_time, tableName=crew_price_history','',NULL,'3.5.3',NULL,NULL,'9683526525'),('20170305151814-1','jhipster','classpath:config/liquibase/changelog/20170305151814_added_entity_CrewPositionHistory.xml','2018-02-26 22:18:47',5,'EXECUTED','7:135c5668a64f02bf81f179a7f5107feb','createTable tableName=crew_position_history','',NULL,'3.5.3',NULL,NULL,'9683526525'),('20170305152044-1','jhipster','classpath:config/liquibase/changelog/20170305152044_added_entity_UserCrewMember.xml','2018-02-26 22:18:47',6,'EXECUTED','7:fc1c04ec02467022f5a5ebe3e1acec7c','createTable tableName=user_crew_member','',NULL,'3.5.3',NULL,NULL,'9683526525'),('20170305152430-1','jhipster','classpath:config/liquibase/changelog/20170305152430_added_entity_UserCrewPositionHistory.xml','2018-02-26 22:18:47',7,'EXECUTED','7:1f52733202ae4fc9c571a221cea7e794','createTable tableName=user_crew_position_history','',NULL,'3.5.3',NULL,NULL,'9683526525'),('20170305152914-1','jhipster','classpath:config/liquibase/changelog/20170305152914_added_entity_UserCrewPriceHistory.xml','2018-02-26 22:18:47',8,'EXECUTED','7:2b921962db6937b8f86e2ab95de02eb1','createTable tableName=user_crew_price_history; dropDefaultValue columnName=date_time, tableName=user_crew_price_history','',NULL,'3.5.3',NULL,NULL,'9683526525'),('20170305153103-1','jhipster','classpath:config/liquibase/changelog/20170305153103_added_entity_MarketStatusHistory.xml','2018-02-26 22:18:47',9,'EXECUTED','7:80592f099ab5786be7a1b7915ad3c9a8','createTable tableName=market_status_history; dropDefaultValue columnName=date_time, tableName=market_status_history','',NULL,'3.5.3',NULL,NULL,'9683526525'),('20170306220237-1','jhipster','classpath:config/liquibase/changelog/20170306220237_added_entity_UserCrewPrice.xml','2018-02-26 22:18:47',10,'EXECUTED','7:d3b65275d5abea31a97aa09aab20a32b','createTable tableName=user_crew_price','',NULL,'3.5.3',NULL,NULL,'9683526525'),('20170305151234-2','jhipster','classpath:config/liquibase/changelog/20170305151234_added_entity_constraints_CrewMember.xml','2018-02-26 22:18:47',11,'EXECUTED','7:6b244172d25bf9e2d7a5af00ba28d669','addForeignKeyConstraint baseTableName=crew_member, constraintName=fk_crew_member_crew_id, referencedTableName=crew; addUniqueConstraint constraintName=one_person_in_each_seat, tableName=crew_member','',NULL,'3.5.3',NULL,NULL,'9683526525'),('20170305151406-2','jhipster','classpath:config/liquibase/changelog/20170305151406_added_entity_constraints_CrewPriceHistory.xml','2018-02-26 22:18:47',12,'EXECUTED','7:9b6bcc5dbbf502d9219c59b1aac19c4f','addForeignKeyConstraint baseTableName=crew_price_history, constraintName=fk_crew_price_history_crew_id, referencedTableName=crew','',NULL,'3.5.3',NULL,NULL,'9683526525'),('20170305151814-2','jhipster','classpath:config/liquibase/changelog/20170305151814_added_entity_constraints_CrewPositionHistory.xml','2018-02-26 22:18:47',13,'EXECUTED','7:04311e9fea202ff519bc15de66e8b080','addForeignKeyConstraint baseTableName=crew_position_history, constraintName=fk_crew_position_history_crew_id, referencedTableName=crew; addUniqueConstraint constraintName=one_position_per_day, tableName=crew_position_history','',NULL,'3.5.3',NULL,NULL,'9683526525'),('20170305152044-2','jhipster','classpath:config/liquibase/changelog/20170305152044_added_entity_constraints_UserCrewMember.xml','2018-02-26 22:18:47',14,'EXECUTED','7:0496840c40c69d7027b284f8784d93d7','addForeignKeyConstraint baseTableName=user_crew_member, constraintName=fk_user_crew_member_user_id, referencedTableName=jhi_user; addForeignKeyConstraint baseTableName=user_crew_member, constraintName=fk_user_crew_member_crew_id, referencedTableNa...','',NULL,'3.5.3',NULL,NULL,'9683526525'),('20170305152430-2','jhipster','classpath:config/liquibase/changelog/20170305152430_added_entity_constraints_UserCrewPositionHistory.xml','2018-02-26 22:18:47',15,'EXECUTED','7:79ef2cb7a7cdc85483d25a5e2eb12edb','addForeignKeyConstraint baseTableName=user_crew_position_history, constraintName=fk_user_crew_position_history_user_id, referencedTableName=jhi_user; addForeignKeyConstraint baseTableName=user_crew_position_history, constraintName=fk_user_crew_pos...','',NULL,'3.5.3',NULL,NULL,'9683526525'),('20170305152914-2','jhipster','classpath:config/liquibase/changelog/20170305152914_added_entity_constraints_UserCrewPriceHistory.xml','2018-02-26 22:18:48',16,'EXECUTED','7:5d90b2e30287aec1b7b97edf3ea3bb40','addForeignKeyConstraint baseTableName=user_crew_price_history, constraintName=fk_user_crew_price_history_user_id, referencedTableName=jhi_user','',NULL,'3.5.3',NULL,NULL,'9683526525'),('20170306220237-2','jhipster','classpath:config/liquibase/changelog/20170306220237_added_entity_constraints_UserCrewPrice.xml','2018-02-26 22:18:48',17,'EXECUTED','7:75c7f0205ed8722e99f7717739358c91','addForeignKeyConstraint baseTableName=user_crew_price, constraintName=fk_user_crew_price_user_id, referencedTableName=jhi_user; addUniqueConstraint constraintName=one_price_per_crew, tableName=user_crew_price','',NULL,'3.5.3',NULL,NULL,'9683526525');
/*!40000 ALTER TABLE `DATABASECHANGELOG` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `DATABASECHANGELOGLOCK`
--

DROP TABLE IF EXISTS `DATABASECHANGELOGLOCK`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `DATABASECHANGELOGLOCK` (
  `ID` int(11) NOT NULL,
  `LOCKED` bit(1) NOT NULL,
  `LOCKGRANTED` datetime DEFAULT NULL,
  `LOCKEDBY` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `DATABASECHANGELOGLOCK`
--

LOCK TABLES `DATABASECHANGELOGLOCK` WRITE;
/*!40000 ALTER TABLE `DATABASECHANGELOGLOCK` DISABLE KEYS */;
INSERT INTO `DATABASECHANGELOGLOCK` VALUES (1,'\0',NULL,NULL);
/*!40000 ALTER TABLE `DATABASECHANGELOGLOCK` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `crew`
--

DROP TABLE IF EXISTS `crew`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `crew` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `sex` varchar(255) NOT NULL,
  `image` varchar(255) DEFAULT NULL,
  `price` decimal(10,2) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=135 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `crew`
--

LOCK TABLES `crew` WRITE;
/*!40000 ALTER TABLE `crew` DISABLE KEYS */;
INSERT INTO `crew` VALUES (1,'Pembroke M1','male','NULL',300.00),(2,'Pembroke M2','male','NULL',136.00),(3,'Pembroke M3','male','NULL',17.74),(4,'Pembroke W1','female','NULL',193.78),(5,'Pembroke W2','female','NULL',63.49),(6,'Oriel M1','male','NULL',254.25),(7,'Oriel M2','male','NULL',56.53),(8,'Oriel M3','male','NULL',16.83),(9,'Oriel W1','female','NULL',300.00),(10,'Oriel W2','female','NULL',67.26),(11,'Wadham M1','male','NULL',227.49),(12,'Wadham M2','male','NULL',71.26),(13,'Wadham M3','male','NULL',53.31),(14,'Wadham W1','female','NULL',254.25),(15,'Wadham W2','female','NULL',84.97),(16,'Catherine\'s M1','male','NULL',208.50),(17,'Catherine\'s M2','male','NULL',51.76),(18,'Catherine\'s W1','female','NULL',82.47),(19,'Catherine\'s W2','female','NULL',29.77),(20,'Hertford M1','male','NULL',193.78),(21,'Hertford M2','male','NULL',45.89),(22,'Hertford W1','female','NULL',208.50),(23,'Hertford W2','female','NULL',40.50),(24,'Christ Church M1','male','NULL',181.74),(25,'Christ Church M2','male','NULL',61.68),(26,'Christ Church W1','female','NULL',227.49),(27,'Christ Church W2','female','NULL',37.96),(28,'Christ Church W3','female','NULL',33.16),(29,'Balliol M1','male','NULL',171.57),(30,'Balliol M2','male','NULL',58.20),(31,'Balliol W1','female','NULL',117.01),(32,'Balliol W2','female','NULL',51.76),(33,'Wolfson M1','male','NULL',162.76),(34,'Wolfson M2','male','NULL',69.23),(35,'Wolfson M3','male','NULL',36.73),(36,'Wolfson W1','female','NULL',154.98),(37,'Wolfson W2','female','NULL',93.06),(38,'Wolfson W3','female','NULL',54.90),(39,'Magdalen M1','male','NULL',154.98),(40,'Magdalen M2','male','NULL',39.22),(41,'Magdalen W1','female','NULL',136.00),(42,'Magdalen W2','female','NULL',39.22),(43,'John\'s M1','male','NULL',148.03),(44,'John\'s M2','male','NULL',41.81),(45,'John\'s W1','female','NULL',181.74),(46,'John\'s W2','female','NULL',61.68),(47,'Jesus M1','male','NULL',141.74),(48,'Jesus M2','male','NULL',65.35),(49,'Jesus M3','male','NULL',27.61),(50,'Jesus W1','female','NULL',141.74),(51,'Jesus W2','female','NULL',47.31),(52,'Trinity M1','male','NULL',130.71),(53,'Trinity M2','male','NULL',50.24),(54,'Trinity M3','male','NULL',37.96),(55,'Trinity W1','female','NULL',113.01),(56,'Trinity W2','female','NULL',45.89),(57,'Teddy M1','male','NULL',125.82),(58,'Teddy M2','male','NULL',48.76),(59,'Teddy M3','male','NULL',19.60),(60,'Teddy W1','female','NULL',109.24),(61,'New College M1','male','NULL',121.27),(62,'New College M2','male','NULL',54.90),(63,'New College W1','female','NULL',130.71),(64,'New College W2','female','NULL',48.76),(65,'Mansfield M1','male','NULL',117.01),(66,'Mansfield W1','female','NULL',95.99),(67,'Mansfield W2','female','NULL',41.81),(68,'Univ M1','male','NULL',113.01),(69,'Univ M2','male','NULL',47.31),(70,'Univ M3','male','NULL',20.55),(71,'Univ W1','female','NULL',162.76),(72,'Keble M1','male','NULL',109.24),(73,'Keble M2','male','NULL',59.92),(74,'Keble M3','male','NULL',32.01),(75,'Keble W1','female','NULL',171.57),(76,'Anne\'s M1','male','NULL',105.67),(77,'Anne\'s W1','female','NULL',105.67),(78,'Anne\'s W2','female','NULL',43.14),(79,'Lincoln M1','male','NULL',102.28),(80,'Lincoln M2','male','NULL',40.50),(81,'Lincoln W1','female','NULL',148.03),(82,'Lincoln W2','female','NULL',53.31),(83,'Lady Margaret M1','male','NULL',99.06),(84,'Lady Margaret M2','male','NULL',33.16),(85,'Lady Margaret W1','female','NULL',102.28),(86,'Merton M1','male','NULL',95.99),(87,'Merton M2','male','NULL',34.33),(88,'Merton M3','male','NULL',18.66),(89,'Merton W1','female','NULL',71.26),(90,'Merton W2','female','NULL',35.52),(91,'Exeter M1','male','NULL',93.06),(92,'Exeter M2','male','NULL',30.88),(93,'Exeter W1','female','NULL',80.07),(94,'Somerville M1','male','NULL',90.25),(95,'Somerville W1','female','NULL',125.82),(96,'Somerville W2','female','NULL',56.53),(97,'Corpus Christi M1','male','NULL',87.55),(98,'Corpus Christi M2','male','NULL',29.77),(99,'Corpus Christi W1','female','NULL',59.92),(100,'Corpus Christi W2','female','NULL',32.01),(101,'Worcester M1','male','NULL',84.97),(102,'Worcester M2','male','NULL',21.51),(103,'Worcester W1','female','NULL',90.25),(104,'Worcester W2','female','NULL',44.50),(105,'Brasenose M1','male','NULL',82.47),(106,'Brasenose M2','male','NULL',43.14),(107,'Brasenose W1','female','NULL',87.55),(108,'Brasenose W2','female','NULL',30.88),(109,'Linacre M1','male','NULL',80.07),(110,'Linacre W1','female','NULL',99.06),(111,'Linacre W2','female','NULL',50.24),(112,'Queen\'s M1','male','NULL',77.76),(113,'Queen\'s W1','female','NULL',75.52),(114,'Antony\'s M1','male','NULL',75.52),(115,'Antony\'s M2','male','NULL',28.68),(116,'Antony\'s W1','female','NULL',69.23),(117,'Hugh\'s M1','male','NULL',73.36),(118,'Hugh\'s M2','male','NULL',26.55),(119,'Hugh\'s M3','male','NULL',25.51),(120,'Hugh\'s W1','female','NULL',77.76),(121,'Hugh\'s W2','female','NULL',34.33),(122,'Peter\'s M1','male','NULL',67.26),(123,'Peter\'s M2','male','NULL',22.49),(124,'Peter\'s W1','female','NULL',65.35),(125,'Green Templeton M1','male','NULL',63.49),(126,'Green Templeton W1','female','NULL',121.27),(127,'Green Templeton W2','female','NULL',28.68),(128,'Hilda\'s M1','male','NULL',44.50),(129,'Hilda\'s M2','male','NULL',24.49),(130,'Hilda\'s W1','female','NULL',73.36),(131,'Hilda\'s W2','female','NULL',36.73),(132,'Regent\'s M1','male','NULL',39.22),(133,'Regent\'s W1','female','NULL',58.20),(134,'Benet\'s M1','male','NULL',23.48);
/*!40000 ALTER TABLE `crew` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `crew_member`
--

DROP TABLE IF EXISTS `crew_member`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `crew_member` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `seat` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `crew_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `one_person_in_each_seat` (`crew_id`,`seat`),
  CONSTRAINT `fk_crew_member_crew_id` FOREIGN KEY (`crew_id`) REFERENCES `crew` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1207 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `crew_member`
--

LOCK TABLES `crew_member` WRITE;
/*!40000 ALTER TABLE `crew_member` DISABLE KEYS */;
INSERT INTO `crew_member` VALUES (1,1,'Jesse Sigal',29),(2,2,'Laszlo Berencei',29),(3,3,'Alban Schmid',29),(4,4,'Benjamin Hubbert',29),(5,5,'Roman Rothaermel',29),(6,6,'Oscar Lyons',29),(7,7,'Edward Ashton',29),(8,8,'William Wathey',29),(9,9,'Moyo Tian',29),(10,1,'Benjamin Elliott',30),(11,2,'Daniel Rastelli',30),(12,3,'Matthew Hoyle',30),(13,4,'Tobias Schillings',30),(14,5,'Luke Chester',30),(15,6,'Ilya Shemmer',30),(16,7,'Thomas Vogl',30),(17,8,'Johan Henriksson',30),(18,9,'Friederike Winter',30),(19,1,'Thomas Plews',105),(20,2,'Tobias de Mendonça',105),(21,3,'Imre Juhasz',105),(22,4,'Henry Wheeler',105),(23,5,'Samuel White',105),(24,6,'Peter Edmondson',105),(25,7,'Alexander van Leeuwen',105),(26,8,'Toby Sims',105),(27,9,'Emily Patterson',105),(28,1,'James Roper',106),(29,2,'Matteo Maciel',106),(30,3,'Gregory Hartle',106),(31,4,'Richard O’Halloran',106),(32,5,'Oliver Hanson',106),(33,6,'Tom McQueen',106),(34,7,'Will Bunce',106),(35,8,'Peter Scanlon',106),(36,9,'Samuel White',106),(37,1,'Timothy Yandle',24),(38,2,'Peter Kilfeather',24),(39,3,'David Baker',24),(40,4,'George Mantzos',24),(41,5,'Harrison Green',24),(42,6,'Robert McCraith',24),(43,7,'Philippos Mastroyannis',24),(44,8,'Nicolas Desclee',24),(45,9,'Ashley Ellington',24),(46,1,'Joao Cavalheiro',25),(47,2,'Dominik Schwarz',25),(48,3,'David Schipilov',25),(49,4,'Marcus Roeschlin',25),(50,5,'William Franklin',25),(51,6,'Harmon Barlow',25),(52,7,'Claas Ludwig',25),(53,8,'Anurag Kapur',25),(54,9,'Gregory Horne',25),(55,1,'Thomas Fairclough',97),(56,2,'Arthur Morris',97),(57,3,'Arthur Berkley',97),(58,4,'Kelvin Justiva',97),(59,5,'Max Phillips',97),(60,6,'Adam Wigley',97),(61,7,'Alexandre Tchen',97),(62,8,'James Baker',97),(63,9,'emile roberts',97),(64,1,'William Song',98),(65,2,'Ryan Salter',98),(66,3,'Michael Zaayman',98),(67,4,'Niels Jakobsen',98),(68,5,'patrik gerber',98),(69,6,'John Myers',98),(70,7,'Bjoern Brauer',98),(71,8,'Bertie Veres',98),(72,9,'James Neale',98),(73,1,'Maximilian Spicer',91),(74,2,'James Chasty',91),(75,3,'Christy Kin-Cleaves',91),(76,4,'Philip Moseley',91),(77,5,'Aidan Walker',91),(78,6,'Matthew Holyoak',91),(79,7,'Meng Kuok',91),(80,8,'Oscar North',91),(81,9,'Nina Min',91),(82,1,'Cameron Eadie',92),(83,2,'Mark Hay',92),(84,3,'Shane Clark',92),(85,4,'David Coates',92),(86,5,'Charles Ford',92),(87,6,'Kevin Chapman',92),(88,7,'James Hind',92),(89,8,'Benjamin Hammond',92),(90,9,'Victoria Gullo',92),(91,1,'Zong Chua',125),(92,2,'Henry-James Meiring',125),(93,3,'Gareth Watson',125),(94,4,'Adrien Hairault ',125),(95,5,'Christopher Sisca',125),(96,6,'Michael Argentieri',125),(97,7,'Fabio Sanna',125),(98,8,'Michael Smets',125),(99,9,'Aman Gupt',125),(100,1,'Patrick Austin',20),(101,2,'Robert House',20),(102,3,'Maciej Bugala',20),(103,4,'Freddie Kaufmann',20),(104,5,'Jack Waterman',20),(105,6,'Hamish Streeter',20),(106,7,'Henry Connor',20),(107,8,'Daniel Rozairo',20),(108,9,'Juliette Caverly',20),(109,1,'Mateo Solans',21),(110,2,'Yifu Tao',21),(111,3,'Dominik Koller',21),(112,4,'Justus Mentzel',21),(113,5,'Evangelos Dimopoulos',21),(114,6,'James Parr',21),(115,7,'Shashvat Shukla',21),(116,8,'Tobias John',21),(117,9,'Naomi Housego Day',21),(118,1,'Tim Koch',47),(119,2,'Stanley Billington',47),(120,3,'Benjamin Tucker',47),(121,4,'Ben Brown',47),(122,5,'Robert Strachan',47),(123,6,'Keir Bowater',47),(124,7,'John Gardner',47),(125,8,'Arthur Arnould',47),(126,9,'Iona Gilby',47),(127,1,'Owen Winter',48),(128,2,'Peter Todd',48),(129,3,'Leo Maedje',48),(130,4,'Simon Hulse',48),(131,5,'Ian Townsend',48),(132,6,'Nowell Phelps',48),(133,7,'Matthew Taylor',48),(134,8,'Adam Mazarelo',48),(135,9,'Catrin Howells',48),(136,1,'Michael Pears',49),(137,2,'Elliot Long',49),(138,3,'Aiden McGuirk',49),(139,4,'Benjamin Rackham',49),(140,5,'Liam Griffin',49),(141,6,'Piotr Wozniczka',49),(142,7,'Matt Oliver',49),(143,8,'Samuel Hoskin',49),(144,9,'Florence Smith',49),(145,1,'Matthew Lister',72),(146,2,'Luis Messner',72),(147,3,'Vojtech Havlicek',72),(148,4,'Raphael Heim',72),(149,5,'Frederick Kohlhas',72),(150,6,'Thomas Foster',72),(151,7,'Sven Jaeschke',72),(152,8,'Joe Ingram',72),(153,9,'Antonia Stutter',72),(154,1,'Baden Burns',73),(155,2,'Alex Gabel',73),(156,3,'Alexander Dallman-Porter',73),(157,4,'William Cook',73),(158,5,'Edward Soler',73),(159,6,'Krzysztof Oramus',73),(160,7,'Alexander Davies',73),(161,8,'David Drahi',73),(162,9,'Matthew Lister',73),(163,1,'Marcus Moore',74),(164,2,'Leon Van Riesen-Haupt',74),(165,3,'Christopher Everett',74),(166,4,'Yannis Baur',74),(167,5,'Augustus Brown',74),(168,6,'Luka Katic',74),(169,7,'Sungmin Cho',74),(170,8,'Sam Edwards',74),(171,9,'Alistair Parker',74),(172,1,'Giacomo Arrighini',83),(173,2,'Rudy Bunel',83),(174,3,'James Brebner',83),(175,4,'Benjamin Moore',83),(176,5,'Sam Rowan',83),(177,6,'Oliver Watson',83),(178,7,'Jonathan Catt',83),(179,8,'Robert Dickens',83),(180,9,'Esther Rollinson',83),(181,1,'Sean Kelly',84),(182,2,'Richard Heit',84),(183,3,'Vernon Moore',84),(184,4,'Matthew Beech ',84),(185,5,'Arber Gjoka',84),(186,6,'Ben Gregson',84),(187,7,'Owen Sweeney',84),(188,8,'Harry Ward',84),(189,9,'Stefanie Feltwell',84),(190,1,'Alexander Otto',109),(191,2,'Matthias Haslberger',109),(192,3,'Mehran Hosseini',109),(193,4,'Artur Doshchyn',109),(194,5,'Julian Ashwin',109),(195,6,'Thees Spreckelsen',109),(196,7,'Derek Xu',109),(197,8,'Stefan Hubner',109),(198,9,'Anna Thamm',109),(199,1,'Marios Charalambous',79),(200,2,'Oliver Featherstone',79),(201,3,'Ignacio Correa',79),(202,4,'Edward Roberts',79),(203,5,'Michael Niklaus',79),(204,6,'Thomas Pert',79),(205,7,'Jan Kvasnicka',79),(206,8,'Montague Lamb',79),(207,9,'Ursula Sentance',79),(208,1,'Alexander Hell',80),(209,2,'Fabio Morabito',80),(210,3,'Caspar Pfrunder',80),(211,4,'Fabian Soltermann',80),(212,5,'Maximilian Camphausen',80),(213,6,'Andre Nemeth',80),(214,7,'Michael Ben Yehuda',80),(215,8,'Glenn Cahilly-Bretzin',80),(216,9,'Katherine Halcrow',80),(217,1,'Alexander Choy',39),(218,2,'Andreas Janssen',39),(219,3,'Akhil Seth',39),(220,4,'Andrew Taylor',39),(221,5,'Jack Noble',39),(222,6,'Will Nelson',39),(223,7,'Sebastian Morris-Dyer',39),(224,8,'Thomas Duckett',39),(225,9,'David Horwich',39),(226,1,'Giacomo Santoro',40),(227,2,'Thomas Chamberlain',40),(228,3,'Alessandro Bautista',40),(229,4,'Andrew Zhu',40),(230,5,'Dominik Wagner',40),(231,6,'Yuchen Lu',40),(232,7,'Danny Cowan',40),(233,8,'Matthew Vautrey',40),(234,9,'Julian Kirk',40),(235,1,'Jazza McMillan-Clenaghan',65),(236,2,'Michael Lau',65),(237,3,'Henry Williams',65),(238,4,'Andrew Garratt',65),(239,5,'George Kiaeren',65),(240,6,'Oliver Farquharson',65),(241,7,'Nikolas Mattheis',65),(242,8,'Tariq Ali',65),(243,9,'Olivia Dwyer',65),(244,1,'Ryan Burke',86),(245,2,'Dylan Gutt',86),(246,3,'Lukas Krone',86),(247,4,'Ivo Gruev',86),(248,5,'Lukas Koch',86),(249,6,'Mantas Abazorius',86),(250,7,'Samuel Picard',86),(251,8,'Jacob White',86),(252,9,'Katherine Davies',86),(253,1,'Wilfred Ngia',87),(254,2,'Benjamin Hartnell-Booth',87),(255,3,'Thomas Miller',87),(256,4,'Richard Willett',87),(257,5,'Christian Willmes',87),(258,6,'Michael Bruckner',87),(259,7,'Tom Murphy',87),(260,8,'Paul de Jong',87),(261,9,'Zijun Chen',87),(262,1,'Gian Piero Milani',88),(263,2,'Andrew Kenyon-Roberts',88),(264,3,'William Whitehouse',88),(265,4,'Timothy Liau',88),(266,5,'Frederick Cheatle',88),(267,6,'Tito Bastianello',88),(268,7,'Piotr Khrkowski',88),(269,8,'Anthony Wohns',88),(270,9,'Emily Capstick',88),(271,1,'Laurence Watts',61),(272,2,'Oliver Turnbull',61),(273,3,'Nicholas Smart',61),(274,4,'Lorenzo Venturini',61),(275,5,'Maximilian Kadarauch',61),(276,6,'Maclej Olszewski',61),(277,7,'Nicholas Evans',61),(278,8,'Theo Merchant',61),(279,9,'Arthur Wotton',61),(280,1,'James Herholdt',62),(281,2,'Maximilian Rupps',62),(282,3,'Brett Sokol',62),(283,4,'Tim Hauser',62),(284,5,'Marian Kupculak',62),(285,6,'George Singleton',62),(286,7,'Joshua Hayler',62),(287,8,'Nicholas Sale',62),(288,9,'Darrion Mohan',62),(289,1,'Simon Bevan',6),(290,2,'Jan Ernst',6),(291,3,'Philipp Grunewald',6),(292,4,'Zachary Zajicek',6),(293,5,'Hal Frigaard',6),(294,6,'Louis Lamont',6),(295,7,'Robert Boswall',6),(296,8,'Achim Harzheim',6),(297,9,'Sophia Fearon',6),(298,1,'Bertie McIntosh',7),(299,2,'Maxilmillian Shreeve-McGiffen',7),(300,3,'Patrick Morrish',7),(301,4,'Thomas Williamson',7),(302,5,'Marcel Stolz',7),(303,6,'Jamie Wallis',7),(304,7,'Dan Strachan',7),(305,8,'Alexander Deak',7),(306,9,'Alex Jackson',7),(307,1,'Jack Blowers',8),(308,2,'Enis Nazif',8),(309,3,'Aleksander Kaminski',8),(310,4,'Lukas Beckerhinn',8),(311,5,'Daniel  Hall',8),(312,6,'Charles Fletcher',8),(313,7,'Robert Underwood',8),(314,8,'Joel Stanley-Cunning',8),(315,9,'Bertie McIntosh',8),(316,1,'Maximilian Jost',1),(317,2,'Adam Rebick',1),(318,3,'Khalid Mohsen',1),(319,4,'Kieran Wachsmuth',1),(320,5,'Benjamin Thomson',1),(321,6,'Matthew  Kerr',1),(322,7,'Alexander Oldroyd',1),(323,8,'Benjamin Stimpson',1),(324,9,'Jennifer Ehr',1),(325,1,'Samuel Rush',2),(326,2,'George Cull',2),(327,3,'Piers Brecher',2),(328,4,'Augustus Porter',2),(329,5,'Zixi Liang',2),(330,6,'Christopher Foster',2),(331,7,'Edward Rolls',2),(332,8,'David Dearlove',2),(333,9,'Tsvetana Myagkova',2),(334,1,'Christopher Wingard',3),(335,2,'Constantin Schneider',3),(336,3,'Maksymilian Marzec',3),(337,4,'Felix Walker',3),(338,5,'Austin Rose',3),(339,6,'Lewis Terry',3),(340,7,'Johannes Drahanowsky',3),(341,8,'Gregory Hynes',3),(342,9,'Sang Chun',3),(343,1,'Andreas Heilmann',112),(344,2,'Avi Mayorcas',112),(345,3,'Andre Hector',112),(346,4,'Andrew Turnbull',112),(347,5,'Henry Gray',112),(348,6,'Alexander Smith',112),(349,7,'Brodie Middleton',112),(350,8,'Maurice Gedney',112),(351,9,'Tristan Giron',112),(352,1,'James Brown',132),(353,2,'Jacob Cheli',132),(354,3,'Ethan Dockery',132),(355,4,'Harold Davies-Ball',132),(356,5,'Rhys Williams',132),(357,6,'Harriet McLeod',132),(358,7,'Alexander Priestley-Leach',132),(359,8,'Samuel Woor',132),(360,9,'Adam Large',132),(361,1,'Jun Liu',94),(362,2,'Daniel Simmonson',94),(363,3,'Atticus Albright',94),(364,4,'Pakhei Hao',94),(365,5,'Edwin Silverthorne',94),(366,6,'Edward Stowell',94),(367,7,'Toby Sanderson',94),(368,8,'Rohan O\'Reilly',94),(369,9,'Kevin Judd',94),(370,1,'Rory Gopsill',76),(371,2,'Benjamin Rudling',76),(372,3,'Jake Turner',76),(373,4,'Peter Strain',76),(374,5,'Daniel Thomson',76),(375,6,'Alasdair Paren',76),(376,7,'Lukas Beck',76),(377,8,'Thomas Munro',76),(378,9,'Amy Young',76),(379,1,'Sven Olsson',114),(380,2,'Dominic Gerhartz',114),(381,3,'James Haw',114),(382,4,'Fabio Schmidt-Fischbach',114),(383,5,'Nicholas Stenner',114),(384,6,'Robert Wood',114),(385,7,'Tilman Graff',114),(386,8,'Patrick Tiney',114),(387,9,'Natalia Cedzova',114),(388,1,'Kate Samuelson',115),(389,2,'Joana Westphal',115),(390,3,'Ivo Bantel',115),(391,4,'Antti Rauhala',115),(392,5,'Fabian Reitzug',115),(393,6,'Felix Soliman',115),(394,7,'Jonne Kamphorst',115),(395,8,'Ivaylo Walinga',115),(396,9,'Olha Homonchuk',115),(397,1,'Tim Baark',134),(398,2,'Leone Astolfi',134),(399,3,'Samuel Maginnis',134),(400,4,'Theodore Dick',134),(401,5,'William Harman',134),(402,6,'William Frost',134),(403,7,'Jack Williams',134),(404,8,'Marco Hughes',134),(405,9,'Caitriona Quigley',134),(406,1,'Niels Wicke',16),(407,2,'Alexander Herkert',16),(408,3,'Mateusz  Szczesny',16),(409,4,'Christopher George',16),(410,5,'Jack Shepherd-Cross',16),(411,6,'Dave Smith',16),(412,7,'Oliver Berry',16),(413,8,'Matthias Mergenthaler',16),(414,9,'Emma Hibbett',16),(415,1,'Nantas Nardelli',17),(416,2,'Nicolas Avramov',17),(417,3,'Sam Duffy',17),(418,4,'Frederik Schmitz',17),(419,5,'Titus Krahn',17),(420,6,'Samuel Hilditch',17),(421,7,'Jakob Kaeppler',17),(422,8,'Adam Megyeri',17),(423,9,'Katy-Louise Whelan',17),(424,1,'William Gasson',57),(425,2,'Kevin Gibbons',57),(426,3,'Benjamin Evans',57),(427,4,'Edward Baker',57),(428,5,'Marc Rocher',57),(429,6,'Joseph White',57),(430,7,'Joshua Smith',57),(431,8,'Douglas Chesterton',57),(432,9,'Natasha Cook',57),(433,1,'David dinneen',58),(434,2,'Michael Shao',58),(435,3,'Alistair Ross',58),(436,4,'Timothy Robinson',58),(437,5,'Aksel Saukko-Paavola',58),(438,6,'Samuel Sussmes',58),(439,7,'Oliver Woodhall',58),(440,8,'Oliver Shepherd',58),(441,9,'Catriona Campbell',58),(442,1,'Julia Camps',59),(443,2,'Alexander Moss',59),(444,3,'Thomas Knight',59),(445,4,'Fraser Casbolt',59),(446,5,'Agastya Pisharody',59),(447,6,'Jack Johnson',59),(448,7,'Jason Zhang',59),(449,8,'Michael Coughlan',59),(450,9,'Josephine Finch',59),(451,1,'Edwin Phua',128),(452,2,'Lorenzo Saccon',128),(453,3,'Max Piotrowicz',128),(454,4,'Si Hong Ng',128),(455,5,'Simon Biasi',128),(456,6,'Peter Belcak',128),(457,7,'Alexander Koffman',128),(458,8,'Felix Clarke',128),(459,9,'Charlotte Laycock',128),(460,1,'Matthew Jones',129),(461,2,'Samuel J Reid',129),(462,3,'Leo Panish',129),(463,4,'Magnus Jeffery',129),(464,5,'Leon Zaporski',129),(465,6,'Christian Peters',129),(466,7,'Jonas Hansohm',129),(467,8,'Ishaan Kapoor',129),(468,9,'Alexander Koffman',129),(469,1,'James Atkey',117),(470,2,'Thomas Barrett',117),(471,3,'Matthew Clark',117),(472,4,'William Dupree',117),(473,5,'Sheng Ho',117),(474,6,'Nicolas Clay',117),(475,7,'Edward Piggott',117),(476,8,'Henry Mellor',117),(477,9,'Eleanor Vickers',117),(478,1,'Howard Hall',118),(479,2,'Alexander Collins',118),(480,3,'Thomas Bladon',118),(481,4,'Artur Kula',118),(482,5,'Edward Elvin',118),(483,6,'Stoyan Dimitrov',118),(484,7,'Matthew Waters',118),(485,8,'Jakob Hassler',118),(486,9,'Roxanne Orloff',118),(487,1,'Samuel Byrne',119),(488,2,'Antoni Hanke',119),(489,3,'Jorge Rius',119),(490,4,'George Cherry',119),(491,5,'Guy Finny',119),(492,6,'Christopher Mason',119),(493,7,'Thomas Brewis',119),(494,8,'Kristof Kolar',119),(495,9,'Mia Donn',119),(496,1,'Brian K W Leong',43),(497,2,'David Videnovic',43),(498,3,'Jonathan  Henry',43),(499,4,'Stefan Clarke',43),(500,5,'Nicholas Hall',43),(501,6,'Lewis O\'Shaughnessy',43),(502,7,'Michal Kreft',43),(503,8,'Josiah Brunet',43),(504,9,'Mary Curwen',43),(505,1,'Joshua Clements',44),(506,2,'Andreas Kjaer',44),(507,3,'Sebastian Towers',44),(508,4,'Benedict Gardner',44),(509,5,'Alistair Hankey',44),(510,6,'Seb Orbell',44),(511,7,'Alastair Baldry',44),(512,8,'Solomon White',44),(513,9,'Izzy Fewster',44),(514,1,'Paul Rathke',122),(515,2,'Thomas Hadavizadeh',122),(516,3,'Alexander Jones',122),(517,4,'Rex Roxburgh',122),(518,5,'Titus-Stefan Dascalu',122),(519,6,'Samuel Heywood',122),(520,7,'James Gunnell',122),(521,8,'Remco Geervliet',122),(522,9,'Hamish de Nett',122),(523,1,'Joao Crema',123),(524,2,'Rohit Bagewadi',123),(525,3,'Marco Spartera',123),(526,4,'Christian Goodbrake',123),(527,5,'John Hodgkinson',123),(528,6,'David Van Der Linden',123),(529,7,'Marcus Spiegel',123),(530,8,'Willem La Tulip-Troost',123),(531,9,'izzy garratt',123),(532,1,'Joseph Adelinia',52),(533,2,'Benjamin de Jager',52),(534,3,'Jonathan Christie',52),(535,4,'Samuel Lewin',52),(536,5,'Robert Jones',52),(537,6,'Charles Pearson',52),(538,7,'Roshan Sreekumar',52),(539,8,'Alex Miles',52),(540,9,'Gemma Francis',52),(541,1,'Thomas O\'Driscoll',53),(542,2,'Tom Gardner',53),(543,3,'Mark Verhagen',53),(544,4,'Michael Garstka',53),(545,5,'Samuel Renshaw',53),(546,6,'Aled Evans',53),(547,7,'Jonas Sandbrink',53),(548,8,'Dominic Forstermann',53),(549,9,'James Winship',53),(550,1,'Marcus Pegg',54),(551,2,'Barteeban Rajappan',54),(552,3,'Connor King',54),(553,4,'Michael Eyres',54),(554,5,'Frank Hawes',54),(555,6,'Fergus Mclanaghan',54),(556,7,'Theodore Cornish',54),(557,8,'Frederick Follows',54),(558,9,'Morgan Elsmore',54),(559,1,'George Russell',68),(560,2,'Mathias Michaud',68),(561,3,'Joseph Page',68),(562,4,'filip barczentewicz',68),(563,5,'joseph dolphin',68),(564,6,'aashraya jha',68),(565,7,'Patrick Hudson',68),(566,8,'Angus Menzies',68),(567,9,'Alexander Inch',68),(568,1,'Thomas Leslie',69),(569,2,'Matthew Proctor',69),(570,3,'Euan Friend',69),(571,4,'Dylan Dissanayake',69),(572,5,'Giulio Mazzotta',69),(573,6,'Rusheng Chew',69),(574,7,'Colm Murphy',69),(575,8,'Declan Miller',69),(576,9,'Francesca Griffin',69),(577,1,'Daniel Percival',70),(578,2,'Daniel McLoughlin',70),(579,3,'Alexander Shickell',70),(580,4,'Linden Schrecker',70),(581,5,'Bennedict Williams',70),(582,6,'Ali Cigari',70),(583,7,'Andrew Bridger',70),(584,8,'Mark Brookes',70),(585,9,'Nina Hardrzwniak',70),(586,1,'Adam Lewis-Douglas',11),(587,2,'Ben Crane',11),(588,3,'Jacob Scorey',11),(589,4,'Adam Roberts',11),(590,5,'Hazem Hassan',11),(591,6,'Joseph Blackmore',11),(592,7,'Lorenzo Sintini',11),(593,8,'James Evry',11),(594,9,'Timothy Davies',11),(595,1,'Mishel Ghassibe',12),(596,2,'George Tresidder',12),(597,3,'Jon Thorbjarnarson',12),(598,4,'Jackson Cooper-Driver',12),(599,5,'Alexander Proudman',12),(600,6,'Nicolas Basty',12),(601,7,'Moritz Schnorpfeil',12),(602,8,'Adithya Kale',12),(603,9,'Ivy Manning',12),(604,1,'Avishek Mondal',13),(605,2,'Jan Gruszczynski',13),(606,3,'David Robinson',13),(607,4,'Douglas Crockett',13),(608,5,'Adam Golinski',13),(609,6,'Sam Durley',13),(610,7,'Thomas Johnes',13),(611,8,'Antony Houghton',13),(612,9,'Siu Chua',13),(613,1,'Kevin Schlegel',33),(614,2,'Philipp Schafer',33),(615,3,'Hans-Joachim Kunz',33),(616,4,'Simen Sopp',33),(617,5,'joshua combs',33),(618,6,'Christian Coester',33),(619,7,'Ethan Friederich',33),(620,8,'Christian Vaas',33),(621,9,'Allison Bryan',33),(622,1,'Kristan Boyett',34),(623,2,'Hendrik Andresen',34),(624,3,'Samuel Abernethy',34),(625,4,'Louis Slade',34),(626,5,'Simon Eberz',34),(627,6,'Christopher Theaker',34),(628,7,'James de Jonge',34),(629,8,'Sabin Sulzer',34),(630,9,'Gwilym Jones',34),(631,1,'Thomas Yeung',35),(632,2,'Marcus Tutert',35),(633,3,'Edouard Laurent',35),(634,4,'Federico Lombardi',35),(635,5,'Kristjan Josson',35),(636,6,'Moritz Moeller',35),(637,7,'Philipp Schmitt',35),(638,8,'Matthias Aengenheyster',35),(639,9,'Jorn Reniers',35),(640,1,'Camron Nourshargh',101),(641,2,'Benjamin Phillips',101),(642,3,'will crawford',101),(643,4,'Ivan Simon',101),(644,5,'tom williams',101),(645,6,'Alistair Mowbray',101),(646,7,'James Heatley',101),(647,8,'Alexander Nevin',101),(648,9,'Jameson Lee',101),(649,1,'Jachym Solecky',102),(650,2,'Myles Langley',102),(651,3,'Alexander Lacey',102),(652,4,'Peter Kiss',102),(653,5,'Christian Zabilowicz',102),(654,6,'James Lorenz',102),(655,7,'Benjamin Moule',102),(656,8,'ben darwent',102),(657,9,'Eleanor Drage',102),(658,1,'Annika Moslein',31),(659,2,'Stephanie Gaglione',31),(660,3,'Julia Davis',31),(661,4,'Laura Blenkarn',31),(662,5,'Brita Bergland',31),(663,6,'Jessica Gorrill',31),(664,7,'Fanny Louvier',31),(665,8,'Leah Mitchell',31),(666,9,'Saad Hamid',31),(667,1,'Isabelle Brennan',32),(668,2,'Rebecca Collins',32),(669,3,'Leonor Dos Aidos',32),(670,4,'Antonia Delius',32),(671,5,'Frida Bowe',32),(672,6,'Mathilde Chanut',32),(673,7,'Hannah Williams',32),(674,8,'Tanya Milne',32),(675,9,'Henrique Aguiar',32),(676,1,'Grace Hickman',107),(677,2,'Petra Kone',107),(678,3,'Juliette Allen',107),(679,4,'Anna Dobson',107),(680,5,'Esme Haywood',107),(681,6,'Elizabeth  Matthams',107),(682,7,'Katherine Ramsey',107),(683,8,'Emily Tench',107),(684,9,'James  Maye',107),(685,1,'Karen Zhang',108),(686,2,'Hannah Lipczynski',108),(687,3,'Celine Penant',108),(688,4,'Madeleine Luszczak',108),(689,5,'Sian Hale',108),(690,6,'Johanna Beer',108),(691,7,'Eleanor Dodd',108),(692,8,'Emily Patterson',108),(693,9,'Tobias de Mendonça',108),(694,1,'Rebecca Conway-Jones',26),(695,2,'Amanda Forman',26),(696,3,'Sophie Shakespeare',26),(697,4,'Rebecca Wallings',26),(698,5,'Anna Shepherd',26),(699,6,'Lise du Buisson',26),(700,7,'Jessica Wang',26),(701,8,'Ciara Ward',26),(702,9,'Laura Betteridge',26),(703,1,'Anna Schmidtmann',27),(704,2,'Ariane Garside',27),(705,3,'Sylvie MacDonald',27),(706,4,'Layla Stahr',27),(707,5,'Simona sulikova',27),(708,6,'Anke Kloock',27),(709,7,'Judith Macht',27),(710,8,'Charmaine Lang',27),(711,9,'Imogen Dalivalle',27),(712,1,'Xueer Zhou',28),(713,2,'Nicole Rothmeyer',28),(714,3,'Leah Morabito',28),(715,4,'Madison Porter',28),(716,5,'Sandra Posern',28),(717,6,'Charlotte Thomson',28),(718,7,'Carla Fuentes-Lopez',28),(719,8,'Maya Siegel',28),(720,9,'Hafeez Rajwani',28),(721,1,'Emily Williams',99),(722,2,'Florence Goodrich',99),(723,3,'Lucie Rigaud Drayton',99),(724,4,'Bethanne Jones',99),(725,5,'Megan Healy',99),(726,6,'martha wallace',99),(727,7,'Katie Hurt',99),(728,8,'Francesca Parkes',99),(729,9,'Molly Willett',99),(730,1,'Phoebe Tealby-Watson',100),(731,2,'Victoria Morris',100),(732,3,'Lauren Parsons',100),(733,4,'Amy Shao',100),(734,5,'Harriet-Rose Noons',100),(735,6,'Katherina Kirchhof',100),(736,7,'Clare Wolfle',100),(737,8,'Hannah Germain',100),(738,9,'Hannah Taylor',100),(739,1,'Yasmine Copley',93),(740,2,'Alice Butcher',93),(741,3,'Jocelyn Barker',93),(742,4,'Natasha Edgell',93),(743,5,'Amy Bull',93),(744,6,'Eleanor Hall',93),(745,7,'Victoria Gullo',93),(746,8,'Francesca Tindall',93),(747,9,'Eleanor Hilton',93),(748,1,'Stephanie Wolff',126),(749,2,'Laurienne Gardner',126),(750,3,'Idun Rognerud',126),(751,4,'Ilona Quahe',126),(752,5,'Emily Davenport',126),(753,6,'jenny tran',126),(754,7,'Isabell von Loga',126),(755,8,'Erin  Butler',126),(756,9,'Clarissa Coveney',126),(757,1,'Pippa Gunn',127),(758,2,'Megan Shipton',127),(759,3,'Anna Prior',127),(760,4,'Katharina Rogenhofer',127),(761,5,'Katharine Ross',127),(762,6,'Rachel  Mullin',127),(763,7,'Hanna Pettersson',127),(764,8,'Allison Barry',127),(765,9,'Laurienne Gardner',127),(766,1,'Amy Holguin',22),(767,2,'Eleanor Vogt',22),(768,3,'Harriet Thomas',22),(769,4,'Sophie Clark',22),(770,5,'Annabel Ault',22),(771,6,'Madeleine Burrell',22),(772,7,'Sarah Edwards',22),(773,8,'Philippa Thornton',22),(774,9,'Joseph Wynn',22),(775,1,'Madalyn Farrar',23),(776,2,'Rosie Bound',23),(777,3,'Grace Davis',23),(778,4,'Faye Heron',23),(779,5,'Rebecca Kirk',23),(780,6,'Kaja Zaremba',23),(781,7,'Christina Nahanni',23),(782,8,'Emily Pagden',23),(783,9,'Catherine Dumbill',23),(784,1,'Jenyth Evans',50),(785,2,'Lauren Jones',50),(786,3,'Seren Marsh',50),(787,4,'Helena Pickford',50),(788,5,'Jennifer Curtis',50),(789,6,'Elsebine Bolier',50),(790,7,'Alexandra Mighiu',50),(791,8,'Elysia Hannaford',50),(792,9,'Berenika Kotelko',50),(793,1,'Olivia Cook',51),(794,2,'Maeve Mahony',51),(795,3,'Emily R Barter',51),(796,4,'Georgina Whitehead',51),(797,5,'Keelin Willis',51),(798,6,'Amy Hosking',51),(799,7,'Isobel Blythe',51),(800,8,'Antoinette Cowling',51),(801,9,'Michael Swain',51),(802,1,'Emma Rogers',75),(803,2,'Jessica Morgan',75),(804,3,'Lisa Martin',75),(805,4,'Melissa Hinkley',75),(806,5,'Emma Carter',75),(807,6,'Hannah Coles',75),(808,7,'Eliza Argyropoulos',75),(809,8,'Maaike van Swieten',75),(810,9,'Michael Hobley',75),(811,1,'Esther Rollinson',85),(812,2,'Aoife Elwood',85),(813,3,'Saskia Thomas',85),(814,4,'Sonja Stiebahl',85),(815,5,'Lana Purcell',85),(816,6,'Evie Colman',85),(817,7,'Lydia Field',85),(818,8,'Hanna Schnitzer',85),(819,9,'James Wills',85),(820,1,'Josephine Elliott',110),(821,2,'Abigail Wilson',110),(822,3,'Tuuli Kasso',110),(823,4,'Fijnanada Van Klingeren',110),(824,5,'Sarah Schneider',110),(825,6,'Florianne Verkroost',110),(826,7,'Celestine Funfgeld',110),(827,8,'Anna Mikkelborg',110),(828,9,'Arianna Schuler Scott',110),(829,1,'Hannah Mosley',111),(830,2,'Jae-Young Lee',111),(831,3,'Alia Komsany',111),(832,4,'Dina Hestad',111),(833,5,'Heather Dun',111),(834,6,'Hilary Wynne',111),(835,7,'Ashley Tsai',111),(836,8,'Anna Thamm',111),(837,9,'Julian Ashwin',111),(838,1,'Tiziana Imstepf',81),(839,2,'Heather McTaggart',81),(840,3,'Eirian Yem',81),(841,4,'Amelia Shard',81),(842,5,'Lucie Kaempfer',81),(843,6,'Nikita Turck',81),(844,7,'Victoria McGowan',81),(845,8,'Joana Clemens',81),(846,9,'Haden Spence',81),(847,1,'Cassandra Popham',82),(848,2,'Lucie Ayliffe-Daly',82),(849,3,'Sophia Koepke',82),(850,4,'Maryn D Brown',82),(851,5,'Annina Graedel',82),(852,6,'Zoe Clark',82),(853,7,'Sofie Behluli',82),(854,8,'Lauren Malm',82),(855,9,'Marios Charalambous',82),(856,1,'Olivia Kinsey',41),(857,2,'Maria Zuberek',41),(858,3,'Alice Doyle',41),(859,4,'Clare Cocker',41),(860,5,'Neria Aylward',41),(861,6,'Amelia Grice',41),(862,7,'Katherine Franklin',41),(863,8,'Isabelle Beaudoin',41),(864,9,'Alice Williams',41),(865,1,'Eleanor  Hogg',42),(866,2,'Isabelle Newell',42),(867,3,'Marta Andres Crespo',42),(868,4,'Olivia McDaniel',42),(869,5,'Rachael Brown',42),(870,6,'Megan Knowles',42),(871,7,'Emily Carroll',42),(872,8,'Alice Svedberg',42),(873,9,'Olivia Kinsey',42),(874,1,'Kara Vouilloz',66),(875,2,'Rebecca Sinnott',66),(876,3,'Jeanne Lerasle',66),(877,4,'Sonia Brunstad',66),(878,5,'Ella Grodzinski',66),(879,6,'Kirstie Bosman',66),(880,7,'Kate Gerrand',66),(881,8,'Kathryn Jones',66),(882,9,'Alexandra Pitchford',66),(883,1,'Victoria Pereira',67),(884,2,'Mingjie Diao',67),(885,3,'Nikki Bailey',67),(886,4,'Georgia Fisher',67),(887,5,'Carys Greenaway',67),(888,6,'Lara-An Nedee',67),(889,7,'Lucy McGauran Porter',67),(890,8,'Felicity Fox',67),(891,9,'Grace Bowland',67),(892,1,'Venla Karppinen',89),(893,2,'Josephine Smith',89),(894,3,'Jennifer Friske',89),(895,4,'Hermioni Grassi',89),(896,5,'Anne Schreuder',89),(897,6,'Esther Borsi',89),(898,7,'Paris Jaggers',89),(899,8,'Charlotte Oakes',89),(900,9,'Julianna Barker',89),(901,1,'Cyara Buchuck-Wilsenach',90),(902,2,'Katrina Gadsby',90),(903,3,'Matilde da Silva',90),(904,4,'Emma Ball',90),(905,5,'Beth McCullagh',90),(906,6,'Charlotte Fields',90),(907,7,'Hanna Glattfelder',90),(908,8,'Emily Capstick',90),(909,9,'Tyson Rallens',90),(910,1,'Malina Graf',63),(911,2,'Alice Caddock',63),(912,3,'Bethany Carter',63),(913,4,'Embla Hocking',63),(914,5,'Victoire Dejean',63),(915,6,'Rebecca Esselstein',63),(916,7,'Annabel Ford',63),(917,8,'Astrid Brakstad',63),(918,9,'Fraser Boistelle',63),(919,1,'Kaisa De Bel',64),(920,2,'Grace Day',64),(921,3,'Abigail Whalen',64),(922,4,'Kendya Goodman',64),(923,5,'Sophie Brocks',64),(924,6,'Alexandra Brodersen',64),(925,7,'Constance Howell',64),(926,8,'Charlotte Keigher',64),(927,9,'Charlotte E Bream',64),(928,1,'Eleanor Juckes',9),(929,2,'Grace Lissenden',9),(930,3,'Eleanor Thomson',9),(931,4,'Emily Navidi',9),(932,5,'Aysha Strachan',9),(933,6,'Lara Bonney',9),(934,7,'Sarah Foster',9),(935,8,'Charlotte Nugent',9),(936,9,'Eddie Shields',9),(937,1,'Fanxi Liu',10),(938,2,'Sofija Paneva',10),(939,3,'Simone Fraser',10),(940,4,'Aisuluu Bakchieva',10),(941,5,'Justine Ellis',10),(942,6,'Ruiyi Wang',10),(943,7,'Lauren  Hill',10),(944,8,'Amanda Higgin',10),(945,9,'Huw Davies',10),(946,1,'Deborah Cotton',4),(947,2,'Ameya Barve',4),(948,3,'Julia Cockcroft',4),(949,4,'Elizabeth Madden',4),(950,5,'Isabelle Rocroi',4),(951,6,'Alys Howells',4),(952,7,'Lena Depner',4),(953,8,'Molly Hall',4),(954,9,'Imogen Brown',4),(955,1,'Amelia Powell',5),(956,2,'Laura Shuttler',5),(957,3,'Imogen Hobby',5),(958,4,'Moana Graham',5),(959,5,'Laura Larkin',5),(960,6,'Carolina Valensise',5),(961,7,'Jessica Ellins',5),(962,8,'Natalie Haarer ',5),(963,9,'Aimee Cochrane',5),(964,1,'Sophia Sosnina',113),(965,2,'Shengqi Zhu',113),(966,3,'Emily Barran',113),(967,4,'Eve Mason',113),(968,5,'Catherine Hall',113),(969,6,'Hannah Reynolds',113),(970,7,'Stephanie E Budenberg',113),(971,8,'Rebecca Sims',113),(972,9,'Patrick Love',113),(973,1,'Alice Dyer',133),(974,2,'Isabelle Blain',133),(975,3,'Eleanor Jenkins',133),(976,4,'Christina Mitchell',133),(977,5,'Megan Bennett',133),(978,6,'Charlotte Haley',133),(979,7,'Orlaith Fox',133),(980,8,'Madeleine Duperouzei',133),(981,9,'Shauna Brown',133),(982,1,'Anna Gluck',95),(983,2,'Marie Ducroizet-boitaud',95),(984,3,'Ellie Fielding',95),(985,4,'Rachel Drummey',95),(986,5,'Olivia Will',95),(987,6,'Elizambeth McGowan',95),(988,7,'Hannah Asiki',95),(989,8,'Meryem Arik',95),(990,9,'Imogen Laycock',95),(991,1,'Hannah Patrick',96),(992,2,'Mie Kronborg Olesen',96),(993,3,'Lucy Bannatyne',96),(994,4,'Alyssa Crabb',96),(995,5,'Anna Jones',96),(996,6,'Rachel Donnachie',96),(997,7,'Zsofi Palasik',96),(998,8,'Maebh Mulligan Smith',96),(999,9,'Elinor Lamrick',96),(1000,1,'Charlotte Miles',77),(1001,2,'Giuliana Savini',77),(1002,3,'Kendra Sanders',77),(1003,4,'Nadia Haworth',77),(1004,5,'Juliet Martin',77),(1005,6,'Eliza Hardman Lea',77),(1006,7,'Alice Corbin',77),(1007,8,'Alison Walsh',77),(1008,9,'Isobel Ford',77),(1009,1,'Emilie Munro',78),(1010,2,'Megan Hollis',78),(1011,3,'Eillen Martinez',78),(1012,4,'Isabel Carter',78),(1013,5,'Daisy Hughes',78),(1014,6,'Reyan Coskun',78),(1015,7,'Heloise Hunter',78),(1016,8,'Jiali Zhang',78),(1017,9,'Theo Costain',78),(1018,1,'Nicola Mathieson',116),(1019,2,'Katharine HOEGER',116),(1020,3,'Margot Grubert',116),(1021,4,'Olivia Jones',116),(1022,5,'Ryah Thomas',116),(1023,6,'Emma Rimpilainen',116),(1024,7,'Vanessa Meier',116),(1025,8,'Charlotte Asdal',116),(1026,9,'Cordelia Pnczek',116),(1027,1,'Caitlin Gray',18),(1028,2,'Holly Smith',18),(1029,3,'Rachel Ibbetson',18),(1030,4,'Anna Redgrave',18),(1031,5,'Clare Leckie',18),(1032,6,'Rachel Craig-McFeely',18),(1033,7,'Oriane Grant',18),(1034,8,'Zoe Curtis',18),(1035,9,'Madeleine McCarthy',18),(1036,1,'Palmo Tenzin',19),(1037,2,'Chloe Bregazzi',19),(1038,3,'Orlaith Breen',19),(1039,4,'Amelia Brunton',19),(1040,5,'Meirian Evans',19),(1041,6,'Heather Tong',19),(1042,7,'Hannah Morrisey',19),(1043,8,'Carina Schwarz',19),(1044,9,'Laurel Crosby',19),(1045,1,'Theodora Bruun',60),(1046,2,'Maria Tsekhmistrenko',60),(1047,3,'Rachael McManus',60),(1048,4,'Kathryn Reece',60),(1049,5,'Megan Jones',60),(1050,6,'Rosie Munday',60),(1051,7,'Jane Ellis',60),(1052,8,'Madeleine Morrison',60),(1053,9,'Megan Carter',60),(1054,1,'Lucy Hyde',130),(1055,2,'Anisha Chopra',130),(1056,3,'Lucy Williams',130),(1057,4,'Elizabeth Bryan',130),(1058,5,'Anna James-Bott',130),(1059,6,'Emma Skeels',130),(1060,7,'Julia Halligan',130),(1061,8,'Wiesje van den Heerik',130),(1062,9,'Anna Bassadone',130),(1063,1,'Sabina Manzini',131),(1064,2,'Annabel Killen',131),(1065,3,'Hannah Bossers',131),(1066,4,'Hannah Wilkinson',131),(1067,5,'Nathania Silalahi',131),(1068,6,'Sichen Liu',131),(1069,7,'Shui Mak',131),(1070,8,'Ombline Damy',131),(1071,9,'Anisha Chopra',131),(1072,1,'Clara Dijkstra',120),(1073,2,'Lana Firth',120),(1074,3,'Alice Worsley',120),(1075,4,'Brenda Mshiu',120),(1076,5,'Laura Bishop',120),(1077,6,'Caroline Green',120),(1078,7,'Fay Amstutz',120),(1079,8,'Rhiannon Davies',120),(1080,9,'Lydia Coxon',120),(1081,1,'Eleanor Platt',121),(1082,2,'Alaina Oltrogge',121),(1083,3,'Jennifer Holmes',121),(1084,4,'Megan Beretta',121),(1085,5,'Naomi Hirst',121),(1086,6,'Theresa Feicht',121),(1087,7,'Frances Barwick Ward',121),(1088,8,'Sarah Hyland',121),(1089,9,'Nathan Peters',121),(1090,1,'Karen Heathcote',45),(1091,2,'Lucy Field',45),(1092,3,'Lynn Lewis-Bevan',45),(1093,4,'Isabella Stephens',45),(1094,5,'Jennifer Massingham',45),(1095,6,'Ria Dinsdale',45),(1096,7,'Madeleine Warner',45),(1097,8,'Laura Boddy',45),(1098,9,'Peter Beardsmore',45),(1099,1,'Jahnavi Kalayil',46),(1100,2,'Caitlin French',46),(1101,3,'Malika Saksena',46),(1102,4,'Morgan Mohr',46),(1103,5,'Mary Curwen',46),(1104,6,'Maria Brett',46),(1105,7,'Kathryn Annesley',46),(1106,8,'Helena Murphy',46),(1107,9,'Cara Shearer',46),(1108,1,'Ellena Murray',124),(1109,2,'Flora Fergusson',124),(1110,3,'Alice Inman',124),(1111,4,'Lucy Streeten',124),(1112,5,'isabel watts',124),(1113,6,'Elizabeth Gardner',124),(1114,7,'Polly Dunn',124),(1115,8,'Colette Lipp',124),(1116,9,'Imogen Learmonth',124),(1117,1,'Anna Broughton',55),(1118,2,'Lydia Bockmuehl',55),(1119,3,'Kirsten Stewart',55),(1120,4,'Melissa Rose',55),(1121,5,'Charlotte Green',55),(1122,6,'Elizabeth Holdcroft',55),(1123,7,'Rhiannon Heard',55),(1124,8,'Ellie Vickery',55),(1125,9,'Marina Smith',55),(1126,1,'Hannah Perkins',56),(1127,2,'Kirsty Peacock',56),(1128,3,'Julia Pieza',56),(1129,4,'Cheng Foo',56),(1130,5,'Penny Streatfeild',56),(1131,6,'Debbie Malden',56),(1132,7,'Anna Wall',56),(1133,8,'Gemma Francis',56),(1134,9,'Ellie Vickery',56),(1135,1,'Sophie Wicken',71),(1136,2,'Sarah Faulkner',71),(1137,3,'Ffion Price',71),(1138,4,'Isobel Salt',71),(1139,5,'Sarah Haynes',71),(1140,6,'Emma Lepinay',71),(1141,7,'Amy Hughes',71),(1142,8,'Alisa Musanovic',71),(1143,9,'Emma Franklin',71),(1144,1,'Lara Sarrionandia-Thomas',14),(1145,2,'Elizabeth Fox',14),(1146,3,'Katherine Aston',14),(1147,4,'Emma Richards',14),(1148,5,'Zara Shepherd-Brierley',14),(1149,6,'Eloise Page',14),(1150,7,'Sarah Hiepler',14),(1151,8,'Livia Kaiser',14),(1152,9,'Francesca Murphy',14),(1153,1,'Rujia Wei',15),(1154,2,'Megan Edwards',15),(1155,3,'Kimberley Webb',15),(1156,4,'Sophia Pepes',15),(1157,5,'Flora Clark',15),(1158,6,'Niamh Quille',15),(1159,7,'Emilia Staniaszek',15),(1160,8,'Paula Kaanders',15),(1161,9,'Dani Chattenton',15),(1162,1,'Philippa Hammond',36),(1163,2,'Estelle Beguin',36),(1164,3,'Claire Holubowskyj',36),(1165,4,'Beatrice Faleri',36),(1166,5,'Alexandra Isard',36),(1167,6,'Serina Lyons',36),(1168,7,'Jessica Dunham',36),(1169,8,'Georgina Bowyer',36),(1170,9,'Daina Sadurska',36),(1171,1,'Kerstin Frie',37),(1172,2,'Sarah Walsh',37),(1173,3,'Anysia Nguyen',37),(1174,4,'Zhouran Zhang',37),(1175,5,'Tereza Bartonickova',37),(1176,6,'Verena Wiedemann',37),(1177,7,'Bronwen Hudson',37),(1178,8,'Poppy Smallwood',37),(1179,9,'Sonja Schauman',37),(1180,1,'Charlotte Hepburn',38),(1181,2,'Natasha Bowyer',38),(1182,3,'Martyna Syposz',38),(1183,4,'Allison Bryan',38),(1184,5,'Sophie L Scharlin-Pettee',38),(1185,6,'Sharlayne Waller',38),(1186,7,'Daina Sadurska',38),(1187,8,'Henrike Puchta',38),(1188,9,'Adam Ferris',38),(1189,1,'Eleanor Drage',103),(1190,2,'Isabelle Moss',103),(1191,3,'Iona Woodward',103),(1192,4,'Eloise Barker',103),(1193,5,'Krisztina Almasi',103),(1194,6,'Hannah R Meadows',103),(1195,7,'Ella Duffy',103),(1196,8,'Hannah Langlands',103),(1197,9,'Alexander Nevin',103),(1198,1,'Hannah Broman',104),(1199,2,'Sarah Gilkerson',104),(1200,3,'Adriana Gardner',104),(1201,4,'Saava O\'Kirwan',104),(1202,5,'Fenna Van Engelen',104),(1203,6,'Anna Herzog',104),(1204,7,'Teodora Musatoiu',104),(1205,8,'maya heine',104),(1206,9,'Sophie Harbord',104);
/*!40000 ALTER TABLE `crew_member` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `crew_position_history`
--

DROP TABLE IF EXISTS `crew_position_history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `crew_position_history` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `day` int(11) NOT NULL,
  `position` int(11) NOT NULL,
  `bumps` int(11) NOT NULL,
  `dividend` decimal(10,2) NOT NULL,
  `crew_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `one_position_per_day` (`crew_id`,`day`),
  CONSTRAINT `fk_crew_position_history_crew_id` FOREIGN KEY (`crew_id`) REFERENCES `crew` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=135 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `crew_position_history`
--

LOCK TABLES `crew_position_history` WRITE;
/*!40000 ALTER TABLE `crew_position_history` DISABLE KEYS */;
INSERT INTO `crew_position_history` VALUES (1,0,1,0,0.00,1),(2,0,12,0,0.00,2),(3,0,72,0,0.00,3),(4,0,5,0,0.00,4),(5,0,36,0,0.00,5),(6,0,2,0,0.00,6),(7,0,40,0,0.00,7),(8,0,73,0,0.00,8),(9,0,1,0,0.00,9),(10,0,34,0,0.00,10),(11,0,3,0,0.00,11),(12,0,32,0,0.00,12),(13,0,42,0,0.00,13),(14,0,2,0,0.00,14),(15,0,26,0,0.00,15),(16,0,4,0,0.00,16),(17,0,43,0,0.00,17),(18,0,27,0,0.00,18),(19,0,60,0,0.00,19),(20,0,5,0,0.00,20),(21,0,47,0,0.00,21),(22,0,4,0,0.00,22),(23,0,51,0,0.00,23),(24,0,6,0,0.00,24),(25,0,37,0,0.00,25),(26,0,3,0,0.00,26),(27,0,53,0,0.00,27),(28,0,57,0,0.00,28),(29,0,7,0,0.00,29),(30,0,39,0,0.00,30),(31,0,16,0,0.00,31),(32,0,43,0,0.00,32),(33,0,8,0,0.00,33),(34,0,33,0,0.00,34),(35,0,54,0,0.00,35),(36,0,9,0,0.00,36),(37,0,23,0,0.00,37),(38,0,41,0,0.00,38),(39,0,9,0,0.00,39),(40,0,52,0,0.00,40),(41,0,12,0,0.00,41),(42,0,52,0,0.00,42),(43,0,10,0,0.00,43),(44,0,50,0,0.00,44),(45,0,6,0,0.00,45),(46,0,37,0,0.00,46),(47,0,11,0,0.00,47),(48,0,35,0,0.00,48),(49,0,62,0,0.00,49),(50,0,11,0,0.00,50),(51,0,46,0,0.00,51),(52,0,13,0,0.00,52),(53,0,44,0,0.00,53),(54,0,53,0,0.00,54),(55,0,17,0,0.00,55),(56,0,47,0,0.00,56),(57,0,14,0,0.00,57),(58,0,45,0,0.00,58),(59,0,70,0,0.00,59),(60,0,18,0,0.00,60),(61,0,15,0,0.00,61),(62,0,41,0,0.00,62),(63,0,13,0,0.00,63),(64,0,45,0,0.00,64),(65,0,16,0,0.00,65),(66,0,22,0,0.00,66),(67,0,50,0,0.00,67),(68,0,17,0,0.00,68),(69,0,46,0,0.00,69),(70,0,69,0,0.00,70),(71,0,8,0,0.00,71),(72,0,18,0,0.00,72),(73,0,38,0,0.00,73),(74,0,58,0,0.00,74),(75,0,7,0,0.00,75),(76,0,19,0,0.00,76),(77,0,19,0,0.00,77),(78,0,49,0,0.00,78),(79,0,20,0,0.00,79),(80,0,51,0,0.00,80),(81,0,10,0,0.00,81),(82,0,42,0,0.00,82),(83,0,21,0,0.00,83),(84,0,57,0,0.00,84),(85,0,20,0,0.00,85),(86,0,22,0,0.00,86),(87,0,56,0,0.00,87),(88,0,71,0,0.00,88),(89,0,32,0,0.00,89),(90,0,55,0,0.00,90),(91,0,23,0,0.00,91),(92,0,59,0,0.00,92),(93,0,28,0,0.00,93),(94,0,24,0,0.00,94),(95,0,14,0,0.00,95),(96,0,40,0,0.00,96),(97,0,25,0,0.00,97),(98,0,60,0,0.00,98),(99,0,38,0,0.00,99),(100,0,58,0,0.00,100),(101,0,26,0,0.00,101),(102,0,68,0,0.00,102),(103,0,24,0,0.00,103),(104,0,48,0,0.00,104),(105,0,27,0,0.00,105),(106,0,49,0,0.00,106),(107,0,25,0,0.00,107),(108,0,59,0,0.00,108),(109,0,28,0,0.00,109),(110,0,21,0,0.00,110),(111,0,44,0,0.00,111),(112,0,29,0,0.00,112),(113,0,30,0,0.00,113),(114,0,30,0,0.00,114),(115,0,61,0,0.00,115),(116,0,33,0,0.00,116),(117,0,31,0,0.00,117),(118,0,63,0,0.00,118),(119,0,64,0,0.00,119),(120,0,29,0,0.00,120),(121,0,56,0,0.00,121),(122,0,34,0,0.00,122),(123,0,67,0,0.00,123),(124,0,35,0,0.00,124),(125,0,36,0,0.00,125),(126,0,15,0,0.00,126),(127,0,61,0,0.00,127),(128,0,48,0,0.00,128),(129,0,65,0,0.00,129),(130,0,31,0,0.00,130),(131,0,54,0,0.00,131),(132,0,52,0,0.00,132),(133,0,39,0,0.00,133),(134,0,66,0,0.00,134);
/*!40000 ALTER TABLE `crew_position_history` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `crew_price_history`
--

DROP TABLE IF EXISTS `crew_price_history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `crew_price_history` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `date_time` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `price` decimal(10,2) NOT NULL,
  `crew_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_crew_price_history_crew_id` (`crew_id`),
  CONSTRAINT `fk_crew_price_history_crew_id` FOREIGN KEY (`crew_id`) REFERENCES `crew` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=135 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `crew_price_history`
--

LOCK TABLES `crew_price_history` WRITE;
/*!40000 ALTER TABLE `crew_price_history` DISABLE KEYS */;
INSERT INTO `crew_price_history` VALUES (1,'2018-02-27 00:06:43',300.00,1),(2,'2018-02-27 00:06:43',136.00,2),(3,'2018-02-27 00:06:43',17.74,3),(4,'2018-02-27 00:06:43',193.78,4),(5,'2018-02-27 00:06:43',63.49,5),(6,'2018-02-27 00:06:43',254.25,6),(7,'2018-02-27 00:06:43',56.53,7),(8,'2018-02-27 00:06:43',16.83,8),(9,'2018-02-27 00:06:44',300.00,9),(10,'2018-02-27 00:06:44',67.26,10),(11,'2018-02-27 00:06:44',227.49,11),(12,'2018-02-27 00:06:44',71.26,12),(13,'2018-02-27 00:06:44',53.31,13),(14,'2018-02-27 00:06:44',254.25,14),(15,'2018-02-27 00:06:44',84.97,15),(16,'2018-02-27 00:06:44',208.50,16),(17,'2018-02-27 00:06:44',51.76,17),(18,'2018-02-27 00:06:44',82.47,18),(19,'2018-02-27 00:06:44',29.77,19),(20,'2018-02-27 00:06:44',193.78,20),(21,'2018-02-27 00:06:44',45.89,21),(22,'2018-02-27 00:06:44',208.50,22),(23,'2018-02-27 00:06:44',40.50,23),(24,'2018-02-27 00:06:44',181.74,24),(25,'2018-02-27 00:06:45',61.68,25),(26,'2018-02-27 00:06:45',227.49,26),(27,'2018-02-27 00:06:45',37.96,27),(28,'2018-02-27 00:06:45',33.16,28),(29,'2018-02-27 00:06:45',171.57,29),(30,'2018-02-27 00:06:45',58.20,30),(31,'2018-02-27 00:06:45',117.01,31),(32,'2018-02-27 00:06:45',51.76,32),(33,'2018-02-27 00:06:45',162.76,33),(34,'2018-02-27 00:06:45',69.23,34),(35,'2018-02-27 00:06:45',36.73,35),(36,'2018-02-27 00:06:45',154.98,36),(37,'2018-02-27 00:06:45',93.06,37),(38,'2018-02-27 00:06:45',54.90,38),(39,'2018-02-27 00:06:45',154.98,39),(40,'2018-02-27 00:06:45',39.22,40),(41,'2018-02-27 00:06:46',136.00,41),(42,'2018-02-27 00:06:46',39.22,42),(43,'2018-02-27 00:06:46',148.03,43),(44,'2018-02-27 00:06:46',41.81,44),(45,'2018-02-27 00:06:46',181.74,45),(46,'2018-02-27 00:06:46',61.68,46),(47,'2018-02-27 00:06:46',141.74,47),(48,'2018-02-27 00:06:46',65.35,48),(49,'2018-02-27 00:06:46',27.61,49),(50,'2018-02-27 00:06:46',141.74,50),(51,'2018-02-27 00:06:46',47.31,51),(52,'2018-02-27 00:06:46',130.71,52),(53,'2018-02-27 00:06:46',50.24,53),(54,'2018-02-27 00:06:46',37.96,54),(55,'2018-02-27 00:06:46',113.01,55),(56,'2018-02-27 00:06:46',45.89,56),(57,'2018-02-27 00:06:46',125.82,57),(58,'2018-02-27 00:06:47',48.76,58),(59,'2018-02-27 00:06:47',19.60,59),(60,'2018-02-27 00:06:47',109.24,60),(61,'2018-02-27 00:06:47',121.27,61),(62,'2018-02-27 00:06:47',54.90,62),(63,'2018-02-27 00:06:47',130.71,63),(64,'2018-02-27 00:06:47',48.76,64),(65,'2018-02-27 00:06:47',117.01,65),(66,'2018-02-27 00:06:47',95.99,66),(67,'2018-02-27 00:06:47',41.81,67),(68,'2018-02-27 00:06:47',113.01,68),(69,'2018-02-27 00:06:47',47.31,69),(70,'2018-02-27 00:06:47',20.55,70),(71,'2018-02-27 00:06:47',162.76,71),(72,'2018-02-27 00:06:47',109.24,72),(73,'2018-02-27 00:06:47',59.92,73),(74,'2018-02-27 00:06:48',32.01,74),(75,'2018-02-27 00:06:48',171.57,75),(76,'2018-02-27 00:06:48',105.67,76),(77,'2018-02-27 00:06:48',105.67,77),(78,'2018-02-27 00:06:48',43.14,78),(79,'2018-02-27 00:06:48',102.28,79),(80,'2018-02-27 00:06:48',40.50,80),(81,'2018-02-27 00:06:48',148.03,81),(82,'2018-02-27 00:06:48',53.31,82),(83,'2018-02-27 00:06:48',99.06,83),(84,'2018-02-27 00:06:48',33.16,84),(85,'2018-02-27 00:06:48',102.28,85),(86,'2018-02-27 00:06:48',95.99,86),(87,'2018-02-27 00:06:48',34.33,87),(88,'2018-02-27 00:06:48',18.66,88),(89,'2018-02-27 00:06:48',71.26,89),(90,'2018-02-27 00:06:49',35.52,90),(91,'2018-02-27 00:06:49',93.06,91),(92,'2018-02-27 00:06:49',30.88,92),(93,'2018-02-27 00:06:49',80.07,93),(94,'2018-02-27 00:06:49',90.25,94),(95,'2018-02-27 00:06:49',125.82,95),(96,'2018-02-27 00:06:49',56.53,96),(97,'2018-02-27 00:06:49',87.55,97),(98,'2018-02-27 00:06:49',29.77,98),(99,'2018-02-27 00:06:49',59.92,99),(100,'2018-02-27 00:06:49',32.01,100),(101,'2018-02-27 00:06:49',84.97,101),(102,'2018-02-27 00:06:49',21.51,102),(103,'2018-02-27 00:06:49',90.25,103),(104,'2018-02-27 00:06:49',44.50,104),(105,'2018-02-27 00:06:49',82.47,105),(106,'2018-02-27 00:06:49',43.14,106),(107,'2018-02-27 00:06:50',87.55,107),(108,'2018-02-27 00:06:50',30.88,108),(109,'2018-02-27 00:06:50',80.07,109),(110,'2018-02-27 00:06:50',99.06,110),(111,'2018-02-27 00:06:50',50.24,111),(112,'2018-02-27 00:06:50',77.76,112),(113,'2018-02-27 00:06:50',75.52,113),(114,'2018-02-27 00:06:50',75.52,114),(115,'2018-02-27 00:06:50',28.68,115),(116,'2018-02-27 00:06:50',69.23,116),(117,'2018-02-27 00:06:50',73.36,117),(118,'2018-02-27 00:06:50',26.55,118),(119,'2018-02-27 00:06:50',25.51,119),(120,'2018-02-27 00:06:50',77.76,120),(121,'2018-02-27 00:06:50',34.33,121),(122,'2018-02-27 00:06:50',67.26,122),(123,'2018-02-27 00:06:51',22.49,123),(124,'2018-02-27 00:06:51',65.35,124),(125,'2018-02-27 00:06:51',63.49,125),(126,'2018-02-27 00:06:51',121.27,126),(127,'2018-02-27 00:06:51',28.68,127),(128,'2018-02-27 00:06:51',44.50,128),(129,'2018-02-27 00:06:51',24.49,129),(130,'2018-02-27 00:06:51',73.36,130),(131,'2018-02-27 00:06:51',36.73,131),(132,'2018-02-27 00:06:51',39.22,132),(133,'2018-02-27 00:06:51',58.20,133),(134,'2018-02-27 00:06:51',23.48,134);
/*!40000 ALTER TABLE `crew_price_history` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jhi_authority`
--

DROP TABLE IF EXISTS `jhi_authority`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `jhi_authority` (
  `name` varchar(50) NOT NULL,
  PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jhi_authority`
--

LOCK TABLES `jhi_authority` WRITE;
/*!40000 ALTER TABLE `jhi_authority` DISABLE KEYS */;
INSERT INTO `jhi_authority` VALUES ('ROLE_ADMIN'),('ROLE_USER');
/*!40000 ALTER TABLE `jhi_authority` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jhi_persistent_audit_event`
--

DROP TABLE IF EXISTS `jhi_persistent_audit_event`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `jhi_persistent_audit_event` (
  `event_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `principal` varchar(100) NOT NULL,
  `event_date` timestamp NULL DEFAULT NULL,
  `event_type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`event_id`),
  KEY `idx_persistent_audit_event` (`principal`,`event_date`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jhi_persistent_audit_event`
--

LOCK TABLES `jhi_persistent_audit_event` WRITE;
/*!40000 ALTER TABLE `jhi_persistent_audit_event` DISABLE KEYS */;
INSERT INTO `jhi_persistent_audit_event` VALUES (1,'admin','2018-02-26 22:20:20','AUTHENTICATION_SUCCESS'),(2,'alexa','2018-02-27 00:11:11','AUTHENTICATION_SUCCESS'),(3,'admin','2018-02-27 00:11:22','AUTHENTICATION_SUCCESS'),(4,'alexa','2018-02-27 00:11:46','AUTHENTICATION_SUCCESS');
/*!40000 ALTER TABLE `jhi_persistent_audit_event` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jhi_persistent_audit_evt_data`
--

DROP TABLE IF EXISTS `jhi_persistent_audit_evt_data`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `jhi_persistent_audit_evt_data` (
  `event_id` bigint(20) NOT NULL,
  `name` varchar(150) NOT NULL,
  `value` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`event_id`,`name`),
  KEY `idx_persistent_audit_evt_data` (`event_id`),
  CONSTRAINT `fk_evt_pers_audit_evt_data` FOREIGN KEY (`event_id`) REFERENCES `jhi_persistent_audit_event` (`event_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jhi_persistent_audit_evt_data`
--

LOCK TABLES `jhi_persistent_audit_evt_data` WRITE;
/*!40000 ALTER TABLE `jhi_persistent_audit_evt_data` DISABLE KEYS */;
/*!40000 ALTER TABLE `jhi_persistent_audit_evt_data` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jhi_social_user_connection`
--

DROP TABLE IF EXISTS `jhi_social_user_connection`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `jhi_social_user_connection` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(255) NOT NULL,
  `provider_id` varchar(255) NOT NULL,
  `provider_user_id` varchar(255) NOT NULL,
  `rank` bigint(20) NOT NULL,
  `display_name` varchar(255) DEFAULT NULL,
  `profile_url` varchar(255) DEFAULT NULL,
  `image_url` varchar(255) DEFAULT NULL,
  `access_token` varchar(255) NOT NULL,
  `secret` varchar(255) DEFAULT NULL,
  `refresh_token` varchar(255) DEFAULT NULL,
  `expire_time` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_id` (`user_id`,`provider_id`,`provider_user_id`),
  UNIQUE KEY `user_id_2` (`user_id`,`provider_id`,`rank`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jhi_social_user_connection`
--

LOCK TABLES `jhi_social_user_connection` WRITE;
/*!40000 ALTER TABLE `jhi_social_user_connection` DISABLE KEYS */;
/*!40000 ALTER TABLE `jhi_social_user_connection` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jhi_user`
--

DROP TABLE IF EXISTS `jhi_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `jhi_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `login` varchar(100) NOT NULL,
  `password_hash` varchar(60) DEFAULT NULL,
  `first_name` varchar(50) DEFAULT NULL,
  `last_name` varchar(50) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `image_url` varchar(256) DEFAULT NULL,
  `activated` bit(1) NOT NULL,
  `lang_key` varchar(5) DEFAULT NULL,
  `activation_key` varchar(20) DEFAULT NULL,
  `reset_key` varchar(20) DEFAULT NULL,
  `created_by` varchar(50) NOT NULL,
  `created_date` timestamp NOT NULL DEFAULT current_timestamp(),
  `reset_date` timestamp NULL DEFAULT NULL,
  `last_modified_by` varchar(50) DEFAULT NULL,
  `last_modified_date` timestamp NULL DEFAULT NULL,
  `college` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `login` (`login`),
  UNIQUE KEY `idx_user_login` (`login`),
  UNIQUE KEY `email` (`email`),
  UNIQUE KEY `idx_user_email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jhi_user`
--

LOCK TABLES `jhi_user` WRITE;
/*!40000 ALTER TABLE `jhi_user` DISABLE KEYS */;
INSERT INTO `jhi_user` VALUES (1,'system','$2a$10$mE.qmcV0mFU5NcKh73TZx.z4ueI/.bDWbj0T1BYyqP481kGGarKLG','System','System','system@localhost','','','en',NULL,NULL,'system','2018-02-26 22:18:46',NULL,'system',NULL,NULL),(2,'anonymoususer','$2a$10$j8S5d7Sr7.8VTOYNviDPOeWX8KcYILUVJBsYV83Y5NtECayypx9lO','Anonymous','User','anonymous@localhost','','','en',NULL,NULL,'system','2018-02-26 22:18:46',NULL,'system',NULL,NULL),(3,'admin','$2a$10$gSAhZrxMllrbgj/kkK9UceBPpChGWJA7SYIb1Mqo.n5aNLq1/oRrC','Administrator','Administrator','admin@localhost','','','en',NULL,NULL,'system','2018-02-26 22:18:46',NULL,'system',NULL,NULL),(4,'user','$2a$10$VEjxo0jq2YG9Rbk2HmX9S.k1uZBGYUHdUcid3g/vfiEl7lwWgOH/K','User','User','user@localhost','','','en',NULL,NULL,'system','2018-02-26 22:18:46',NULL,'system',NULL,NULL),(5,'alexa','$2a$10$pDUs0ig1cgKFQPiuPshLoOOYdrm3ISsyTSSUTZVlLq3WP6T9SrEM.','alexa','',NULL,NULL,'','en','67319501836496426916',NULL,'anonymousUser','2018-02-27 00:11:11',NULL,'admin','2018-02-27 00:11:34','newc');
/*!40000 ALTER TABLE `jhi_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jhi_user_authority`
--

DROP TABLE IF EXISTS `jhi_user_authority`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `jhi_user_authority` (
  `user_id` bigint(20) NOT NULL,
  `authority_name` varchar(50) NOT NULL,
  PRIMARY KEY (`user_id`,`authority_name`),
  KEY `fk_authority_name` (`authority_name`),
  CONSTRAINT `fk_authority_name` FOREIGN KEY (`authority_name`) REFERENCES `jhi_authority` (`name`),
  CONSTRAINT `fk_user_id` FOREIGN KEY (`user_id`) REFERENCES `jhi_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jhi_user_authority`
--

LOCK TABLES `jhi_user_authority` WRITE;
/*!40000 ALTER TABLE `jhi_user_authority` DISABLE KEYS */;
INSERT INTO `jhi_user_authority` VALUES (1,'ROLE_ADMIN'),(1,'ROLE_USER'),(3,'ROLE_ADMIN'),(3,'ROLE_USER'),(4,'ROLE_USER'),(5,'ROLE_ADMIN'),(5,'ROLE_USER');
/*!40000 ALTER TABLE `jhi_user_authority` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `market_status_history`
--

DROP TABLE IF EXISTS `market_status_history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `market_status_history` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `date_time` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `day` int(11) NOT NULL,
  `open` bit(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `market_status_history`
--

LOCK TABLES `market_status_history` WRITE;
/*!40000 ALTER TABLE `market_status_history` DISABLE KEYS */;
INSERT INTO `market_status_history` VALUES (1,'2018-02-26 22:20:01',0,'\0');
/*!40000 ALTER TABLE `market_status_history` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_crew_member`
--

DROP TABLE IF EXISTS `user_crew_member`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_crew_member` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `sex` varchar(255) NOT NULL,
  `seat` int(11) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  `crew_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `one_crew_per_seat` (`user_id`,`sex`,`seat`),
  KEY `fk_user_crew_member_crew_id` (`crew_id`),
  CONSTRAINT `fk_user_crew_member_crew_id` FOREIGN KEY (`crew_id`) REFERENCES `crew` (`id`),
  CONSTRAINT `fk_user_crew_member_user_id` FOREIGN KEY (`user_id`) REFERENCES `jhi_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_crew_member`
--

LOCK TABLES `user_crew_member` WRITE;
/*!40000 ALTER TABLE `user_crew_member` DISABLE KEYS */;
INSERT INTO `user_crew_member` VALUES (1,'male',1,5,NULL),(2,'male',2,5,NULL),(3,'male',3,5,NULL),(4,'male',4,5,NULL),(5,'male',5,5,NULL),(6,'male',6,5,NULL),(7,'male',7,5,NULL),(8,'male',8,5,NULL),(9,'male',9,5,NULL),(10,'female',1,5,NULL),(11,'female',2,5,NULL),(12,'female',3,5,NULL),(13,'female',4,5,NULL),(14,'female',5,5,NULL),(15,'female',6,5,NULL),(16,'female',7,5,NULL),(17,'female',8,5,NULL),(18,'female',9,5,NULL);
/*!40000 ALTER TABLE `user_crew_member` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_crew_position_history`
--

DROP TABLE IF EXISTS `user_crew_position_history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_crew_position_history` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `sex` varchar(255) NOT NULL,
  `day` int(11) NOT NULL,
  `seat` int(11) NOT NULL,
  `bumps` int(11) NOT NULL,
  `dividend` decimal(10,2) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  `crew_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `one_outcome_per_seat_per_day` (`user_id`,`sex`,`seat`,`day`),
  KEY `fk_user_crew_position_history_crew_id` (`crew_id`),
  CONSTRAINT `fk_user_crew_position_history_crew_id` FOREIGN KEY (`crew_id`) REFERENCES `crew` (`id`),
  CONSTRAINT `fk_user_crew_position_history_user_id` FOREIGN KEY (`user_id`) REFERENCES `jhi_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_crew_position_history`
--

LOCK TABLES `user_crew_position_history` WRITE;
/*!40000 ALTER TABLE `user_crew_position_history` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_crew_position_history` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_crew_price`
--

DROP TABLE IF EXISTS `user_crew_price`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_crew_price` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `sex` varchar(255) NOT NULL,
  `value` decimal(10,2) NOT NULL,
  `cash` decimal(10,2) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `one_price_per_crew` (`user_id`,`sex`),
  CONSTRAINT `fk_user_crew_price_user_id` FOREIGN KEY (`user_id`) REFERENCES `jhi_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_crew_price`
--

LOCK TABLES `user_crew_price` WRITE;
/*!40000 ALTER TABLE `user_crew_price` DISABLE KEYS */;
INSERT INTO `user_crew_price` VALUES (1,'male',0.00,1000.00,5),(2,'female',0.00,1000.00,5);
/*!40000 ALTER TABLE `user_crew_price` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_crew_price_history`
--

DROP TABLE IF EXISTS `user_crew_price_history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_crew_price_history` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `sex` varchar(255) NOT NULL,
  `date_time` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `value` decimal(10,2) NOT NULL,
  `cash` decimal(10,2) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_user_crew_price_history_user_id` (`user_id`),
  CONSTRAINT `fk_user_crew_price_history_user_id` FOREIGN KEY (`user_id`) REFERENCES `jhi_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_crew_price_history`
--

LOCK TABLES `user_crew_price_history` WRITE;
/*!40000 ALTER TABLE `user_crew_price_history` DISABLE KEYS */;
INSERT INTO `user_crew_price_history` VALUES (1,'male','2018-02-27 00:11:11',0.00,1000.00,5),(2,'female','2018-02-27 00:11:11',0.00,1000.00,5);
/*!40000 ALTER TABLE `user_crew_price_history` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'dreambumps'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-02-27  0:12:31
