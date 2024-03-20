use db_moviesdb_jpa;

INSERT INTO `role`(`code`,`disription`,`name`)VALUES("ADMIN","ADMIN","admin");
INSERT INTO `role`(`code`,`disription`,`name`)VALUES("USER","USER","user");


INSERT INTO permission(`code`,`disription`,`name`, `functionurl`)VALUES("ADMIN_HOME","Trang chủ ADMIN","Home Admin", "/admin/home");
INSERT INTO role_permission(roleid, permissionid) values(1,1);


/*
INSERT INTO permission(`code`,`disription`,`name`, `functionurl`)VALUES("USER_MANAGE","Quản lý người dùng","User Management function", "/admin/accountmanage");
INSERT INTO permission(`code`,`disription`,`name`, `functionurl`)VALUES("GENER_MANAGE","Quản lý thể loại","Genre Management function", "/admin/genermanage");
INSERT INTO permission(`code`,`disription`,`name`, `functionurl`)VALUES("WORK_MANAGE","Quản lý tác phẩm","Work Management function", "/admin/workmanage");
INSERT INTO permission(`code`,`disription`,`name`, `functionurl`)VALUES("RELATEDPARTY_MANAGE","Quản lý tác giả, producer, studio,... ","Relateparty Management function", "/admin/relatedpartymanage");
INSERT INTO permission(`code`,`disription`,`name`, `functionurl`)VALUES("COMMENT_MANAGE","Quản lý bình luận","Comment Management function", "/admin/commentmanage");
INSERT INTO permission(`code`,`disription`,`name`, `functionurl`)VALUES("REVIEW_MANAGE","Quản lý đánh giá","Review Management function", "/admin/reviewmanage");
INSERT INTO permission(`code`,`disription`,`name`, `functionurl`)VALUES("CONTACT_MANAGE","Quản lý thông tin liên hệ admin","Contract Management function", "/admin/contactmanage");

*/

insert into user(email, password, fullname,  status) VALUES("admin123@gmail.com", "$2a$10$/RUbuT9KIqk6f8enaTQiLOXzhnUkiwEJRdtzdrMXXwU7dgnLKTCYG", "Admin123@", 1);
insert into user(email, password, fullname, status) VALUES("user123@gmail.com", "$2a$10$/RUbuT9KIqk6f8enaTQiLOXzhnUkiwEJRdtzdrMXXwU7dgnLKTCYG", "User123@", 1);
insert into user(email, password, fullname, status) VALUES("user1234@gmail.com", "$2a$10$/RUbuT9KIqk6f8enaTQiLOXzhnUkiwEJRdtzdrMXXwU7dgnLKTCYG", "User123@", 1);



insert into role_user(roleid, userid) values(1,1);
insert into role_user(roleid, userid) values(2,1);
insert into role_user(roleid, userid) values(2,2);
insert into role_user(roleid, userid) values(2,3);


SELECT * FROM user u, role r, permission p, role_permission rp, role_user ru
where u.id=ru.userid and ru.roleid=r.id and r.id=rp.roleid and rp.permissionid = p.id and p.functionURL = "/admin/home" and userId = 1