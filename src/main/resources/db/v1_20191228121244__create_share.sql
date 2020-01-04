CREATE TABLE `content_center`.`share`(
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `user_id` INT(11),
  `title` VARCHAR(64),
  `create_time` DATETIME,
  `update_time` DATETIME,
  `is_original` BIT(11),
  `author` VARCHAR(64),
  `cover` VARCHAR(64),
  `summary` VARCHAR(64),
  `price` INT(11),
  `download_url` VARCHAR(128),
  `buy_count` INT(11),
  `show_flag` BIT(11),
  `audit_status` VARCHAR(64),
  `reason` VARCHAR(128),
  PRIMARY KEY (`id`)
);
