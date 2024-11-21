-- RELACOES:
-- Bruxo e Casa: 1:N
-- Bruxo e Varinha: 1:1
-- Bruxo e Vassoura: 1:N
-- Bruxo e Matéria: N:N
-- Bruxo e Feitiço: N:N

DROP DATABASE IF EXISTS Hogwarts;
CREATE DATABASE Hogwarts;
USE Hogwarts;

CREATE TABLE Feitico(
idFeitico INT PRIMARY KEY AUTO_INCREMENT,
nome VARCHAR(45) NOT NULL,
tipo VARCHAR(45) NOT NULL,
efeito VARCHAR(45) NOT NULL,
dano INT
);

CREATE TABLE Casa(
idCasa INT PRIMARY KEY AUTO_INCREMENT,
nome VARCHAR(45) NOT NULL,
fundador VARCHAR(45) NOT NULL,
mascote VARCHAR(45) NOT NULL,
elemento VARCHAR(45) NOT NULL
);

CREATE TABLE Bruxo(
idBruxo INT PRIMARY KEY AUTO_INCREMENT,
nome VARCHAR(45) NOT NULL,
idade INT NOT NULL,
ano INT NOT NULL,
idCasa INT,
CONSTRAINT fk_casa FOREIGN KEY (idCasa)
	REFERENCES Casa (idCasa) ON UPDATE CASCADE
);

CREATE TABLE Vassoura(
idVassoura INT PRIMARY KEY AUTO_INCREMENT,
velocidade INT NOT NULL,
resistencia INT NOT NULL,
conforto INT,
estabilidade INT NOT NULL,
idBruxo INT,
CONSTRAINT fk_bruxo_vassoura FOREIGN KEY (idBruxo)
	REFERENCES Bruxo (idBruxo) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE Varinha(
idVarinha INT PRIMARY KEY AUTO_INCREMENT,
nucleo VARCHAR(45) NOT NULL,
madeira VARCHAR(45) NOT NULL,
bruxo_varinha INT,
CONSTRAINT fk_varinha FOREIGN KEY (bruxo_varinha)
	REFERENCES Bruxo (idBruxo) ON UPDATE CASCADE
);

CREATE TABLE Materia(
idMateria INT PRIMARY KEY AUTO_INCREMENT,
nome VARCHAR(45) NOT NULL,
professor VARCHAR(45) NOT NULL
);

CREATE TABLE BruxoMateria (
idBruxo INT,
idMateria INT,
nota DECIMAL(4,2),
PRIMARY KEY (idBruxo, idMateria),
CONSTRAINT fk_bruxo_materia FOREIGN KEY (idBruxo)
	REFERENCES Bruxo (idBruxo) ON DELETE CASCADE ON UPDATE CASCADE,
CONSTRAINT fk_materia_bruxo FOREIGN KEY (idMateria)
	REFERENCES Materia (idMateria) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE BruxoFeitico (
    idBruxo INT,
    idFeitico INT,
    PRIMARY KEY (idBruxo, idFeitico),
    CONSTRAINT fk_bruxo_feitico FOREIGN KEY (idBruxo)
        REFERENCES Bruxo (idBruxo) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT fk_feitico_bruxo FOREIGN KEY (idFeitico)
        REFERENCES Feitico (idFeitico) ON DELETE CASCADE ON UPDATE CASCADE
);

INSERT INTO Casa (nome, fundador, mascote, elemento) VALUES 
('Grifinória', 'Godric Gryffindor', 'Leão', 'Fogo'),
('Sonserina', 'Salazar Slytherin', 'Serpente', 'Água'),
('Corvinal', 'Rowena Ravenclaw', 'Corvo', 'Ar'),
('Lufa-Lufa', 'Helga Hufflepuff', 'Texugo', 'Terra');

INSERT INTO Bruxo (nome, idade, ano, idCasa) VALUES 
('Harry Potter', 17, 7, 1),
('Hermione Granger', 17, 7, 1),
('Draco Malfoy', 17, 7, 2),
('Luna Lovegood', 16, 6, 3),
('Cedrico Diggory', 17, 7, 4);

INSERT INTO Materia (nome, professor) VALUES 
('Defesa Contra as Artes das Trevas', 'Severo Snape'),
('Poções', 'Horácio Slughorn'),
('Herbologia', 'Pomona Sprout'),
('Transfiguração', 'Minerva McGonagall');

INSERT INTO Feitico (nome, tipo, efeito, dano) VALUES 
('Expelliarmus', 'Ofensivo', 'Desarmar o inimigo', 30),
('Lumos', 'Suporte', 'Iluminar ambientes', 0),
('Wingardium Leviosa', 'Utilitário', 'Levitar objetos', 0),
('Avada Kedavra', 'Ofensivo', 'Morte instantânea', 100);

INSERT INTO Vassoura (velocidade, resistencia, conforto, estabilidade, idBruxo) VALUES 
(300, 90, 80, 85, 1),
(280, 85, 70, 80, 1),
(270, 80, 75, 75, 2),
(290, 88, 78, 82, 4);

INSERT INTO BruxoMateria (idBruxo, idMateria, nota) VALUES 
(1, 1, 9.5),
(1, 2, 8.5),
(2, 1, 10.0),
(3, 3, 7.0),
(4, 4, 6.5);

INSERT INTO BruxoFeitico (idBruxo, idFeitico) VALUES 
(1, 1),
(2, 3),
(3, 4),
(4, 2),
(5, 1);

UPDATE Bruxo SET idade = 18 WHERE idBruxo = 1;
UPDATE Casa SET elemento = 'Chama' WHERE idCasa = 1;
UPDATE Vassoura SET conforto = 85 WHERE idVassoura = 1;
UPDATE Feitico SET dano = 40 WHERE idFeitico = 1;
UPDATE Materia SET professor = 'Gilderoy Lockhart' WHERE idMateria = 1;

DELETE FROM BruxoMateria WHERE idBruxo = 3 AND idMateria = 3;
DELETE FROM BruxoFeitico WHERE idBruxo = 5 AND idFeitico = 1;
DELETE FROM Vassoura WHERE idVassoura = 4;

SELECT b.nome AS Bruxo, c.nome AS Casa 
FROM Bruxo b
JOIN Casa c ON b.idCasa = c.idCasa;

SELECT b.nome AS Bruxo, m.nome AS Materia, bm.nota AS Nota
FROM BruxoMateria bm
JOIN Bruxo b ON bm.idBruxo = b.idBruxo
JOIN Materia m ON bm.idMateria = m.idMateria;

SELECT b.nome AS Bruxo, f.nome AS Feitico_Favorito
FROM BruxoFeitico bf
JOIN Bruxo b ON bf.idBruxo = b.idBruxo
JOIN Feitico f ON bf.idFeitico = f.idFeitico;

CREATE VIEW ViewBruxoMateria AS
SELECT 
    b.nome AS Bruxo, 
    m.nome AS Materia, 
    m.professor AS Professor, 
    bm.nota AS Nota
FROM BruxoMateria bm
JOIN Bruxo b ON bm.idBruxo = b.idBruxo
JOIN Materia m ON bm.idMateria = m.idMateria;

SELECT * FROM ViewBruxoMateria;

DROP VIEW ViewBruxoMateria;