create table if not exists customers(
	id serial PRIMARY KEY,
	name varchar(255) not null,
	email varchar(255) not null,
	phone varchar(255) not null,
	document varchar(255) not null,
	created_at TIMESTAMP not null,
	updated_at TIMESTAMP
);
