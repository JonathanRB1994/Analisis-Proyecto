USE FACTURACION_ANALISIS;

/* ################### DEFINICION DEL LOGIN ################### */
/*
	ROL
		Admin 
		Vendedor
*/

DROP TABLE IF EXISTS EMPLEADO;
DROP TABLE IF EXISTS ROL;

CREATE TABLE ROL(
	ID INT NOT NULL,	
	NOMBRE VARCHAR(30),
	PRIMARY KEY (ID)
);

INSERT INTO ROL(ID, NOMBRE) VALUES (1, 'ADMINISTRADOR');
INSERT INTO ROL(ID, NOMBRE) VALUES (2, 'VENDEDOR');

CREATE TABLE EMPLEADO(
	ID INT NOT NULL AUTO_INCREMENT,
	NOMBRE VARCHAR(50) NOT NULL,
	APELLIDOS VARCHAR(30) NOT NULL,
	ESTADO BIT NOT NULL DEFAULT 1,
	CLAVE VARCHAR(30) NOT NULL,
	EMAIL VARCHAR(50),
    TELEFONO VARCHAR(15),
    ROL INT NOT NULL,
	PRIMARY KEY (ID),
    FOREIGN KEY (ROL) REFERENCES facturacion_analisis.rol(id)
);

INSERT INTO EMPLEADO(ID, NOMBRE, APELLIDOS, ESTADO, CLAVE, ROL) VALUES (1, 'ROOT', 'ROOT', 1, '1234', 1); 



/* ################### CONFIGURACION DEL SISTEMA ################### */
DROP TABLE IF EXISTS CONFIGURACION;

CREATE TABLE CONFIGURACION(
	ID INT NOT NULL,
	NOMBRE_COMERCIAL VARCHAR(50),
	RAZON_SOCIAL VARCHAR(50),
	DIRECCION VARCHAR(100),
	NIT VARCHAR(12),
	PRIMARY KEY (ID)
);

INSERT INTO CONFIGURACION(ID, NOMBRE_COMERCIAL, RAZON_SOCIAL, DIRECCION, NIT) VALUES (1, 'DEFAULT', 'DEFACULT', 'DEFAULT', 'DEFAULT');

/* ################### RESOLUCION DEL SISTEMA ################### */
DROP TABLE IF EXISTS RESOLUCION_SISTEMA;

CREATE TABLE RESOLUCION_SISTEMA(
	ID INT NOT NULL,
	NO_RESOLUCION VARCHAR(30) NOT NULL,
	FECHA_AUTORIZACION DATE NOT NULL,
	NO_MAQUINA INT NOT NULL,
	PRIMARY KEY (ID)
);

INSERT INTO RESOLUCION_SISTEMA(ID, NO_RESOLUCION, FECHA_AUTORIZACION, NO_MAQUINA) VALUES(1, 'DEFAULT', sysdate(), 0);

/* ################### RESOLUCIONES ################### */
DROP TABLE IF EXISTS RESOLUCION;

CREATE TABLE RESOLUCION(
	ID INT NOT NULL AUTO_INCREMENT,
	TIPO VARCHAR(15) NOT NULL,
	NO_RESOLUCION VARCHAR(30) NOT NULL,
	NO_SERIE VARCHAR(30) NOT NULL,
	NO_INICIAL INT NOT NULL,
	NO_FINAL INT NOT NULL,
	NO_ACTUAL INT NOT NULL,
	ESTADO BIT NOT NULL,
	CONTRIBUYENTE_CHICO BIT NOT NULL,
	FECHA_AUTORIZACION DATE NOT NULL,
    FECHA_VENCIMIENTO DATE NOT NULL,
	FECHA_INGRESO DATE NOT NULL,
	PRIMARY KEY (ID)
);


/* ################### INVENTARIO ################### */
DROP TABLE IF EXISTS INVENTARIO;
DROP TABLE IF EXISTS MARCA;
DROP TABLE IF EXISTS PRESENTACION;
DROP TABLE IF EXISTS UNIDAD;
DROP TABLE IF EXISTS PRODUCTO;

CREATE TABLE MARCA(
	ID INT NOT NULL AUTO_INCREMENT,
	NOMBRE VARCHAR(30) NOT NULL,
	PRIMARY KEY (ID)
);

CREATE TABLE PRESENTACION(
	ID INT NOT NULL AUTO_INCREMENT,
	NOMBRE VARCHAR(30) NOT NULL,
	PRIMARY KEY (ID)
);

CREATE TABLE UNIDAD(
	ID INT NOT NULL AUTO_INCREMENT,
	NOMBRE VARCHAR(30) NOT NULL,
	PRIMARY KEY (ID)
);

CREATE TABLE PRODUCTO(
	ID INT NOT NULL AUTO_INCREMENT,
	NOMBRE VARCHAR(30) NOT NULL,
	PRIMARY KEY (ID)
);

CREATE TABLE INVENTARIO(
	ID INT NOT NULL AUTO_INCREMENT,
    PRECIO DECIMAL(9,2) NOT NULL,
	CANTIDAD INT NOT NULL DEFAULT 0,
	PRODUCTO INT NOT NULL,
	UNIDAD INT NOT NULL,
	PRESENTACION INT NOT NULL,
	MARCA INT NOT NULL,
    ESTADO BIT NOT NULL default 1,
	PRIMARY KEY (ID),
	FOREIGN KEY (PRODUCTO) REFERENCES facturacion_analisis.producto(id),
	FOREIGN KEY (UNIDAD) REFERENCES facturacion_analisis.unidad(id),
	FOREIGN KEY (PRESENTACION) REFERENCES facturacion_analisis.presentacion(id),
	FOREIGN KEY (MARCA) REFERENCES facturacion_analisis.marca(id)
);

/* ################### CLIENTES ################### */
DROP TABLE IF EXISTS CLIENTE;

CREATE TABLE CLIENTE(
	ID INT NOT NULL AUTO_INCREMENT,
	NOMBRE VARCHAR(50) NOT NULL,
	APELLIDOS VARCHAR(30) NOT NULL,
	NIT VARCHAR(12) DEFAULT 'C/F',
	DIRECCION VARCHAR(100) NOT NULL DEFAULT 'CIUDAD',
	TELEFONO VARCHAR(12),
	EMAIL VARCHAR(50),
	PRIMARY KEY (ID)
);

INSERT INTO CLIENTE (ID, NOMBRE, APELLIDOS, NIT, DIRECCION) VALUE(1, "C/F", "C/F", "C/F", "CIUDAD");


/* ################### VENTA ################### */
DROP TABLE IF EXISTS TRANSACCION;

CREATE TABLE TRANSACCION(
	ID INT NOT NULL AUTO_INCREMENT,
    RESOLUCION INT NOT NULL,
    CORRELATIVO INT NOT NULL,    
    TOTAL DECIMAL(9,2) NOT NULL,
    FECHA DATE NOT NULL,
    ESTADO VARCHAR(15) NOT NULL,
    PRIMARY KEY (ID),
    FOREIGN KEY (RESOLUCION) REFERENCES facturacion_analisis.resolucion(id)
    
);

DROP TABLE IF EXISTS VENTA;

CREATE TABLE VENTA(
	ID INT NOT NULL AUTO_INCREMENT,
    TRANSACCION INT NOT NULL,
    CLIENTE INT NOT NULL,
    NOMBRE_COMERCIAL VARCHAR(50) NOT NULL,
	RAZON_SOCIAL VARCHAR(50) NOT NULL,
	DIRECCION VARCHAR(100) NOT NULL,
	NIT VARCHAR(12) NOT NULL,
    EMPLEADO INT NOT NULL,
    RESOLUCION_SISTEMA VARCHAR(30) NOT NULL,
    PRIMARY KEY (ID),
    FOREIGN KEY (TRANSACCION) REFERENCES facturacion_analisis.transaccion(id),
    FOREIGN KEY (CLIENTE) REFERENCES facturacion_analisis.cliente(id),
    FOREIGN KEY (EMPLEADO) REFERENCES facturacion_analisis.empleado(id)
);

DROP TABLE IF EXISTS DETALLE_VENTA;

CREATE TABLE DETALLE_VENTA(
	ID INT NOT NULL AUTO_INCREMENT,
    VENTA INT NOT NULL,
    INVENTARIO INT NOT NULL,
    CANTIDAD INT NOT NULL,
    PRECIO DECIMAL(9,2) NOT NULL,
    TOTAL INT NOT NULL,
    PRIMARY KEY (ID),
    FOREIGN KEY (VENTA) REFERENCES facturacion_analisis.venta(id),
    FOREIGN KEY (INVENTARIO) REFERENCES facturacion_analisis.inventario(id)
);


/*########################### COMPRA ##########################*/
DROP TABLE IF EXISTS COMPRA;

CREATE TABLE COMPRA(
	ID INT NOT NULL AUTO_INCREMENT,    
    P_NOMBRE VARCHAR(30) NOT NULL,
    P_NIT VARCHAR(30) NOT NULL,
    TOTAL DECIMAL(9,2) NOT NULL,	
    EMPLEADO INT NOT NULL,
    FECHA DATE NOT NULL,
    PRIMARY KEY (ID),
    FOREIGN KEY (EMPLEADO) REFERENCES facturacion_analisis.empleado(id)
);

DROP TABLE IF EXISTS DETALLE_COMPRA;

CREATE TABLE DETALLE_COMPRA(
	ID INT NOT NULL AUTO_INCREMENT,
    COMPRA INT NOT NULL,
    INVENTARIO INT NOT NULL,
    CANTIDAD INT NOT NULL,
    PRECIO DECIMAL(9,2) NOT NULL,
    TOTAL INT NOT NULL,
    PRIMARY KEY (ID),
    FOREIGN KEY (COMPRA) REFERENCES facturacion_analisis.compra(id),
    FOREIGN KEY (INVENTARIO) REFERENCES facturacion_analisis.inventario(id)
);


