CREATE SCHEMA `dblab_test`;

CREATE TABLE `dblab_test`.`user` (
  `userID` INT NOT NULL,
  `state` INT NOT NULL,
  PRIMARY KEY (`userID`));

CREATE TABLE `dblab_test`.`session1` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `text` VARCHAR(200) NULL,
  `userID` INT NOT NULL,
  PRIMARY KEY (`ID`),
	INDEX `user` (`userID`),
    FOREIGN KEY (`userID`)
	REFERENCES `dblab_test`.`user`(`userID`)
	ON DELETE CASCADE
);
