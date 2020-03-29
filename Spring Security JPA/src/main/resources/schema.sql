drop table IF EXISTS users;

CREATE TABLE users (
	id int4 NOT NULL,
	active bool NOT NULL,
	password varchar(255) NULL,
	user_name varchar(255) NULL,
	roles varchar(255) NULL,
	CONSTRAINT users_pkey PRIMARY KEY (id)
);