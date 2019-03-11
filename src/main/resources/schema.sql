DROP TABLE IF EXISTS USERS_ROLES;
DROP TABLE IF EXISTS ROLES;
DROP TABLE IF EXISTS USERS;
DROP TABLE IF EXISTS BOOKS;
DROP TABLE IF EXISTS GENRES;

CREATE TABLE  IF NOT EXISTS GENRES (
  ID    			INT PRIMARY KEY AUTO_INCREMENT,
  NAME		    VARCHAR(50)
);

CREATE TABLE IF NOT EXISTS BOOKS (
  ID				  INT PRIMARY KEY AUTO_INCREMENT,
  AUTHOR      VARCHAR (50),
  NAME		    VARCHAR(50),
  GENRE_ID		INT
);

CREATE TABLE IF NOT EXISTS USERS (
  ID          INT PRIMARY KEY  AUTO_INCREMENT,
  NAME        VARCHAR(50),
  SURNAME     VARCHAR(50),
  USER_NAME   VARCHAR(50),
  PASSWORD    VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS ROLES (
  ID          INT PRIMARY KEY  AUTO_INCREMENT,
  ROLE_NAME   VARCHAR(50)
);

CREATE TABLE IF NOT EXISTS USERS_ROLES (
  ID				  INT PRIMARY KEY AUTO_INCREMENT,
  USER_ID     INT,
  ROLE_ID     INT
);

ALTER TABLE BOOKS ADD FOREIGN KEY (GENRE_ID) REFERENCES GENRES (ID) ON DELETE CASCADE;
ALTER TABLE USERS_ROLES ADD FOREIGN KEY (USER_ID) REFERENCES USERS (ID);
ALTER TABLE USERS_ROLES ADD FOREIGN KEY (ROLE_ID) REFERENCES ROLES (ID);

CREATE TABLE IF NOT EXISTS PERSISTENT_LOGINS (
  USERNAME    VARCHAR(100) NOT NULL,
  SERIES      VARCHAR(64) PRIMARY KEY,
  TOKEN       VARCHAR(64) NOT NULL,
  LAST_USED   TIMESTAMP NOT NULL
);