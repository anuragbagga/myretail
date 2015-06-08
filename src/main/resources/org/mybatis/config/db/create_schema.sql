
-- change your mysql password in db.properties

CREATE SCHEMA myretail if not exists;

use database myretail;

CREATE TABLE `item_details` (
  `id` int(8) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `sku` varchar(50) DEFAULT NULL,
  `category_id` varchar(10) NOT NULL,
  `updated_dt` date NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=7564 DEFAULT CHARSET=latin1;


CREATE TABLE `item_price` (
  `id` int(8) NOT NULL,
  `price` decimal(6,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- insert data into item_details

insert into item_details values ('5555','Stroller','AEX143','Baby',curdate());
insert into item_details values ('5543','Optimus Prime','IOL123','Toys',curdate());
insert into item_details values ('7563','Sega Genesis','XYZ904','Toys',curdate());


-- insert data into item_price

insert into item_price values('5555','199.99');
insert into item_price values('5543','13.37');
insert into item_price values('7563','149.99');

