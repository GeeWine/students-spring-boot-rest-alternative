CREATE TABLE STUDENT (
  ID        LONG PRIMARY KEY AUTO_INCREMENT,
  AGE       LONG NOT NULL,
  NAME      VARCHAR(64) NOT NULL,
  GRADE     LONG);

CREATE ALIAS FIRST_NAME FOR "hu.geewine.studentsspringbootrest.util.Function.first_name";

CREATE ALIAS LAST_NAME FOR "hu.geewine.studentsspringbootrest.util.Function.last_name";