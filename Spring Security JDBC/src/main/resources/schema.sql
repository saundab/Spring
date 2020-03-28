drop table IF EXISTS users;
drop table IF EXISTS authorities;

CREATE TABLE users (
	id int4 NOT NULL,
	active bool NOT NULL,
	password varchar(255) NULL,
	user_name varchar(255) NULL,
	CONSTRAINT users_pkey PRIMARY KEY (id)
);

CREATE TABLE authorities (
	id int4 NOT NULL,
	authority varchar(255) NULL,
	user_name varchar(255) NULL,
	CONSTRAINT authorities_pkey PRIMARY KEY (id)
);
