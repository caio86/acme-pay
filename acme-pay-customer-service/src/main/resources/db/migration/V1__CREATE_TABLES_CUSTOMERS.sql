create table if not exists customers(
	id serial PRIMARY KEY,
	name varchar(50) not null,
	email varchar(100) not null,
	phone varchar(20) not null,
	document varchar(20) not null
);
