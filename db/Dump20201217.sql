CREATE DATABASE  IF NOT EXISTS `ems` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `ems`;
-- MySQL dump 10.13  Distrib 8.0.20, for Win64 (x86_64)
--
-- Host: localhost    Database: ems
-- ------------------------------------------------------
-- Server version	8.0.20

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `company_bank_acc_info`
--

DROP TABLE IF EXISTS `company_bank_acc_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `company_bank_acc_info` (
  `ACC_ID` int NOT NULL AUTO_INCREMENT,
  `ACC_NAME` varchar(45) NOT NULL,
  `ACC_NUM` varchar(50) NOT NULL,
  `ACC_TYPE` char(1) NOT NULL COMMENT '0 = current, 1 = savings',
  `BANK_NAME` varchar(45) NOT NULL,
  `BRANCH_NAME` varchar(45) NOT NULL,
  `CUR_BAL` decimal(19,2) NOT NULL,
  PRIMARY KEY (`ACC_ID`),
  CONSTRAINT `COMPANY_BANK_ACC_TYPE_CONST_chk` CHECK (((`ACC_TYPE` = _utf8mb4'0') or (`ACC_TYPE` = _utf8mb4'1')))
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `company_bank_acc_info`
--

LOCK TABLES `company_bank_acc_info` WRITE;
/*!40000 ALTER TABLE `company_bank_acc_info` DISABLE KEYS */;
INSERT INTO `company_bank_acc_info` VALUES (1,'Salary Disburse Account','86754641234','0','DBBL','Karwan Bazar Corporate',1007000.00);
/*!40000 ALTER TABLE `company_bank_acc_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `emp_bank_acc_info`
--

DROP TABLE IF EXISTS `emp_bank_acc_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `emp_bank_acc_info` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `ACC_NAME` varchar(45) NOT NULL,
  `ACC_NUM` varchar(50) NOT NULL,
  `ACC_TYPE` char(1) NOT NULL COMMENT '0 = current, 1 = savings',
  `BANK_NAME` varchar(45) NOT NULL,
  `BRANCH_NAME` varchar(45) NOT NULL,
  `CUR_BAL` decimal(10,2) NOT NULL,
  `EMP_ID` int NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `EMP_ID_UNIQUE` (`EMP_ID`),
  CONSTRAINT `EMP_ID_FK` FOREIGN KEY (`EMP_ID`) REFERENCES `employee` (`EMP_ID`),
  CONSTRAINT `ACC_TYPE_CONST_chk` CHECK (((`ACC_TYPE` = _utf8mb4'0') or (`ACC_TYPE` = _utf8mb4'1')))
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `emp_bank_acc_info`
--

LOCK TABLES `emp_bank_acc_info` WRITE;
/*!40000 ALTER TABLE `emp_bank_acc_info` DISABLE KEYS */;
INSERT INTO `emp_bank_acc_info` VALUES (1,'nm','988888888','0','mnbnm','mnnmn',0.00,1011);
/*!40000 ALTER TABLE `emp_bank_acc_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employee` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `EMP_ID` int NOT NULL,
  `EMP_NAME` varchar(45) NOT NULL,
  `GRADE` char(1) NOT NULL,
  `ADDRESS` varchar(150) NOT NULL,
  `MOBILE` char(11) NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `Employee_ID_UNIQUE` (`EMP_ID`),
  KEY `FKl021ytasfcjoqcc3qi8h7mkv8` (`GRADE`),
  CONSTRAINT `FKl021ytasfcjoqcc3qi8h7mkv8` FOREIGN KEY (`GRADE`) REFERENCES `employee_salary_by_grade` (`GRADE_ID`),
  CONSTRAINT `EMP_ID_CONST` CHECK (((`EMP_ID` > 1000) and (`EMP_ID` < 10000))),
  CONSTRAINT `GRADE_CONST` CHECK (((`GRADE` >= _utf8mb4'1') and (`GRADE` <= _utf8mb4'6'))),
  CONSTRAINT `MOBILE_CONST` CHECK ((char_length(`MOBILE`) = 11))
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee`
--

LOCK TABLES `employee` WRITE;
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
INSERT INTO `employee` VALUES (2,1001,'uksafu','4','hjsfbd','01987777777'),(3,1002,'mn,m','3','jmnj','01888888888'),(4,1003,'sd','4','dfg','01777777777'),(5,1004,'sjkdfs','1','sjdf','01777777777'),(6,1005,'njsfdn','4','jnsndfj','01999999999'),(7,1006,'jdfkdf','3','jdxfdkf','01777777777'),(8,1007,'dsgd','2','dfg','01999999999'),(9,1008,'xdx','3','xcd','01888888888'),(10,1009,'dfgfg','3','cfbgfc','01888888888'),(11,1010,'hjbbj','4','bhjhb','01222222222'),(12,1011,'hjbbj','2','bhjhb','01222222222');
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee_salary_by_grade`
--

DROP TABLE IF EXISTS `employee_salary_by_grade`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employee_salary_by_grade` (
  `GRADE_ID` char(1) NOT NULL,
  `SALARY` decimal(10,2) NOT NULL,
  PRIMARY KEY (`GRADE_ID`),
  CONSTRAINT `GRADE_CONST_chk` CHECK (((`GRADE_ID` >= _utf8mb4'1') and (`GRADE_ID` <= 6)))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='employee salary by Grade';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee_salary_by_grade`
--

LOCK TABLES `employee_salary_by_grade` WRITE;
/*!40000 ALTER TABLE `employee_salary_by_grade` DISABLE KEYS */;
INSERT INTO `employee_salary_by_grade` VALUES ('1',45900.00),('2',39150.00),('3',32400.00),('4',25650.00),('5',18900.00),('6',12150.00);
/*!40000 ALTER TABLE `employee_salary_by_grade` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'ems'
--

--
-- Dumping routines for database 'ems'
--
/*!50003 DROP PROCEDURE IF EXISTS `calculate_salary_by_grade` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `calculate_salary_by_grade`(in basic_sal_of_lowest_grade integer)
BEGIN
	DECLARE x tinyint;
	DECLARE basic_sal int;
	DECLARE current_basic int;
	DECLARE total_sal int;
    
    DECLARE errno INT;
    DECLARE msg TEXT;
    
    DECLARE EXIT HANDLER FOR SQLEXCEPTION 
    BEGIN
        ROLLBACK;
		select '0' as msg;
        -- Here the current DA is nonempty because no prior statements
        -- executing within the handler have cleared it
		/*GET CURRENT DIAGNOSTICS CONDITION 1
		  errno = MYSQL_ERRNO, msg = MESSAGE_TEXT;
		SELECT 'current DA before mapped insert' AS op, errno, msg;
		GET STACKED DIAGNOSTICS CONDITION 1
		  errno = MYSQL_ERRNO, msg = MESSAGE_TEXT;
		SELECT 'stacked DA before mapped insert' AS op, errno, msg;*/
    END;
    
    START TRANSACTION;
    
    set x = 6;
    set current_basic = basic_sal_of_lowest_grade;
    
		delete from employee_salary_by_grade;
        
            loop_label:  LOOP
				IF  x < 1 THEN 
					LEAVE  loop_label;
				END  IF;
                
                set basic_sal = current_basic;
                set total_sal = basic_sal + basic_sal * 0.35;
                
                insert into employee_salary_by_grade(GRADE_ID, SALARY) values(x, total_sal);
                
                set current_basic = current_basic + 5000;
                set x = x - 1;
                
			END LOOP;
	COMMIT;
    
    select '1' as msg;
    
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

-- Dump completed on 2020-12-17 15:49:55
