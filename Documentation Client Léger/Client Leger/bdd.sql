CREATE TABLE `Bateau` (
  `idBateau` int(11) NOT NULL,
  `imageBateau` varchar(255) DEFAULT NULL,
  `nomBateau` varchar(50) NOT NULL,
  `description` text NOT NULL,
  `prix` float NOT NULL,
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `idCommande` int(11) DEFAULT NULL,
  `idBoutique` int(11) DEFAULT NULL,
  `idEmployer` int(11) NOT NULL,
  `idCategorie` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
CREATE TABLE `Boutique` (
  `idBoutique` int(11) NOT NULL,
  `active` enum('non-confirme','confirme') NOT NULL,
  `id_bateau` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
CREATE TABLE `Categorie` (
  `idCategorie` int(11) NOT NULL,
  `nomCategorie` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
CREATE TABLE `Client` (
  `idClient` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE `Commande` (
  `idCommande` int(11) NOT NULL,
  `archive` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `adresseFacturation` varchar(255) DEFAULT NULL,
  `montantHT` double DEFAULT NULL,
  `montantTTC` double DEFAULT NULL,
  `tva` double DEFAULT NULL,
  `idClient` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
CREATE TABLE `Compte` (
  `IdCompte` int(11) NOT NULL,
  `nom` varchar(50) NOT NULL,
  `prenom` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  `mdp` varchar(50) NOT NULL,
  `num` varchar(50) NOT NULL,
  `dateNaissance` date NOT NULL,
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `ban` int(2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
CREATE TABLE `Employer` (
  `idEmployer` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE `Facture` (
  `idFacture` int(11) NOT NULL,
  `dateFacture` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `dateReglement` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `idCommande` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
ALTER TABLE `Bateau`
  ADD PRIMARY KEY (`idBateau`),
  ADD KEY `idCommande` (`idCommande`),
  ADD KEY `idBoutique` (`idBoutique`),
  ADD KEY `idEmployer` (`idEmployer`),
  ADD KEY `idCategorie` (`idCategorie`);
ALTER TABLE `Boutique`
  ADD PRIMARY KEY (`idBoutique`);
ALTER TABLE `Categorie`
  ADD PRIMARY KEY (`idCategorie`);
ALTER TABLE `Client`
  ADD PRIMARY KEY (`idClient`);
ALTER TABLE `Commande`
  ADD PRIMARY KEY (`idCommande`),
  ADD KEY `idClient` (`idClient`);
ALTER TABLE `Compte`
  ADD PRIMARY KEY (`IdCompte`);
ALTER TABLE `Employer`
  ADD PRIMARY KEY (`idEmployer`);
  ALTER TABLE `Facture`
  ADD PRIMARY KEY (`idFacture`),
  ADD KEY `idCommande` (`idCommande`);
ALTER TABLE `Bateau`
  MODIFY `idBateau` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
ALTER TABLE `Boutique`
  MODIFY `idBoutique` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
ALTER TABLE `Categorie`
  MODIFY `idCategorie` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
ALTER TABLE `Commande`
  MODIFY `idCommande` int(11) NOT NULL AUTO_INCREMENT;
ALTER TABLE `Compte`
  MODIFY `IdCompte` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
ALTER TABLE `Facture`
  MODIFY `idFacture` int(11) NOT NULL AUTO_INCREMENT;
ALTER TABLE `Bateau`
  ADD CONSTRAINT `bateau_ibfk_1` FOREIGN KEY (`idCommande`) REFERENCES `Commande` (`idCommande`),
  ADD CONSTRAINT `bateau_ibfk_2` FOREIGN KEY (`idBoutique`) REFERENCES `Boutique` (`idBoutique`),
  ADD CONSTRAINT `bateau_ibfk_3` FOREIGN KEY (`idEmployer`) REFERENCES `Employer` (`idEmployer`),
  ADD CONSTRAINT `bateau_ibfk_4` FOREIGN KEY (`idCategorie`) REFERENCES `Categorie` (`idCategorie`);
ALTER TABLE `Commande`
  ADD CONSTRAINT `commande_ibfk_1` FOREIGN KEY (`idClient`) REFERENCES `Client` (`idClient`);
ALTER TABLE `Facture`
  ADD CONSTRAINT `facture_ibfk_1` FOREIGN KEY (`idCommande`) REFERENCES `Commande` (`idCommande`);
--Delimiters
DROP TRIGGER IF EXISTS affectUsers;
DELIMITER |
CREATE TRIGGER affectUsers AFTER INSERT 
ON Compte 
FOR EACH ROW
BEGIN
   INSERT INTO Client(idClient) VALUES(NEW.idCompte);
END |
DELIMITER ;

DROP TRIGGER IF EXISTS addBoutique;
DELIMITER |
CREATE TRIGGER addBoutique AFTER INSERT 
ON Bateau 
FOR EACH ROW
BEGIN
   INSERT INTO Boutique(id_bateau) VALUES (NEW.idBateau);
END |
DROP TRIGGER IF EXISTS Calcul;
DELIMITER //
CREATE TRIGGER Calcul
BEFORE UPDATE ON Commande
FOR EACH ROW
BEGIN
DECLARE prixb DOUBLE;
   SELECT prix INTO prixb
   FROM Bateau
   WHERE Bateau.idCommande = NEW.idCommande;
   SET NEW.montantHT = prixb;
   SET NEW.tva = prixb * 20 / 100;
   SET NEW.montantTTC = prixb  * 1.2;
END //
DELIMITER ;
--views
DROP VIEW IF EXISTS `clientall`;
DROP VIEW IF EXISTS `viewbateau`;
DROP VIEW IF EXISTS `viewfacture`;
DROP VIEW IF EXISTS `employerall`;

CREATE VIEW `clientall`  AS  select `c`.`IdCompte` AS `id`,`c`.`nom` AS `nom`,`c`.`prenom` AS `prenom`,`c`.`email` AS `email`,`c`.`mdp` AS `mdp`,`c`.`num` AS `telephone`,`c`.`dateNaissance` AS `dateNaissance`,`c`.`created_at` AS `created_at`,`c`.`ban` AS `ban`,`e`.`idClient` AS `idClient` 
from (`Compte` `c` left join `Client` `e` on((`e`.`idClient` = `c`.`IdCompte`))) 
where (`e`.`idClient` is not null) 
group by `c`.`IdCompte`,`c`.`nom`,`c`.`prenom`,`c`.`email`,`c`.`mdp`,`c`.`num`,`c`.`dateNaissance`,`c`.`created_at`,`c`.`ban`,`e`.`idClient`;

CREATE VIEW `employerall`  AS  select `c`.`IdCompte` AS `id`,`c`.`nom` AS `nom`,`c`.`prenom` AS `prenom`,`c`.`email` AS `email`,`c`.`mdp` AS `mdp`,`c`.`num` AS `telephone`,`c`.`dateNaissance` AS `dateNaissance`,`c`.`created_at` AS `created_at`,`c`.`ban` AS `ban`,`e`.`idEmployer` AS `idEmployer` 
from (`Compte` `c` left join `Employer` `e` on((`e`.`idEmployer` = `c`.`IdCompte`))) 
where (`e`.`idEmployer` is not null) 
group by `c`.`IdCompte`,`c`.`nom`,`c`.`prenom`,`c`.`email`,`c`.`mdp`,`c`.`num`,`c`.`dateNaissance`,`c`.`created_at`,`c`.`ban`,`e`.`idEmployer`;

CREATE VIEW `viewfacture`  AS  select `Compte`.`nom` AS `nom`,`Compte`.`prenom` AS `prenom`,`Compte`.`email` AS `email`,`Bateau`.`nomBateau` AS `nombateau`,`Commande`.`montantTTC` AS `montantTTC`,`Facture`.`dateFacture` AS `dateFacture`,`Facture`.`dateReglement` AS `dateReglement` from (((`Compte` join `Bateau`) join `Commande`) join `Facture`) ;