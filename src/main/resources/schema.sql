create table if not exists user
(
    id          bigint(19)  not null primary key,
    name        varchar(32) not null,
    number      varchar(32) not null,
    password    varchar(60) not null,
    role        int         not null default 1,
    create_time datetime    not null default current_timestamp,
    update_time datetime    not null default current_timestamp on update current_timestamp,
    version     int                  default 0,
    unique (number),
    index (number)
);

create table if not exists teacher
(
    id          bigint(19) not null primary key,
    post        varchar(16),
    college     varchar(32),
    create_time datetime   not null default current_timestamp,
    update_time datetime   not null default current_timestamp on
        update current_timestamp,
    unique (id),
    index (id)
);

create table if not exists student
(
    id          bigint(19) not null primary key,
    clazz       varchar(16),
    college     varchar(32),
    create_time datetime   not null default current_timestamp,
    update_time datetime   not null default current_timestamp on update current_timestamp,
    unique (id),
    index (id)
);
create table if not exists laboratory
(
    number          int      not null primary key,
    computer_number int               default null,
    create_time     datetime not null default current_timestamp,
    update_time     datetime not null default current_timestamp on update current_timestamp,
    version         int               default 0,
    index (number)

);
create table if not exists lab_course
(
    id          bigint(19) not null,
    lab_id      bigint(19) not null,
    cid         bigint(19) not null,
    week        varchar(3),
    day         varchar(3),
    section       varchar(3),
    status      boolean            default false,
    create_time datetime   not null default current_timestamp,
    update_time datetime   not null default current_timestamp on update current_timestamp,
    version     int                 default 0,
    index (id),
    index (lab_id),
    index (cid)
);
create table if not exists laboratory_student
(
    id          bigint(19)  not null primary key,
    name        varchar(32) not null,
    student_id  bigint(19)  not null,
    teacher_id  bigint(19)  not null,
    create_time datetime    not null default current_timestamp,
    update_time datetime    not null default current_timestamp on update current_timestamp,
    version     int                  default 0,
    index (id),
    index (student_id),
    index (teacher_id)
);
create table if not exists course
(
    id          bigint(19)  not null primary key,
    name        varchar(32) not null,
    teacher_id  bigint(19)  not null,
    create_time datetime    not null default current_timestamp,
    update_time datetime    not null default current_timestamp on update current_timestamp,
    version     int                  default 0,
    index (id),
    index (teacher_id)
);
create table if not exists student_course
(
    id          bigint(19) not null primary key,
    student_id  bigint(19) not null,
    course_id   bigint(19) not null,
    create_time datetime   not null default current_timestamp,
    update_time datetime   not null default current_timestamp on update current_timestamp,
    version     int                 default 0,
    index (course_id),
    index (student_id)
);
create table if not exists notice
(
    id          bigint(19)  not null primary key,
    title       varchar(45) not null,
    context     varchar(2000),
    create_time datetime    not null default current_timestamp,
    update_time datetime    not null default current_timestamp on update current_timestamp,
    version     int                  default 0,
    index (id)
);
