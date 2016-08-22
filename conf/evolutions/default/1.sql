# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table itementity (
  id                        bigint not null,
  quantity                  integer,
  product_id                bigint not null,
  wishlist_id               bigint not null,
  constraint pk_itementity primary key (id))
;

create table productentity (
  id                        bigint not null,
  name                      varchar(255),
  stock                     integer,
  price                     float,
  available                 boolean,
  constraint pk_productentity primary key (id))
;

create table wishlistentity (
  id                        bigint not null,
  username                  varchar(255),
  constraint pk_wishlistentity primary key (id))
;

create sequence Item;

create sequence Product;

create sequence WishList;

alter table itementity add constraint fk_itementity_product_1 foreign key (product_id) references productentity (id);
create index ix_itementity_product_1 on itementity (product_id);
alter table itementity add constraint fk_itementity_wishlist_2 foreign key (wishlist_id) references wishlistentity (id);
create index ix_itementity_wishlist_2 on itementity (wishlist_id);



# --- !Downs

drop table if exists itementity cascade;

drop table if exists productentity cascade;

drop table if exists wishlistentity cascade;

drop sequence if exists Item;

drop sequence if exists Product;

drop sequence if exists WishList;

