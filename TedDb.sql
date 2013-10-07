SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`roles`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`roles` (
  `roles` INT NOT NULL,
  `rolename` VARCHAR(45) NULL,
  PRIMARY KEY (`roles`),
  UNIQUE INDEX `roles_UNIQUE` (`roles` ASC),
  UNIQUE INDEX `rolename_UNIQUE` (`rolename` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`user` (
  `username` VARCHAR(16) NOT NULL,
  `email` VARCHAR(255) NOT NULL,
  `password` VARCHAR(32) NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `lastname` VARCHAR(45) NOT NULL,
  `roles_roles` INT NOT NULL,
  PRIMARY KEY (`username`),
  UNIQUE INDEX `username_UNIQUE` (`username` ASC),
  INDEX `fk_user_roles1_idx` (`roles_roles` ASC),
  CONSTRAINT `fk_user_roles1`
    FOREIGN KEY (`roles_roles`)
    REFERENCES `mydb`.`roles` (`roles`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


-- -----------------------------------------------------
-- Table `mydb`.`provider`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`provider` (
  `provider_name` VARCHAR(16) NOT NULL,
  `address` VARCHAR(32) NOT NULL,
  `social_security_number` BIGINT NOT NULL,
  PRIMARY KEY (`provider_name`));


-- -----------------------------------------------------
-- Table `mydb`.`product`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`product` (
  `product_name` VARCHAR(16) NOT NULL,
  `serial_number` BIGINT NOT NULL,
  `product_description` VARCHAR(32) NOT NULL,
  `type` VARCHAR(45) NULL,
  `weight` INT NULL,
  PRIMARY KEY (`product_name`, `serial_number`));


-- -----------------------------------------------------
-- Table `mydb`.`warehouse`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`warehouse` (
  `warehouse_name` VARCHAR(16) NOT NULL,
  `warehouse_description` VARCHAR(255) NOT NULL,
  `warehouse_address` VARCHAR(32) NOT NULL,
  `state` VARCHAR(32) NOT NULL,
  UNIQUE INDEX `warehouse_name_UNIQUE` (`warehouse_name` ASC),
  PRIMARY KEY (`warehouse_name`));


-- -----------------------------------------------------
-- Table `mydb`.`provider_has_product`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`provider_has_product` (
  `provider_provider_name` VARCHAR(16) NOT NULL,
  `product_product_name` VARCHAR(16) NOT NULL,
  `product_serial_number` BIGINT NOT NULL,
  `price` INT NOT NULL,
  PRIMARY KEY (`provider_provider_name`, `product_product_name`, `product_serial_number`),
  INDEX `fk_provider_has_product_product1_idx` (`product_product_name` ASC, `product_serial_number` ASC),
  INDEX `fk_provider_has_product_provider1_idx` (`provider_provider_name` ASC),
  CONSTRAINT `fk_provider_has_product_provider1`
    FOREIGN KEY (`provider_provider_name`)
    REFERENCES `mydb`.`provider` (`provider_name`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_provider_has_product_product1`
    FOREIGN KEY (`product_product_name` , `product_serial_number`)
    REFERENCES `mydb`.`product` (`product_name` , `serial_number`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


-- -----------------------------------------------------
-- Table `mydb`.`provider_has_product_has_warehouse`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`provider_has_product_has_warehouse` (
  `provider_has_product_provider_provider_name` VARCHAR(16) NOT NULL,
  `provider_has_product_product_product_name` VARCHAR(16) NOT NULL,
  `provider_has_product_product_serial_number` BIGINT NOT NULL,
  `warehouse_warehouse_name` VARCHAR(16) NOT NULL,
  `quantity` INT NULL,
  PRIMARY KEY (`provider_has_product_provider_provider_name`, `provider_has_product_product_product_name`, `provider_has_product_product_serial_number`, `warehouse_warehouse_name`),
  INDEX `fk_provider_has_product_has_warehouse_warehouse1_idx` (`warehouse_warehouse_name` ASC),
  INDEX `fk_provider_has_product_has_warehouse_provider_has_product1_idx` (`provider_has_product_provider_provider_name` ASC, `provider_has_product_product_product_name` ASC, `provider_has_product_product_serial_number` ASC),
  CONSTRAINT `fk_provider_has_product_has_warehouse_provider_has_product1`
    FOREIGN KEY (`provider_has_product_provider_provider_name` , `provider_has_product_product_product_name` , `provider_has_product_product_serial_number`)
    REFERENCES `mydb`.`provider_has_product` (`provider_provider_name` , `product_product_name` , `product_serial_number`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_provider_has_product_has_warehouse_warehouse1`
    FOREIGN KEY (`warehouse_warehouse_name`)
    REFERENCES `mydb`.`warehouse` (`warehouse_name`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


-- -----------------------------------------------------
-- Table `mydb`.`warehouse_activity`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`warehouse_activity` (
  `origin` VARCHAR(45) NULL,
  `destination` VARCHAR(45) NULL,
  `product` VARCHAR(45) NULL,
  `timeT` VARCHAR(45) NULL,
  `actionA` VARCHAR(45) NULL)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
