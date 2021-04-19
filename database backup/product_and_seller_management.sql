-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3307
-- Generation Time: Apr 19, 2021 at 02:47 PM
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
-- Database: `product_and_seller_management`
--

-- --------------------------------------------------------

--
-- Table structure for table `product`
--

CREATE TABLE `product` (
  `ProductID` int(11) NOT NULL,
  `ProductName` varchar(45) NOT NULL DEFAULT 'NOTNULL',
  `ProductType` varchar(45) NOT NULL,
  `MinimumPrice` decimal(15,2) NOT NULL,
  `ProductDescription` varchar(300) NOT NULL,
  `AddDate` date NOT NULL,
  `ClosingDate` date NOT NULL,
  `SellerID` varchar(30) DEFAULT NULL,
  `ProductStatus` varchar(10) NOT NULL,
  `CustomerID` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `product`
--

INSERT INTO `product` (`ProductID`, `ProductName`, `ProductType`, `MinimumPrice`, `ProductDescription`, `AddDate`, `ClosingDate`, `SellerID`, `ProductStatus`, `CustomerID`) VALUES
(1, 'Magic Watch', 'Health', '90000.00', 'Heart Beat counting watch', '2021-03-12', '2021-06-21', 'S001Nimal', 'Sold', 'Saman01'),
(2, 'Stainless steel insert', 'Food', '25000.00', 'that\'ll transform your charcoal grill into a PIZZA OVEN. Yes, you can make pizza on your grill, and dinner is at your house!', '2020-12-02', '2021-05-25', 'S002Kamal', 'Available', NULL),
(3, 'Step count shoe', 'Health', '50000.00', 'count the step when you walk', '2021-02-21', '2021-06-25', 'S003Amal', 'Available', NULL),
(4, 'Magnetic strips', 'Food', '95000.00', 'designed to hang beer bottles and keep them off your shelf so there is more room in your fridge. ', '2021-03-05', '2021-05-21', 'S004Samantha', 'Available', NULL),
(5, 'Pan stirrer ', 'Food', '100000.00', 'Your arms will be forever thankful for when a recipe calls for continuous stirring.', '2021-03-06', '2021-05-16', 'S005Nishantha', 'Available', NULL);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `product`
--
ALTER TABLE `product`
  ADD PRIMARY KEY (`ProductID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `product`
--
ALTER TABLE `product`
  MODIFY `ProductID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
