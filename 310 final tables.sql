create database Final;
use final;
create table Item(ID int auto_increment,
itemCode varchar(10) unique,
itemDescription varchar(50),
price decimal(4,2) default('0'),
primary key(ID)
);

create table Purchase(ID int auto_increment,
itemId int not null,
quantity int not null,
purchaseDate datetime default(current_date()),
primary key(ID)
);
create table Shipping(ID int auto_increment,
itemID int not null,
quantity int not null,
shippingDate date unique,
primary key(ID)
);
