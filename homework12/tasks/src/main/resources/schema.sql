DROP TABLE IF EXISTS tasks;
CREATE TABLE tasks (
  id BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
  name VARCHAR(255) NOT NULL,
  description VARCHAR(255) NOT NULL);

DROP TABLE IF EXISTS urgenttasks;
CREATE TABLE urgenttasks (
  id BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
  name VARCHAR(255) NOT NULL,
  description VARCHAR(255) NOT NULL);







