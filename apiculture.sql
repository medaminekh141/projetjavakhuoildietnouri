-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : mer. 11 juin 2025 à 16:17
-- Version du serveur : 10.4.32-MariaDB
-- Version de PHP : 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `apiculture`
--

-- --------------------------------------------------------

--
-- Structure de la table `ferme`
--

CREATE TABLE `ferme` (
  `id` int(11) NOT NULL,
  `localisation` varchar(255) NOT NULL,
  `fermier_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `ferme`
--

INSERT INTO `ferme` (`id`, `localisation`, `fermier_id`) VALUES
(6, 'bizerte', 5);

-- --------------------------------------------------------

--
-- Structure de la table `fermier`
--

CREATE TABLE `fermier` (
  `id` int(11) NOT NULL,
  `nom` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `fermier`
--

INSERT INTO `fermier` (`id`, `nom`) VALUES
(4, 'aa'),
(5, 'amine');

-- --------------------------------------------------------

--
-- Structure de la table `ruche`
--

CREATE TABLE `ruche` (
  `id` int(11) NOT NULL,
  `code` varchar(50) NOT NULL,
  `ferme_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `ruche`
--

INSERT INTO `ruche` (`id`, `code`, `ferme_id`) VALUES
(8, '123', 6);

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `ferme`
--
ALTER TABLE `ferme`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fermier_id` (`fermier_id`);

--
-- Index pour la table `fermier`
--
ALTER TABLE `fermier`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `ruche`
--
ALTER TABLE `ruche`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `code` (`code`),
  ADD KEY `ferme_id` (`ferme_id`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `ferme`
--
ALTER TABLE `ferme`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT pour la table `fermier`
--
ALTER TABLE `fermier`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT pour la table `ruche`
--
ALTER TABLE `ruche`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `ferme`
--
ALTER TABLE `ferme`
  ADD CONSTRAINT `ferme_ibfk_1` FOREIGN KEY (`fermier_id`) REFERENCES `fermier` (`id`) ON DELETE CASCADE;

--
-- Contraintes pour la table `ruche`
--
ALTER TABLE `ruche`
  ADD CONSTRAINT `ruche_ibfk_1` FOREIGN KEY (`ferme_id`) REFERENCES `ferme` (`id`) ON DELETE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
