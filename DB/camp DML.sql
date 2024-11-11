-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema ssafy_camp
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema ssafy_camp
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `ssafy_camp` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `ssafy_camp` ;

-- -----------------------------------------------------
-- Table `ssafy_camp`.`tbl_camp_category`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ssafy_camp`.`tbl_camp_category` ;

CREATE TABLE IF NOT EXISTS `ssafy_camp`.`tbl_camp_category` (
  `category_id` INT NOT NULL,
  `camp_category` VARCHAR(30) NULL DEFAULT NULL,
  PRIMARY KEY (`category_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `ssafy_camp`.`tbl_sido`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ssafy_camp`.`tbl_sido` ;

CREATE TABLE IF NOT EXISTS `ssafy_camp`.`tbl_sido` (
  `sido_id` INT NOT NULL,
  `sido_name` VARCHAR(50) NULL DEFAULT NULL,
  PRIMARY KEY (`sido_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `ssafy_camp`.`tbl_camp`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ssafy_camp`.`tbl_camp` ;

CREATE TABLE IF NOT EXISTS `ssafy_camp`.`tbl_camp` (
  `camp_id` INT NOT NULL,
  `sido_id` INT NOT NULL,
  `category_id` INT NOT NULL,
  `camp_name` VARCHAR(30) NULL DEFAULT NULL,
  `camp_hompage` VARCHAR(200) NULL DEFAULT NULL,
  `camp_explanation` VARCHAR(200) NULL DEFAULT NULL,
  `camp_phone` VARCHAR(15) NULL DEFAULT NULL,
  `camp_character` VARCHAR(1000) NULL DEFAULT NULL,
  PRIMARY KEY (`camp_id`),
  INDEX `FK_tbl_sido_TO_tbl_camp_1` (`sido_id` ASC) VISIBLE,
  INDEX `FK_tbl_camp_category_TO_tbl_camp_1` (`category_id` ASC) VISIBLE,
  CONSTRAINT `FK_tbl_camp_category_TO_tbl_camp_1`
    FOREIGN KEY (`category_id`)
    REFERENCES `ssafy_camp`.`tbl_camp_category` (`category_id`),
  CONSTRAINT `FK_tbl_sido_TO_tbl_camp_1`
    FOREIGN KEY (`sido_id`)
    REFERENCES `ssafy_camp`.`tbl_sido` (`sido_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `ssafy_camp`.`tbl_address`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ssafy_camp`.`tbl_address` ;

CREATE TABLE IF NOT EXISTS `ssafy_camp`.`tbl_address` (
  `camp_id` INT NOT NULL,
  `camp_road_address` VARCHAR(50) NULL DEFAULT NULL,
  `camp_number_address` VARCHAR(50) NULL DEFAULT NULL,
  `longitude` DECIMAL(10,7) NULL DEFAULT NULL,
  `latitude` DECIMAL(10,8) NULL DEFAULT NULL,
  PRIMARY KEY (`camp_id`),
  CONSTRAINT `FK_tbl_camp_TO_tbl_address_1`
    FOREIGN KEY (`camp_id`)
    REFERENCES `ssafy_camp`.`tbl_camp` (`camp_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `ssafy_camp`.`tbl_user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ssafy_camp`.`tbl_user` ;

CREATE TABLE IF NOT EXISTS `ssafy_camp`.`tbl_user` (
  `user_id` VARCHAR(30) NOT NULL,
  `user_password` VARCHAR(30) NULL DEFAULT NULL,
  `user_phone` VARCHAR(30) NULL DEFAULT NULL,
  `user_age` INT NULL DEFAULT NULL,
  `user_email` VARCHAR(30) NULL DEFAULT NULL,
  `user_gender` VARCHAR(2) NULL DEFAULT NULL,
  `user_signup` TIMESTAMP NULL DEFAULT NULL,
  `user_delete` TIMESTAMP NULL DEFAULT NULL,
  `user_mileage` BIGINT NULL DEFAULT NULL,
  `user_url` VARCHAR(255) NULL DEFAULT NULL,
  `user_temp_auth` VARCHAR(30) NULL DEFAULT NULL,
  `user_res` CHAR(13) NULL DEFAULT NULL,
  `user_grade` VARCHAR(30) NULL DEFAULT NULL,
  PRIMARY KEY (`user_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `ssafy_camp`.`tbl_boards`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ssafy_camp`.`tbl_boards` ;

CREATE TABLE IF NOT EXISTS `ssafy_camp`.`tbl_boards` (
  `board_id` INT NOT NULL,
  `board_title` VARCHAR(100) NULL DEFAULT NULL,
  `board_content` TEXT NULL DEFAULT NULL,
  `board_created_at` TIMESTAMP NULL DEFAULT NULL,
  `board_view` INT NULL DEFAULT NULL,
  `img_url` VARCHAR(255) NULL DEFAULT NULL,
  `user_id` VARCHAR(30) NOT NULL,
  PRIMARY KEY (`board_id`),
  INDEX `FK_tbl_user_TO_tbl_boards_1` (`user_id` ASC) VISIBLE,
  CONSTRAINT `FK_tbl_user_TO_tbl_boards_1`
    FOREIGN KEY (`user_id`)
    REFERENCES `ssafy_camp`.`tbl_user` (`user_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `ssafy_camp`.`tbl_camp_product`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ssafy_camp`.`tbl_camp_product` ;

CREATE TABLE IF NOT EXISTS `ssafy_camp`.`tbl_camp_product` (
  `product_id` INT NOT NULL,
  `product_name` VARCHAR(20) NULL DEFAULT NULL,
  `product_category` INT NULL DEFAULT NULL,
  PRIMARY KEY (`product_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `ssafy_camp`.`tbl_camp_shop`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ssafy_camp`.`tbl_camp_shop` ;

CREATE TABLE IF NOT EXISTS `ssafy_camp`.`tbl_camp_shop` (
  `shop_id` INT NOT NULL,
  `shop_title` VARCHAR(30) NULL DEFAULT NULL,
  `shop_date` TIMESTAMP NULL DEFAULT NULL,
  `shop_view_count` INT NULL DEFAULT NULL,
  `shop_status` VARCHAR(30) NULL DEFAULT NULL,
  `shop_img` VARCHAR(255) NULL DEFAULT NULL,
  `shop_price` BIGINT NULL DEFAULT NULL,
  `shope_comment` VARCHAR(1000) NULL DEFAULT NULL,
  `Field` VARCHAR(255) NULL DEFAULT NULL,
  `product_id` INT NOT NULL,
  `user_id` VARCHAR(30) NOT NULL,
  PRIMARY KEY (`shop_id`),
  INDEX `FK_tbl_camp_product_TO_tbl_camp_shop_1` (`product_id` ASC) VISIBLE,
  INDEX `FK_tbl_user_TO_tbl_camp_shop_1` (`user_id` ASC) VISIBLE,
  CONSTRAINT `FK_tbl_camp_product_TO_tbl_camp_shop_1`
    FOREIGN KEY (`product_id`)
    REFERENCES `ssafy_camp`.`tbl_camp_product` (`product_id`),
  CONSTRAINT `FK_tbl_user_TO_tbl_camp_shop_1`
    FOREIGN KEY (`user_id`)
    REFERENCES `ssafy_camp`.`tbl_user` (`user_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `ssafy_camp`.`tbl_facilities`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ssafy_camp`.`tbl_facilities` ;

CREATE TABLE IF NOT EXISTS `ssafy_camp`.`tbl_facilities` (
  `camp_id2` INT NOT NULL,
  `camp_id` INT NOT NULL,
  `f_elec` TINYINT(1) NULL DEFAULT NULL,
  `f_hot_water` TINYINT(1) NULL DEFAULT NULL,
  `f_wifi` TINYINT(1) NULL DEFAULT NULL,
  `f_firewood` TINYINT(1) NULL DEFAULT NULL,
  `f_trails` TINYINT(1) NULL DEFAULT NULL,
  `f_swimming` TINYINT(1) NULL DEFAULT NULL,
  `f_playground` TINYINT(1) NULL DEFAULT NULL,
  `f_market` TINYINT(1) NULL DEFAULT NULL,
  `f_toilerts` INT NULL DEFAULT NULL,
  `f_showers` INT NULL DEFAULT NULL,
  `f_sinks` INT NULL DEFAULT NULL,
  `f_fire_extinguishers` INT NULL DEFAULT NULL,
  PRIMARY KEY (`camp_id2`),
  CONSTRAINT `FK_tbl_camp_TO_tbl_facilities_1`
    FOREIGN KEY (`camp_id2`)
    REFERENCES `ssafy_camp`.`tbl_camp` (`camp_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `ssafy_camp`.`tbl_glamping`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ssafy_camp`.`tbl_glamping` ;

CREATE TABLE IF NOT EXISTS `ssafy_camp`.`tbl_glamping` (
  `camp_id2` INT NOT NULL,
  `camp_id` INT NOT NULL,
  `g_bed` TINYINT(1) NULL DEFAULT NULL,
  `g_tv` TINYINT(1) NULL DEFAULT NULL,
  `g_fridge` TINYINT(1) NULL DEFAULT NULL,
  `g_internet` TINYINT(1) NULL DEFAULT NULL,
  `g_toilet` TINYINT(1) NULL DEFAULT NULL,
  `g_aricon` TINYINT(1) NULL DEFAULT NULL,
  `g_heater` TINYINT(1) NULL DEFAULT NULL,
  `g_cook_tool` TINYINT(1) NULL DEFAULT NULL,
  PRIMARY KEY (`camp_id2`),
  CONSTRAINT `FK_tbl_camp_TO_tbl_glamping_1`
    FOREIGN KEY (`camp_id2`)
    REFERENCES `ssafy_camp`.`tbl_camp` (`camp_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `ssafy_camp`.`tbl_near_facilities`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ssafy_camp`.`tbl_near_facilities` ;

CREATE TABLE IF NOT EXISTS `ssafy_camp`.`tbl_near_facilities` (
  `camp_id2` INT NOT NULL,
  `camp_id` INT NOT NULL,
  `n_fishing` TINYINT(1) NULL DEFAULT NULL,
  `n_hiking` TINYINT(1) NULL DEFAULT NULL,
  `n_beach` TINYINT(1) NULL DEFAULT NULL,
  `n_water_sports` TINYINT(1) NULL DEFAULT NULL,
  `n_valley` TINYINT(1) NULL DEFAULT NULL,
  `n_river` TINYINT(1) NULL DEFAULT NULL,
  `n_pool` TINYINT(1) NULL DEFAULT NULL,
  `n_youth` TINYINT(1) NULL DEFAULT NULL,
  `n_farm` TINYINT(1) NULL DEFAULT NULL,
  `n_kids` TINYINT(1) NULL DEFAULT NULL,
  PRIMARY KEY (`camp_id2`),
  CONSTRAINT `FK_tbl_camp_TO_tbl_near_facilities_1`
    FOREIGN KEY (`camp_id2`)
    REFERENCES `ssafy_camp`.`tbl_camp` (`camp_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `ssafy_camp`.`tbl_op`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ssafy_camp`.`tbl_op` ;

CREATE TABLE IF NOT EXISTS `ssafy_camp`.`tbl_op` (
  `camp_id2` INT NOT NULL,
  `camp_id` INT NOT NULL,
  `o_weekday` TINYINT(1) NULL DEFAULT NULL,
  `o_weekend` TINYINT(1) NULL DEFAULT NULL,
  `o_spring` TINYINT(1) NULL DEFAULT NULL,
  `o_summber` TINYINT(1) NULL DEFAULT NULL,
  `o_fall` TINYINT(1) NULL DEFAULT NULL,
  `o_winter` TINYINT(1) NULL DEFAULT NULL,
  PRIMARY KEY (`camp_id2`),
  CONSTRAINT `FK_tbl_camp_TO_tbl_op_1`
    FOREIGN KEY (`camp_id2`)
    REFERENCES `ssafy_camp`.`tbl_camp` (`camp_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `ssafy_camp`.`tbl_res`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ssafy_camp`.`tbl_res` ;

CREATE TABLE IF NOT EXISTS `ssafy_camp`.`tbl_res` (
  `res_id` INT NOT NULL,
  `res_date` TIMESTAMP NULL DEFAULT NULL,
  `res_status` VARCHAR(30) NULL DEFAULT NULL,
  `user_id2` VARCHAR(30) NOT NULL,
  `camp_id` INT NOT NULL,
  PRIMARY KEY (`res_id`),
  INDEX `FK_tbl_user_TO_tbl_res_1` (`user_id2` ASC) VISIBLE,
  INDEX `FK_tbl_camp_TO_tbl_res_1` (`camp_id` ASC) VISIBLE,
  CONSTRAINT `FK_tbl_camp_TO_tbl_res_1`
    FOREIGN KEY (`camp_id`)
    REFERENCES `ssafy_camp`.`tbl_camp` (`camp_id`),
  CONSTRAINT `FK_tbl_user_TO_tbl_res_1`
    FOREIGN KEY (`user_id2`)
    REFERENCES `ssafy_camp`.`tbl_user` (`user_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `ssafy_camp`.`tbl_review`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ssafy_camp`.`tbl_review` ;

CREATE TABLE IF NOT EXISTS `ssafy_camp`.`tbl_review` (
  `review_id` INT NOT NULL,
  `camp_rate` INT NULL DEFAULT NULL,
  `comment` TEXT NULL DEFAULT NULL,
  `created_at` TIMESTAMP NULL DEFAULT NULL,
  `updated_at` TIMESTAMP NULL DEFAULT NULL,
  `user_id` VARCHAR(30) NOT NULL,
  `camp_id` INT NOT NULL,
  PRIMARY KEY (`review_id`),
  INDEX `FK_tbl_user_TO_tbl_review_1` (`user_id` ASC) VISIBLE,
  INDEX `FK_tbl_camp_TO_tbl_review_1` (`camp_id` ASC) VISIBLE,
  CONSTRAINT `FK_tbl_camp_TO_tbl_review_1`
    FOREIGN KEY (`camp_id`)
    REFERENCES `ssafy_camp`.`tbl_camp` (`camp_id`),
  CONSTRAINT `FK_tbl_user_TO_tbl_review_1`
    FOREIGN KEY (`user_id`)
    REFERENCES `ssafy_camp`.`tbl_user` (`user_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
