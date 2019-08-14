-- INSERT INTO Account value (1,'password','USER','dawithw@gmail.com');
-- INSERT INTO Address value (1,'Miami','FL','1470 NE 123rd St','33161');
-- INSERT INTO User value (1,'Dawit','Habte','3059514775',1,1);

-- INSERT INTO `product` VALUES (1,'better quality',null,'iPhone',123.0,34);
-- INSERT into `product` values (2,'High camera',null,'Huawei',345.0,6);
-- insert into `product` values (3 ,'Style',null,'LG',450.0,4);

insert into `address` (id, street, city, state, zip) values (1,'1000 N 4th St','Fairfield','IA','52557'),(2,'2020 S 5th St','Los Angeles','CA','90124'),(3,'2020 S 5th St','Los Angeles','CA','90124'),(4,'2020 S 5th St','Los Angeles','CA','90124');


insert into user (id,email, first_name, last_name, password,role, address_id ) values (1,'mulu@mum.edu','Male','Guy','$2a$10$S2KhQp4RhIHeMO0OYpqQleBsEFZ0qoIH1MJb9xvYaKNvYl33iJx6m','ADMIN',2);
insert into user (id,email, first_name, last_name, password,role, address_id ) values (2,'seller@mum.edu','Male','Guy','$2a$10$S2KhQp4RhIHeMO0OYpqQleBsEFZ0qoIH1MJb9xvYaKNvYl33iJx6m','SELLER',1);
insert into user (id,email, first_name, last_name, password,role, address_id ) values (3,'buyer@mum.edu','Male','Guy','$2a$10$S2KhQp4RhIHeMO0OYpqQleBsEFZ0qoIH1MJb9xvYaKNvYl33iJx6m','BUYER',3);
-- insert  into `product` values (1, true,'White amazing dress','Tilfi',100,3), (2, true,'Shinny long open dress','Gabi',100,3) , (3,true ,'Different colours mixed','Gersy',150,3), (4,true ,'Wedding dress and holidy','Traditional trouser',50,4);
insert  into product (id,approve, description, name, price,quantity,user_id ) values (1, true,'LG Phone','Huawie',100,3,2), (2, true,'Honer Phone','Houner',100,3,2) , (3,false ,'Alkater phone','Alkater',150,3,2),(4,false ,'Alkater phone','Alkater',50,4,2);

