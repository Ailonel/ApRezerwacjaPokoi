-- MySQL dump 10.13  Distrib 5.7.9, for Win64 (x86_64)
--
-- Host: localhost    Database: rezerwacja_pokoi
-- ------------------------------------------------------
-- Server version	5.7.9-log

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
-- Table structure for table `obiekt`
--

DROP TABLE IF EXISTS `obiekt`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `obiekt` (
  `id_obiektu` int(11) NOT NULL,
  `nazwa_obiektu` varchar(30) DEFAULT NULL,
  `kod_pocztowy` varchar(30) DEFAULT NULL,
  `miejscowosc` varchar(30) DEFAULT NULL,
  `ulica` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id_obiektu`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `obiekt`
--

LOCK TABLES `obiekt` WRITE;
/*!40000 ALTER TABLE `obiekt` DISABLE KEYS */;
INSERT INTO `obiekt` VALUES (1,'U Mateusza','38-610','Polańczyk','Bieszczadzka 3');
/*!40000 ALTER TABLE `obiekt` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pokoj`
--

DROP TABLE IF EXISTS `pokoj`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pokoj` (
  `id_pokoju` int(11) NOT NULL AUTO_INCREMENT,
  `numer_pokoju` int(11) DEFAULT NULL,
  `cena_za_dzien` int(11) DEFAULT NULL,
  `liczba_osob` int(11) DEFAULT NULL,
  `miejsce_parking` int(11) DEFAULT NULL,
  `miejsce_jadalnia` int(11) DEFAULT NULL,
  `id_obiektu` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_pokoju`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pokoj`
--

LOCK TABLES `pokoj` WRITE;
/*!40000 ALTER TABLE `pokoj` DISABLE KEYS */;
INSERT INTO `pokoj` VALUES (1,1,100,2,1,1,1),(2,2,100,2,2,2,1),(3,3,100,2,3,3,1);
/*!40000 ALTER TABLE `pokoj` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pokoj_zdjecie`
--

DROP TABLE IF EXISTS `pokoj_zdjecie`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pokoj_zdjecie` (
  `id_zdjecie` int(11) NOT NULL AUTO_INCREMENT,
  `miniatura` tinyint(1) DEFAULT NULL,
  `zdjecie` varchar(40) DEFAULT NULL,
  `id_pokoju` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_zdjecie`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pokoj_zdjecie`
--

LOCK TABLES `pokoj_zdjecie` WRITE;
/*!40000 ALTER TABLE `pokoj_zdjecie` DISABLE KEYS */;
INSERT INTO `pokoj_zdjecie` VALUES (1,0,'Foto/U_Mateusza/1/1.jpg',1),(2,0,'Foto/U_Mateusza/1/2.jpg',1),(3,0,'Foto/U_Mateusza/1/3.jpg',1),(4,0,'Foto/U_Mateusza/1/4.jpg',1),(5,0,'Foto/U_Mateusza/2/1.jpg',2),(6,0,'Foto/U_Mateusza/2/2.jpg',2),(7,0,'Foto/U_Mateusza/2/3.jpg',2),(8,0,'Foto/U_Mateusza/2/4.jpg',2),(9,0,'Foto/U_Mateusza/3/1.jpg',3),(10,0,'Foto/U_Mateusza/3/2.jpg',3),(11,0,'Foto/U_Mateusza/3/3.jpg',3);
/*!40000 ALTER TABLE `pokoj_zdjecie` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pracownik`
--

DROP TABLE IF EXISTS `pracownik`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pracownik` (
  `id_pracownika` int(11) NOT NULL AUTO_INCREMENT,
  `haslo` varchar(10) DEFAULT NULL,
  `imie` varchar(10) DEFAULT NULL,
  `nazwisko` varchar(10) DEFAULT NULL,
  `czy_wlasciciel` tinyint(1) DEFAULT NULL,
  `numer_telefonu` int(11) DEFAULT NULL,
  `email` varchar(30) DEFAULT NULL,
  `pensja` int(11) DEFAULT NULL,
  `id_obiektu` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_pracownika`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pracownik`
--

LOCK TABLES `pracownik` WRITE;
/*!40000 ALTER TABLE `pracownik` DISABLE KEYS */;
/*!40000 ALTER TABLE `pracownik` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rezerwacja`
--

DROP TABLE IF EXISTS `rezerwacja`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rezerwacja` (
  `id_rezerwacji` int(11) NOT NULL AUTO_INCREMENT,
  `data_rezerwacji` date DEFAULT NULL,
  `data_przyjazdu` date DEFAULT NULL,
  `data_wyjazdu` date DEFAULT NULL,
  `potwierdzenie` tinyint(1) DEFAULT NULL,
  `id_uzytkownika` int(11) DEFAULT NULL,
  `id_pokoju` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_rezerwacji`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rezerwacja`
--

LOCK TABLES `rezerwacja` WRITE;
/*!40000 ALTER TABLE `rezerwacja` DISABLE KEYS */;
INSERT INTO `rezerwacja` VALUES (1,'2016-01-03','2016-01-04','2016-01-14',0,1,1),(2,'2016-01-03','2015-12-04','2015-12-17',1,1,1),(3,'2016-01-03','2015-12-04','2015-12-17',1,1,2),(4,'2016-01-03','2015-12-04','2015-12-17',1,1,2),(5,'2016-01-03','2015-10-02','2015-11-10',1,1,1),(6,'2016-01-03','2015-10-02','2015-11-10',1,1,3),(8,'2016-01-03','2016-01-08','2016-01-15',1,5,2),(9,'2016-01-03','2016-01-08','2016-01-15',1,5,2);
/*!40000 ALTER TABLE `rezerwacja` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `uzytkownik`
--

DROP TABLE IF EXISTS `uzytkownik`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `uzytkownik` (
  `id_uzytkownika` int(11) NOT NULL AUTO_INCREMENT,
  `haslo` varchar(20) DEFAULT NULL,
  `imie` varchar(20) DEFAULT NULL,
  `nazwisko` varchar(20) DEFAULT NULL,
  `email` varchar(20) DEFAULT NULL,
  `nr_telefonu` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id_uzytkownika`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `uzytkownik`
--

LOCK TABLES `uzytkownik` WRITE;
/*!40000 ALTER TABLE `uzytkownik` DISABLE KEYS */;
INSERT INTO `uzytkownik` VALUES (1,'d3','Mateusz','Mazurek','mazurek@gmail.com',NULL),(2,'qwerty','Jan','Kowalski','jkow@o2.pl',NULL),(3,'K','K','K','K',NULL),(4,'qq','Jon','Kow','kow@a.pl',NULL),(5,'test',NULL,'','test@gmail.com',NULL);
/*!40000 ALTER TABLE `uzytkownik` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `zameldowanie`
--

DROP TABLE IF EXISTS `zameldowanie`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `zameldowanie` (
  `id_zameldowania` int(11) NOT NULL AUTO_INCREMENT,
  `data_zameldowania` date DEFAULT NULL,
  `data_wymeldowania` date DEFAULT NULL,
  `nr_dowodu` varchar(15) DEFAULT NULL,
  `pesel` int(11) DEFAULT NULL,
  `placona_kwota` int(11) DEFAULT NULL,
  `id_uzytkownika` int(11) DEFAULT NULL,
  `id_pokoju` int(11) DEFAULT NULL,
  `id_rezerwacji` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_zameldowania`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `zameldowanie`
--

LOCK TABLES `zameldowanie` WRITE;
/*!40000 ALTER TABLE `zameldowanie` DISABLE KEYS */;
/*!40000 ALTER TABLE `zameldowanie` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-01-05 12:38:16
