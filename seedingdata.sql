use db_moviesdb_jpa;

INSERT INTO `role`(`code`,`disription`,`name`)VALUES("ADMIN","ADMIN","admin");
INSERT INTO `role`(`code`,`disription`,`name`)VALUES("USER","USER","user");

insert into user(email, fullname, password, status) VALUES("admin123@gmail.com", "$2a$10$/RUbuT9KIqk6f8enaTQiLOXzhnUkiwEJRdtzdrMXXwU7dgnLKTCYG", "Admin123@", 1);
insert into user(email, fullname, password, status) VALUES("user123@gmail.com", "$2a$10$/RUbuT9KIqk6f8enaTQiLOXzhnUkiwEJRdtzdrMXXwU7dgnLKTCYG", "User123@", 1);
insert into user(email, fullname, password, status) VALUES("user1234@gmail.com", "$2a$10$/RUbuT9KIqk6f8enaTQiLOXzhnUkiwEJRdtzdrMXXwU7dgnLKTCYG", "User123@", 1);


insert into role_user(roleid, userid) values(1,1);
insert into role_user(roleid, userid) values(2,1);
insert into role_user(roleid, userid) values(2,2);
insert into role_user(roleid, userid) values(2,3);

