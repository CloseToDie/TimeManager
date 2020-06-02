create table task
(
    id          int identity
        constraint task_pk
            primary key nonclustered,
    description varchar(255),
    project_id  int
        constraint project_id
            references project
            on delete cascade
)
go

create unique index task_id_uindex
    on task (id)
go