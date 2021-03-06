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
-- Table structure for table `tabela-de-bairros`
--

DROP TABLE IF EXISTS `tabela-de-bairros`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tabela-de-bairros` (
  `N Localidade` int(11) DEFAULT NULL,
  `Nome Localidade` text,
  `Nome Municipio` text
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tabela-de-bairros`
--

LOCK TABLES `tabela-de-bairros` WRITE;
/*!40000 ALTER TABLE `tabela-de-bairros` DISABLE KEYS */;
INSERT INTO `tabela-de-bairros` VALUES (132,' AFLITOS','RECIFE'),(779,' AFOGADOS','RECIFE'),(337,' AGUA FRIA','RECIFE'),(493,' ALTO DO MANDU','RECIFE'),(540,' ALTO JOSE BONIFACIO','RECIFE'),(523,' ALTO JOSE DO PINHO','RECIFE'),(949,' ALTO SANTA TEREZINHA','RECIFE'),(507,' APIPUCOS','RECIFE'),(850,' AREIAS','RECIFE'),(329,' ARRUDA','RECIFE'),(999,' BAIRRO IGNORADO','RECIFE'),(876,' BARRO','RECIFE'),(370,' BEBERIBE','RECIFE'),(205,' BOA VIAGEM','RECIFE'),(86,' BOA VISTA','RECIFE'),(426,' BOMBA DO HEMETERIO','RECIFE'),(809,' BONGI','RECIFE'),(256,' BRASILIA TEIMOSA','RECIFE'),(612,' BREJO DA GUABIRABA','RECIFE'),(574,' BREJO DE BEBERIBE','RECIFE'),(51,' CABANGA','RECIFE'),(841,' CACOTE','RECIFE'),(353,' CAJUEIRO','RECIFE'),(310,' CAMPINA DO BARRETO','RECIFE'),(280,' CAMPO GRANDE','RECIFE'),(434,' CASA AMARELA','RECIFE'),(442,' CASA FORTE','RECIFE'),(744,' CAXANGA','RECIFE'),(728,' CIDADE UNIVERSITARIA','RECIFE'),(60,' COELHOS','RECIFE'),(884,' COHAB','RECIFE'),(922,' COQUEIRAL','RECIFE'),(680,' CORDEIRO','RECIFE'),(620,' CORREGO DO JENIPAPO','RECIFE'),(752,' CURADO','RECIFE'),(140,' DERBY','RECIFE'),(590,' DOIS IRMAOS','RECIFE'),(396,' DOIS UNIDOS','RECIFE'),(175,' ENCRUZILHADA','RECIFE'),(710,' ENGENHO DO MEIO','RECIFE'),(124,' ESPINHEIRO','RECIFE'),(833,' ESTANCIA','RECIFE'),(345,' FUNDAO','RECIFE'),(167,' GRACAS','RECIFE'),(418,' GUABIRABA','RECIFE'),(302,' HIPODROMO','RECIFE'),(230,' IBURA','RECIFE'),(78,' ILHA DO LEITE','RECIFE'),(760,' ILHA DO RETIRO','RECIFE'),(43,' ILHA JOANA BEZERRA','RECIFE'),(264,' IMBIRIBEIRA','RECIFE'),(213,' IPSEP','RECIFE'),(698,' IPUTINGA','RECIFE'),(159,' JAQUEIRA','RECIFE'),(868,' JARDIM SAO PAULO','RECIFE'),(825,' JIQUIA','RECIFE'),(221,' JORDAO','RECIFE'),(388,' LINHA DO TIRO','RECIFE'),(582,' MACAXEIRA','RECIFE'),(647,' MADALENA','RECIFE'),(515,' MANGABEIRA','RECIFE'),(795,' MANGUEIRA','RECIFE'),(485,' MONTEIRO','RECIFE'),(531,' MORRO DA CONCEICAO','RECIFE'),(787,' MUSTARDINHA','RECIFE'),(566,' NOVA DESCOBERTA','RECIFE'),(94,' PAISSANDU','RECIFE'),(469,' PARNAMIRIM','RECIFE'),(400,' PASSARINHO','RECIFE'),(639,' PAU FERRO','RECIFE'),(299,' PEIXINHOS','RECIFE'),(248,' PINA','RECIFE'),(477,' POCO','RECIFE'),(930,' PONTO DE PARADA','RECIFE'),(361,' PORTO DA MADEIRA','RECIFE'),(663,' PRADO','RECIFE'),(19,'RECIFE','RECIFE'),(183,' ROSARINHO','RECIFE'),(817,' SAN MARTIN','RECIFE'),(902,' SANCHO','RECIFE'),(450,' SANTANA','RECIFE'),(108,' SANTO AMARO','RECIFE'),(27,' SANTO ANTONIO','RECIFE'),(35,' SAO JOSE','RECIFE'),(604,' SITIO DOS PINTOS','RECIFE'),(116,' SOLEDADE','RECIFE'),(191,' TAMARINEIRA','RECIFE'),(892,' TEJIPIO','RECIFE'),(655,' TORRE','RECIFE'),(272,' TORREAO','RECIFE'),(701,' TORROES','RECIFE'),(914,' TOTO','RECIFE'),(736,' VARZEA','RECIFE'),(558,' VASCO DA GAMA','RECIFE'),(671,' ZUMBI','RECIFE');
/*!40000 ALTER TABLE `tabela-de-bairros` ENABLE KEYS */;
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
