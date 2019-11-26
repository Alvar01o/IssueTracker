INSERT INTO `issuetracker`.`rol`(`descripcion`,`name`,`valor`)VALUES("Rol de administrador (Super) de Proyectos","ROLE_ADMIN",'0');
INSERT INTO `issuetracker`.`rol`(`descripcion`,`name`,`valor`)VALUES("Rol de administrador de Proyectos","ROLE_MANAGER",'1');
INSERT INTO `issuetracker`.`rol`(`descripcion`,`name`,`valor`)VALUES("Rol de Trabajador en los Proyectos","ROLE_DEVELOPER",'2');
INSERT INTO `issuetracker`.`rol`(`descripcion`,`name`,`valor`)VALUES("Rol Sin Asignar de momento","ROLE_NEW",'3');
INSERT INTO `user` (`id`,`apellido`,`creacion`,`email`,`nombre`,`pass`) VALUES (1,'mercado','2019-11-14 00:17:45','alvaro@admin.com','alvaro','$2a$10$mFRW7IwlLUL8pAH8cXWA5uxtl/YDxopDKxwYGpu1f.lfF66CH5Idm');
/*INSERT INTO `user` (`id`,`apellido`,`creacion`,`email`,`nombre`,`pass`) VALUES (2,'mercado2','2019-11-14 00:17:45','alvaro@manager.com','alvaro','$2a$10$z6B3Wsnk3p7LNG2J/CvjcO0vobZyTbcUR5P5YbJPt/ppPeyGrZj3S');
INSERT INTO `user` (`id`,`apellido`,`creacion`,`email`,`nombre`,`pass`) VALUES (3,'mercado3','2019-11-14 00:17:45','alvaro@developer.com','alvaro','$2a$10$vEBD5zBZxlpTawh/7H4NluCIxYD0hzr1XqaV01objyiioojpDk9fO');*/
INSERT INTO `user` (`id`,`apellido`,`creacion`,`email`,`nombre`,`pass`) VALUES (2,'mercado3','2019-11-14 00:17:45','alvaro.mercado@fiuni.edu.py','alvaro','$2a$10$vEBD5zBZxlpTawh/7H4NluCIxYD0hzr1XqaV01objyiioojpDk9fO');
insert into `issuetracker`.grupos (creacion, nombre) values ("2019-10-09 21:00:00", "Grupo1")
insert into `issuetracker`.grupos (creacion, nombre) values ("2019-10-09 21:00:00", "Grupo2")
INSERT INTO `issuetracker`.`proyectos` (`descripcion`,`nombre`,`grupo_id`) VALUES ("Proyecto de Prueba","Proyecto #1", 1);
INSERT INTO `issuetracker`.`user_roles` (`proyecto_id`,`rol_id`,`user_id`) VALUES(1,1,1);
/*INSERT INTO `issuetracker`.`user_roles` (`proyecto_id`,`rol_id`,`user_id`) VALUES(1,2,2);
INSERT INTO `issuetracker`.`user_roles` (`proyecto_id`,`rol_id`,`user_id`) VALUES(1,3,3);*/
