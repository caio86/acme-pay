create table  if not exists notifications(
	id serial PRIMARY KEY,
	document VARCHAR(20) not null UNIQUE,
  status VARCHAR(10) not null CHECK (status IN ('ACTIVE', 'SUSPENDED')),
  created_at TIMESTAMP not null,
  updated_at TIMESTAMP
);
