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
INSERT INTO `company_bank_acc_info` VALUES (1,'Salary Disburse Account','86754641234','0','DBBL','Karwan Bazar Corporate',0.00);
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
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `emp_bank_acc_info`
--

LOCK TABLES `emp_bank_acc_info` WRITE;
/*!40000 ALTER TABLE `emp_bank_acc_info` DISABLE KEYS */;
INSERT INTO `emp_bank_acc_info` VALUES (1,'Grade_1_DummyEmployee_Account','8786543987118790','0','DBBL','Karwan Bazar Corporate Branch',0.00,1001),(2,'Grade_2_DummyEmployee_Account','8786543987118799','0','DBBL','Karwan Bazar Corporate Branch',0.00,1002),(3,'Grade_3_DummyEmployee_1_Account','8786643987118790','0','DBBL','Karwan Bazar Corporate Branch',0.00,1003),(4,'Grade_3_DummyEmployee_2_Account','9786643987118790','0','DBBL','Karwan Bazar Corporate Branch',0.00,1004),(5,'Grade_4_DummyEmployee_1_Account','8786643987818790','0','DBBL','Karwan Bazar Corporate Branch',0.00,1005),(6,'Grade_4_DummyEmployee_2_Address','8786532987118790','0','DBBL','Karwan Bazar Corporate Branch',0.00,1006),(7,'Grade_5_DummyEmployee_1_Account','8787992987118790','0','DBBL','Karwan Bazar Corporate Branch',0.00,1007),(8,'Grade_5_DummyEmployee_2_Address','8786532987568790','0','DBBL','Karwan Bazar Corporate Branch',0.00,1008),(9,'Grade_6_DummyEmployee_1_Account','8786532987561122','0','DBBL','Karwan Bazar Corporate Branch',0.00,1009),(10,'Grade_6_DummyEmployee_2_Account','7786532987561122','0','DBBL','Karwan Bazar Corporate Branch',0.00,1010);
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
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee`
--

LOCK TABLES `employee` WRITE;
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
INSERT INTO `employee` VALUES (13,1001,'Grade_1_DummyEmployee','1','Grade_1_DummyEmployee_Address','01754889876'),(14,1002,'Grade_2_DummyEmployee','2','Grade_2_DummyEmployee_Address','01754889870'),(15,1003,'Grade_3_DummyEmployee_1','3','Grade_3_DummyEmployee_1_Address','01954889170'),(16,1004,'Grade_3_DummyEmployee_2','3','Grade_3_DummyEmployee_2_Address','01954889178'),(17,1005,'Grade_4_DummyEmployee_1','4','Grade_4_DummyEmployee_1_Address','01854889178'),(18,1006,'Grade_4_DummyEmployee_2','4','Grade_4_DummyEmployee_2_Address','01854889170'),(19,1007,'Grade_5_DummyEmployee_1','5','Grade_5_DummyEmployee_1_Address','01854880178'),(20,1008,'Grade_5_DummyEmployee_2','5','Grade_5_DummyEmployee_2_Address','01354880178'),(21,1009,'Grade_6_DummyEmployee_1','6','Grade_6_DummyEmployee_1_Address','01354880179'),(22,1010,'Grade_6_DummyEmployee_2','6','Grade_6_DummyEmployee_2_Address','01884880179');
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
INSERT INTO `employee_salary_by_grade` VALUES ('1',54000.00),('2',47250.00),('3',40500.00),('4',33750.00),('5',27000.00),('6',20250.00);
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
/*!50003 DROP PROCEDURE IF EXISTS `salary_disburse_process` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `salary_disburse_process`(in emp_ids varchar(1000))
BEGIN
	DECLARE company_balance decimal(19,2);
	DECLARE employee_id int;
	DECLARE employee_sal decimal(10,2);
    DECLARE output varchar(1000) DEFAULT '';
    
    -- for dubug purpose
    -- DECLARE errno INT;
    -- DECLARE msg TEXT;
        
    -- cursor flag
    DECLARE done INT DEFAULT FALSE;
    
    -- declare cursor for employee salary info
	DECLARE curEmpSalInfo
		CURSOR FOR
			select a.EMP_ID, b.SALARY from employee a
				inner join employee_salary_by_grade b on a.GRADE = b.GRADE_ID
                where FIND_IN_SET (emp_id, emp_ids)
                order by b.GRADE_ID desc;
                
	-- declare NOT FOUND handler, for cursor
	DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = TRUE;
    
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
    
			-- comapny balance initial fetch
			select CUR_BAL from company_bank_acc_info into company_balance;
    
			-- open cursor
			OPEN curEmpSalInfo;
    
			iterate_resultset: LOOP
			FETCH curEmpSalInfo INTO employee_id, employee_sal;
			IF done OR (company_balance - employee_sal < 0) THEN
				LEAVE iterate_resultset;
			END IF;
            
			-- debit company main account
                update company_bank_acc_info set cur_bal = cur_bal - employee_sal where acc_id = 1;
                
			-- credit employee salary account
                update emp_bank_acc_info set cur_bal = cur_bal + employee_sal where emp_id = employee_id;
                
                set company_balance = company_balance - employee_sal;
					
				-- create output
				set output = CONCAT(output, employee_id, ',');
                
			END LOOP iterate_resultset;
            
			CLOSE curEmpSalInfo;
            
            -- commit point
            COMMIT;
            
            IF done THEN
                select '1' as msg; -- all employee gets salary
			ELSE
				select output as msg; -- partial employee gets salary
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

-- Dump completed on 2020-12-20  4:29:19
