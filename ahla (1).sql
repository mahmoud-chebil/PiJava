-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : mer. 11 mai 2022 à 16:48
-- Version du serveur : 10.4.22-MariaDB
-- Version de PHP : 7.4.27

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `ahla`
--

-- --------------------------------------------------------

--
-- Structure de la table `activite`
--

CREATE TABLE `activite` (
  `id` int(11) NOT NULL,
  `nom_activite` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `type_activite` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `prix_activite` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `activite`
--

INSERT INTO `activite` (`id`, `nom_activite`, `type_activite`, `prix_activite`) VALUES
(1, 'Camping', 'Environmental', 150),
(2, 'cascade', 'sport', 10000);

-- --------------------------------------------------------

--
-- Structure de la table `cart`
--

CREATE TABLE `cart` (
  `id` int(11) NOT NULL,
  `quantity` int(11) NOT NULL,
  `productid` int(11) NOT NULL,
  `price` int(11) NOT NULL,
  `name` varchar(30) NOT NULL,
  `produits` varchar(50) NOT NULL,
  `total` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `category_product`
--

CREATE TABLE `category_product` (
  `id` int(11) NOT NULL,
  `name` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `category_product`
--

INSERT INTO `category_product` (`id`, `name`) VALUES
(1, 'Mahmoud'),
(6, 'SPORT'),
(7, 'CULTURE'),
(8, 'CINEMA'),
(9, 'ESPRIT');

-- --------------------------------------------------------

--
-- Structure de la table `commande`
--

CREATE TABLE `commande` (
  `id` int(11) NOT NULL,
  `id_user_id` int(11) DEFAULT NULL,
  `idpanier_id` int(11) DEFAULT NULL,
  `nom` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `prenom` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `adressecomplet` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `telephone` int(11) NOT NULL,
  `total` double NOT NULL,
  `quantites` longtext COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '(DC2Type:array)',
  `etat` int(11) NOT NULL,
  `email` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `produits` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `commande`
--

INSERT INTO `commande` (`id`, `id_user_id`, `idpanier_id`, `nom`, `prenom`, `adressecomplet`, `telephone`, `total`, `quantites`, `etat`, `email`, `produits`) VALUES
(8, NULL, NULL, 'Mekni', 'Fares', 'tunis ariana', 56140361, 35, 'a:1:{i:0;i:1;}', 0, 'fares.mekni.jr@gmail.com', ''),
(9, NULL, NULL, 'Mekni', 'Mohamed', 'tunis ariana', 56140361, 35, 'a:1:{i:0;i:1;}', 0, 'mohamedfares.mekni@esprit.tn', ''),
(10, NULL, NULL, 'Mekni', 'Fares', 'tunis ariana', 56140361, 90, 'a:1:{i:0;i:1;}', 1, 'fares.mekni.jr@gmail.com', ''),
(11, NULL, NULL, 'Mekni', 'Fares', 'tunis ariana', 56140361, 12, 'a:1:{i:0;i:1;}', 0, 'fares.mekni.jr@gmail.com', ''),
(12, NULL, NULL, 'Mekni', 'Fares', 'tunis ariana', 56140361, 12, 'a:1:{i:0;i:1;}', 0, 'fares.mekni.jr@gmail.com', '');

-- --------------------------------------------------------

--
-- Structure de la table `commande_product`
--

CREATE TABLE `commande_product` (
  `commande_id` int(11) NOT NULL,
  `product_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `commande_product`
--

INSERT INTO `commande_product` (`commande_id`, `product_id`) VALUES
(8, 4),
(9, 4),
(11, 6),
(12, 6);

-- --------------------------------------------------------

--
-- Structure de la table `devis`
--

CREATE TABLE `devis` (
  `id` int(11) NOT NULL,
  `reservation_id` int(11) DEFAULT NULL,
  `prix_tot` double NOT NULL,
  `remise` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `devis`
--

INSERT INTO `devis` (`id`, `reservation_id`, `prix_tot`, `remise`) VALUES
(2, 2, 10, 0),
(3, 3, 10, 0),
(4, 4, 10, 0),
(5, 5, 70, 0),
(6, 6, 20, 0),
(7, 7, 20, 0),
(8, 8, 400, 0),
(9, 9, 890, 0),
(10, 10, 690, 0),
(11, 11, 621, 0.1),
(12, 12, 690, 0);

-- --------------------------------------------------------

--
-- Structure de la table `doctrine_migration_versions`
--

CREATE TABLE `doctrine_migration_versions` (
  `version` varchar(191) COLLATE utf8_unicode_ci NOT NULL,
  `executed_at` datetime DEFAULT NULL,
  `execution_time` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `doctrine_migration_versions`
--

INSERT INTO `doctrine_migration_versions` (`version`, `executed_at`, `execution_time`) VALUES
('DoctrineMigrations\\Version20220212023206', '2022-02-23 17:56:13', 406),
('DoctrineMigrations\\Version20220309142052', '2022-03-09 15:22:26', 250),
('DoctrineMigrations\\Version20220309153038', '2022-03-09 16:43:07', 23),
('DoctrineMigrations\\Version20220309154301', '2022-03-09 16:43:07', 1),
('DoctrineMigrations\\Version20220309204104', '2022-03-09 21:41:33', 467),
('DoctrineMigrations\\Version20220309231948', '2022-03-10 00:20:17', 98);

-- --------------------------------------------------------

--
-- Structure de la table `equipement`
--

CREATE TABLE `equipement` (
  `id` int(11) NOT NULL,
  `libelle_equipement` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `image` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `description_equipement` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `prix_equipement` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Structure de la table `evenement`
--

CREATE TABLE `evenement` (
  `id` int(11) NOT NULL,
  `prix_evenement` double NOT NULL,
  `decription_evenement` longtext COLLATE utf8mb4_unicode_ci NOT NULL,
  `titre_evenement` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `nbre_places` int(11) NOT NULL,
  `date_debut` date NOT NULL,
  `date_fin` date NOT NULL,
  `tel` int(11) DEFAULT NULL,
  `email` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `evenement`
--

INSERT INTO `evenement` (`id`, `prix_evenement`, `decription_evenement`, `titre_evenement`, `nbre_places`, `date_debut`, `date_fin`, `tel`, `email`) VALUES
(1, 10, 'ok', 'event', 0, '2022-04-01', '2022-04-04', 23445562, 'fares.mekni.jr@gmail.com'),
(2, 10, 'ahla', 'event', 20, '2017-01-01', '2017-01-01', 56140361, 'fares.mekni.jr@gmail.com'),
(3, 40, 'Randonnée', 'Randonnée', 20, '2017-01-01', '2017-01-01', 56140361, 'fares.mekni.jr@gmail.com'),
(4, 69, 'kayak', 'kayak', 0, '2017-01-01', '2017-01-01', 56140361, 'fares.mekni.jr@gmail.com'),
(5, 89, 'natation', 'natation', 20, '2017-01-01', '2017-01-01', 56140361, 'fares.mekni.jr@gmail.com'),
(6, 25, 'marche', 'marche', 20, '2017-01-01', '2017-01-01', 56140361, 'fares.mekni.jr@gmail.com'),
(7, 20, 'event', 'event', 20, '2022-03-09', '2022-05-01', 56140361, 'fares.mekni.jr@gmail.com');

-- --------------------------------------------------------

--
-- Structure de la table `evenement_activite`
--

CREATE TABLE `evenement_activite` (
  `evenement_id` int(11) NOT NULL,
  `activite_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `evenement_activite`
--

INSERT INTO `evenement_activite` (`evenement_id`, `activite_id`) VALUES
(2, 2),
(7, 2);

-- --------------------------------------------------------

--
-- Structure de la table `images`
--

CREATE TABLE `images` (
  `id` int(11) NOT NULL,
  `image_name` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `images`
--

INSERT INTO `images` (`id`, `image_name`) VALUES
(1, 'Capture d’écran (2)-62291945150ad.png'),
(2, 'Capture d’écran (2)-6229d81372814.png'),
(3, 'Capture d’écran (1)-6229d876258a0.png'),
(4, 'Capture d’écran (2)-6229d89b4b57f.png'),
(5, 'Capture d’écran (1)-6229d8bc2819b.png'),
(6, 'Capture d’écran (1)-6229d94e1ba69.png'),
(7, 'Capture d’écran (2)-6229f62fc50b8.png'),
(8, 'Capture d’écran (1)-6229f67cb975a.png');

-- --------------------------------------------------------

--
-- Structure de la table `messages`
--

CREATE TABLE `messages` (
  `id` int(11) NOT NULL,
  `sender_id` int(11) NOT NULL,
  `recipient_id` int(11) NOT NULL,
  `title` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `message` longtext COLLATE utf8mb4_unicode_ci NOT NULL,
  `created_at` datetime NOT NULL,
  `is_read` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `messages`
--

INSERT INTO `messages` (`id`, `sender_id`, `recipient_id`, `title`, `message`, `created_at`, `is_read`) VALUES
(1, 23, 31, 'FARES', 'kafdzmkahfamkhvf', '2022-03-10 00:23:10', 1),
(2, 31, 45, 'Test2', 'merci', '2022-03-10 14:02:50', 1);

-- --------------------------------------------------------

--
-- Structure de la table `panier`
--

CREATE TABLE `panier` (
  `id` int(11) NOT NULL,
  `date_panier` date NOT NULL,
  `libellep` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Structure de la table `panier_equipement`
--

CREATE TABLE `panier_equipement` (
  `panier_id` int(11) NOT NULL,
  `equipement_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Structure de la table `product`
--

CREATE TABLE `product` (
  `id` int(11) NOT NULL,
  `category_id` int(11) NOT NULL,
  `name` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `description` longtext COLLATE utf8mb4_unicode_ci NOT NULL,
  `price` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `product`
--

INSERT INTO `product` (`id`, `category_id`, `name`, `description`, `price`) VALUES
(3, 6, 'SAC CAMPING', 'TND', '80'),
(4, 9, 'SAC DE COUCHAGE', 'TND', '35'),
(6, 9, 'MOUSTIQUAIRE', 'TND', '12'),
(7, 6, 'Fares', 'merci', '120'),
(8, 7, 'Fares', 'merci', '11');

-- --------------------------------------------------------

--
-- Structure de la table `product_images`
--

CREATE TABLE `product_images` (
  `product_id` int(11) NOT NULL,
  `images_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `product_images`
--

INSERT INTO `product_images` (`product_id`, `images_id`) VALUES
(3, 3),
(4, 4),
(6, 6),
(7, 7),
(8, 8);

-- --------------------------------------------------------

--
-- Structure de la table `reclamation`
--

CREATE TABLE `reclamation` (
  `id` int(11) NOT NULL,
  `id_user_id` int(11) DEFAULT NULL,
  `id_type_id` int(11) DEFAULT NULL,
  `titre` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `desc_rec` longtext COLLATE utf8mb4_unicode_ci NOT NULL,
  `date_rec` date NOT NULL,
  `reponse` longtext COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `etat` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `rating` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `reclamation`
--

INSERT INTO `reclamation` (`id`, `id_user_id`, `id_type_id`, `titre`, `desc_rec`, `date_rec`, `reponse`, `etat`, `rating`) VALUES
(2, 23, 1, 'hgdhkgd', 'tdhfx,g', '2017-01-01', NULL, 'traiter', NULL),
(8, 23, 1, 'hhh', 'kkkk', '2017-01-01', NULL, NULL, NULL),
(9, 23, 2, 'hhh', 'kkkk', '2017-01-01', NULL, NULL, NULL),
(16, 23, 1, 'faffa', 'fafaff', '2017-01-01', NULL, NULL, NULL),
(17, 23, 1, 'dddd', 'ddd', '2017-01-01', NULL, NULL, NULL),
(18, 23, 1, 'fffff', 'dadadadad', '2017-01-01', NULL, NULL, NULL),
(19, 23, 1, 'akzhvfkmhv', 'h:jvfj:hv', '2017-01-01', NULL, NULL, NULL),
(20, 23, 1, 'daadadadad', 'dadada', '2017-01-01', NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Structure de la table `reservation`
--

CREATE TABLE `reservation` (
  `id` int(11) NOT NULL,
  `even_id` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `nb_personne` int(11) NOT NULL,
  `dateres` date NOT NULL,
  `etat` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `reservation`
--

INSERT INTO `reservation` (`id`, `even_id`, `user_id`, `nb_personne`, `dateres`, `etat`) VALUES
(2, 1, 31, 1, '2022-03-09', 1),
(3, 1, 31, 1, '2022-03-09', 1),
(4, 1, 31, 1, '2022-03-09', 1),
(5, 1, 31, 7, '2022-03-10', 1),
(6, 1, 31, 2, '2022-03-10', 0),
(7, 1, 31, 2, '2022-03-10', 0),
(8, 3, 31, 10, '2022-03-10', 0),
(9, 5, 31, 10, '2022-03-10', 0),
(10, 4, 31, 10, '2022-03-10', 1),
(11, 4, 31, 10, '2022-03-10', 1),
(12, 4, 31, 10, '2022-03-10', 0);

-- --------------------------------------------------------

--
-- Structure de la table `reset_password_request`
--

CREATE TABLE `reset_password_request` (
  `id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `selector` varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL,
  `hashed_token` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `requested_at` datetime NOT NULL COMMENT '(DC2Type:datetime_immutable)',
  `expires_at` datetime NOT NULL COMMENT '(DC2Type:datetime_immutable)'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `reset_password_request`
--

INSERT INTO `reset_password_request` (`id`, `user_id`, `selector`, `hashed_token`, `requested_at`, `expires_at`) VALUES
(3, 38, 'aSTPcXKWLdh4geX59PYV', 'Y4j02hk3J2jJKH3v8HVUa3bBHLErtLxg99FBCHTbepg=', '2022-03-07 19:43:52', '2022-03-07 20:43:52');

-- --------------------------------------------------------

--
-- Structure de la table `type_reclamation`
--

CREATE TABLE `type_reclamation` (
  `id` int(11) NOT NULL,
  `description` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `type_reclamation`
--

INSERT INTO `type_reclamation` (`id`, `description`) VALUES
(1, 'profil'),
(2, 'event'),
(3, 'Equipement'),
(4, 'autre');

-- --------------------------------------------------------

--
-- Structure de la table `user`
--

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `email` varchar(180) COLLATE utf8mb4_unicode_ci NOT NULL,
  `roles` longtext COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '(DC2Type:json)',
  `password` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `username` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `is_verified` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `telephone` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `is_expired` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `image` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `mailcode` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `user`
--

INSERT INTO `user` (`id`, `email`, `roles`, `password`, `username`, `is_verified`, `telephone`, `is_expired`, `image`, `mailcode`) VALUES
(68, 'mohamedfares.mekni@esprit.tn', '[\"ROLE_ADMIN\"]', '58656159ece5541a58e7ee7d32baa16a', 'fares', 'false', '58140361', 'false', 'Asus.jpg', '60068'),
(69, 'fares.mekni.jr@gmail.com', '[\"ROLE_USER\"]', '58656159ece5541a58e7ee7d32baa16a', 'faresmekni', '1', '56140361', 'false', 'Capture d’écran (2).png', 'NULL'),
(70, 'labbouz.mariem@esprit.tn', '[\"ROLE_USER\"]', 'd41d8cd98f00b204e9800998ecf8427e', 'mariem', 'false', '95171714', 'false', 'Asus.jpg', '9032'),
(71, 'ferchichi.ghofrane@esprit.tn', '[\"ROLE_USER\"]', '58656159ece5541a58e7ee7d32baa16a', 'ghofrane', 'false', '56140361', 'false', 'Asus.jpg', '64068');

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `activite`
--
ALTER TABLE `activite`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `category_product`
--
ALTER TABLE `category_product`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `commande`
--
ALTER TABLE `commande`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UNIQ_6EEAA67D89663B89` (`idpanier_id`),
  ADD KEY `IDX_6EEAA67D79F37AE5` (`id_user_id`);

--
-- Index pour la table `commande_product`
--
ALTER TABLE `commande_product`
  ADD PRIMARY KEY (`commande_id`,`product_id`),
  ADD KEY `IDX_25F1760D82EA2E54` (`commande_id`),
  ADD KEY `IDX_25F1760D4584665A` (`product_id`);

--
-- Index pour la table `devis`
--
ALTER TABLE `devis`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UNIQ_8B27C52BB83297E7` (`reservation_id`);

--
-- Index pour la table `doctrine_migration_versions`
--
ALTER TABLE `doctrine_migration_versions`
  ADD PRIMARY KEY (`version`);

--
-- Index pour la table `equipement`
--
ALTER TABLE `equipement`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `evenement`
--
ALTER TABLE `evenement`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `evenement_activite`
--
ALTER TABLE `evenement_activite`
  ADD PRIMARY KEY (`evenement_id`,`activite_id`),
  ADD KEY `IDX_3713CEFDFD02F13` (`evenement_id`),
  ADD KEY `IDX_3713CEFD9B0F88B1` (`activite_id`);

--
-- Index pour la table `images`
--
ALTER TABLE `images`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `messages`
--
ALTER TABLE `messages`
  ADD PRIMARY KEY (`id`),
  ADD KEY `IDX_DB021E96F624B39D` (`sender_id`),
  ADD KEY `IDX_DB021E96E92F8F78` (`recipient_id`);

--
-- Index pour la table `panier`
--
ALTER TABLE `panier`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `panier_equipement`
--
ALTER TABLE `panier_equipement`
  ADD PRIMARY KEY (`panier_id`,`equipement_id`),
  ADD KEY `IDX_DC61FF66F77D927C` (`panier_id`),
  ADD KEY `IDX_DC61FF66806F0F5C` (`equipement_id`);

--
-- Index pour la table `product`
--
ALTER TABLE `product`
  ADD PRIMARY KEY (`id`),
  ADD KEY `IDX_D34A04AD12469DE2` (`category_id`);

--
-- Index pour la table `product_images`
--
ALTER TABLE `product_images`
  ADD PRIMARY KEY (`product_id`,`images_id`),
  ADD KEY `IDX_8263FFCE4584665A` (`product_id`),
  ADD KEY `IDX_8263FFCED44F05E5` (`images_id`);

--
-- Index pour la table `reclamation`
--
ALTER TABLE `reclamation`
  ADD PRIMARY KEY (`id`),
  ADD KEY `IDX_CE60640479F37AE5` (`id_user_id`),
  ADD KEY `IDX_CE6064041BD125E3` (`id_type_id`);

--
-- Index pour la table `reservation`
--
ALTER TABLE `reservation`
  ADD PRIMARY KEY (`id`),
  ADD KEY `IDX_42C849558AB236CF` (`even_id`),
  ADD KEY `IDX_42C84955A76ED395` (`user_id`);

--
-- Index pour la table `reset_password_request`
--
ALTER TABLE `reset_password_request`
  ADD PRIMARY KEY (`id`),
  ADD KEY `IDX_7CE748AA76ED395` (`user_id`);

--
-- Index pour la table `type_reclamation`
--
ALTER TABLE `type_reclamation`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UNIQ_8D93D649E7927C74` (`email`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `activite`
--
ALTER TABLE `activite`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT pour la table `category_product`
--
ALTER TABLE `category_product`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT pour la table `commande`
--
ALTER TABLE `commande`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT pour la table `devis`
--
ALTER TABLE `devis`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT pour la table `equipement`
--
ALTER TABLE `equipement`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `evenement`
--
ALTER TABLE `evenement`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT pour la table `images`
--
ALTER TABLE `images`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT pour la table `messages`
--
ALTER TABLE `messages`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT pour la table `panier`
--
ALTER TABLE `panier`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `product`
--
ALTER TABLE `product`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT pour la table `reclamation`
--
ALTER TABLE `reclamation`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=25;

--
-- AUTO_INCREMENT pour la table `reservation`
--
ALTER TABLE `reservation`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT pour la table `reset_password_request`
--
ALTER TABLE `reset_password_request`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT pour la table `type_reclamation`
--
ALTER TABLE `type_reclamation`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT pour la table `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=72;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `commande`
--
ALTER TABLE `commande`
  ADD CONSTRAINT `FK_6EEAA67D79F37AE5` FOREIGN KEY (`id_user_id`) REFERENCES `user` (`id`),
  ADD CONSTRAINT `FK_6EEAA67D89663B89` FOREIGN KEY (`idpanier_id`) REFERENCES `panier` (`id`);

--
-- Contraintes pour la table `commande_product`
--
ALTER TABLE `commande_product`
  ADD CONSTRAINT `FK_25F1760D4584665A` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `FK_25F1760D82EA2E54` FOREIGN KEY (`commande_id`) REFERENCES `commande` (`id`) ON DELETE CASCADE;

--
-- Contraintes pour la table `devis`
--
ALTER TABLE `devis`
  ADD CONSTRAINT `FK_8B27C52BB83297E7` FOREIGN KEY (`reservation_id`) REFERENCES `reservation` (`id`);

--
-- Contraintes pour la table `evenement_activite`
--
ALTER TABLE `evenement_activite`
  ADD CONSTRAINT `FK_3713CEFD9B0F88B1` FOREIGN KEY (`activite_id`) REFERENCES `activite` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `FK_3713CEFDFD02F13` FOREIGN KEY (`evenement_id`) REFERENCES `evenement` (`id`) ON DELETE CASCADE;

--
-- Contraintes pour la table `messages`
--
ALTER TABLE `messages`
  ADD CONSTRAINT `FK_DB021E96E92F8F78` FOREIGN KEY (`recipient_id`) REFERENCES `user` (`id`),
  ADD CONSTRAINT `FK_DB021E96F624B39D` FOREIGN KEY (`sender_id`) REFERENCES `user` (`id`);

--
-- Contraintes pour la table `panier_equipement`
--
ALTER TABLE `panier_equipement`
  ADD CONSTRAINT `FK_DC61FF66806F0F5C` FOREIGN KEY (`equipement_id`) REFERENCES `equipement` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `FK_DC61FF66F77D927C` FOREIGN KEY (`panier_id`) REFERENCES `panier` (`id`) ON DELETE CASCADE;

--
-- Contraintes pour la table `product`
--
ALTER TABLE `product`
  ADD CONSTRAINT `FK_D34A04AD12469DE2` FOREIGN KEY (`category_id`) REFERENCES `category_product` (`id`);

--
-- Contraintes pour la table `product_images`
--
ALTER TABLE `product_images`
  ADD CONSTRAINT `FK_8263FFCE4584665A` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `FK_8263FFCED44F05E5` FOREIGN KEY (`images_id`) REFERENCES `images` (`id`) ON DELETE CASCADE;

--
-- Contraintes pour la table `reclamation`
--
ALTER TABLE `reclamation`
  ADD CONSTRAINT `FK_CE6064041BD125E3` FOREIGN KEY (`id_type_id`) REFERENCES `type_reclamation` (`id`),
  ADD CONSTRAINT `FK_CE60640479F37AE5` FOREIGN KEY (`id_user_id`) REFERENCES `user` (`id`);

--
-- Contraintes pour la table `reservation`
--
ALTER TABLE `reservation`
  ADD CONSTRAINT `FK_42C849558AB236CF` FOREIGN KEY (`even_id`) REFERENCES `evenement` (`id`),
  ADD CONSTRAINT `FK_42C84955A76ED395` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);

--
-- Contraintes pour la table `reset_password_request`
--
ALTER TABLE `reset_password_request`
  ADD CONSTRAINT `FK_7CE748AA76ED395` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
