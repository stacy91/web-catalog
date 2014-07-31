SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema myOnlineShopDB
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `myOnlineShopDB` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `myOnlineShopDB` ;

-- -----------------------------------------------------
-- Table `myOnlineShopDB`.`Brands`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `myOnlineShopDB`.`Brands` (
  `Id` INT NOT NULL AUTO_INCREMENT,
  `BrandName` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`Id`),
  UNIQUE INDEX `Id_UNIQUE` (`Id` ASC),
  UNIQUE INDEX `BrandName_UNIQUE` (`BrandName` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `myOnlineShopDB`.`Goods_Model`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `myOnlineShopDB`.`Goods_Model` (
  `Id` INT NOT NULL AUTO_INCREMENT,
  `BrandId` INT NOT NULL,
  `ModelName` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`Id`),
  UNIQUE INDEX `Id_UNIQUE` (`Id` ASC),
  INDEX `BrandId_FK_idx` (`BrandId` ASC),
  UNIQUE INDEX `ModelName_UNIQUE` (`ModelName` ASC),
  CONSTRAINT `Goods_Model_BrandId_FK_Brands`
    FOREIGN KEY (`BrandId`)
    REFERENCES `myOnlineShopDB`.`Brands` (`Id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `myOnlineShopDB`.`Goods_ScreenSize`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `myOnlineShopDB`.`Goods_ScreenSize` (
  `Id` INT NOT NULL AUTO_INCREMENT,
  `ScreenSize` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`Id`),
  UNIQUE INDEX `Id_UNIQUE` (`Id` ASC),
  UNIQUE INDEX `ScreenSize_UNIQUE` (`ScreenSize` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `myOnlineShopDB`.`Goods`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `myOnlineShopDB`.`Goods` (
  `Id` INT NOT NULL AUTO_INCREMENT,
  `ModelId` INT NOT NULL,
  `ScreenSizeId` INT NOT NULL,
  `MemoryId` INT NOT NULL,
  `ColorId` INT NOT NULL,
  `ImageURL` VARCHAR(255) NULL,
  `Price` DECIMAL NOT NULL,
  `AmountInStock` INT NOT NULL,
  PRIMARY KEY (`Id`),
  UNIQUE INDEX `Id_UNIQUE` (`Id` ASC),
  INDEX `ModelId_FK_idx` (`ModelId` ASC),
  INDEX `ScreenSizeId_FK_idx` (`ScreenSizeId` ASC),
  CONSTRAINT `Goods_ModelId_FK_Goods_Model`
    FOREIGN KEY (`ModelId`)
    REFERENCES `myOnlineShopDB`.`Goods_Model` (`Id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `ScreenSiGoods_zeId_FK_Goods_ScreenSize`
    FOREIGN KEY (`ScreenSizeId`)
    REFERENCES `myOnlineShopDB`.`Goods_ScreenSize` (`Id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `myOnlineShopDB`.`UserRoles`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `myOnlineShopDB`.`UserRoles` (
  `Id` INT NOT NULL AUTO_INCREMENT,
  `Role` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`Id`),
  UNIQUE INDEX `Id_UNIQUE` (`Id` ASC),
  UNIQUE INDEX `Role_UNIQUE` (`Role` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `myOnlineShopDB`.`Users`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `myOnlineShopDB`.`Users` (
  `Id` INT NOT NULL AUTO_INCREMENT,
  `RoleId` INT NOT NULL,
  `Login` VARCHAR(45) NOT NULL,
  `Password` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`Id`),
  UNIQUE INDEX `Id_UNIQUE` (`Id` ASC),
  UNIQUE INDEX `Login_UNIQUE` (`Login` ASC),
  INDEX `RoleId_FK_idx` (`RoleId` ASC),
  CONSTRAINT `Users_RoleId_FK_UserRoles`
    FOREIGN KEY (`RoleId`)
    REFERENCES `myOnlineShopDB`.`UserRoles` (`Id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `myOnlineShopDB`.`Arrival`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `myOnlineShopDB`.`Arrival` (
  `Id` INT NOT NULL AUTO_INCREMENT,
  `GoodsId` INT NOT NULL,
  `UserId` INT NOT NULL,
  `Amount` INT NOT NULL,
  `Price` INT NOT NULL,
  `Time` DATETIME NOT NULL,
  PRIMARY KEY (`Id`),
  UNIQUE INDEX `Id_UNIQUE` (`Id` ASC),
  INDEX `GoodsId_FK_idx` (`GoodsId` ASC),
  INDEX `UserId_FK_idx` (`UserId` ASC),
  CONSTRAINT `Arrival_GoodsId_FK_Goods`
    FOREIGN KEY (`GoodsId`)
    REFERENCES `myOnlineShopDB`.`Goods` (`Id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `Arrival_UserId_FK_Users`
    FOREIGN KEY (`UserId`)
    REFERENCES `myOnlineShopDB`.`Users` (`Id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `myOnlineShopDB`.`Orders_Sales`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `myOnlineShopDB`.`Orders_Sales` (
  `Id` INT NOT NULL AUTO_INCREMENT,
  `GoodsId` INT NOT NULL,
  `UserId` INT NOT NULL,
  `IsSold` TINYINT(1) NOT NULL,
  `Amount` INT NOT NULL,
  `Time` DATETIME NOT NULL,
  PRIMARY KEY (`Id`),
  UNIQUE INDEX `Id_UNIQUE` (`Id` ASC),
  INDEX `UserId_FK_idx` (`UserId` ASC),
  INDEX `GoodsId_FK_idx` (`GoodsId` ASC),
  CONSTRAINT `Orders_Sales_GoodsId_FK_Goods`
    FOREIGN KEY (`GoodsId`)
    REFERENCES `myOnlineShopDB`.`Goods` (`Id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `Orders_Sales_UserId_FK_Users`
    FOREIGN KEY (`UserId`)
    REFERENCES `myOnlineShopDB`.`Users` (`Id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
