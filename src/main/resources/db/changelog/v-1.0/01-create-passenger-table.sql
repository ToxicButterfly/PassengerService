create table passenger (
    id serial not null,
    rating float4,
    email varchar(255),
    full_name varchar(255),
    username varchar(255),
    primary key (id))
