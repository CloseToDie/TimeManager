create table [user]
(
    id       int identity
        constraint user_pk
            primary key nonclustered,
    username varchar(255),
    password varchar(255),
    isAdmin  tinyint      default 0 not null,
    email    varchar(255) default 255,
    salt     varbinary(255)
)
go

create unique index user_id_uindex
    on [user] (id)
go
