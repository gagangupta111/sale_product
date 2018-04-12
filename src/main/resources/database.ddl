create database openshiftapp1;
use openshiftapp1;
create table Sale (

   saleId INT NOT NULL auto_increment,
   saleDate Date default null,
   saleAmount    INT NOT NULL,
   productID INT  default NULL,
   PRIMARY KEY (saleId)

);

create table Product (

   productID INT NOT NULL auto_increment,
   productName VARCHAR(40) default NULL,
   PRIMARY KEY (productID)

);