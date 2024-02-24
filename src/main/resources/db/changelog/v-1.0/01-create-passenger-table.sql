create table passenger (
    id serial not null,
    rating float4,
    email varchar(255),
    full_name varchar(255),
    password varchar(255),
    username varchar(255),
    primary key (id))

GO

INSERT INTO passenger(full_name, username, email, password, rating)
VALUES ('Николай Николаев', 'Ник', 'Kolya@gmail.com', '123457', '5.0');
