DROP sequence myonlineshopdb IF EXISTS;
CREATE sequence myonlineshopdb; 

CREATE TABLE arrival (
  Id int(11) ,
  GoodsId int(11) ,
  UserId int(11) ,
  Amount int(11) ,
  Price int(11),
  Time timestamp,
  constraint arrival_pk primary key (Id)
) 


CREATE TABLE brands (
  Id int(11),
  BrandName varchar(45)
) 





CREATE TABLE goods (
  Id int(11),
  BrandId int(11)L,
  Model varchar(45),
  Price decimal(10,0) NOT NULL,
  AmountInStock int(11) NOT NULL,
  PRIMARY KEY (Id),
  UNIQUE KEY Id_UNIQUE (Id),
  UNIQUE KEY Model_UNIQUE (Model),
  KEY ModelId_FK_idx (BrandId),
  KEY ScreenSizeId_FK_idx (Model),
  CONSTRAINT Goods_BrandId_FK_Brands FOREIGN KEY (BrandId) REFERENCES brands (Id) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=82 DEFAULT CHARSET=utf8;






CREATE TABLE orders_sales (
  Id int(11) NOT NULL AUTO_INCREMENT,
  GoodsId int(11) NOT NULL,
  UserId int(11) NOT NULL,
  IsSold tinyint(1) NOT NULL,
  Amount int(11) NOT NULL,
  TimeOrdered timestamp NULL DEFAULT NULL,
  TimeSold timestamp NULL DEFAULT NULL,
  PRIMARY KEY (Id),
  UNIQUE KEY Id_UNIQUE (Id),
  KEY UserId_FK_idx (UserId),
  KEY GoodsId_FK_idx (GoodsId),
  CONSTRAINT Orders_Sales_GoodsId_FK_Goods FOREIGN KEY (GoodsId) REFERENCES goods (Id) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT Orders_Sales_UserId_FK_Users FOREIGN KEY (UserId) REFERENCES users (Id) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=65 DEFAULT CHARSET=utf8;




CREATE TABLE userroles (
  Id int(11) NOT NULL AUTO_INCREMENT,
  Role varchar(45) NOT NULL,
  PRIMARY KEY (Id),
  UNIQUE KEY Id_UNIQUE (Id),
  UNIQUE KEY Role_UNIQUE (Role)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;



CREATE TABLE users (
  Id int(11) NOT NULL AUTO_INCREMENT,
  RoleId int(11) NOT NULL,
  Login varchar(45) NOT NULL,
  Password varchar(255) NOT NULL,
  PRIMARY KEY (Id),
  UNIQUE KEY Id_UNIQUE (Id),
  UNIQUE KEY Login_UNIQUE (Login),
  KEY RoleId_FK_idx (RoleId),
  CONSTRAINT Users_RoleId_FK_UserRoles FOREIGN KEY (RoleId) REFERENCES userroles (Id) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=48 DEFAULT CHARSET=utf8;

