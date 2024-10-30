codigo base de datos: 
* *Tabla restaurante:* *
CREATE TABLE Restaurante (
    id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    nombre VARCHAR(255),
    cif VARCHAR(50)
);
ALTER TABLE Restaurante ADD favorito BOOLEAN DEFAULT FALSE;

* *Tabla menu:* *
CREATE TABLE Menu (
    idRest INT,
    idItem VARCHAR(5) PRIMARY KEY,
    nombre VARCHAR(100),
    precio DOUBLE NOT NULL,
    FOREIGN KEY (idRest) REFERENCES Restaurante(id)
);

* *Tabla cliente:* *
CREATE TABLE Cliente (
    idCli INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 2000, INCREMENT BY 1),
    nombre VARCHAR(50),
    apellido1 VARCHAR(50),
    apellido2 VARCHAR(50)
);
  
* *Tabla direccion:* *
CREATE TABLE Direccion (
	idDir INT PRIMARY KEY,
	idCli INT,
	tipo VARCHAR(20) NOT NULL,
	nombre VARCHAR (30) NOT NULL,
	numero INT NOT NULL,
	piso VARCHAR (2),
	puerta VARCHAR (2),
	FOREIGN KEY (idCli) REFERENCES Cliente(idCli)
);

* *Tabla repartidor:* *
CREATE TABLE Repartidor(
	idRepar INT PRIMARY KEY,
	nombre VARCHAR (50),
	apellidos VARCHAR (50)
);

* *Tabla pedido:* *
CREATE TABLE Pedido (
	idPed INT PRIMARY KEY,
    idCli INT,
    nombre VARCHAR(50),
    origen VARCHAR(150),
    destino INT,
    precioTotal DOUBLE,
    hora TIME, 
    idRepar INT,
    FOREIGN KEY (idRepar) REFERENCES Repartidor(idRepar),
    FOREIGN KEY (idCli) REFERENCES Cliente(idCli),
    FOREIGN KEY (destino) REFERENCES Direccion(idDir)
);
