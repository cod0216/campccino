DROP TABLE IF EXISTS `tbl_camp`;

CREATE TABLE `tbl_camp` (
	`camp_id`	INT	NOT NULL,
	`sido_id`	INT	NOT NULL,
	`category_id`	INT	NOT NULL,
	`camp_name`	VARCHAR(30)	NULL,
	`camp_hompage`	VARCHAR(200)	NULL,
	`camp_explanation`	VARCHAR(1000)	NULL,
	`camp_phone`	VARCHAR(15)	NULL,
	`camp_character`	VARCHAR(1000)	NULL
);

DROP TABLE IF EXISTS `tbl_address`;

CREATE TABLE `tbl_address` (
	`camp_id`	INT	NOT NULL,
	`camp_road_address`	VARCHAR(50)	NULL,
	`camp_number_address`	VARCHAR(50)	NULL,
	`longitude`	decimal(10,7)	NULL,
	`latitude`	decimal(10,8)	NULL
);

DROP TABLE IF EXISTS `tbl_sido`;

CREATE TABLE `tbl_sido` (
	`sido_id`	INT	NOT NULL,
	`sido_name`	VARCHAR(50)	NULL
);

DROP TABLE IF EXISTS `tbl_camp_category`;

CREATE TABLE `tbl_camp_category` (
	`category_id`	INT	NOT NULL,
	`camp_category`	VARCHAR(30)	NULL
);

DROP TABLE IF EXISTS `tbl_camp_product`;

CREATE TABLE `tbl_camp_product` (
	`product_id`	INT	NOT NULL,
	`product_name`	VARCHAR(20)	NULL,
	`product_category`	INT	NULL
);

DROP TABLE IF EXISTS `tbl_camp_shop`;

CREATE TABLE `tbl_camp_shop` (
	`shop_id`	INT	NOT NULL,
	`shop_title`	VARCHAR(30)	NULL,
	`shop_date`	TIMESTAMP	NULL,
	`shop_view_count`	INT	NULL,
	`shop_status`	VARCHAR(30)	NULL,
	`shop_img`	VARCHAR(255)	NULL,
	`shop_price`	BIGINT	NULL,
	`shope_comment`	VARCHAR(1000)	NULL,
	`Field`	VARCHAR(255)	NULL,
	`product_id`	INT	NOT NULL,
	`user_id`	VARCHAR(30)	NOT NULL
);

DROP TABLE IF EXISTS `tbl_user`;

CREATE TABLE `tbl_user` (
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
  `user_grade` VARCHAR(30) NULL DEFAULT NULL
);

DROP TABLE IF EXISTS `tbl_review`;

CREATE TABLE `tbl_review` (
	`review_id`	INT	NOT NULL,
	`camp_rate`	INT	NULL,
	`comment`	TEXT	NULL,
	`created_at`	TIMESTAMP	NULL,
	`updated_at`	TIMESTAMP	NULL,
	`user_id`	VARCHAR(30)	NOT NULL,
	`camp_id`	INT	NOT NULL
);

DROP TABLE IF EXISTS `tbl_boards`;

CREATE TABLE `tbl_boards` (
	`board_id`	INT	NOT NULL,
	`board_title`	VARCHAR(100)	NULL,
	`board_content`	TEXT	NULL,
	`board_created_at`	TIMESTAMP	NULL,
	`board_view`	INT	NULL,
	`img_url`	VARCHAR(255)	NULL,
	`user_id`	VARCHAR(30)	NOT NULL
);

DROP TABLE IF EXISTS `tbl_res`;

CREATE TABLE `tbl_res` (
	`res_id`	INT	NOT NULL,
	`res_date`	TIMESTAMP	NULL,
	`res_status`	VARCHAR(30)	NULL,
	`user_id`	VARCHAR(30)	NOT NULL,
	`camp_id`	INT	NOT NULL
);

DROP TABLE IF EXISTS `tbl_op`;

CREATE TABLE `tbl_op` (
	`camp_id`	INT	NOT NULL,
	`o_weekday`	BOOLEAN	NULL,
	`o_weekend`	BOOLEAN	NULL,
	`o_spring`	BOOLEAN	NULL,
	`o_summer`	BOOLEAN	NULL,
	`o_fall`	BOOLEAN	NULL,
	`o_winter`	BOOLEAN	NULL
);

DROP TABLE IF EXISTS `tbl_facilities`;

CREATE TABLE `tbl_facilities` (
	`camp_id`	INT	NOT NULL,
	`f_elec`	BOOLEAN	NULL,
	`f_hot_water`	BOOLEAN	NULL,
	`f_wifi`	BOOLEAN	NULL,
	`f_firewood`	BOOLEAN	NULL,
	`f_trails`	BOOLEAN	NULL,
	`f_swimming`	BOOLEAN	NULL,
	`f_playground`	BOOLEAN	NULL,
	`f_market`	BOOLEAN	NULL,
	`f_toilerts`	INT	NULL,
	`f_showers`	INT	NULL,
	`f_sinks`	INT	NULL,
	`f_fire_extinguishers`	INT	NULL
);

DROP TABLE IF EXISTS `tbl_near_facilities`;

CREATE TABLE `tbl_near_facilities` (
	`camp_id`	INT	NOT NULL,
	`n_fishing`	BOOLEAN	NULL,
	`n_hiking`	BOOLEAN	NULL,
	`n_beach`	BOOLEAN	NULL,
	`n_water_sports`	BOOLEAN	NULL,
	`n_valley`	BOOLEAN	NULL,
	`n_river`	BOOLEAN	NULL,
	`n_pool`	BOOLEAN	NULL,
	`n_youth`	BOOLEAN	NULL,
	`n_farm`	BOOLEAN	NULL,
	`n_kids`	BOOLEAN	NULL
);

DROP TABLE IF EXISTS `tbl_glamping`;

CREATE TABLE `tbl_glamping` (
	`camp_id`	INT	NOT NULL,
	`g_bed`	BOOLEAN	NULL,
	`g_tv`	BOOLEAN	NULL,
	`g_fridge`	BOOLEAN	NULL,
	`g_internet`	BOOLEAN	NULL,
	`g_toilet`	BOOLEAN	NULL,
	`g_aircon`	BOOLEAN	NULL,
	`g_heater`	BOOLEAN	NULL,
	`g_cook_tool`	BOOLEAN	NULL
);

ALTER TABLE `tbl_camp` ADD CONSTRAINT `PK_TBL_CAMP` PRIMARY KEY (
	`camp_id`
);

ALTER TABLE `tbl_address` ADD CONSTRAINT `PK_TBL_ADDRESS` PRIMARY KEY (
	`camp_id`
);

ALTER TABLE `tbl_sido` ADD CONSTRAINT `PK_TBL_SIDO` PRIMARY KEY (
	`sido_id`
);

ALTER TABLE `tbl_camp_category` ADD CONSTRAINT `PK_TBL_CAMP_CATEGORY` PRIMARY KEY (
	`category_id`
);

ALTER TABLE `tbl_camp_product` ADD CONSTRAINT `PK_TBL_CAMP_PRODUCT` PRIMARY KEY (
	`product_id`
);

ALTER TABLE `tbl_camp_shop` ADD CONSTRAINT `PK_TBL_CAMP_SHOP` PRIMARY KEY (
	`shop_id`
);

ALTER TABLE `tbl_user` ADD CONSTRAINT `PK_TBL_USER` PRIMARY KEY (
	`user_id`
);

ALTER TABLE `tbl_review` ADD CONSTRAINT `PK_TBL_REVIEW` PRIMARY KEY (
	`review_id`
);

ALTER TABLE `tbl_boards` ADD CONSTRAINT `PK_TBL_BOARDS` PRIMARY KEY (
	`board_id`
);

ALTER TABLE `tbl_res` ADD CONSTRAINT `PK_TBL_RES` PRIMARY KEY (
	`res_id`
);

ALTER TABLE `tbl_op` ADD CONSTRAINT `PK_TBL_OP` PRIMARY KEY (
	`camp_id`
);

ALTER TABLE `tbl_facilities` ADD CONSTRAINT `PK_TBL_FACILITIES` PRIMARY KEY (
	`camp_id`
);

ALTER TABLE `tbl_near_facilities` ADD CONSTRAINT `PK_TBL_NEAR_FACILITIES` PRIMARY KEY (
	`camp_id`
);

ALTER TABLE `tbl_glamping` ADD CONSTRAINT `PK_TBL_GLAMPING` PRIMARY KEY (
	`camp_id`
);

ALTER TABLE `tbl_address` ADD CONSTRAINT `FK_tbl_camp_TO_tbl_address_1` FOREIGN KEY (
	`camp_id`
)
REFERENCES `tbl_camp` (
	`camp_id`
);

ALTER TABLE `tbl_op` ADD CONSTRAINT `FK_tbl_camp_TO_tbl_op_1` FOREIGN KEY (
	`camp_id`
)
REFERENCES `tbl_camp` (
	`camp_id`
);

ALTER TABLE `tbl_facilities` ADD CONSTRAINT `FK_tbl_camp_TO_tbl_facilities_1` FOREIGN KEY (
	`camp_id`
)
REFERENCES `tbl_camp` (
	`camp_id`
);

ALTER TABLE `tbl_near_facilities` ADD CONSTRAINT `FK_tbl_camp_TO_tbl_near_facilities_1` FOREIGN KEY (
	`camp_id`
)
REFERENCES `tbl_camp` (
	`camp_id`
);

ALTER TABLE `tbl_glamping` ADD CONSTRAINT `FK_tbl_camp_TO_tbl_glamping_1` FOREIGN KEY (
	`camp_id`
)

REFERENCES `tbl_camp` (
	`camp_id`

);