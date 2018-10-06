create table if not exists roles
(
	id serial not null
		constraint roles_pkey
			primary key,
	role varchar(30)
)
;

alter table roles owner to postgres
;

create table if not exists users
(
	id serial not null
		constraint users_pkey
			primary key,
	username varchar(30) not null,
	password varchar not null,
	roleid integer not null
		constraint users_roles_id_fk
			references roles,
	chiefid integer not null
		constraint users_users_id_fk
			references users,
	salary integer not null
)
;

alter table users owner to postgres
;

create unique index if not exists users_id_uindex
	on users (id)
;

create unique index if not exists users_username_uindex
	on users (username)
;

create unique index if not exists roles_id_uindex
	on roles (id)
;

