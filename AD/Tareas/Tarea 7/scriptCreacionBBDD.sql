CREATE database bbddTarea7;
USE bbddTarea7;
#Estructura de tabla para la tabla 'alumnos'
CREATE TABLE IF NOT EXISTS alumnos (
     DNI varchar(9) NOT NULL,
     Nombre varchar(50) NOT NULL,
     Apellidos varchar(70) NOT NULL,
     Direccion varchar(100) NOT NULL,
     FechaNac date NOT NULL,
	PRIMARY KEY (DNI));
# Volcar la base de datos para la tabla 'alumnos'
INSERT INTO alumnos VALUES
('12345678A', 'José Alberto', 'González Pérez', 'C/Albahaca, nº14, 1ºD', '1986-07-15'),
('23456789B', 'Almudena', 'Cantero Verdemar', 'Avd/ Profesor Alvarado, n27, 8ºA', '1988-11-04'),
('14785236d', 'Martín', 'Díaz Jiménez', 'C/Luis de Gongora, nº2.', '1987-03-09'),
('96385274f', 'Lucas', 'Buendia Portes', 'C/Pintor Sorolla, nº 16, 4ºB', '1988-07-10');

CREATE TABLE IF NOT EXISTS matriculas (
	 ID int NOT NULL AUTO_INCREMENT,	
     DNI varchar(9) NOT NULL,
     NombreModulo varchar(60) NOT NULL,
     Curso varchar(5) NOT NULL,
     Nota double NOT NULL,
     PRIMARY KEY (ID),
    FOREIGN KEY (DNI) REFERENCES alumnos (DNI));
    
 INSERT INTO matriculas VALUES
(1, '12345678A', 'Desarrollo de Interfaces', '20-21', 5.5),
(id, '12345678A', 'Acceso a Datos', '21-22', 7.6),
(id, '12345678A', 'Programación de Servicios y procesos', '20-21', 8.4),
(id, '23456789B', 'Sistemas de Gestión Empresarial', '19-20', 6.3),
(id, '23456789B', 'PMDM', '21-22', 4.5),
(id, '23456789B', 'Acceso a Datos', '21-22', 9.7),
(id, '14785236d', 'Desarrollo de Interfaces', '19-20', 3.3),
(id, '14785236d', 'Entornos de Desarrollo ', '21-22', 6.2),
(id, '14785236d', 'Lenguajes de Marcas', '18-19', 8.4),
(id, '96385274f', 'Acceso a Datos', '20-21', 9.2),
(id, '96385274f', 'Programación', '21-22', 5.8),
(id, '96385274f', 'Desarrollo de Interfaces', '20-21', 6.6),
(id, '96385274f', 'Bases de Datos', '18-19', 8.8);