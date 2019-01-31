drop table if exists corporate;
create table corporate
(
  id        char(36)     not null primary key,
  name      varchar(255) not null,
  enabled   boolean      not null,
  status    tinyint      not null,
  type      tinyint      not null,
  created_by varchar(30)  not null,
  created_on datetime(3)  not null,
  version   int          not null,
  updated_by varchar(30)  not null,
  updated_on datetime(3)  not null
) engine = InnoDB,
  charset = utf8mb4;

drop table if exists user;
create table user (
  id char(36) not null primary key ,
  username varchar(30) not null,
  email varchar(255) null,
  phone char(11) not null,
  name varchar(20) not null,
  member_of char(36) not null,
  version int not null,
  created_by varchar(30) not null,
  created_on datetime(3) not null,
  updated_by varchar(30) not null,
  updated_on datetime(3) not null,
  index idx_member_of (member_of)
) engine = InnoDB,
  charset = utf8mb4;

