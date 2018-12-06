-- auto-generated definition
create table contact
(
  id  int auto_increment,
  start_time bigint null,
  end_time bigint null,
  state tinyint null,
  roomId    int           null,
  renterId  int           null,
  rent       double(10, 3) null,
  lease_term varchar(115)  null,
  remark     text          null,
  primary key (id)
);

-- auto-generated definition
create table house
(
  id        int auto_increment,
  neighborhood varchar(50) null, 
  province_id  int null,
  city_id int null,
  county_id int null,
  address varchar(255) null,
  type          tinyint       null,
  max_customer  int           null,
  rent          double(10, 3) null,
  state         tinyint       null,
  service_charge double(5, 3)  null,
  roomer_id     int           null,
  description   text          null,
  freetime      tinyint       null,
  picture       varchar(105)  null,
  primary key (id)
);

-- auto-generated definition
create table user
(
  id   int auto_increment,
  name     varchar(45)  null,
  province_id  int null,
  city_id int null,
  county_id int null,
  address varchar(255) null,
  phone    varchar(45)  null,
  birthday bigint        null,
  gender   tinyint      null,
  state    tinyint      null,
  password varchar(100) null,
  primary key (id)
);

-- auto-generated definition
create table view_record
(
  id int auto_increment,
  planTime     bigint null,
  renterId     int      null,
  roomId       int      null,
  description   text     null,
  roomerAck    tinyint  null,
  adminAck     tinyint  null,
  state         tinyint  null,
  primary key (id)
);

create table province 
(
    id int auto_increment,
    content varchar(30) null,
    primary key (id)
);

create table city 
(
    id int auto_increment,
    content varchar(30) null,
    province_id int null,
    primary key (id)
)

create table county
(
    id int auto_increment,
    content varchar(30) null,
    county_id int null,
    primary key (id)
);

