create table timelog
(
    id         int identity
        constraint timelog_pk
            primary key nonclustered,
    date       date    default NULL,
    stop       time    default NULL,
    start      time    default NULL,
    task_id    int     default 0
        constraint task_id
            references task
            on delete cascade,
    billable   tinyint default 1,
    user_id    int
        constraint timelog_user_id_fk
            references [user],
    spent_time float
)
go

create unique index timelog_id_uindex
    on timelog (id)
go