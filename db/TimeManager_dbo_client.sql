create table client
(
    id     int identity
        constraint client_pk
            primary key nonclustered,
    name   varchar(255) default 255,
    salary float
)
go

create unique index client_id_uindex
    on client (id)
go