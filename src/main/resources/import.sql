INSERT INTO `issuetracker`.`rol`(`descripcion`,`name`,`valor`)VALUES("Descripcion del Rol","Rol de prueba",'1');
INSERT INTO `issuetracker`.`rol`(`descripcion`,`name`,`valor`)VALUES("Descripcion del Rol","Rol de prueba2",'2');
INSERT INTO `issuetracker`.`user`(`apellido`,`email`,`nombre`,`pass`)VALUES("Apellido","example@example.com","Nombre","12345");
INSERT INTO `issuetracker`.`user_roles`(`user_id`,`role_id`)VALUES('1','1');
INSERT INTO `issuetracker`.`user_roles`(`user_id`,`role_id`)VALUES('1','2');