-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 22-11-2025 a las 00:02:42
-- Versión del servidor: 10.4.32-MariaDB
-- Versión de PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `circo_adrianiglesias`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `artista`
--

CREATE TABLE `artista` (
  `id` int(11) NOT NULL,
  `apodo` varchar(25) NOT NULL,
  `especialidad` varchar(150) NOT NULL,
  `id_persona` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `artista`
--

INSERT INTO `artista` (`id`, `apodo`, `especialidad`, `id_persona`) VALUES
(2, 'Bugman', 'acrobacia,humor', 2),
(3, 'ebony', 'equilibrismo', 15);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `coordinacion`
--

CREATE TABLE `coordinacion` (
  `id` int(11) NOT NULL,
  `senior` tinyint(1) NOT NULL,
  `fechasenior` date NOT NULL,
  `id_persona` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `coordinacion`
--

INSERT INTO `coordinacion` (`id`, `senior`, `fechasenior`, `id_persona`) VALUES
(1, 0, '2025-11-16', 1),
(2, 1, '1990-02-04', 16);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `credenciales`
--

CREATE TABLE `credenciales` (
  `id` int(11) NOT NULL,
  `nombre` varchar(25) NOT NULL,
  `password` varchar(40) NOT NULL,
  `id_persona` int(11) NOT NULL,
  `perfiles` varchar(25) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `credenciales`
--

INSERT INTO `credenciales` (`id`, `nombre`, `password`, `id_persona`, `perfiles`) VALUES
(1, 'coordinador', '1234', 1, 'COORDINACION'),
(3, 'adri', '1234', 2, 'ARTISTA'),
(4, 'pepe', '1234', 12, 'ARTISTA'),
(8, 'alba', '1234', 15, 'ARTISTA'),
(9, 'kampary', '1234', 16, 'COORDINACION');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `espectaculo`
--

CREATE TABLE `espectaculo` (
  `id` int(11) NOT NULL,
  `nombre` varchar(25) NOT NULL,
  `fechaini` date NOT NULL,
  `fechafin` date NOT NULL,
  `id_coordinacion` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `espectaculo`
--

INSERT INTO `espectaculo` (`id`, `nombre`, `fechaini`, `fechafin`, `id_coordinacion`) VALUES
(1, 'Flores Carmesí', '2025-11-16', '2025-11-23', 1),
(2, 'Periquitos Asesinos', '2025-11-21', '2025-12-25', 1),
(3, 'sadadd', '2025-11-21', '2025-12-21', 2),
(4, 'asdasd', '2025-11-21', '2025-12-21', 2),
(5, 'asdadd', '2025-12-21', '2025-12-21', 2),
(6, '11', '2025-11-21', '2025-12-21', 2),
(7, 'espectaculo', '2025-11-21', '2025-11-21', 2),
(8, 'especatulorandom', '2025-12-21', '2026-01-21', 2);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `numero`
--

CREATE TABLE `numero` (
  `id` int(11) NOT NULL,
  `orden` int(11) NOT NULL,
  `nombre` varchar(25) NOT NULL,
  `duracion` double NOT NULL,
  `id_espectaculo` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `numero`
--

INSERT INTO `numero` (`id`, `orden`, `nombre`, `duracion`, `id_espectaculo`) VALUES
(1, 1, 'Amapola Nocturna', 10, 1),
(2, 2, 'Sensacion carmesi', 5, 1),
(3, 3, 'Girasol Real', 7.5, 1),
(4, 1, 'asdsad', 4, 6),
(5, 1, 'asdasd', 4.5, 7),
(6, 1, 'Leones Voladores', 3.5, 8);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `participa`
--

CREATE TABLE `participa` (
  `idArt` int(11) NOT NULL,
  `idNumero` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `participa`
--

INSERT INTO `participa` (`idArt`, `idNumero`) VALUES
(2, 1),
(2, 2),
(2, 1),
(2, 2),
(3, 5),
(3, 5),
(2, 6),
(3, 6);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `persona`
--

CREATE TABLE `persona` (
  `id` int(11) NOT NULL,
  `email` varchar(50) NOT NULL,
  `nombre` varchar(25) NOT NULL,
  `nacionalidad` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `persona`
--

INSERT INTO `persona` (`id`, `email`, `nombre`, `nacionalidad`) VALUES
(1, 'coordinador@coordinador.c', 'coordinador', 'España'),
(2, 'adri@adri.com', 'adri', 'Turquía'),
(5, 'prueba@correo.com', 'Persona Prueba', 'España'),
(11, 'pruebaaas@correo.com', 'Persona asddadas', 'España'),
(12, 'adri@correo.com', 'Adrian Igleisas', 'España'),
(15, 'alba@alba.com', 'alba garcia', 'España'),
(16, 'dani@dani.com', 'dani', 'Holanda');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `artista`
--
ALTER TABLE `artista`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_persona_fk_artista` (`id_persona`);

--
-- Indices de la tabla `coordinacion`
--
ALTER TABLE `coordinacion`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_persona_fk_coordinacion` (`id_persona`);

--
-- Indices de la tabla `credenciales`
--
ALTER TABLE `credenciales`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `uq_credenciales_nombre` (`nombre`),
  ADD KEY `persona_fk` (`id_persona`);

--
-- Indices de la tabla `espectaculo`
--
ALTER TABLE `espectaculo`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `nombreespectaculo` (`nombre`),
  ADD KEY `coordinador_fk_id` (`id_coordinacion`);

--
-- Indices de la tabla `numero`
--
ALTER TABLE `numero`
  ADD PRIMARY KEY (`id`),
  ADD KEY `espectaculo_fk_id` (`id_espectaculo`);

--
-- Indices de la tabla `participa`
--
ALTER TABLE `participa`
  ADD KEY `id_artista_fk` (`idArt`),
  ADD KEY `id_numero_fk` (`idNumero`);

--
-- Indices de la tabla `persona`
--
ALTER TABLE `persona`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `emailuser` (`email`),
  ADD UNIQUE KEY `nombreuser` (`nombre`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `artista`
--
ALTER TABLE `artista`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `coordinacion`
--
ALTER TABLE `coordinacion`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `credenciales`
--
ALTER TABLE `credenciales`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT de la tabla `espectaculo`
--
ALTER TABLE `espectaculo`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT de la tabla `numero`
--
ALTER TABLE `numero`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT de la tabla `persona`
--
ALTER TABLE `persona`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `artista`
--
ALTER TABLE `artista`
  ADD CONSTRAINT `FK_Persona_id_Artista` FOREIGN KEY (`id_persona`) REFERENCES `persona` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `coordinacion`
--
ALTER TABLE `coordinacion`
  ADD CONSTRAINT `id_persona_fk_coordinacion` FOREIGN KEY (`id_persona`) REFERENCES `persona` (`id`);

--
-- Filtros para la tabla `credenciales`
--
ALTER TABLE `credenciales`
  ADD CONSTRAINT `persona_fk` FOREIGN KEY (`id_persona`) REFERENCES `persona` (`id`);

--
-- Filtros para la tabla `espectaculo`
--
ALTER TABLE `espectaculo`
  ADD CONSTRAINT `coordinador_fk_id` FOREIGN KEY (`id_coordinacion`) REFERENCES `coordinacion` (`id`);

--
-- Filtros para la tabla `numero`
--
ALTER TABLE `numero`
  ADD CONSTRAINT `espectaculo_fk_id` FOREIGN KEY (`id_espectaculo`) REFERENCES `espectaculo` (`id`);

--
-- Filtros para la tabla `participa`
--
ALTER TABLE `participa`
  ADD CONSTRAINT `id_artista_fk` FOREIGN KEY (`idArt`) REFERENCES `artista` (`id`),
  ADD CONSTRAINT `id_numero_fk` FOREIGN KEY (`idNumero`) REFERENCES `numero` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
