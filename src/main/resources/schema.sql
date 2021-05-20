create table if not exists Admin
(
    id          bigint(19) not null primary key,
    username    varchar(45),
    password    varchar(45),
    create_time datetime   not null default current_timestamp,
    update_time datetime   not null default current_timestamp on update current_timestamp,
    unique (id),
    index (id)
);

create table if not exists teacher
(
    id          bigint(19) not null primary key,#教师编号
    username    varchar(45),
    password    varchar(45),
    phone       bigint(11),#电话号码
    email       varchar(45),#邮箱
    post        varchar(32),#职位
    college     varchar(45),#所属学院
    course      varchar(45),
    create_time datetime   not null default current_timestamp,
    update_time datetime   not null default current_timestamp on update current_timestamp,
    unique (id),
    index (id)
);

create table if not exists student
(
    id            bigint(19) not null primary key,
    username      varchar(45),
    password      varchar(45),
    teacher_id    bigint(19) not null,
    laboratory_id bigint(19)          default null,
    college       varchar(45),
    create_time   datetime   not null default current_timestamp,
    update_time   datetime   not null default current_timestamp on update current_timestamp,
    unique (id),
    index (id),
    index (teacher_id)
);
create table if not exists laboratory
(
    id                bigint(19) not null primary key,
    name              varchar(32),
    teacher_id        bigint(19)          default null,
    laboratory_number int        not null,
    laboratory_msg    int default 0,
    update_time       datetime   not null default current_timestamp on update current_timestamp,
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
    index (course_id),
    index (student_id)
);
