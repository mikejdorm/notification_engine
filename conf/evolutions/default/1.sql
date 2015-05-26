# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table messages (
  message_type              varchar(31) not null,
  id                        varchar(255) not null,
  from_number               varchar(255) not null,
  to_number                 varchar(255) not null,
  account_id                varchar(255) not null,
  content                   varchar(1600),
  status                    varchar(255),
  created_at                datetime not null,
  updated_at                datetime not null,
  constraint pk_messages primary key (id))
;


# --- !Downs

SET FOREIGN_KEY_CHECKS=0;

drop table messages;

SET FOREIGN_KEY_CHECKS=1;

