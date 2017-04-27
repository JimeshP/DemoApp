# Host: localhost  (Version: 5.6.26-log)
# Date: 2015-10-23 18:01:26
# Generator: MySQL-Front 5.3  (Build 4.243)

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`vrademoapp` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `vrademoapp`;

#
# Structure for table "customer"
#

DROP TABLE IF EXISTS `customer`;
CREATE TABLE `customer` (
  `CUST_ID` int(11) NOT NULL AUTO_INCREMENT,
  `CUST_FIRST_NAME` varchar(255) NOT NULL,
  `CUST_STATUS` tinyint(1) NOT NULL DEFAULT '0',
  `CUST_LAST_NAME` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`CUST_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=59 DEFAULT CHARSET=utf8;

#
# Data for table "customer"
#

INSERT INTO `customer` VALUES (59,'VMware',1,'Inc'),(67,'Interra',1,'Tech');

#
# Structure for table "app_user"
#

DROP TABLE IF EXISTS `app_user`;
CREATE TABLE `app_user` (
  `USER_ID` int(11) NOT NULL AUTO_INCREMENT,
  `Customer_CUST_ID` int(11) NOT NULL,
  `USER_NAME` varchar(255) DEFAULT NULL,
  `PASSWORD` varchar(255) NOT NULL,
  PRIMARY KEY (`USER_ID`),
  UNIQUE KEY `Unique Key` (`USER_NAME`),
  KEY `purchase_Customer` (`Customer_CUST_ID`),
  KEY `Idx_user_name` (`USER_NAME`),
  CONSTRAINT `purchase_Customer` FOREIGN KEY (`Customer_CUST_ID`) REFERENCES `customer` (`CUST_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;

#
# Data for table "app_user"
#

INSERT INTO `app_user` VALUES (23,59,'vmware','vmware'),(31,67,'interra','interra');

#
# Structure for table "address"
#

DROP TABLE IF EXISTS `address`;
CREATE TABLE `address` (
  `ADD_ID` int(11) NOT NULL AUTO_INCREMENT,
  `Customer_CUST_ID` int(11) NOT NULL,
  `ADD_LINE_1` varchar(255) NOT NULL,
  `ADD_LINE_2` varchar(255) DEFAULT NULL,
  `CITY` varchar(255) NOT NULL,
  `STATE` varchar(255) NOT NULL,
  `COUNTRY` varchar(255) NOT NULL,
  `ZIPCODE` varchar(255) NOT NULL,
  PRIMARY KEY (`ADD_ID`),
  KEY `Address_Customer` (`Customer_CUST_ID`),
  CONSTRAINT `Address_Customer` FOREIGN KEY (`Customer_CUST_ID`) REFERENCES `customer` (`CUST_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

#
# Data for table "address"
#

INSERT INTO `address` VALUES (21,59,'3401 Hillview Ave','Palo Alto','Palo Alto','California','USA','94304'),(29,67,'25 Metro Dr','500','San Jose','California','USA','95110');

#
# Structure for table "order_header"
#

DROP TABLE IF EXISTS `order_header`;
CREATE TABLE `order_header` (
  `ORDER_ID` int(11) NOT NULL AUTO_INCREMENT,
  `Customer_CUST_ID` int(11) NOT NULL,
  `STATUS_ID` tinyint(1) NOT NULL,
  `ORDER_DATE` date NOT NULL DEFAULT '0000-00-00',
  PRIMARY KEY (`ORDER_ID`),
  KEY `Customer ID FK` (`Customer_CUST_ID`),
  CONSTRAINT `Customer ID FK` FOREIGN KEY (`Customer_CUST_ID`) REFERENCES `customer` (`CUST_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=84 DEFAULT CHARSET=utf8;

#
# Data for table "order_header"
#

INSERT INTO `order_header` VALUES (84,59,1,'2015-10-23'),(85,67,1,'2015-10-23'),(86,67,1,'2015-10-23'),(87,67,1,'2015-10-23');

#
# Structure for table "product"
#

DROP TABLE IF EXISTS `product`;
CREATE TABLE `product` (
  `PROD_ID` int(11) NOT NULL AUTO_INCREMENT,
  `PROD_NAME` varchar(255) NOT NULL,
  PRIMARY KEY (`PROD_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

#
# Data for table "product"
#

INSERT INTO `product` VALUES (6,'Skateboard 1'),(7,'Skateboard 2'),(8,'Skateboard 3'),(9,'Skateboard 4');
INSERT INTO `product` VALUES (18,'Skateboard 5'),(19,'Skateboard 6'),(20,'Skateboard 7'),(21,'Skateboard 8');
INSERT INTO `product` VALUES (10,'Deck 1'),(11,'Deck 2'),(12,'Deck 3'),(13,'Deck 4');
INSERT INTO `product` VALUES (22,'Deck 5'),(23,'Deck 6'),(24,'Deck 7'),(25,'Deck 8');
INSERT INTO `product` VALUES (14,'Wheels 1'),(15,'Wheels 2'),(16,'Wheels 3'),(17,'Wheels 4');
INSERT INTO `product` VALUES (28,'Wheels 5'),(29,'Wheels 6'),(30,'Wheels 7'),(31,'Wheels 8');




#
# Structure for table "product_price"
#

DROP TABLE IF EXISTS `product_price`;
CREATE TABLE `product_price` (
  `PRD_PRICE_ID` int(11) NOT NULL AUTO_INCREMENT,
  `Product_PROD_ID` int(11) NOT NULL,
  `PRICE_PER_UNIT` int(11) NOT NULL,
  PRIMARY KEY (`PRD_PRICE_ID`),
  KEY `Product_Price_Product` (`Product_PROD_ID`),
  CONSTRAINT `Product_Price_Product` FOREIGN KEY (`Product_PROD_ID`) REFERENCES `product` (`PROD_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

#
# Data for table "product_price"
#

INSERT INTO `product_price` VALUES (11,6,20),(12,7,40),(13,8,50),(14,9,80);
INSERT INTO `product_price` VALUES (23,18,120),(24,19,140),(25,20,150),(26,21,300);

INSERT INTO `product_price` VALUES (15,10,90),(16,11,40),(17,12,60),(18,13,80);
INSERT INTO `product_price` VALUES (27,22,140),(28,23,110),(29,24,130),(30,25,100);

INSERT INTO `product_price` VALUES (19,14,40),(20,15,200),(21,16,50),(22,17,100);
INSERT INTO `product_price` VALUES (33,28,40),(34,29,200),(35,30,60),(36,31,140);

#
# Structure for table "order_line"
#

DROP TABLE IF EXISTS `order_line`;
CREATE TABLE `order_line` (
  `ORDER_LINE_ID` int(11) NOT NULL AUTO_INCREMENT,
  `Order_Header_ORDER_ID` int(11) NOT NULL,
  `Product_PROD_ID` int(11) NOT NULL,
  `Product_Price_PRD_PRICE_ID` int(11) NOT NULL,
  PRIMARY KEY (`ORDER_LINE_ID`),
  KEY `Order_Line_Order_Header` (`Order_Header_ORDER_ID`),
  KEY `Order_Line_Product` (`Product_PROD_ID`),
  KEY `Order_Line_Product_Price` (`Product_Price_PRD_PRICE_ID`),
  CONSTRAINT `Order_Line_Product` FOREIGN KEY (`Product_PROD_ID`) REFERENCES `product` (`PROD_ID`),
  CONSTRAINT `Order_Line_Product_Price` FOREIGN KEY (`Product_Price_PRD_PRICE_ID`) REFERENCES `product_price` (`PRD_PRICE_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=141 DEFAULT CHARSET=utf8;

#
# Data for table "order_line"
#

INSERT INTO `order_line` VALUES (141,84,7,12),(142,84,7,12),(143,85,6,11),(144,85,6,11),(145,86,6,11),(146,86,7,12),(147,86,8,13),(148,87,6,11),(149,87,7,12),(150,86,6,11),(151,86,7,12),(152,86,8,13),(153,87,6,11),(154,87,7,12);
