
-- -----------------------------------------------------
-- Schema tweetapp
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `tweetapp` ;
USE `tweetapp` ;

-- -----------------------------------------------------
-- Table `tweetapp`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `tweetapp`.`user` (
  `us_first_name` VARCHAR(45) NOT NULL,
  `us_last_name` VARCHAR(45) NULL,
  `us_email_id` VARCHAR(45) NOT NULL,
  `us_password` VARCHAR(45) NOT NULL,
  `us_gender` VARCHAR(45) NOT NULL,
  `us_date_of_birth` DATE NULL,
  `us_isvalid` TINYINT NULL,
  UNIQUE INDEX `email_id_UNIQUE` (`us_email_id` ASC) VISIBLE,
  PRIMARY KEY (`us_email_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `tweetapp`.`tweet`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `tweetapp`.`tweet` (
  `tweet_id` INT AUTO_INCREMENT,
  `tweet_msg` VARCHAR(256) NOT NULL,
  `user_email_id` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`tweet_id`,`user_email_id`),
  INDEX `fk_tweet_user_idx` (`user_email_id` ASC) VISIBLE,
  CONSTRAINT `fk_tweet_user`
    FOREIGN KEY (`user_email_id`)
    REFERENCES `tweetapp`.`user` (`us_email_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

INSERT INTO user  (`us_first_name`, `us_last_name`, `us_gender`, `us_date_of_birth`, `us_email_id`, `us_password`, `us_isvalid`)
 VALUES ('Roshini', 'Gopalakrishnan', 'Female', '1997-01-07', 'roshini@gmail.com', 'password', '1'),
 ('Helen', 'Mathew', 'Female', '1993-08-07', 'helen@gmail.com', 'password', '1'),
 ('Anu', 'Micheal', 'Female', '1947-09-26', 'anu@gmail.com', 'anu', '0');
 
 INSERT INTO tweet(tweet_msg,user_email_id)values('Hello welcome to tweet app!','roshini@gmail.com'),
 ('trending tweets','anu@gmail.com'),
 ('congrats!! @kiran','helen@gmail.com');
