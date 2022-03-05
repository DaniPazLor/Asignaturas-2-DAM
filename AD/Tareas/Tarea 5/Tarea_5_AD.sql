
/*1.-    Definir un tipo varray de dimensión 3 para contener los teléfonos
---------------------------------------------------------------------------*/
CREATE OR REPLACE TYPE telefonos AS VARRAY(3) OF VARCHAR2(15);

/*2.-    Crear los tipos dirección, cliente, producto y línea de venta
---------------------------------------------------------------------------*/


/*3.-    Crear un tipo tabla anidada para contener las líneas de una venta
---------------------------------------------------------------------------*/


/*4.-    Crear un tipo venta para los datos de las ventas, cada venta tendrá 
un atributo LINEAS del tipo tabla anidada definida anteriormente:
---------------------------------------------------------------------------*/


/*5.-    Crea el cuerpo del tipo anterior, teniendo en cuenta que se definirá 
la función miembro TOTAL_VENTA que calcula el total de la venta de las líneas
de venta que forman parte de una venta, contará el número de elementos de 
una tabla o de un array y devolverá el número de líneas que tiene la venta.
-----------------------------------------------------------------------------*/


/*6.-    Crear las tablas donde almacenar los objetos de la aplicación. 
Se creará una tabla para clientes, otra para productos y otra para las ventas, 
en dichas tablas se definirán las oportunas claves primarias.
---------------------------------------------------------------------------*/


/*7.-    Inserta dos clientes y cinco productos.
---------------------------------------------------------------------------*/


/*8.-    Insertar en TABLA_VENTAS la venta con IDVENTA 1 para el IDCLIENTE 1
---------------------------------------------------------------------------*/


/*9.-    Insertar en TABLA_VENTAS dos líneas de venta para el IDVENTA 1 
para los productos 1 (la CANTIDAD es 1) y 2 (la CANTIDAD es 2)
---------------------------------------------------------------------------*/


/*10.-    Insertar en TABLA_VENTAS la venta con IDVENTA 2 para el IDCLIENTE
---------------------------------------------------------------------------*/


/*11.-    Insertar en TABLA_VENTAS tres líneas de venta para el IDVENTA 2 
para los productos 1 (la CANTIDAD es 2), 4 (la CANTIDAD es 1) 
y 5 (la CANTIDAD es 4)
---------------------------------------------------------------------------*/


/*12.-    Realizar un procedimiento que recibiendo el identificador visualice 
los datos de la venta.
---------------------------------------------------------------------------*/
