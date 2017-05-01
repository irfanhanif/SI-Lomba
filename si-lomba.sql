-- phpMyAdmin SQL Dump
-- version 4.6.5.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 01, 2017 at 06:10 AM
-- Server version: 10.1.21-MariaDB
-- PHP Version: 7.1.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `si-lomba`
--

-- --------------------------------------------------------

--
-- Table structure for table `anggota_tim`
--

CREATE TABLE `anggota_tim` (
  `id_anggota_tim` int(11) NOT NULL,
  `id_tim` int(11) NOT NULL,
  `id_job` int(11) NOT NULL,
  `id_user` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `bimbingan`
--

CREATE TABLE `bimbingan` (
  `id_bimbingan` int(11) NOT NULL,
  `id_tim` int(11) NOT NULL,
  `id_dosbing` int(11) NOT NULL,
  `tanggal_bimbingan` date NOT NULL,
  `comment` longtext NOT NULL,
  `file_bimbingan` varchar(150) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `dosbing`
--

CREATE TABLE `dosbing` (
  `id_dosbing` int(11) NOT NULL,
  `nama_dosbing` varchar(100) NOT NULL,
  `jurusan_dosbing` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `job`
--

CREATE TABLE `job` (
  `id_job` int(11) NOT NULL,
  `nama_job` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `lomba`
--

CREATE TABLE `lomba` (
  `id_lomba` int(11) NOT NULL,
  `nama_lomba` varchar(30) NOT NULL,
  `penyelenggara` varchar(30) NOT NULL,
  `kategori` varchar(30) NOT NULL,
  `hadiah` varchar(30) NOT NULL,
  `syarat` longtext NOT NULL,
  `deskripsi_lomba` longtext NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `request_tim`
--

CREATE TABLE `request_tim` (
  `id_request` int(11) NOT NULL,
  `id_user` int(11) NOT NULL,
  `id_tim` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `riwayat_user`
--

CREATE TABLE `riwayat_user` (
  `id_riwayat_user` int(11) NOT NULL,
  `id_user` int(11) NOT NULL,
  `id_lomba` int(11) NOT NULL,
  `status` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `tim`
--

CREATE TABLE `tim` (
  `id_tim` int(11) NOT NULL,
  `id_user` int(11) NOT NULL,
  `nama_tim` varchar(50) NOT NULL,
  `maksimal_anggota` int(11) NOT NULL,
  `deskripsi_tim` longtext NOT NULL,
  `file_fotoprofil_tim` varchar(150) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `tim_ikut_lomba`
--

CREATE TABLE `tim_ikut_lomba` (
  `id_tim_ikut_lomba` int(11) NOT NULL,
  `id_tim` int(11) NOT NULL,
  `id_lomba` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `id_user` int(11) NOT NULL,
  `username` varchar(30) NOT NULL,
  `password` varchar(30) NOT NULL,
  `nama` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `jurusan` varchar(150) NOT NULL,
  `file_fotoprofil` varchar(150) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `anggota_tim`
--
ALTER TABLE `anggota_tim`
  ADD PRIMARY KEY (`id_anggota_tim`),
  ADD KEY `id_tim` (`id_tim`),
  ADD KEY `id_job` (`id_job`),
  ADD KEY `id_user` (`id_user`);

--
-- Indexes for table `bimbingan`
--
ALTER TABLE `bimbingan`
  ADD PRIMARY KEY (`id_bimbingan`),
  ADD KEY `id_tim` (`id_tim`),
  ADD KEY `id_dosbing` (`id_dosbing`);

--
-- Indexes for table `dosbing`
--
ALTER TABLE `dosbing`
  ADD PRIMARY KEY (`id_dosbing`);

--
-- Indexes for table `job`
--
ALTER TABLE `job`
  ADD PRIMARY KEY (`id_job`);

--
-- Indexes for table `lomba`
--
ALTER TABLE `lomba`
  ADD PRIMARY KEY (`id_lomba`);

--
-- Indexes for table `request_tim`
--
ALTER TABLE `request_tim`
  ADD PRIMARY KEY (`id_request`),
  ADD KEY `id_user` (`id_user`),
  ADD KEY `id_tim` (`id_tim`);

--
-- Indexes for table `riwayat_user`
--
ALTER TABLE `riwayat_user`
  ADD PRIMARY KEY (`id_riwayat_user`),
  ADD KEY `id_user` (`id_user`),
  ADD KEY `id_lomba` (`id_lomba`);

--
-- Indexes for table `tim`
--
ALTER TABLE `tim`
  ADD PRIMARY KEY (`id_tim`);

--
-- Indexes for table `tim_ikut_lomba`
--
ALTER TABLE `tim_ikut_lomba`
  ADD PRIMARY KEY (`id_tim_ikut_lomba`),
  ADD KEY `id_tim` (`id_tim`),
  ADD KEY `id_lomba` (`id_lomba`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id_user`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `anggota_tim`
--
ALTER TABLE `anggota_tim`
  ADD CONSTRAINT `anggota_tim_ibfk_1` FOREIGN KEY (`id_tim`) REFERENCES `tim` (`id_tim`),
  ADD CONSTRAINT `anggota_tim_ibfk_2` FOREIGN KEY (`id_job`) REFERENCES `job` (`id_job`),
  ADD CONSTRAINT `anggota_tim_ibfk_3` FOREIGN KEY (`id_user`) REFERENCES `user` (`id_user`);

--
-- Constraints for table `bimbingan`
--
ALTER TABLE `bimbingan`
  ADD CONSTRAINT `bimbingan_ibfk_1` FOREIGN KEY (`id_tim`) REFERENCES `tim` (`id_tim`),
  ADD CONSTRAINT `bimbingan_ibfk_2` FOREIGN KEY (`id_dosbing`) REFERENCES `dosbing` (`id_dosbing`);

--
-- Constraints for table `request_tim`
--
ALTER TABLE `request_tim`
  ADD CONSTRAINT `request_tim_ibfk_1` FOREIGN KEY (`id_user`) REFERENCES `user` (`id_user`),
  ADD CONSTRAINT `request_tim_ibfk_2` FOREIGN KEY (`id_tim`) REFERENCES `tim` (`id_tim`);

--
-- Constraints for table `riwayat_user`
--
ALTER TABLE `riwayat_user`
  ADD CONSTRAINT `riwayat_user_ibfk_1` FOREIGN KEY (`id_user`) REFERENCES `user` (`id_user`),
  ADD CONSTRAINT `riwayat_user_ibfk_2` FOREIGN KEY (`id_lomba`) REFERENCES `lomba` (`id_lomba`);

--
-- Constraints for table `tim_ikut_lomba`
--
ALTER TABLE `tim_ikut_lomba`
  ADD CONSTRAINT `tim_ikut_lomba_ibfk_1` FOREIGN KEY (`id_tim`) REFERENCES `tim` (`id_tim`),
  ADD CONSTRAINT `tim_ikut_lomba_ibfk_2` FOREIGN KEY (`id_lomba`) REFERENCES `lomba` (`id_lomba`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
