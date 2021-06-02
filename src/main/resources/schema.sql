create table if not exists user
(
    id bigint(19) not null primary key ,
    name varchar(8) not null ,
    number varchar(12) not null ,
    password varchar(65) not null ,
    role int not null default 1,
    create_time datetime not null default current_timestamp,
    update_time datetime not null default current_timestamp on update current_timestamp,
    version int default 0,
    unique (number),
    index (number)
);

create table if not exists teacher
(
    id          bigint(19) not null primary key,
    phone       bigint(11),
    email       varchar(45),
    post        varchar(32),
    college     varchar(45),
    course      varchar(45),
    create_time datetime   not null default current_timestamp,
    update_time datetime   not null default current_timestamp on update current_timestamp,
    unique (id),
    index (id)
);

create table if not exists student
(
    id            bigint(19) not null primary key,
    clazz        varchar(8),
    college       varchar(45),
    create_time   datetime   not null default current_timestamp,
    update_time   datetime   not null default current_timestamp on update current_timestamp,
    unique (id),
    index (id)
);
create table if not exists laboratory
(
    id                bigint(19) not null primary key,
    name              varchar(32),
    teacher_id        bigint(19)          default null,
    laboratory_msg    int default 0,
    update_time       datetime   not null default current_timestamp on update current_timestamp,
    version int default 0,
    index (id),
    index (laboratory_msg),
    index (teacher_id)
);
create table if not exists laboratory_student
(
    id          bigint(19)  not null primary key,
    name        varchar(45) not null,
    student_id  bigint(19)  not null,
    laboratory_id bigint (19) not null ,
    create_time datetime    not null default current_timestamp,
    update_time datetime    not null default current_timestamp on update current_timestamp,
    version int default 0,
    index (id),
    index (student_id),
    index (laboratory_id)
);
create table if not exists course
(
    id          bigint(19)  not null primary key,
    name        varchar(45) not null,
    teacher_id  bigint(19)  not null,
    create_time datetime    not null default current_timestamp,
    update_time datetime    not null default current_timestamp on update current_timestamp,
    version int default 0,
    index (id),
    index (teacher_id)
);
create table if not exists student_course
(
    id bigint(19) not null primary key ,
    student_id bigint(19) not null ,
    course_id bigint(19) not null ,
    create_time datetime not null default current_timestamp,
    update_time datetime not null default current_timestamp on update current_timestamp,
    version int default 0,
    index (course_id),
    index (student_id)
);
