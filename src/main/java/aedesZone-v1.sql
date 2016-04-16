/*
SQLyog Enterprise - MySQL GUI v8.18 
MySQL - 5.5.5-10.0.21-MariaDB : Database - aedes_zone
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
USE `aedes_zone`;

/*Table structure for table `agravo` */

DROP TABLE IF EXISTS `agravo`;

CREATE TABLE `agravo` (
  `codigo` varchar(20) NOT NULL,
  `agravo` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `agravo` */

insert  into `agravo`(`codigo`,`agravo`) values ('A23','BRUCELOSE'),('A692','DOENCA DE LYME'),('A90','DENGUE'),('A920','FEBRE DE CHIKUNGUNYA'),('A928','FEBRE PELO VIRUS ZIKA'),('B749','FILARIOSE NAO ESPECIFICADA');

/*Table structure for table `bairro_residencia` */

DROP TABLE IF EXISTS `bairro_residencia`;

CREATE TABLE `bairro_residencia` (
  `codigo` int(11) NOT NULL,
  `nome` varchar(100) DEFAULT NULL,
  `municipio` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `bairro_residencia` */

insert  into `bairro_residencia`(`codigo`,`nome`,`municipio`) values (19,'RECIFE','RECIFE'),(27,'SANTO ANTONIO','RECIFE'),(35,'SAO JOSE','RECIFE'),(43,'ILHA JOANA BEZERRA','RECIFE'),(51,'CABANGA','RECIFE'),(60,'COELHOS','RECIFE'),(78,'ILHA DO LEITE','RECIFE'),(86,'BOA VISTA','RECIFE'),(94,'PAISSANDU','RECIFE'),(108,'SANTO AMARO','RECIFE'),(116,'SOLEDADE','RECIFE'),(124,'ESPINHEIRO','RECIFE'),(132,'AFLITOS','RECIFE'),(140,'DERBY','RECIFE'),(159,'JAQUEIRA','RECIFE'),(167,'GRACAS','RECIFE'),(175,'ENCRUZILHADA','RECIFE'),(183,'ROSARINHO','RECIFE'),(191,'TAMARINEIRA','RECIFE'),(205,'BOA VIAGEM','RECIFE'),(213,'IPSEP','RECIFE'),(221,'JORDAO','RECIFE'),(230,'IBURA','RECIFE'),(248,'PINA','RECIFE'),(256,'BRASILIA TEIMOSA','RECIFE'),(264,'IMBIRIBEIRA','RECIFE'),(272,'TORREAO','RECIFE'),(280,'CAMPO GRANDE','RECIFE'),(299,'PEIXINHOS','RECIFE'),(302,'HIPODROMO','RECIFE'),(310,'CAMPINA DO BARRETO','RECIFE'),(329,'ARRUDA','RECIFE'),(337,'AGUA FRIA','RECIFE'),(345,'FUNDAO','RECIFE'),(353,'CAJUEIRO','RECIFE'),(361,'PORTO DA MADEIRA','RECIFE'),(370,'BEBERIBE','RECIFE'),(388,'LINHA DO TIRO','RECIFE'),(396,'DOIS UNIDOS','RECIFE'),(400,'PASSARINHO','RECIFE'),(418,'GUABIRABA','RECIFE'),(426,'BOMBA DO HEMETERIO','RECIFE'),(434,'CASA AMARELA','RECIFE'),(442,'CASA FORTE','RECIFE'),(450,'SANTANA','RECIFE'),(469,'PARNAMIRIM','RECIFE'),(477,'POCO','RECIFE'),(485,'MONTEIRO','RECIFE'),(493,'ALTO DO MANDU','RECIFE'),(507,'APIPUCOS','RECIFE'),(515,'MANGABEIRA','RECIFE'),(523,'ALTO JOSE DO PINHO','RECIFE'),(531,'MORRO DA CONCEICAO','RECIFE'),(540,'ALTO JOSE BONIFACIO','RECIFE'),(558,'VASCO DA GAMA','RECIFE'),(566,'NOVA DESCOBERTA','RECIFE'),(574,'BREJO DE BEBERIBE','RECIFE'),(582,'MACAXEIRA','RECIFE'),(590,'DOIS IRMAOS','RECIFE'),(604,'SITIO DOS PINTOS','RECIFE'),(612,'BREJO DA GUABIRABA','RECIFE'),(620,'CORREGO DO JENIPAPO','RECIFE'),(639,'PAU FERRO','RECIFE'),(647,'MADALENA','RECIFE'),(655,'TORRE','RECIFE'),(663,'PRADO','RECIFE'),(671,'ZUMBI','RECIFE'),(680,'CORDEIRO','RECIFE'),(698,'IPUTINGA','RECIFE'),(701,'TORROES','RECIFE'),(710,'ENGENHO DO MEIO','RECIFE'),(728,'CIDADE UNIVERSITARIA','RECIFE'),(736,'VARZEA','RECIFE'),(744,'CAXANGA','RECIFE'),(752,'CURADO','RECIFE'),(760,'ILHA DO RETIRO','RECIFE'),(779,'AFOGADOS','RECIFE'),(787,'MUSTARDINHA','RECIFE'),(795,'MANGUEIRA','RECIFE'),(809,'BONGI','RECIFE'),(817,'SAN MARTIN','RECIFE'),(825,'JIQUIA','RECIFE'),(833,'ESTANCIA','RECIFE'),(841,'CACOTE','RECIFE'),(850,'AREIAS','RECIFE'),(868,'JARDIM SAO PAULO','RECIFE'),(876,'BARRO','RECIFE'),(884,'COHAB','RECIFE'),(892,'TEJIPIO','RECIFE'),(902,'SANCHO','RECIFE'),(914,'TOTO','RECIFE'),(922,'COQUEIRAL','RECIFE'),(930,'PONTO DE PARADA','RECIFE'),(949,'ALTO SANTA TEREZINHA','RECIFE'),(999,'BAIRRO IGNORADO','RECIFE');

/*Table structure for table `casos_aedes` */

DROP TABLE IF EXISTS `casos_aedes`;

CREATE TABLE `casos_aedes` (
  `nu_notificacao` bigint(15) NOT NULL,
  `tp_notificacao` int(11) DEFAULT NULL,
  `co_cid` varchar(20) NOT NULL,
  `dt_notificacao` datetime DEFAULT NULL,
  `ds_semana_notificacao` int(10) DEFAULT NULL,
  `ano_notificacao` int(4) DEFAULT NULL,
  `dt_diagnostico_sintoma` datetime DEFAULT NULL,
  `ds_semana_sintoma` int(10) DEFAULT NULL,
  `dt_nascimento` datetime DEFAULT NULL,
  `tp_sexo` char(1) DEFAULT NULL,
  `tp_gestante` int(11) DEFAULT NULL,
  `tp_raca_cor` int(11) DEFAULT NULL,
  `tp_escolaridade` int(11) DEFAULT NULL,
  `co_uf_residencia` int(11) DEFAULT NULL,
  `co_municipio_residencia` int(11) DEFAULT NULL,
  `co_distrito_residencia` int(11) DEFAULT NULL,
  `co_bairro_residencia` int(11) DEFAULT NULL,
  `tp_zona_residencia` int(11) DEFAULT NULL,
  `tp_classificacao_final` int(11) DEFAULT NULL,
  `tp_criterio_confirmacao` int(11) DEFAULT NULL,
  `tp_evolucao_caso` int(11) DEFAULT NULL,
  PRIMARY KEY (`nu_notificacao`),
  KEY `FK_casos_aedes_tp_notificao` (`tp_notificacao`),
  KEY `FK_casos_aedes_agravo` (`co_cid`),
  KEY `FK_casos_aedes_gestante` (`tp_gestante`),
  KEY `FK_casos_aedes_raca_cor` (`tp_raca_cor`),
  KEY `FK_casos_aedes_escolaridade` (`tp_escolaridade`),
  KEY `FK_casos_aedes_uf_residencia` (`co_uf_residencia`),
  KEY `FK_casos_aedes_municipio_residencia` (`co_municipio_residencia`),
  KEY `FK_casos_aedes_distrito_residencia` (`co_distrito_residencia`),
  KEY `FK_casos_aedes_bairro_residencia` (`co_bairro_residencia`),
  KEY `FK_casos_aedes_zona_residencia` (`tp_zona_residencia`),
  KEY `FK_casos_aedes_classificacao_final` (`tp_classificacao_final`),
  KEY `FK_casos_aedes_criterio_confirmacao` (`tp_criterio_confirmacao`),
  KEY `FK_casos_aedes_evolucao_caso` (`tp_evolucao_caso`),
  CONSTRAINT `FK_casos_aedes_agravo` FOREIGN KEY (`co_cid`) REFERENCES `agravo` (`codigo`) ON DELETE CASCADE,
  CONSTRAINT `FK_casos_aedes_bairro_residencia` FOREIGN KEY (`co_bairro_residencia`) REFERENCES `bairro_residencia` (`codigo`) ON DELETE SET NULL,
  CONSTRAINT `FK_casos_aedes_classificacao_final` FOREIGN KEY (`tp_classificacao_final`) REFERENCES `classificacao_final` (`codigo`) ON DELETE SET NULL,
  CONSTRAINT `FK_casos_aedes_criterio_confirmacao` FOREIGN KEY (`tp_criterio_confirmacao`) REFERENCES `criterio_confirmacao` (`codigo`) ON DELETE SET NULL,
  CONSTRAINT `FK_casos_aedes_distrito_residencia` FOREIGN KEY (`co_distrito_residencia`) REFERENCES `distrito_residencia` (`codigo`) ON DELETE SET NULL,
  CONSTRAINT `FK_casos_aedes_escolaridade` FOREIGN KEY (`tp_escolaridade`) REFERENCES `escolaridade` (`codigo`) ON DELETE SET NULL,
  CONSTRAINT `FK_casos_aedes_evolucao_caso` FOREIGN KEY (`tp_evolucao_caso`) REFERENCES `evolucao_caso` (`codigo`) ON DELETE SET NULL,
  CONSTRAINT `FK_casos_aedes_gestante` FOREIGN KEY (`tp_gestante`) REFERENCES `tipo_gestante` (`codigo`) ON DELETE SET NULL,
  CONSTRAINT `FK_casos_aedes_municipio_residencia` FOREIGN KEY (`co_municipio_residencia`) REFERENCES `municipio_residencia` (`codigo`) ON DELETE SET NULL,
  CONSTRAINT `FK_casos_aedes_raca_cor` FOREIGN KEY (`tp_raca_cor`) REFERENCES `raca_cor` (`codigo`) ON DELETE SET NULL,
  CONSTRAINT `FK_casos_aedes_tp_notificao` FOREIGN KEY (`tp_notificacao`) REFERENCES `tipo_notificacao` (`codigo`) ON DELETE SET NULL,
  CONSTRAINT `FK_casos_aedes_uf_residencia` FOREIGN KEY (`co_uf_residencia`) REFERENCES `uf_residencia` (`codigo`) ON DELETE SET NULL,
  CONSTRAINT `FK_casos_aedes_zona_residencia` FOREIGN KEY (`tp_zona_residencia`) REFERENCES `zona_residencia` (`codigo`) ON DELETE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `casos_aedes` */

/*Table structure for table `classificacao_final` */

DROP TABLE IF EXISTS `classificacao_final`;

CREATE TABLE `classificacao_final` (
  `codigo` int(11) NOT NULL,
  `descricao` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `classificacao_final` */

insert  into `classificacao_final`(`codigo`,`descricao`) values (1,'DENGUE CLÁSSICO'),(2,'DENGUE COM COMPLICAÇÕES'),(3,'FEBRE HEMORRÁGICA DA DENGUE – FHD'),(4,'SÍNDROME DO CHOQUE DA DENGUE - SCD'),(5,'DESCARTADO'),(8,'INCONCLUSIVO'),(10,'DENGUE'),(11,'DENGUE COM SINAIS DE ALARME'),(12,'DENGUE GRAVE');

/*Table structure for table `criterio_confirmacao` */

DROP TABLE IF EXISTS `criterio_confirmacao`;

CREATE TABLE `criterio_confirmacao` (
  `codigo` int(11) NOT NULL,
  `descricao` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `criterio_confirmacao` */

insert  into `criterio_confirmacao`(`codigo`,`descricao`) values (1,'LABORATÓRIO'),(2,'CLÍNICO EPIDEMIOLÓGICO'),(3,'EM INVESTIGAÇÃO');

/*Table structure for table `distrito_residencia` */

DROP TABLE IF EXISTS `distrito_residencia`;

CREATE TABLE `distrito_residencia` (
  `codigo` int(11) NOT NULL,
  `distrito` varchar(100) DEFAULT NULL,
  `codigo_municipio` int(11) DEFAULT NULL,
  `municipio` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `distrito_residencia` */

insert  into `distrito_residencia`(`codigo`,`distrito`,`codigo_municipio`,`municipio`) values (117,'0001- CENTRO EXPANDIDO',261160,'RECIFE'),(118,'0002- ENCRUZILHADA-BEBERIBE',261160,'RECIFE'),(119,'0003- CASA AMARELA- DOIS IRMAOS',261160,'RECIFE'),(120,'0004- CAXANGA-VARZEA',261160,'RECIFE'),(121,'0005- AFOGADOS-TEJIPIO',261160,'RECIFE'),(122,'0006- IBURA-BOA VIAGEM',261160,'RECIFE');

/*Table structure for table `escolaridade` */

DROP TABLE IF EXISTS `escolaridade`;

CREATE TABLE `escolaridade` (
  `codigo` int(11) NOT NULL,
  `descricao` varchar(250) DEFAULT NULL,
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `escolaridade` */

insert  into `escolaridade`(`codigo`,`descricao`) values (1,'1ª A 4ª SÉRIE INCOMPLETO DO EF'),(2,'4ª SÉRIE COMPLETA DO EF (antigo 1° grau)'),(3,'5ª A 8ª SÉRIE INCOMPLETA DO EF (antigo ginásio ou 1°grau)'),(4,'ENSINO FUNDAMENTAL COMPLETO (antigo ginásio ou 1° grau)'),(5,'ENSINO MÉDIO INCOMPLETO (antigo colegial ou 2° grau)'),(6,'ENSINO MÉDIO COMPLETO (antigo colegial ou 2° grau)'),(7,'EDUCAÇÃO SUPERIOR INCOMPLETA'),(8,'EDUCAÇÃO SUPERIOR COMPLETA'),(9,'IGNORADO'),(10,'NÃO SE APLICA'),(43,'ANALFABETO');

/*Table structure for table `evolucao_caso` */

DROP TABLE IF EXISTS `evolucao_caso`;

CREATE TABLE `evolucao_caso` (
  `codigo` int(11) NOT NULL,
  `descricao` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `evolucao_caso` */

insert  into `evolucao_caso`(`codigo`,`descricao`) values (1,'CURA'),(2,'ÓBITO POR DENGUE'),(3,'ÓBITO POR oUTRAS cAUSAS'),(4,'ÓBITO EM INVESTIGAÇÃO'),(9,'IGNORADO');

/*Table structure for table `municipio_residencia` */

DROP TABLE IF EXISTS `municipio_residencia`;

CREATE TABLE `municipio_residencia` (
  `codigo` int(11) NOT NULL,
  `uf` varchar(4) DEFAULT NULL,
  `municipio` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `municipio_residencia` */

insert  into `municipio_residencia`(`codigo`,`uf`,`municipio`) values (260000,'PE','IGNORADO'),(260005,'PE','ABREU E LIMA'),(260010,'PE','AFOGADOS DA INGAZEIRA'),(260020,'PE','AFRANIO'),(260030,'PE','AGRESTINA'),(260040,'PE','AGUA PRETA'),(260050,'PE','AGUAS BELAS'),(260060,'PE','ALAGOINHA'),(260070,'PE','ALIANCA'),(260080,'PE','ALTINHO'),(260090,'PE','AMARAJI'),(260100,'PE','ANGELIM'),(260105,'PE','ARACOIABA'),(260110,'PE','ARARIPINA'),(260120,'PE','ARCOVERDE'),(260130,'PE','BARRA DE GUABIRABA'),(260140,'PE','BARREIROS'),(260150,'PE','BELEM DE MARIA'),(260160,'PE','BELEM DE SAO FRANCISCO'),(260170,'PE','BELO JARDIM'),(260180,'PE','BETANIA'),(260190,'PE','BEZERROS'),(260200,'PE','BODOCO'),(260210,'PE','BOM CONSELHO'),(260220,'PE','BOM JARDIM'),(260230,'PE','BONITO'),(260240,'PE','BREJAO'),(260250,'PE','BREJINHO'),(260260,'PE','BREJO DA MADRE DE DEUS'),(260270,'PE','BUENOS AIRES'),(260280,'PE','BUIQUE'),(260290,'PE','CABO DE SANTO AGOSTINHO'),(260300,'PE','CABROBO'),(260310,'PE','CACHOEIRINHA'),(260320,'PE','CAETES'),(260330,'PE','CALCADO'),(260340,'PE','CALUMBI'),(260345,'PE','CAMARAGIBE'),(260350,'PE','CAMOCIM DE SAO FELIX'),(260360,'PE','CAMUTANGA'),(260370,'PE','CANHOTINHO'),(260380,'PE','CAPOEIRAS'),(260390,'PE','CARNAIBA'),(260392,'PE','CARNAUBEIRA DA PENHA'),(260400,'PE','CARPINA'),(260410,'PE','CARUARU'),(260415,'PE','CASINHAS'),(260420,'PE','CATENDE'),(260430,'PE','CEDRO'),(260440,'PE','CHA DE ALEGRIA'),(260450,'PE','CHA GRANDE'),(260460,'PE','CONDADO'),(260470,'PE','CORRENTES'),(260480,'PE','CORTES'),(260490,'PE','CUMARU'),(260500,'PE','CUPIRA'),(260510,'PE','CUSTODIA'),(260515,'PE','DORMENTES'),(260520,'PE','ESCADA'),(260530,'PE','EXU'),(260540,'PE','FEIRA NOVA'),(260545,'PE','FERNANDO DE NORONHA'),(260550,'PE','FERREIROS'),(260560,'PE','FLORES'),(260570,'PE','FLORESTA'),(260580,'PE','FREI MIGUELINHO'),(260590,'PE','GAMELEIRA'),(260600,'PE','GARANHUNS'),(260610,'PE','GLORIA DO GOITA'),(260620,'PE','GOIANA'),(260630,'PE','GRANITO'),(260640,'PE','GRAVATA'),(260650,'PE','IATI'),(260660,'PE','IBIMIRIM'),(260670,'PE','IBIRAJUBA'),(260680,'PE','IGARASSU'),(260690,'PE','IGUARACI'),(260700,'PE','INAJA'),(260710,'PE','INGAZEIRA'),(260720,'PE','IPOJUCA'),(260730,'PE','IPUBI'),(260740,'PE','ITACURUBA'),(260750,'PE','ITAIBA'),(260760,'PE','ITAMARACA'),(260765,'PE','ITAMBE'),(260770,'PE','ITAPETIM'),(260775,'PE','ITAPISSUMA'),(260780,'PE','ITAQUITINGA'),(260790,'PE','JABOATAO DOS GUARARAPES'),(260795,'PE','JAQUEIRA'),(260800,'PE','JATAUBA'),(260805,'PE','JATOBA'),(260810,'PE','JOAO ALFREDO'),(260820,'PE','JOAQUIM NABUCO'),(260825,'PE','JUCATI'),(260830,'PE','JUPI'),(260840,'PE','JUREMA'),(260845,'PE','LAGOA DO CARRO'),(260850,'PE','LAGOA DO ITAENGA'),(260860,'PE','LAGOA DO OURO'),(260870,'PE','LAGOA DOS GATOS'),(260875,'PE','LAGOA GRANDE'),(260880,'PE','LAJEDO'),(260890,'PE','LIMOEIRO'),(260900,'PE','MACAPARANA'),(260910,'PE','MACHADOS'),(260915,'PE','MANARI'),(260920,'PE','MARAIAL'),(260930,'PE','MIRANDIBA'),(260940,'PE','MORENO'),(260950,'PE','NAZARE DA MATA'),(260960,'PE','OLINDA'),(260970,'PE','OROBO'),(260980,'PE','OROCO'),(260990,'PE','OURICURI'),(261000,'PE','PALMARES'),(261010,'PE','PALMEIRINA'),(261020,'PE','PANELAS'),(261030,'PE','PARANATAMA'),(261040,'PE','PARNAMIRIM'),(261050,'PE','PASSIRA'),(261060,'PE','PAUDALHO'),(261070,'PE','PAULISTA'),(261080,'PE','PEDRA'),(261090,'PE','PESQUEIRA'),(261100,'PE','PETROLANDIA'),(261110,'PE','PETROLINA'),(261120,'PE','POCAO'),(261130,'PE','POMBOS'),(261140,'PE','PRIMAVERA'),(261150,'PE','QUIPAPA'),(261153,'PE','QUIXABA'),(261160,'PE','RECIFE'),(261170,'PE','RIACHO DAS ALMAS'),(261180,'PE','RIBEIRAO'),(261190,'PE','RIO FORMOSO'),(261200,'PE','SAIRE'),(261210,'PE','SALGADINHO'),(261220,'PE','SALGUEIRO'),(261230,'PE','SALOA'),(261240,'PE','SANHARO'),(261245,'PE','SANTA CRUZ'),(261247,'PE','SANTA CRUZ DA BAIXA VERDE'),(261250,'PE','SANTA CRUZ DO CAPIBARIBE'),(261255,'PE','SANTA FILOMENA'),(261260,'PE','SANTA MARIA DA BOA VISTA'),(261270,'PE','SANTA MARIA DO CAMBUCA'),(261280,'PE','SANTA TEREZINHA'),(261290,'PE','SAO BENEDITO DO SUL'),(261300,'PE','SAO BENTO DO UNA'),(261310,'PE','SAO CAITANO'),(261320,'PE','SAO JOAO'),(261330,'PE','SAO JOAQUIM DO MONTE'),(261340,'PE','SAO JOSE DA COROA GRANDE'),(261350,'PE','SAO JOSE DO BELMONTE'),(261360,'PE','SAO JOSE DO EGITO'),(261370,'PE','SAO LOURENCO DA MATA'),(261380,'PE','SAO VICENTE FERRER'),(261390,'PE','SERRA TALHADA'),(261400,'PE','SERRITA'),(261410,'PE','SERTANIA'),(261420,'PE','SIRINHAEM'),(261430,'PE','MOREILANDIA'),(261440,'PE','SOLIDAO'),(261450,'PE','SURUBIM'),(261460,'PE','TABIRA'),(261470,'PE','TACAIMBO'),(261480,'PE','TACARATU'),(261485,'PE','TAMANDARE'),(261500,'PE','TAQUARITINGA DO NORTE'),(261510,'PE','TEREZINHA'),(261520,'PE','TERRA NOVA'),(261530,'PE','TIMBAUBA'),(261540,'PE','TORITAMA'),(261550,'PE','TRACUNHAEM'),(261560,'PE','TRINDADE'),(261570,'PE','TRIUNFO'),(261580,'PE','TUPANATINGA'),(261590,'PE','TUPARETAMA'),(261600,'PE','VENTUROSA'),(261610,'PE','VERDEJANTE'),(261618,'PE','VERTENTE DO LERIO'),(261620,'PE','VERTENTES'),(261630,'PE','VICENCIA'),(261640,'PE','VITORIA DE SANTO ANTAO'),(261650,'PE','XEXEU');

/*Table structure for table `raca_cor` */

DROP TABLE IF EXISTS `raca_cor`;

CREATE TABLE `raca_cor` (
  `codigo` int(11) NOT NULL,
  `descricao` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `raca_cor` */

insert  into `raca_cor`(`codigo`,`descricao`) values (1,'BRANCA'),(2,'PRETA'),(3,'AMARELA'),(4,'PARDA'),(5,'INDÍGENA'),(9,'IGNORADO');

/*Table structure for table `tipo_gestante` */

DROP TABLE IF EXISTS `tipo_gestante`;

CREATE TABLE `tipo_gestante` (
  `codigo` int(11) NOT NULL,
  `descricao` varchar(150) DEFAULT NULL,
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `tipo_gestante` */

insert  into `tipo_gestante`(`codigo`,`descricao`) values (1,'1º TRIMESTRE'),(2,'2º TRIMESTRE'),(3,'3º TRIMESTRE'),(4,'IDADE GESTACIONAL IGNORADA'),(5,'NÃO'),(6,'NÃO SE APLICA'),(9,'IGNORADO');

/*Table structure for table `tipo_notificacao` */

DROP TABLE IF EXISTS `tipo_notificacao`;

CREATE TABLE `tipo_notificacao` (
  `codigo` int(11) NOT NULL AUTO_INCREMENT,
  `descricao` varchar(150) DEFAULT NULL,
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

/*Data for the table `tipo_notificacao` */

insert  into `tipo_notificacao`(`codigo`,`descricao`) values (1,'NEGATIVA'),(2,'INDIVIDUAL'),(3,'SURTO'),(4,'AGREGADO');

/*Table structure for table `uf_residencia` */

DROP TABLE IF EXISTS `uf_residencia`;

CREATE TABLE `uf_residencia` (
  `codigo` int(11) NOT NULL AUTO_INCREMENT,
  `sigla` varchar(4) DEFAULT NULL,
  `descricao` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB AUTO_INCREMENT=54 DEFAULT CHARSET=latin1;

/*Data for the table `uf_residencia` */

insert  into `uf_residencia`(`codigo`,`sigla`,`descricao`) values (11,'RO','RONDONIA'),(12,'AC','ACRE'),(13,'AM','AMAZONAS'),(14,'RR','RORAIMA'),(15,'PA','PARA'),(16,'AP','AMAPA'),(17,'TO','TOCANTINS'),(21,'MA','MARANHAO'),(22,'PI','PIAUI'),(23,'CE','CEARA'),(24,'RN','RIO GRANDE DO NORTE'),(25,'PB','PARAIBA'),(26,'PE','PERNAMBUCO'),(27,'AL','ALAGOAS'),(28,'SE','SERGIPE'),(29,'BA','BAHIA'),(31,'MG','MINAS GERAIS'),(32,'ES','ESPIRITO SANTO'),(33,'RJ','RIO DE JANEIRO'),(35,'SP','SAO PAULO'),(41,'PR','PARANA'),(42,'SC','SANTA CATARINA'),(43,'RS','RIO GRANDE DO SUL'),(50,'MS','MATO GROSSO DO SUL'),(51,'MT','MATO GROSSO'),(52,'GO','GOIAS'),(53,'DF','DISTRITO FEDERAL');

/*Table structure for table `zona_residencia` */

DROP TABLE IF EXISTS `zona_residencia`;

CREATE TABLE `zona_residencia` (
  `codigo` int(11) NOT NULL,
  `descricao` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `zona_residencia` */

insert  into `zona_residencia`(`codigo`,`descricao`) values (1,'URBANA'),(2,'RURAL'),(3,'PERIURBANA'),(9,'IGNORADO');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
