create table project
(
    id        int identity
        constraint project_pk
            primary key nonclustered,
    name      varchar(255) default 255,
    client_id int
        constraint id
            references client
            on delete cascade,
    salary    float
)
go

create unique index project_id_uindex
    on project (id)
go