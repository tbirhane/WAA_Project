-- INSERT INTO Account value (1,'password','USER','dawithw@gmail.com');
-- INSERT INTO Address value (1,'Miami','FL','1470 NE 123rd St','33161');
-- INSERT INTO User value (1,'Dawit','Habte','3059514775',1,1);

-- INSERT INTO `product` VALUES (1,'better quality',null,'iPhone',123.0,34);
-- INSERT into `product` values (2,'High camera',null,'Huawei',345.0,6);
-- insert into `product` values (3 ,'Style',null,'LG',450.0,4);

insert  into `product` values (1, true,'LG Phone',null,'Huawie',100,3), (2, true,'Honer Phone',null,'Houner',100,3) , (3,true ,'Alkater phone',null,'Alkater',150,3), (4,true ,'Alkater phone',null,'Alkater',50,4);

insert into `account` (id, username, password, role) values (1,'admin','$2a$10$MWhnPbxTbWqN7RkBI3Vyk.UoM8XFSXM4Lew6pK3ZiJkZImaZGovJe','ROLE_ADMIN'),(2,'user','$2a$10$MWhnPbxTbWqN7RkBI3Vyk.UoM8XFSXM4Lew6pK3ZiJkZImaZGovJe','ROLE_USER'),(3,'user2','$2a$10$MWhnPbxTbWqN7RkBI3Vyk.UoM8XFSXM4Lew6pK3ZiJkZImaZGovJe','ROLE_USER');

insert into `address` (id, street, city, state, zip) values (1,'1000 N 4th St','Fairfield','IA','52557'),(2,'2020 S 5th St','Los Angeles','CA','90124');

insert into `user` (id, first_name, last_name, phone, account_id, address_id) values (1,'Male','Guy','111-111-1111',2,1),(2,'Female','Girl','222-222-2222',3,2);



