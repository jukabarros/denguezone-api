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
-- Table structure for table `casos-zika`
--

DROP TABLE IF EXISTS `casos-zika`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `casos-zika` (
  `nu_notificacao` int(11) DEFAULT NULL,
  `tp_notificacao` int(11) DEFAULT NULL,
  `co_cid` text,
  `tp_suspeita` text,
  `dt_notificacao` text,
  `ds_semana_notificacao` int(11) DEFAULT NULL,
  `ano_notificacao` int(11) DEFAULT NULL,
  `co_uf_notificacao` int(11) DEFAULT NULL,
  `co_municipio_notificacao` int(11) DEFAULT NULL,
  `id_regional` int(11) DEFAULT NULL,
  `co_unidade_notificacao` text,
  `dt_diagnostico_sintoma` text,
  `ds_semana_sintoma` int(11) DEFAULT NULL,
  `dt_nascimento` text,
  `nu_idade` text,
  `tp_sexo` text,
  `tp_gestante` int(11) DEFAULT NULL,
  `tp_raca_cor` int(11) DEFAULT NULL,
  `tp_escolaridade` text,
  `co_uf_residencia` int(11) DEFAULT NULL,
  `co_municipio_residencia` int(11) DEFAULT NULL,
  `id_rg_residencia` int(11) DEFAULT NULL,
  `co_distrito_residencia` int(11) DEFAULT NULL,
  `co_bairro_residencia` int(11) DEFAULT NULL,
  `no_bairro_residencia` text,
  `co_logradouro_residencia` text,
  `nm_logradouro_residencia` text,
  `co_geo_campo_1` text,
  `co_geo_campo_2` text,
  `ds_referencia_residencia` text,
  `nu_cep_residencia` text,
  `tp_zona_residencia` int(11) DEFAULT NULL,
  `co_pais_residencia` int(11) DEFAULT NULL,
  `tp_duplicidade` text,
  `st_vincula` text,
  `dt_investigacao` text,
  `tp_classificacao_final` text,
  `tp_criterio_confirmacao` text,
  `tp_autoctone_residencia` text,
  `co_uf_infeccao` text,
  `co_pais_infeccao` int(11) DEFAULT NULL,
  `co_municipio_infeccao` text,
  `co_distrito_infeccao` text,
  `co_bairro_infeccao` int(11) DEFAULT NULL,
  `no_bairro_infeccao` text,
  `st_doenca_trabalho` text,
  `tp_evolucao_caso` text,
  `dt_obito` text,
  `dt_encerramento` text,
  `dt_digitacao` text,
  `dt_transf_us` text,
  `dt_transf_dm` text,
  `dt_transf_sm` text,
  `dt_transf_rm` text,
  `dt_transf_rs` text,
  `dt_transf_se` text,
  `nu_lote_vertical` int(11) DEFAULT NULL,
  `nu_lote_horizontal` text,
  `tp_fluxo_retorno` int(11) DEFAULT NULL,
  `st_fluxo_retorno_recebido` int(11) DEFAULT NULL,
  `ds_identificador_registro` text,
  `st_importado` text,
  `tp_sistema` text
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `casos-zika`
--

LOCK TABLES `casos-zika` WRITE;
/*!40000 ALTER TABLE `casos-zika` DISABLE KEYS */;
INSERT INTO `casos-zika` VALUES (1945578,2,'A928','','2016/01/08 00:00:00',201601,2016,26,261160,1497,'0001813','2015/12/30 00:00:00',201552,'1986/07/02 00:00:00',' 4029,0','F',1,4,'09',26,261160,1497,120,867,'IPUTINGA','','R JORNALISTA LUIZ BELTRAO','','','','',1,1,'','','2016/01/08 00:00:00','','','','',0,'','',0,'','','','','','2016/01/19 00:00:00','','','2016/01/26 00:00:00','','','',2016004,'',0,2,'0026116000046','',''),(2024017,2,'A928','','2015/05/22 00:00:00',201520,2015,26,261160,1497,'0028924','2015/03/09 00:00:00',201510,'2002/02/08 00:00:00',' 4013,0','F',9,9,'',26,261160,1497,118,829,'CAMPINA DO BARRETO','','RUA CANAFE','','','','',1,1,'','','2015/05/22 00:00:00','1','1','1','26',1,'261160','',829,'CAMPINA DO BARRETO','2','1','','2015/08/10 00:00:00','2015/08/06 00:00:00','','2015/10/02 00:00:00','2015/11/24 00:00:00','','','',2015043,'',0,2,'0026116000026','',''),(2158446,2,'A928','','2015/06/17 00:00:00',201524,2015,26,261160,1497,'2517140','2015/06/05 00:00:00',201522,'1988/09/29 00:00:00',' 4026,0','M',6,9,'08',26,261160,1497,122,818,'BOA VIAGEM','','RUA GAL AMERICANO FORTA','','','','',1,1,'','','2015/06/17 00:00:00','1','1','1','26',1,'261160','',0,'','','1','','2015/12/14 00:00:00','2016/01/08 00:00:00','','','2016/01/12 00:00:00','','','',2016002,'',0,2,'0026116000066','',''),(2152423,2,'A928','','2015/12/11 00:00:00',201549,2015,26,261160,1497,'0020613','2015/05/01 00:00:00',201517,'1966/05/24 00:00:00',' 4048,0','F',5,9,'09',26,261160,1497,121,883,'AREIAS','','RUA ERALDO BARROS','','','','',1,1,'','','2015/12/11 00:00:00','1','1','1','26',1,'261160','',0,'','2','','','2015/12/11 00:00:00','2015/12/14 00:00:00','','','2015/12/22 00:00:00','','','',2015047,'',0,2,'0026116000056','',''),(2152424,2,'A928','','2015/12/11 00:00:00',201549,2015,26,261160,1497,'0020613','2015/06/01 00:00:00',201522,'2013/05/02 00:00:00',' 4002,0','F',6,9,'10',26,261160,1497,121,879,'SAN MARTIN','','RUA BETHOVEN','','','','',1,1,'','','2015/12/11 00:00:00','1','1','1','26',1,'261160','',0,'','2','9','','2015/12/11 00:00:00','2015/12/14 00:00:00','','','2015/12/22 00:00:00','','','',2015047,'',0,2,'0026116000056','',''),(2034957,2,'A928','','2015/12/30 00:00:00',201552,2015,26,261160,1497,'0000655','2015/05/05 00:00:00',201518,'1980/02/15 00:00:00',' 4035,0','M',6,9,'09',26,261160,1497,0,841,'CASA AMARELA','','RUA SANTA ISABEL','','','PROXIMO A ESCOLA DRAMIRO CHAVES','',1,1,'','','2015/12/30 00:00:00','1','1','1','26',1,'261160','',841,'CASA AMARELA','','1','','2015/12/30 00:00:00','2015/12/30 00:00:00','','','2016/01/05 00:00:00','','','',2016001,'',0,2,'0026116000036','',''),(2158445,2,'A928','','2015/12/14 00:00:00',201550,2015,26,261160,1497,'2352516','2015/05/11 00:00:00',201519,'1997/01/01 00:00:00',' 4018,0','F',9,9,'09',26,261160,1497,122,818,'BOA VIAGEM','','RUA BRUNO MAIA','','','','',1,1,'','','2015/12/14 00:00:00','1','1','1','26',1,'261160','',0,'','','1','','2015/12/14 00:00:00','2016/01/08 00:00:00','','','2016/01/12 00:00:00','','','',2016002,'',0,2,'0026116000066','',''),(2158444,2,'A928','','2015/12/14 00:00:00',201550,2015,26,261160,1497,'6082564','2015/08/01 00:00:00',201530,'1997/01/01 00:00:00',' 4018,0','F',9,9,'09',26,261160,1497,122,818,'BOA VIAGEM','','RUA ARNOUD DE HOLANDA','','','','',1,1,'','','2015/12/14 00:00:00','1','1','1','26',1,'261160','',0,'','2','1','','2015/12/14 00:00:00','2016/01/08 00:00:00','','','2016/01/12 00:00:00','','','',2016002,'',0,2,'0026116000066','',''),(2158443,2,'A928','','2015/12/14 00:00:00',201550,2015,26,261160,1497,'0000655','2015/05/13 00:00:00',201519,'1963/01/01 00:00:00',' 4052,0','F',6,9,'09',26,261160,1497,122,819,'IPSEP','','RUA RIO MARANHAO','','','','',1,1,'','','2015/12/14 00:00:00','1','1','1','26',1,'261160','',0,'','2','1','','2015/12/14 00:00:00','2016/01/08 00:00:00','','','2016/01/12 00:00:00','','','',2016002,'',0,2,'0026116000066','','');
/*!40000 ALTER TABLE `casos-zika` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-04-03 16:39:39
