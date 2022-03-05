
/*1.-    Definir un tipo varray de dimensi�n 3 para contener los tel�fonos
---------------------------------------------------------------------------*/
CREATE OR REPLACE TYPE telefonos AS VARRAY(3) OF VARCHAR2(15);

/*2.-    Crear los tipos direcci�n, cliente, producto y l�nea de venta
---------------------------------------------------------------------------*/


/*3.-    Crear un tipo tabla anidada para contener las l�neas de una venta
---------------------------------------------------------------------------*/


/*4.-    Crear un tipo venta para los datos de las ventas, cada venta tendr� 
un atributo LINEAS del tipo tabla anidada definida anteriormente:
---------------------------------------------------------------------------*/


/*5.-    Crea el cuerpo del tipo anterior, teniendo en cuenta que se definir� 
la funci�n miembro TOTAL_VENTA que calcula el total de la venta de las l�neas
de venta que forman parte de una venta, contar� el n�mero de elementos de 
una tabla o de un array y devolver� el n�mero de l�neas que tiene la venta.
-----------------------------------------------------------------------------*/


/*6.-    Crear las tablas donde almacenar los objetos de la aplicaci�n. 
Se crear� una tabla para clientes, otra para productos y otra para las ventas, 
en dichas tablas se definir�n las oportunas claves primarias.
---------------------------------------------------------------------------*/


/*7.-    Inserta dos clientes y cinco productos.
---------------------------------------------------------------------------*/


/*8.-    Insertar en TABLA_VENTAS la venta con IDVENTA 1 para el IDCLIENTE 1
---------------------------------------------------------------------------*/


/*9.-    Insertar en TABLA_VENTAS dos l�neas de venta para el IDVENTA 1 
para los productos 1 (la CANTIDAD es 1) y 2 (la CANTIDAD es 2)
---------------------------------------------------------------------------*/


/*10.-    Insertar en TABLA_VENTAS la venta con IDVENTA 2 para el IDCLIENTE
---------------------------------------------------------------------------*/


/*11.-    Insertar en TABLA_VENTAS tres l�neas de venta para el IDVENTA 2 
para los productos 1 (la CANTIDAD es 2), 4 (la CANTIDAD es 1) 
y 5 (la CANTIDAD es 4)
---------------------------------------------------------------------------*/


/*12.-    Realizar un procedimiento que recibiendo el identificador visualice 
los datos de la venta.
---------------------------------------------------------------------------*/
