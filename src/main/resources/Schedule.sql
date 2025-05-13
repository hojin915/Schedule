Use Schedule;

CREATE TABLE `Schedule` (
	`todoId`    INT	NOT NULL,
	`todo`	VARCHAR(200)	NULL,
	`writerName`	VARCHAR(30)	NULL,
	`createdAt`	DATETIME	NULL,
	`updatedAt`	DATETIME	NULL,
	`password`	VARCHAR(30)	NOT NULL,
    `userId`    INT NULL
);

ALTER TABLE `Schedule` ADD CONSTRAINT `PK_SCHEDULE` PRIMARY KEY (
	`todoId`
);

ALTER TABLE `Schedule` MODIFY COLUMN todoId INT NOT NULL AUTO_INCREMENT;

CREATE TABLE `User` (
    `userId`    INT NOT NULL ,
    `userName`  VARCHAR(30) NULL,
    `email` VARCHAR(60)     NULL,
    `createdAt` DATETIME    NULL,
    `updatedAt` DATETIME    NULL
);

ALTER TABLE `User` ADD CONSTRAINT `PK_USER` PRIMARY KEY (
    `userId`
);

ALTER TABLE `User` MODIFY COLUMN userId INT NOT NULL AUTO_INCREMENT;

ALTER TABLE `Schedule` ADD CONSTRAINT `FK_SCHEDULE_USER` FOREIGN KEY (`userId`) REFERENCES `User`(`userId`);