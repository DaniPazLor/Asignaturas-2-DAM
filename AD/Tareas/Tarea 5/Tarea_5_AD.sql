/*1.-    Definir un tipo varray de dimensión 3 para contener los teléfonos
---------------------------------------------------------------------------*/
CREATE OR REPLACE TYPE telefonos_v AS VARRAY(3) OF VARCHAR2(15);
/

/*2.-    Crear los tipos dirección, cliente, producto y línea de venta
---------------------------------------------------------------------------*/
CREATE OR REPLACE TYPE direccion_o AS OBJECT(
    poblacion VARCHAR2(50),
    cod_postal NUMBER(6),
    provincia VARCHAR2(30));
/
CREATE OR REPLACE TYPE cliente_o AS OBJECT(
    id_cliente NUMBER,
    nombre VARCHAR2(50),
    nif VARCHAR2(50),
    direccion direccion_o,
    telefono telefonos_v);
/    
CREATE OR REPLACE TYPE producto_o AS OBJECT(
    id_producto NUMBER,    
    descripcion VARCHAR2(50),
    pvp NUMBER(6),
    stock_actual NUMBER(6));
/
CREATE OR REPLACE TYPE linea_venta_o AS OBJECT(
    num_linea NUMBER,
    idproducto REF producto_o,
    cantidad NUMBER(6));
/    
/*3.-    Crear un tipo tabla anidada para contener las líneas de una venta
---------------------------------------------------------------------------*/
CREATE OR REPLACE TYPE lineas_ventas_t AS TABLE OF linea_venta_o;
/
/*4.-    Crear un tipo venta para los datos de las ventas, cada venta tendrá 
un atributo LINEAS del tipo tabla anidada definida anteriormente:
---------------------------------------------------------------------------*/
CREATE OR REPLACE TYPE venta_o AS OBJECT(
    id_venta NUMBER,
    idcliente REF cliente_o,
    fecha_venta DATE,
    lineas lineas_ventas_t,
    MEMBER FUNCTION total_venta RETURN NUMBER);
/
/*5.-    Crea el cuerpo del tipo anterior, teniendo en cuenta que se definirá 
la función miembro TOTAL_VENTA que calcula el total de la venta de las líneas
de venta que forman parte de una venta, contará el número de elementos de 
una tabla o de un array y devolverá el número de líneas que tiene la venta.
-----------------------------------------------------------------------------*/
CREATE OR REPLACE TYPE BODY venta_o AS 
    MEMBER FUNCTION total_venta RETURN NUMBER IS
        v_total_venta NUMBER;
        v_producto producto_o;
        
    BEGIN
        FOR i IN 1..lineas.COUNT LOOP
            SELECT
                deref(lineas(i).idproducto)INTO v_producto FROM dual;
                v_total_venta := v_total_venta + lineas(i).cantidad * v_producto.pvp;
        END LOOP;
            RETURN v_total_venta;
    END;
END;    
/    

/*6.-    Crear las tablas donde almacenar los objetos de la aplicación. 
Se creará una tabla para clientes, otra para productos y otra para las ventas, 
en dichas tablas se definirán las oportunas claves primarias.
---------------------------------------------------------------------------*/
CREATE TABLE clientes_t OF cliente_o(
   id_cliente PRIMARY KEY,
   nif UNIQUE);
/
CREATE TABLE productos_t OF producto_o(
    id_producto PRIMARY KEY
);
/
CREATE TABLE ventas_t OF venta_o(
    id_venta PRIMARY KEY,
    idcliente NOT NULL
)NESTED TABLE lineas STORE AS lineas_t;
/

/*7.-    Inserta dos clientes y cinco productos.
---------------------------------------------------------------------------*/
INSERT INTO clientes_t VALUES (1,'Juan','01456784M',
                            direccion_o('Puebla de Sanabria',49007,'Zamora'),
                            telefonos_v('980223344','980666888'));
/
INSERT INTO clientes_t VALUES (2,'Rosa','72564987X',
                            direccion_o('A Rúa',26009,'Ourense'),
                            telefonos_v('923564537','923129856'));
/
INSERT INTO productos_t VALUES (10,'Harina',2,100);
/
INSERT INTO productos_t VALUES (20,'Rasqueta',5,55);
/
INSERT INTO productos_t VALUES (30,'Sal',1,1000);
/
INSERT INTO productos_t VALUES (40,'Amasadora',450,25);
/
INSERT INTO productos_t VALUES (50,'Banneton',8,85);
/

/*8.-    Insertar en TABLA_VENTAS la venta con IDVENTA 1 para el IDCLIENTE 1
---------------------------------------------------------------------------*/
INSERT INTO ventas_t SELECT 1,REF(c),'05-03-2022',lineas_ventas_t()
                    FROM clientes_t c WHERE c.id_cliente=1;
/

/*9.-    Insertar en TABLA_VENTAS dos líneas de venta para el IDVENTA 1 
para los productos 1 (la CANTIDAD es 1) y 2 (la CANTIDAD es 2)
---------------------------------------------------------------------------*/
INSERT INTO THE (SELECT v.lineas FROM ventas_t v WHERE v.id_venta=1)
    SELECT 1,REF(p),1 FROM productos_t p WHERE p.id_producto=10;
/
INSERT INTO THE (SELECT v.lineas FROM ventas_t v WHERE v.id_venta=1)
    SELECT 2,REF(p),2 FROM productos_t p WHERE p.id_producto=20;
/

/*10.-    Insertar en TABLA_VENTAS la venta con IDVENTA 2 para el IDCLIENTE 1
---------------------------------------------------------------------------*/
INSERT INTO ventas_t SELECT 2,REF(c),'06-03-2022',lineas_ventas_t()
                    FROM clientes_t c WHERE c.id_cliente=1;
/

/*11.-    Insertar en TABLA_VENTAS tres líneas de venta para el IDVENTA 2 
para los productos 1 (la CANTIDAD es 2), 4 (la CANTIDAD es 1) 
y 5 (la CANTIDAD es 4)
---------------------------------------------------------------------------*/
INSERT INTO THE (SELECT v.lineas FROM ventas_t v WHERE v.id_venta=2)
    SELECT 1,REF(p),2 FROM productos_t p WHERE p.id_producto=10;
/
INSERT INTO THE (SELECT v.lineas FROM ventas_t v WHERE v.id_venta=2)
    SELECT 2,REF(p),1 FROM productos_t p WHERE p.id_producto=40;
/
INSERT INTO THE (SELECT v.lineas FROM ventas_t v WHERE v.id_venta=2)
    SELECT 2,REF(p),5 FROM productos_t p WHERE p.id_producto=50;
/

/*12.-    Realizar un procedimiento que recibiendo el identificador visualice 
los datos de la venta.
---------------------------------------------------------------------------*/
CREATE OR REPLACE PROCEDURE p_datos_venta (id NUMBER) AS 
    v_precio NUMBER; 
    v_total_venta NUMBER; 
    v_cliente cliente_o; 
    v_fecha_venta DATE;
    CURSOR CUR IS SELECT num_linea nlin, DEREF(idproducto) prod, cantidad
                FROM THE (SELECT v.lineas FROM ventas_t v WHERE v.id_venta=id);

    BEGIN 
        SELECT DEREF(idcliente), fecha_venta, v.total_venta() 
                INTO v_cliente, v_fecha_venta, v_total_venta 
                FROM ventas_t v WHERE v.id_venta = id; 
                
        DBMS_OUTPUT.PUT_LINE('CLIENTE: '||v_cliente.nombre);
        DBMS_OUTPUT.PUT_LINE('ID VENTA: '||id|| ' | Fecha: '|| v_fecha_venta);  
        DBMS_OUTPUT.PUT_LINE('POBLACION: '||v_cliente.direccion.poblacion||
                                         ', c.p.:'||v_cliente.direccion.cod_postal||
                                         ', Provincia:'||v_cliente.direccion.provincia); 
        DBMS_OUTPUT.PUT_LINE('-------------------------------------------'); 
        DBMS_OUTPUT.PUT_LINE('LINEA |PRODUCTO| PRECIO | CANTIDAD | TOTAL') ;
        DBMS_OUTPUT.PUT_LINE('-------------------------------------------'); 
    FOR i IN CUR LOOP 
        v_precio:= i.cantidad * i.prod.pvp; 
        DBMS_OUTPUT.PUT_LINE('  ' || i.nlin||' |  '|| i.prod.descripcion ||' |     '|| 
                i.prod.pvp||' €    |   '|| i.cantidad||'   |   '|| v_precio || ' €'); 
    END LOOP; 
        DBMS_OUTPUT.PUT_LINE('Precio total: '||v_total_venta); 
END p_datos_venta; 
/
/*Llamamos al procedimiento que hemos creado*/
BEGIN
    p_datos_venta(2);
END;
/