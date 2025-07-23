create sequence menu_id_seq start with 1 increment by 50;

create table menu
(
    id bigint default nextval('menu_id_seq') not null,
    code        text not null unique,
    name        text not null,
    description text,
    price       numeric not null,
    available   boolean default true not null,
    current_stock integer default 0 not null,
    created_at  timestamp default now() not null,
    updated_at  timestamp default now() not null,
    version     bigint default 0 not null,
    primary key (id)
);
