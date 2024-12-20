

CREATE TABLE projects (
  id BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
   name VARCHAR(255) NOT NULL,
   description VARCHAR(255) NOT NULL,
   create_date date NOT NULL,
   CONSTRAINT pk_projects PRIMARY KEY (id)
);

CREATE TABLE users (
  id BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
   user_name VARCHAR(255) NOT NULL,
   password VARCHAR(255) NOT NULL,
   email VARCHAR(255) NOT NULL,
   role VARCHAR(255) NOT NULL,
   CONSTRAINT pk_users PRIMARY KEY (id)
);