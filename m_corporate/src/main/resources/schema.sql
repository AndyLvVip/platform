drop table if exists corporate;
create table corporate
(
  id        char(36)     not null primary key,
  name      varchar(255) not null,
  enabled   boolean      not null,
  status    tinyint      not null,
  type      tinyint      not null,
  createdBy varchar(30)  not null,
  createdOn datetime(3)  not null,
  version   int          not null,
  updatedBy varchar(30)  not null,
  updatedOn datetime(3)  not null
) engine = InnoDB,
  charset = utf8mb4;