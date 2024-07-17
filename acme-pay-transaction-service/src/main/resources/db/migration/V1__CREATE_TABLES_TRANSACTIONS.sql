create table  if not exists transactions(
	id serial PRIMARY KEY,
	data_transaction TIMESTAMP not null,
	source_account INTEGER not null,
	destination_account INTEGER not null,
	amount DECIMAL(10,2) not null
);
