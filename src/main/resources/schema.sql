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
    title       varchar(16),
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
    id              bigint(19) not null primary key,
    number          bigint(19) not null,
    computer_number int                 default null,
    create_time     datetime   not null default current_timestamp,
    update_time     datetime   not null default current_timestamp on update current_timestamp,
    version         int                 default 0,
    index (number)
);


create table if not exists course
(
    id             bigint(19)  not null primary key,
    cid            bigint(19)  not null,
    name           varchar(32) not null,
    teacher_id     varchar(32) not null,
    student_number int         not null,
    hours          int                  default 1,
    create_time    datetime    not null default current_timestamp,
    update_time    datetime    not null default current_timestamp on update current_timestamp,
    version        int                  default 0,
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
create table if not exists schedule_course
(
    id          bigint(19) not null primary key,
    lab_id      bigint(19) not null,
    cid         bigint(19),
    week        int,
    day         int,
    section     int,
    state       boolean             default true,
    version     int                 default 0,
    name        varchar(32),
    teacher_id  varchar(32),
    create_time datetime   not null default current_timestamp,
    update_time datetime   not null default current_timestamp on update current_timestamp,
    index (lab_id)
);
create table if not exists notice
(
    id          bigint(19)  not null primary key,
    title       varchar(45) not null,
    context     varchar(2000),
    create_time datetime    not null default current_timestamp,
    update_time datetime    not null default current_timestamp on update current_timestamp,
    version  int                  default 0,
    index (id)
);
CREATE TABLE machine (
                           `mid` int NOT NULL,
                           `mname` varchar(32) NOT NULL,
                           `mnumber` varchar(8) NOT NULL,
                           `mtype` varchar(16) NOT NULL,
                           `mstate` varchar(8) NOT NULL,
                           `muser` varchar(12) NOT NULL,
                           `mplace` varchar(24) NOT NULL,
                           create_time datetime    not null default current_timestamp,
                           update_time datetime    not null default current_timestamp on update current_timestamp,
                           version  int                  default 0,
                           PRIMARY KEY (`mid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
# )


