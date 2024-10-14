-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Oct 12, 2024 at 05:39 PM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `booking2`
--

-- --------------------------------------------------------

--
-- Table structure for table `conversation`
--

CREATE TABLE `conversation` (
  `id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `messages`
--

CREATE TABLE `messages` (
  `id` int(11) NOT NULL,
  `message` varchar(255) DEFAULT NULL,
  `conversation_id` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `participants`
--

CREATE TABLE `participants` (
  `conversation_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `pictures`
--

CREATE TABLE `pictures` (
  `id` int(11) NOT NULL,
  `picture_path` varchar(255) DEFAULT NULL,
  `property_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `pictures`
--

INSERT INTO `pictures` (`id`, `picture_path`, `property_id`) VALUES
(14, '180709964.jpg', 44),
(16, 'blgrd.jpeg', 44);

-- --------------------------------------------------------

--
-- Table structure for table `property`
--

CREATE TABLE `property` (
  `id` int(11) NOT NULL,
  `description` varchar(1000) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `landlord_id` int(11) DEFAULT NULL,
  `location` varchar(255) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `picture_path` varchar(255) DEFAULT NULL,
  `ac` bit(1) DEFAULT NULL,
  `breakfast` bit(1) DEFAULT NULL,
  `coordinate_lat` varchar(255) DEFAULT NULL,
  `coordinate_lng` varchar(255) DEFAULT NULL,
  `dinner` bit(1) DEFAULT NULL,
  `guest_number` int(11) DEFAULT NULL,
  `kitchen` bit(1) DEFAULT NULL,
  `lunch` bit(1) DEFAULT NULL,
  `pet_friendly` bit(1) DEFAULT NULL,
  `smoking_allowed` bit(1) DEFAULT NULL,
  `tv` bit(1) DEFAULT NULL,
  `wi_fi` bit(1) DEFAULT NULL,
  `addres` varchar(255) DEFAULT NULL,
  `free_cancelation` bit(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `property`
--

INSERT INTO `property` (`id`, `description`, `title`, `type`, `landlord_id`, `location`, `price`, `picture_path`, `ac`, `breakfast`, `coordinate_lat`, `coordinate_lng`, `dinner`, `guest_number`, `kitchen`, `lunch`, `pet_friendly`, `smoking_allowed`, `tv`, `wi_fi`, `addres`, `free_cancelation`) VALUES
(5, 'Poznata po svetskim noćnim klubovima i kristalno čistim plažama, savršena za zabavu i odmor.	', 'Ibiza Nightlife	', 'Vila', 35, 'Španija', 250, '166286302.jpg', b'1', b'0', '0', '0', b'1', 0, b'0', b'1', b'0', b'0', b'0', b'1', 'Calle Mayor 50, Madrid', b'1'),
(6, 'Putovanje kroz istorijske gradove Sevilju, Granadu i Kordobu, sa zapanjujućom arhitekturom.', 'Andalusian Journey', 'Hotel', 34, 'Španija', 160, '161172301.jpg', b'1', b'0', '0', '0', b'1', 0, b'0', b'1', b'0', b'1', b'1', b'0', 'Calle Mayor 50, Madrid', b'1'),
(8, 'Luksuzni odmor na Azurnoj obali, sa plavim morem i glamuroznim mestima poput Nice i Kana.', 'French Riviera Escape', 'Vikendica', 35, 'Francuska', 300, '294522352.jpg', b'0', b'0', '0', '0', b'1', 0, b'0', b'1', b'1', b'0', b'1', b'0', 'Rue de Rivoli 10, Paris', b'1'),
(11, 'Provedite nezaboravan dan vozeći se gondolama kroz romantične kanale Venecije.	', 'Venice Canal Romance', 'Apartman', 34, 'Italija', 220, '88845342.jpg', b'0', b'0', '0', '0', b'0', 0, b'0', b'1', b'1', b'0', b'0', b'0', 'Via Roma 25, Rome', b'1'),
(12, 'Otkrijte prelepe primorske gradove kao što su Positano i Amalfi sa spektakularnim pogledima.', 'Amalfi Coast Retreat', 'Vila', 35, 'Italija', 330, '571683246.jpg', b'0', b'0', '0', '0', b'1', 0, b'0', b'0', b'0', b'1', b'1', b'0', 'Via Roma 25, Rome', b'1'),
(14, 'Nudimo Vam komforni standardni i luksuzni apartmani sa garažnim mestom, kuhinjom, parkingom i svim potrebnim dodatnim uslugama.', 'Sky', 'Apartman', 34, 'Srbija', 100, '316991459.jpg', b'0', b'1', '0', '0', b'0', 0, b'0', b'1', b'1', b'1', b'0', b'0', 'Kneza Mihaila 42, Beograd', b'1'),
(17, 'Kristalno čiste plaže i luksuzni resorti idealni za odmor i avanturu na ostrvu Puket.', 'Phuket Beach Paradise', 'Vila', 34, 'Tajland', 130, '425071780.jpg', b'1', b'0', '0', '0', b'0', 0, b'0', b'0', b'1', b'1', b'1', b'0', 'Sukhumvit Road 21, Bangkok', b'1'),
(20, 'Moderan apartman sa luksuznim sadržajima, u blizini Central Parka, idealan za duži boravak.	', 'AKA Central Park', 'Apartman', 34, 'Amerika', 700, '35246237.jpg', b'1', b'0', '0', '0', b'1', 0, b'0', b'0', b'0', b'0', b'1', b'1', '42 W 58th St, New York, NY 10019', b'1'),
(21, 'Luksuzna vila sa bazenom, privatnim vrtom i modernim dizajnom, smeštena u prestižnom delu grada.', 'The Beverly Hills Estate', 'Vila', 34, 'Amerika', 2000, 'beverly.jpg', b'1', b'0', '0', '0', b'1', 0, b'0', b'1', b'0', b'0', b'0', b'0', '1100 Carolyn Way, Beverly Hills, CA 90210', b'1'),
(40, 'Istražite Gaudijeva remek-dela, uživajte u prelepim plažama i bogatoj katalonskoj kulturi.', 'Barcelona Culture & Sea', 'Apartman', 50, 'Španija', 180, '474527617.jpg', b'1', b'0', '1', '1', b'0', 2, b'1', b'0', b'1', b'1', b'1', b'0', 'Carrer de Casanova', b'1'),
(41, 'Romantično putovanje kroz najpoznatije znamenitosti Pariza, uključujući Ajfelov toranj.	', 'Parisian Dream', 'Hotel', 50, 'Francuska', 200, '206871828.jpg', b'0', b'0', '1', '1', b'0', 4, b'0', b'0', b'1', b'1', b'1', b'0', 'Rue de Rivoli , Pariz', b'1'),
(42, 'Istražite veličanstvene dvorce doline Loare i vinograde u srcu Francuske.	', 'Loire Valley Castles', 'Hotel', 50, 'Francuska', 330, '492010980.jpg', b'1', b'1', '1', '1', b'1', 2, b'0', b'1', b'1', b'0', b'1', b'0', 'Rue des Tanneurs , Dolina Loare', b'0'),
(43, 'Istražite Koloseum, Rimsku forum i Vatikanske muzeje u srcu večnog grada.	', 'Rome Ancient Wonders', 'Apartman', 50, 'Italija', 180, '474534899.jpg', b'1', b'0', '1', '1', b'0', 4, b'1', b'0', b'0', b'1', b'1', b'0', 'Via dei Fori Imperiali , Rome', b'1'),
(44, 'Hotel Moskva smešten je u zgradi izgrađenoj u ampir stilu, koja predstavlja gradsku znamenitost. Gosti mogu besplatno i neograničeno koristiti velnes i spa centar, kao i teretanu. Hotel se nalazi na idealnoj lokaciji u glavnoj beogradskoj ulici. Poseduje à-la-carte restoran i čuvenu poslastičarnicu. U elegantnim i klimatizovanim smeštajnim jedinicama obezbeđen je besplatan bežični internet.', 'Moskva', 'Hotel', 50, 'Srbija', 220, '76505261.jpg', b'1', b'1', '1', '1', b'1', 2, b'0', b'1', b'1', b'1', b'1', b'0', 'Balkanska 1, Stari Grad, Beograd', b'1'),
(45, 'koničan luksuzni hotel na uglu Central Parka, poznat po glamuru i vrhunskoj usluzi.', 'The Plaza Hotel', 'Hotel', 50, 'Amerika', 800, 'plaza.jpg', b'1', b'1', '1', '1', b'1', 1, b'0', b'1', b'1', b'0', b'1', b'0', 'New York City , New York', b'0');

-- --------------------------------------------------------

--
-- Table structure for table `reservation`
--

CREATE TABLE `reservation` (
  `id` int(11) NOT NULL,
  `end_date` date DEFAULT NULL,
  `start_date` date DEFAULT NULL,
  `property_id` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `reservation`
--

INSERT INTO `reservation` (`id`, `end_date`, `start_date`, `property_id`, `user_id`) VALUES
(7, '2024-10-10', '2024-10-09', 40, 50),
(8, '2024-10-11', '2024-10-10', 44, 50),
(12, '2024-10-12', '2024-10-11', 5, 50),
(13, '2024-10-13', '2024-10-12', 12, 51);

-- --------------------------------------------------------

--
-- Table structure for table `reviews`
--

CREATE TABLE `reviews` (
  `id` int(11) NOT NULL,
  `review` varchar(255) DEFAULT NULL,
  `star_number` int(11) DEFAULT NULL,
  `property_id` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `reviews`
--

INSERT INTO `reviews` (`id`, `review`, `star_number`, `property_id`, `user_id`) VALUES
(5, 'najbolja usluga , najbolja lokacija , najbolji hotel !', 5, 44, 50),
(6, 'najgora usluga , najgora lokacija , najgora hotel !', 1, 44, 50),
(8, 'bla bla bla', 3, 44, 50),
(9, 'super objekat , doci cu ponovo', 5, 40, 48),
(10, 'sjajno', 5, 40, 48);

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `role` enum('ADMIN','IZDAVAC','KORISNIK') DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `address`, `email`, `password`, `phone`, `role`, `username`) VALUES
(34, 'AA52', 'gmail@com.com', '$2a$10$iSvjemjqnOhsvjsT/h8SPeSafMQWjLaMS.YIfvaaAJ..Yrrb5gIxW', '032301230', 'KORISNIK', 'testupdate'),
(35, 'marko', 'marko@gmail.com', '$2a$10$lEOs0u1Gc91R1em/6wpBeOv4E0.ILtvEA4lje86B4Xt0OXVQLehEq', '', 'KORISNIK', 'markan'),
(45, 'Nemanjina 52, Beograd', 'user1234@gmail.com', '$2a$10$ae9ctjLO/nCxAOeTolIbn.ROwdES.OtTiwVRI6pVmZtq4SfD9iPDa', '032301230', 'KORISNIK', 'user1234'),
(48, 'Jugoslovenske Armije 19 , Backa Palanka', 'test@gmail.com', '$2a$10$EqiiLCllo1HenQd9aDIR.uGvwfJUSgZSRZyvDRwHPHWDJt/ZLB6OO', '032301230', 'IZDAVAC', 'test'),
(50, 'JA56', 'komadmatija56@gmail.com', '$2a$10$1md71QL/5ZokTI.Xd1w9ZeccObgNc31e8QWne54YglqkBvqmQaP7O', '1234565', 'IZDAVAC', 'matija'),
(51, 'JA56', 'test1@gmail.com', '$2a$10$3.k1Nuv0Raat.xXP9egF1.2RNIMTabExthWVMQD.dXa2Do6oNNbNG', '1234565', 'KORISNIK', 'test'),
(52, 'JA56', 'admin@gmail.com', '$2a$10$EUtpeUG8nQuzi/.Pk.uoEe0BxvpRH7zWQhi5i/.2bnAvVAl8R0k/6', '0606400779', 'ADMIN', 'admin'),
(54, 'Nemanjina 52', 'miljan@gmail.com', '$2a$10$1fEp7uIUIUJUkoM0AxzD0OrhKe1/2AGH2T9xBKBTwq9mVKaZjfRRa', '1234565', NULL, 'Miljan'),
(55, 'Nemanjina 52', 'tadija@gmail.com', '$2a$10$hHbgLv14a.iIt/ao4wJgIudQ2Fp9K7lMsLCR98sOVqEwdf8PBoAGC', '1234565', NULL, 'tadija'),
(57, 'Nemanjina 52', 'branka@gmail.com', '$2a$10$uMjNlqKmne8dSOigMJ3VluJdV5kQFkLxTMw6fTnjYUHD/Yc1a9awS', '1234565', 'KORISNIK', 'Branka'),
(58, 'Nemanjina 52', 'nina@gmail.com', '$2a$10$b.Z6636h0QC6depDSMUszeO9a2CRJF.6qLRi7otAhDtGJpnT//90W', '1234565', 'KORISNIK', 'Nina');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `conversation`
--
ALTER TABLE `conversation`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `messages`
--
ALTER TABLE `messages`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKde7b83jwmlq63aqpj5qtege0h` (`conversation_id`),
  ADD KEY `FK2qlxjpv0tk58347eku71n54eg` (`user_id`);

--
-- Indexes for table `participants`
--
ALTER TABLE `participants`
  ADD KEY `FKlygocmohuh11svfduxxw8ykk6` (`user_id`),
  ADD KEY `FK9y2iiymbmwgdumd9t6nexj81m` (`conversation_id`);

--
-- Indexes for table `pictures`
--
ALTER TABLE `pictures`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKs09619fabpt694rs8ol6rkedn` (`property_id`);

--
-- Indexes for table `property`
--
ALTER TABLE `property`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKrapbqvky0l3r9q4hu5a61il5r` (`landlord_id`);

--
-- Indexes for table `reservation`
--
ALTER TABLE `reservation`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKc1js26be8wn824r1uhmpfe7qk` (`property_id`),
  ADD KEY `FKm4oimk0l1757o9pwavorj6ljg` (`user_id`);

--
-- Indexes for table `reviews`
--
ALTER TABLE `reviews`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK14laql60ccbgwtecv3xflkcj7` (`property_id`),
  ADD KEY `FKsdlcf7wf8l1k0m00gik0m6b1m` (`user_id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `uc_email` (`email`),
  ADD UNIQUE KEY `UKob8kqyqqgmefl0aco34akdtpe` (`email`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `conversation`
--
ALTER TABLE `conversation`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `messages`
--
ALTER TABLE `messages`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `pictures`
--
ALTER TABLE `pictures`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT for table `property`
--
ALTER TABLE `property`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=50;

--
-- AUTO_INCREMENT for table `reservation`
--
ALTER TABLE `reservation`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT for table `reviews`
--
ALTER TABLE `reviews`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=69;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `messages`
--
ALTER TABLE `messages`
  ADD CONSTRAINT `FK2qlxjpv0tk58347eku71n54eg` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  ADD CONSTRAINT `FKde7b83jwmlq63aqpj5qtege0h` FOREIGN KEY (`conversation_id`) REFERENCES `conversation` (`id`);

--
-- Constraints for table `participants`
--
ALTER TABLE `participants`
  ADD CONSTRAINT `FK9y2iiymbmwgdumd9t6nexj81m` FOREIGN KEY (`conversation_id`) REFERENCES `conversation` (`id`),
  ADD CONSTRAINT `FKlygocmohuh11svfduxxw8ykk6` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);

--
-- Constraints for table `pictures`
--
ALTER TABLE `pictures`
  ADD CONSTRAINT `FKs09619fabpt694rs8ol6rkedn` FOREIGN KEY (`property_id`) REFERENCES `property` (`id`);

--
-- Constraints for table `property`
--
ALTER TABLE `property`
  ADD CONSTRAINT `FKrapbqvky0l3r9q4hu5a61il5r` FOREIGN KEY (`landlord_id`) REFERENCES `user` (`id`);

--
-- Constraints for table `reservation`
--
ALTER TABLE `reservation`
  ADD CONSTRAINT `FKc1js26be8wn824r1uhmpfe7qk` FOREIGN KEY (`property_id`) REFERENCES `property` (`id`),
  ADD CONSTRAINT `FKm4oimk0l1757o9pwavorj6ljg` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);

--
-- Constraints for table `reviews`
--
ALTER TABLE `reviews`
  ADD CONSTRAINT `FK14laql60ccbgwtecv3xflkcj7` FOREIGN KEY (`property_id`) REFERENCES `property` (`id`),
  ADD CONSTRAINT `FKsdlcf7wf8l1k0m00gik0m6b1m` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
