-- phpMyAdmin SQL Dump
-- version 3.4.5
-- http://www.phpmyadmin.net
--
-- Servidor: localhost
-- Tiempo de generaci�n: 13-02-2012 a las 20:15:08
-- Versi�n del servidor: 5.5.16
-- Versi�n de PHP: 5.3.8

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";

--
-- Base de datos: `alumnos`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `alumnos`
--

CREATE TABLE IF NOT EXISTS `alumnos` (
  `DNI` varchar(9) NOT NULL,
  `Nombre` varchar(50) NOT NULL,
  `Apellidos` varchar(70) NOT NULL,
  `Direccion` varchar(100) NOT NULL,
  `FechaNac` date NOT NULL,
  PRIMARY KEY (`DNI`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `alumnos`
--

INSERT INTO `alumnos` (`DNI`, `Nombre`, `Apellidos`, `Direccion`, `FechaNac`) VALUES
('12345678A', 'Jos� Alberto', 'Gonz�lez P�rez', 'C/Albahaca, n�14, 1�D', '1986-07-15'),
('23456789B', 'Almudena', 'Cantero Verdemar', 'Avd/ Profesor Alvarado, n27, 8�A', '1988-11-04'),
('14785236d', 'Mart�n', 'D�az Jim�nez', 'C/Luis de Gongora, n�2.', '1987-03-09'),
('96385274f', 'Lucas', 'Buendia Portes', 'C/Pintor Sorolla, n� 16, 4�B', '1988-07-10');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `matriculas`
--

CREATE TABLE IF NOT EXISTS `matriculas` (
  `DNI` varchar(9) NOT NULL,
  `NombreModulo` varchar(60) NOT NULL,
  `Curso` varchar(5) NOT NULL,
  `Nota` double NOT NULL,
  PRIMARY KEY (`DNI`,`NombreModulo`,`Curso`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `matriculas`
--

INSERT INTO `matriculas` (`DNI`, `NombreModulo`, `Curso`, `Nota`) VALUES
('12345678A', 'Acceso a datos', '11-12', 7.5),
('12345678A', 'Desarrollo de Interfaces', '11-12', 8.6),
('12345678A', 'Entornos de desarrollo', '11-12', 9),
('12345678A', 'Sistemas inform�ticos', '11-12', 7.3);
