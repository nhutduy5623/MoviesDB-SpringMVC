-- MySQL dump 10.13  Distrib 8.0.13, for Win64 (x86_64)
--
-- Host: localhost    Database: db_moviesdb_jpa
-- ------------------------------------------------------
-- Server version	8.0.13

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Dumping data for table `comment`
--

LOCK TABLES `comment` WRITE;
/*!40000 ALTER TABLE `comment` DISABLE KEYS */;
/*!40000 ALTER TABLE `comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `commentlikedetail`
--

LOCK TABLES `commentlikedetail` WRITE;
/*!40000 ALTER TABLE `commentlikedetail` DISABLE KEYS */;
/*!40000 ALTER TABLE `commentlikedetail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `genre`
--

LOCK TABLES `genre` WRITE;
/*!40000 ALTER TABLE `genre` DISABLE KEYS */;
INSERT INTO `genre` VALUES (57,NULL,NULL,'development@gmail.com','2024-02-10 16:00:26.047000','ANIME','Anime','https://gamek.mediacdn.vn/133514250583805952/2022/3/3/isekai-anime-2-16463226192861784063387.jpg'),(58,NULL,NULL,'development@gmail.com','2024-02-12 18:22:39.145000','MANGA','Manga','https://e0.pxfuel.com/wallpapers/474/271/desktop-wallpaper-speed-art-one-piece-manga-manga.jpg');
/*!40000 ALTER TABLE `genre` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `permission`
--

LOCK TABLES `permission` WRITE;
/*!40000 ALTER TABLE `permission` DISABLE KEYS */;
INSERT INTO `permission` VALUES (1,NULL,NULL,'development@gmail.com','2024-02-22 20:09:10.621000','ADMIN_HOME','Trang chủ ADMIN','/admin/home','Admin Home'),(2,'anonymousUser','2024-01-29 16:17:03.277000','anonymousUser','2024-01-29 16:17:03.277000','GENER_MANAGE','Quản lý thể loại','/admin/genre','Genre Management function'),(3,NULL,NULL,'development@gmail.com','2024-02-22 19:28:48.099000','WORK_MANAGE','Quản lý tác phẩm','/admin/work','WORK Management function'),(4,'anonymousUser','2024-02-02 15:44:52.440000','anonymousUser','2024-02-02 15:44:52.440000','SUBGENRE_MANAGE','Quản lý thể loại','/admin/subgenre','SubGenre Management function'),(5,'anonymousUser','2024-02-12 17:38:43.267000','anonymousUser','2024-02-12 17:38:43.267000','AUTHORIZATION_MANAGE','Quản lý phân quyền','/admin/permission','Authorization Management function'),(6,'anonymousUser','2024-02-12 17:39:45.078000','anonymousUser','2024-02-12 17:39:45.078000','AUTHORIZATION_ROLE_MANAGE','Quản lý phân quyền role','/admin/role','Authorization Role Management function'),(8,NULL,NULL,'development@gmail.com','2024-02-22 20:14:54.978000','ACCOUNT_MANAGEMENT','Quản lý tài khoản','/admin/account','Account Management');
/*!40000 ALTER TABLE `permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `relatedparty`
--

LOCK TABLES `relatedparty` WRITE;
/*!40000 ALTER TABLE `relatedparty` DISABLE KEYS */;
/*!40000 ALTER TABLE `relatedparty` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `relatedparty_work`
--

LOCK TABLES `relatedparty_work` WRITE;
/*!40000 ALTER TABLE `relatedparty_work` DISABLE KEYS */;
/*!40000 ALTER TABLE `relatedparty_work` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `relatedparty_workdetail`
--

LOCK TABLES `relatedparty_workdetail` WRITE;
/*!40000 ALTER TABLE `relatedparty_workdetail` DISABLE KEYS */;
/*!40000 ALTER TABLE `relatedparty_workdetail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `relatedpartyrole`
--

LOCK TABLES `relatedpartyrole` WRITE;
/*!40000 ALTER TABLE `relatedpartyrole` DISABLE KEYS */;
/*!40000 ALTER TABLE `relatedpartyrole` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,NULL,NULL,NULL,NULL,'ADMIN','ADMIN','admin'),(2,NULL,NULL,NULL,NULL,'USER','USER','user'),(3,NULL,NULL,'anonymousUser','2024-02-02 15:47:20.126000','DEVELOPMENT','Nhà phát triển','Development'),(5,NULL,NULL,'development@gmail.com','2024-02-22 20:15:46.301000','Account','Quản lý tài khoản','Account');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `role_permission`
--

LOCK TABLES `role_permission` WRITE;
/*!40000 ALTER TABLE `role_permission` DISABLE KEYS */;
INSERT INTO `role_permission` VALUES (3,2),(3,4),(3,5),(3,6),(3,3),(3,1),(1,3),(1,1),(5,1);
/*!40000 ALTER TABLE `role_permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `role_user`
--

LOCK TABLES `role_user` WRITE;
/*!40000 ALTER TABLE `role_user` DISABLE KEYS */;
INSERT INTO `role_user` VALUES (1,1),(2,1),(2,2),(2,3),(3,4);
/*!40000 ALTER TABLE `role_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `serie`
--

LOCK TABLES `serie` WRITE;
/*!40000 ALTER TABLE `serie` DISABLE KEYS */;
/*!40000 ALTER TABLE `serie` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `subgenre`
--

LOCK TABLES `subgenre` WRITE;
/*!40000 ALTER TABLE `subgenre` DISABLE KEYS */;
INSERT INTO `subgenre` VALUES (20,NULL,NULL,'development@gmail.com','2024-02-10 17:01:00.292000','FANTASY','Fantasy','Thế giới tưởng tượng','https://static1.srcdn.com/wordpress/wp-content/uploads/2023/10/best-dark-fantasy-anime-featured-image.jpg'),(22,'development@gmail.com','2024-02-10 17:17:35.690000','development@gmail.com','2024-02-10 17:17:35.690000','ISEKAI','Isekai','Chuyển sinh sang thế giới khác','https://cdn.sforum.vn/sforum/wp-content/uploads/2023/10/anime-isekai-2.jpg');
/*!40000 ALTER TABLE `subgenre` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `subgenre_genre`
--

LOCK TABLES `subgenre_genre` WRITE;
/*!40000 ALTER TABLE `subgenre_genre` DISABLE KEYS */;
INSERT INTO `subgenre_genre` VALUES (22,57),(22,58),(20,57),(20,58);
/*!40000 ALTER TABLE `subgenre_genre` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `subgenre_work`
--

LOCK TABLES `subgenre_work` WRITE;
/*!40000 ALTER TABLE `subgenre_work` DISABLE KEYS */;
/*!40000 ALTER TABLE `subgenre_work` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `subpermission`
--

LOCK TABLES `subpermission` WRITE;
/*!40000 ALTER TABLE `subpermission` DISABLE KEYS */;
/*!40000 ALTER TABLE `subpermission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,NULL,NULL,NULL,NULL,NULL,NULL,'admin123@gmail.com','Admin123@','$2a$10$/RUbuT9KIqk6f8enaTQiLOXzhnUkiwEJRdtzdrMXXwU7dgnLKTCYG',1),(2,NULL,NULL,NULL,NULL,NULL,NULL,'user123@gmail.com','User123@','$2a$10$/RUbuT9KIqk6f8enaTQiLOXzhnUkiwEJRdtzdrMXXwU7dgnLKTCYG',1),(3,NULL,NULL,NULL,NULL,NULL,NULL,'user1234@gmail.com','User123@','$2a$10$/RUbuT9KIqk6f8enaTQiLOXzhnUkiwEJRdtzdrMXXwU7dgnLKTCYG',1),(4,NULL,NULL,'anonymousUser','2024-01-29 16:21:52.035000','test','DEVELOPMENT','development@gmail.com','Development','$2a$10$/RUbuT9KIqk6f8enaTQiLOXzhnUkiwEJRdtzdrMXXwU7dgnLKTCYG',1);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `userfavoritedetail`
--

LOCK TABLES `userfavoritedetail` WRITE;
/*!40000 ALTER TABLE `userfavoritedetail` DISABLE KEYS */;
/*!40000 ALTER TABLE `userfavoritedetail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `viewervotedetail`
--

LOCK TABLES `viewervotedetail` WRITE;
/*!40000 ALTER TABLE `viewervotedetail` DISABLE KEYS */;
/*!40000 ALTER TABLE `viewervotedetail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `work`
--

LOCK TABLES `work` WRITE;
/*!40000 ALTER TABLE `work` DISABLE KEYS */;
/*!40000 ALTER TABLE `work` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-02-22 20:22:47
