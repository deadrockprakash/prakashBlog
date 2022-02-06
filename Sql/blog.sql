create database blog;

create table blog(
                     id int auto_increment primary key ,
                     title varchar(50) not null,
                     content varchar(300),
                     author varchar(10) not null,
                     created_date datetime
);

create table comment(
                        id int auto_increment primary key ,
                        blog_id int references blog(id),
                        comment varchar(100),
                        author varchar(10) references user(username),
                        create_date datetime,
                        modified_date datetime
);

create table user(
                     id int auto_increment primary key ,
                     username varchar(10) unique key ,
                     password varchar(50),
                     firstname varchar(10),
                     lastname varchar(10)
);
create table role(
                     id int auto_increment primary key ,
                     name varchar(15) not null );

alter table user
    add role_id int;

alter table user
    ADD CONSTRAINT FK_userRole
        foreign key (role_id) references role(id) ;

insert into role(name) values('user');
insert into user(username, password, firstname, lastname) values ('dizprakash','prakash123','prakash','subedi');
insert into user(username, password, firstname, lastname) values ('dizprakash','prakash123','prakash','subedi');
select * from user;
select * from role;
select * from blog;
select * from comment;
delete from role where role.id = 4;
delete from user where id = 2;

