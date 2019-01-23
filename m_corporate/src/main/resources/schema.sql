drop table if exists corporate;
create table corporate (
  id char(36) not null primary key,
  name varchar(50) not null,
  createdOn datetime(3) not null,
  createdBy varchar(30) not null,
  updatedOn datetime(3) not null,
  updatedBy varchar(30) not null,
  version int not null,
  status tinyint not null,
  type tinyint not null,
  enabled tinyint(1) not null
) engine = InnoDb, charset = utf8mb4;