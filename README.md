codigo base de datos: \n
* * Tabla restaurante: * *
CREATE TABLE Restaurante (
    id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    nombre VARCHAR(255),
    cif VARCHAR(50)
);
ALTER TABLE Restaurante ADD favorito BOOLEAN DEFAULT FALSE;

* * Tabla menu: * *
CREATE TABLE Menu (
    idRest INT,
    idItem VARCHAR(5) PRIMARY KEY,
    nombre VARCHAR(100),
    precio DOUBLE,
    FOREIGN KEY (idRest) REFERENCES Restaurante(id)
);

* * Tabla pedido: * *
  
