INSERT INTO `issuetracker`.`rol`(`descripcion`,`name`,`valor`)VALUES("Rol de administrador (Super) de Proyectos","Super Administrador",'0');
INSERT INTO `issuetracker`.`rol`(`descripcion`,`name`,`valor`)VALUES("Rol de administrador de Proyectos","Administrador",'1');
INSERT INTO `issuetracker`.`rol`(`descripcion`,`name`,`valor`)VALUES("Rol de Trabajador en los Proyectos","Programador",'2');
INSERT INTO `issuetracker`.`user`(`apellido`,`email`,`nombre`,`pass`)VALUES("Apellido","example@example.com","Nombre","12345");
insert into `issuetracker`.grupos (creacion, nombre) values ("2019-10-09 21:00:00", "Grupo1")
insert into `issuetracker`.grupos (creacion, nombre) values ("2019-10-09 21:00:00", "Grupo2")
INSERT INTO `issuetracker`.`user_roles`(`user_id`,`role_id`,`proyecto_id`) VALUES('1','1','1');
INSERT INTO `issuetracker`.`user_roles`(`user_id`,`role_id`,`proyecto_id`) VALUES('1','2','1');
