USE FACTURACION_ANALISIS;


INSERT INTO MARCA(NOMBRE) VALUES('SAMSUNG');
INSERT INTO MARCA(NOMBRE) VALUES('XPERIA');

INSERT INTO PRODUCTO(NOMBRE) VALUES ('S6');
INSERT INTO PRODUCTO(NOMBRE) VALUES ('S8');

INSERT INTO PRESENTACION(NOMBRE) VALUES('TELEFONO');

INSERT INTO UNIDAD(NOMBRE) VALUES('UNIDAD');
INSERT INTO UNIDAD(NOMBRE) VALUES('CAJA');

INSERT INTO INVENTARIO(PRODUCTO, MARCA, PRESENTACION, UNIDAD, PRECIO) VALUES (2,1,1,1,500);


SELECT * FROM EMPLEADO;
SELECT * FROM CLIENTE;
SELECT * FROM PRODUCTO;
SELECT * FROM MARCA;
SELECT * FROM PRESENTACION;
SELECT * FROM UNIDAD;
SELECT * FROM INVENTARIO;
SELECT * FROM INVENTARIO WHERE PRODUCTO IN (SELECT ID FROM PRODUCTO WHERE NOMBRE='S8');
SELECT * FROM RESOLUCION;
UPDATE RESOLUCION SET ESTADO=1 WHERE ID=1;
UPDATE INVENTARIO SET CANTIDAD=30 WHERE ID=1;
SELECT * FROM TRANSACCION;

SELECT DV.ID, P.NOMBRE, M.NOMBRE, PR.NOMBRE, U.NOMBRE, DV.CANTIDAD, DV.PRECIO, DV.TOTAL 
FROM DETALLE_VENTA DV, INVENTARIO I, PRODUCTO P, MARCA M, PRESENTACION PR, UNIDAD U 
WHERE DV.INVENTARIO=I.ID AND I.PRODUCTO=P.ID AND I.MARCA=M.ID AND I.PRESENTACION=PR.ID AND I.UNIDAD=U.ID;

SELECT P.NOMBRE AS PRODUCTO, M.NOMBRE AS MARCA, PR.NOMBRE AS PRESENTACION, U.NOMBRE AS UNIDAD, DV.CANTIDAD AS CANTIDAD, DV.PRECIO AS PRECIO, DV.TOTAL AS TOTAL 
FROM DETALLE_VENTA DV
INNER JOIN INVENTARIO I ON DV.INVENTARIO=I.ID
INNER JOIN PRODUCTO P ON I.PRODUCTO=P.ID
INNER JOIN MARCA M ON I.MARCA=M.ID
INNER JOIN PRESENTACION PR ON I.PRESENTACION=PR.ID
INNER JOIN UNIDAD U ON I.UNIDAD=U.ID;