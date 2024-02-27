CREATE DATABASE coderhouse;

CREATE TABLE coderhouse.clients (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50),
    surname VARCHAR(50),
    birthday DATE
);

CREATE TABLE coderhouse.sale (
  id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
  created_at datetime(6) NOT NULL,
  quantity int(11) NOT NULL,
  total double NOT NULL,
  client_id INT NOT NULL,
  KEY `FK_client_id` (client_id),
  CONSTRAINT `FK_client_id` FOREIGN KEY (client_id) REFERENCES clients (id)
);

CREATE TABLE coderhouse.sale_product (
  sale_id INT NOT NULL,
  product_id INT NOT NULL,
  KEY `FK_sale_id` (sale_id),
  KEY `FK_product_id` (product_id),
  CONSTRAINT `FK_sale_id` FOREIGN KEY (sale_id) REFERENCES sales (id),
  CONSTRAINT `FK_product_id` FOREIGN KEY (product_id) REFERENCES products (id)
);