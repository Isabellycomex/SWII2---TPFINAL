CREATE DATABASE SalesManagement;
USE SalesManagement;
 
CREATE TABLE `salesmen` (
  `id` INT AUTO_INCREMENT PRIMARY KEY,
  `name` varchar(30) NOT NULL,
  `city` varchar(15) NOT NULL,
  `commission` DECIMAL(5,2) NOT NULL
);

CREATE TABLE `customers` (
  `id` INT AUTO_INCREMENT PRIMARY KEY,
  `name` varchar(30) NOT NULL,
  `city` varchar(15) NOT NULL,
  `grade` NUMERIC(3) NOT NULL,
  `salesman_id` INT,
  FOREIGN KEY (salesman_id) REFERENCES salesmen(id)
);

CREATE TABLE `orders` (
  `id` INT AUTO_INCREMENT PRIMARY KEY,
  `purchase_amount` DECIMAL(8,2) NOT NULL,
  `created_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  `salesman_id` INT NOT NULL,
  `customer_id` INT NOT NULL,
  FOREIGN KEY (salesman_id) REFERENCES salesmen(id),
  FOREIGN KEY (customer_id) REFERENCES customers(id)
);

SELECT * FROM salesmen;
SELECT * FROM customers;
SELECT * FROM orders;