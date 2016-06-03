CREATE TABLE `pizza` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `categorie` varchar(255) DEFAULT NULL,
  `reference` varchar(255) DEFAULT NULL,
  `libelle` varchar(255) DEFAULT NULL,
  `prix` double NOT NULL,
  `url_image` varchar(255) DEFAULT NULL
);

ALTER TABLE `pizza` ADD CONSTRAINT `code_unique` UNIQUE(`reference`);

CREATE TABLE `performance` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `service` varchar(255) DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  `tempsExecution` int(11) DEFAULT NULL
);

ALTER TABLE `performance` ADD CONSTRAINT `service_unique` UNIQUE(`service`);