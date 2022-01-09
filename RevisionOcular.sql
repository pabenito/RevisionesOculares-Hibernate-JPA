-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema revocular
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema revocular
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `revocular` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `revocular` ;

-- -----------------------------------------------------
-- Table `revocular`.`tclient`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `revocular`.`tclient` (
  `NIF` VARCHAR(50) NOT NULL,
  `NOMBRE` VARCHAR(250) NOT NULL,
  `APELLIDOS` VARCHAR(250) NOT NULL,
  `EDAD` INT NOT NULL,
  PRIMARY KEY (`NIF`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `revocular`.`teye`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `revocular`.`teye` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `NIF` VARCHAR(50) NOT NULL,
  `CONSULTA` DATE NOT NULL,
  `OD_ESFERA` DOUBLE NOT NULL,
  `OD_CILINDRO` DOUBLE NOT NULL,
  `OD_ADICION` DOUBLE NOT NULL,
  `OD_AGUDEZA` DOUBLE NOT NULL,
  `OI_ESFERA` DOUBLE NOT NULL,
  `OI_CILINDRO` DOUBLE NOT NULL,
  `OI_ADICION` DOUBLE NOT NULL,
  `OI_AGUDEZA` DOUBLE NOT NULL,
  PRIMARY KEY (`ID`),
  INDEX `FK_NIF_idx` (`NIF` ASC) VISIBLE,
  CONSTRAINT `FK_NIF`
    FOREIGN KEY (`NIF`)
    REFERENCES `revocular`.`tclient` (`NIF`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
