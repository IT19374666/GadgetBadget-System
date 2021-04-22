-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3307
-- Generation Time: Apr 22, 2021 at 05:29 PM
-- Server version: 10.4.17-MariaDB
-- PHP Version: 8.0.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `funds_and_fundingbody_managment`
--

-- --------------------------------------------------------

--
-- Table structure for table `fundingbody`
--

CREATE TABLE `fundingbody` (
  `idFundingBody` int(11) NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `address` varchar(45) DEFAULT NULL,
  `phone` int(10) DEFAULT NULL,
  `interestArea` varchar(45) DEFAULT NULL,
  `fund_range` decimal(15,2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `fundingbody`
--

INSERT INTO `fundingbody` (`idFundingBody`, `name`, `email`, `address`, `phone`, `interestArea`, `fund_range`) VALUES
(1, 'Y.S.Dias', 'dias123@gmail.com', '10/2,Colombo 7', 772569325, 'IoT', '600000.00'),
(2, 'W.D.Perera', 'perera345@gmail.com', '20/4,Kalubowila,Nugegoda', 752698321, 'DataScience', '200000.00'),
(3, 'S.L.Yasas', 'yasas567@gmail.com', '44/5,Kakirawa,Anuradhapura', 714896325, 'Machine learning', '100000.00'),
(6, 'A.S.Jayasinghe', 'jaya2021@yahoo.com', '15/8,Awissawella road,Seethawaka', 715896324, 'IoT', '90000.00'),
(7, 'A.A.Rupasinghe', 'acreations123@gmail.com', '7/A,High level road,Maharagama', 115836752, 'Health ', '800000.00');

-- --------------------------------------------------------

--
-- Table structure for table `funds`
--

CREATE TABLE `funds` (
  `FundID` int(11) NOT NULL,
  `researchID` int(11) DEFAULT NULL,
  `fundingBodyID` int(11) DEFAULT NULL,
  `currentStage` int(11) DEFAULT NULL,
  `fundsForCurrentStage` decimal(15,2) DEFAULT NULL,
  `totalFunds` decimal(15,2) DEFAULT NULL,
  `description` varchar(45) DEFAULT NULL,
  `totalFundedAmount` decimal(15,2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `funds`
--

INSERT INTO `funds` (`FundID`, `researchID`, `fundingBodyID`, `currentStage`, `fundsForCurrentStage`, `totalFunds`, `description`, `totalFundedAmount`) VALUES
(1, 1, 3, 2, '20000.00', '100000.00', 'quater for each stage', NULL),
(2, 5, 2, 3, '15000.00', '60000.00', 'quater for each stage', '45000.00'),
(3, 9, 2, 3, '24000.00', '96000.00', 'quater for each stage', '72000.00'),
(4, 4, 5, 2, '12000.00', '48000.00', 'quater for each stage', '24000.00'),
(5, 5, 2, 1, '15000.00', '60000.00', '4 stages', '15000.00');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `fundingbody`
--
ALTER TABLE `fundingbody`
  ADD PRIMARY KEY (`idFundingBody`);

--
-- Indexes for table `funds`
--
ALTER TABLE `funds`
  ADD PRIMARY KEY (`FundID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `fundingbody`
--
ALTER TABLE `fundingbody`
  MODIFY `idFundingBody` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `funds`
--
ALTER TABLE `funds`
  MODIFY `FundID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
