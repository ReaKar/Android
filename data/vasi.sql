SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

DROP SCHEMA IF EXISTS `ergasia2` ;
CREATE SCHEMA IF NOT EXISTS `ergasia2` DEFAULT CHARACTER SET utf8 ;
USE `ergasia2` ;

-- -----------------------------------------------------
-- Table `ergasia2`.`SA`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ergasia2`.`SA` (
  `hashkey` VARCHAR(80) NOT NULL,
  `deviceName` VARCHAR(45) NOT NULL,
  `InterfaceIP` VARCHAR(45) NOT NULL,
  `InterfaceMacAddr` VARCHAR(45) NOT NULL,
  `OsVersion` VARCHAR(45) NOT NULL,
  `NmapVersion` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`hashkey`),
  UNIQUE INDEX `hashkey_UNIQUE` (`hashkey` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `ergasia2`.`admin`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ergasia2`.`admin` (
  `idadmin` INT(11) NOT NULL,
  `username` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idadmin`),
  UNIQUE INDEX `id_UNIQUE` (`idadmin` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `ergasia2`.`nmapjobs`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ergasia2`.`nmapjobs` (
  `idnmapjobs` INT(11) NOT NULL,
  `nmapjobscol` VARCHAR(45) NOT NULL,
  `flagperiodic` VARCHAR(45) NOT NULL,
  `timeperiodic` FLOAT NOT NULL,
  `SA_hashkey` VARCHAR(80) NOT NULL,
  PRIMARY KEY (`idnmapjobs`),
  UNIQUE INDEX `idnmapjobs_UNIQUE` (`idnmapjobs` ASC),
  INDEX `fk_nmapjobs_SA1_idx` (`SA_hashkey` ASC),
  CONSTRAINT `fk_nmapjobs_SA1`
    FOREIGN KEY (`SA_hashkey`)
    REFERENCES `ergasia2`.`SA` (`hashkey`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `ergasia2`.`nmapjobsresults`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ergasia2`.`nmapjobsresults` (
  `idnmapjobsresults` INT(11) NOT NULL AUTO_INCREMENT,
  `scanning` VARCHAR(200) NULL DEFAULT NULL,
  `verboseLevel` VARCHAR(200) NULL DEFAULT NULL,
  `debuggingLevel` VARCHAR(200) NULL DEFAULT NULL,
  `trace` VARCHAR(200) NULL DEFAULT NULL,
  `status` VARCHAR(200) NULL DEFAULT NULL,
  `address` VARCHAR(200) NULL DEFAULT NULL,
  `hostnames` VARCHAR(200) NULL DEFAULT NULL,
  `ports` VARCHAR(200) NULL DEFAULT NULL,
  `os` VARCHAR(200) NULL DEFAULT NULL,
  `uptime` VARCHAR(200) NULL DEFAULT NULL,
  `tcpsequence` VARCHAR(200) NULL DEFAULT NULL,
  `ipidsequence` VARCHAR(200) NULL DEFAULT NULL,
  `tcptssequence` VARCHAR(45) NULL DEFAULT NULL,
  `timestamp` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `SA_hashkey` VARCHAR(80) NOT NULL,
  `nmapjobs_idnmapjobs` INT(11) NOT NULL,
  PRIMARY KEY (`idnmapjobsresults`),
  UNIQUE INDEX `idnmapjobsresults_UNIQUE` (`idnmapjobsresults` ASC),
  INDEX `fk_nmapjobsresults_SA1_idx` (`SA_hashkey` ASC),
  INDEX `fk_nmapjobsresults_nmapjobs1_idx` (`nmapjobs_idnmapjobs` ASC),
  CONSTRAINT `fk_nmapjobsresults_nmapjobs1`
    FOREIGN KEY (`nmapjobs_idnmapjobs`)
    REFERENCES `ergasia2`.`nmapjobs` (`idnmapjobs`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_nmapjobsresults_SA1`
    FOREIGN KEY (`SA_hashkey`)
    REFERENCES `ergasia2`.`SA` (`hashkey`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `ergasia2`.`client`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ergasia2`.`client` (
  `username` VARCHAR(40) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`username`),
  UNIQUE INDEX `username_UNIQUE` (`username` ASC))
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
