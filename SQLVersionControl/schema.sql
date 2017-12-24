-- MySQL dump 10.13  Distrib 5.7.19, for Win64 (x86_64)
--
-- Host: localhost    Database: db_coursescheduler
-- ------------------------------------------------------
-- Server version	5.7.19-log

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
-- Table structure for table `course`
--

DROP TABLE IF EXISTS `course`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `course` (
  `CourseID` int(11) NOT NULL AUTO_INCREMENT,
  `UserID` int(10) unsigned NOT NULL,
  `Code` int(11) NOT NULL,
  `CourseName` varchar(128) NOT NULL,
  `Category` tinyint(4) NOT NULL,
  `Credit` tinyint(1) NOT NULL,
  `Teacher` varchar(128) DEFAULT NULL,
  `Day1Start` int(11) DEFAULT NULL,
  `Day1End` int(11) DEFAULT NULL,
  `Day2Start` int(11) DEFAULT NULL,
  `Day2End` int(11) DEFAULT NULL,
  `Day3Start` int(11) DEFAULT NULL,
  `Day3End` int(11) DEFAULT NULL,
  `ExamDate` timestamp NULL DEFAULT NULL,
  `ExamDurationMinute` tinyint(4) DEFAULT NULL,
  `Semester` int(11) NOT NULL,
  PRIMARY KEY (`CourseID`),
  KEY `Course_UserID_FK_idx` (`UserID`),
  CONSTRAINT `Course_UserID_FK` FOREIGN KEY (`UserID`) REFERENCES `student` (`UserID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `major`
--

DROP TABLE IF EXISTS `major`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `major` (
  `MajorID` int(11) NOT NULL AUTO_INCREMENT,
  `MajorName` varchar(256) NOT NULL,
  `University` varchar(128) NOT NULL,
  `EntranceYear` int(11) unsigned NOT NULL,
  PRIMARY KEY (`MajorID`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `student`
--

DROP TABLE IF EXISTS `student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `student` (
  `ID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `UserID` int(10) unsigned DEFAULT NULL,
  `State` varchar(128) NOT NULL,
  `Substate` varchar(128) DEFAULT NULL,
  `MajorID` int(11) DEFAULT NULL,
  `RegistrationDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `FirstName` varchar(128) DEFAULT NULL,
  `LastName` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `UserID_UNIQUE` (`UserID`),
  KEY `FK_student_major_MajorID_idx` (`MajorID`),
  CONSTRAINT `FK_student_major_MajorID` FOREIGN KEY (`MajorID`) REFERENCES `major` (`MajorID`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping routines for database 'db_coursescheduler'
--
/*!50003 DROP PROCEDURE IF EXISTS `ADD_STUDENT_COURSE_BY_FIELDS` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `ADD_STUDENT_COURSE_BY_FIELDS`(IN uid int(10), IN cod int(11), IN coname varchar(128), IN category tinyint(4), IN credit tinyint(1), 
					IN teacher varchar(128), IN d1s int(11), IN d1e int(11), IN d2s int(11), IN d2e int(11), IN d3s int(11), IN d3e int(11), 
					IN examdate timestamp, IN examduration tinyint(4), IN semester int(11))
BEGIN

	INSERT INTO course (UserID, `Code`, CourseName, Category, Credit, Teacher, Day1Start, Day1End, Day2Start, Day2End, Day3Start, Day3End, ExamDate, ExamDurationMinute, Semester)
				VALUES (uid,	cod,	coname,		category, credit, teacher, d1s,		  d1e,		d2s,	  d2e,		d3s,	  d3e,		examdate, examduration,		 semester);

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `SET_STUDENT_MAJOR_BY_FIELDS` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `SET_STUDENT_MAJOR_BY_FIELDS`(IN uid int(10), IN mjname varchar(256), IN uni varchar(128), IN entyear int(11))
BEGIN
    SELECT MajorID, MajorName, University, EntranceYear INTO @mjid, @mjnm, @mjun, @mjey FROM major WHERE MajorName = mjname AND University = uni AND EntranceYear = entyear;
    SELECT COUNT(MajorID) INTO @c FROM (SELECT MajorID FROM major WHERE MajorName = mjname AND University = uni AND EntranceYear = entyear) AS mjRow;
    IF (@c >= 1) THEN
      UPDATE student SET MajorID = @mjid WHERE UserID = uid;
    ELSE
      INSERT INTO major (MajorName, University, EntranceYear) VALUES (mjname, uni, entyear);
      SELECT LAST_INSERT_ID() INTO @mjidn;
      UPDATE student SET MajorID = @mjidn WHERE UserID = uid;
    END IF;
  END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed
