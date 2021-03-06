-- MySQL dump 10.13  Distrib 5.6.28, for debian-linux-gnu (i686)
--
-- Host: 127.0.0.1    Database: Dengue_Zone
-- ------------------------------------------------------
-- Server version	5.6.28-0ubuntu0.15.10.1

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
-- Table structure for table `tabela-uf`
--

DROP TABLE IF EXISTS `tabela-uf`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tabela-uf` (
  `codigo` int(11) DEFAULT NULL,
  `sigla` text,
  `descricao` text
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tabela-uf`
--

LOCK TABLES `tabela-uf` WRITE;
/*!40000 ALTER TABLE `tabela-uf` DISABLE KEYS */;
INSERT INTO `tabela-uf` VALUES (12,'AC','ACRE'),(27,'AL','ALAGOAS'),(16,'AP','AMAPA'),(13,'AM','AMAZONAS'),(29,'BA','BAHIA'),(23,'CE','CEARA'),(53,'DF','DISTRITO FEDERAL'),(32,'ES','ESPIRITO SANTO'),(52,'GO','GOIAS'),(21,'MA','MARANHAO'),(51,'MT','MATO GROSSO'),(50,'MS','MATO GROSSO DO SUL'),(31,'MG','MINAS GERAIS'),(15,'PA','PARA'),(25,'PB','PARAIBA'),(41,'PR','PARANA'),(26,'PE','PERNAMBUCO'),(22,'PI','PIAUI'),(33,'RJ','RIO DE JANEIRO'),(24,'RN','RIO GRANDE DO NORTE'),(43,'RS','RIO GRANDE DO SUL'),(11,'RO','RONDONIA'),(14,'RR','RORAIMA'),(42,'SC','SANTA CATARINA'),(35,'SP','SAO PAULO'),(28,'SE','SERGIPE'),(17,'TO','TOCANTINS');
/*!40000 ALTER TABLE `tabela-uf` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-04-03 16:39:40
