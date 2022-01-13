package controlador;

import java.sql.*;
import modelo.*;

/**
 * Clase que contiene los métodos para mediar en la interacción 
 * entre el usuario de la aplicación y la base de datos
 *
 * @author Daniel Paz Lorenzo
 */
public class ConsultasBBDD {

    private final ConexionBBDD conexionBBDD;

    /**
     * Constructor de la clase que genera la un conector con la bbdd
     */
    public ConsultasBBDD() {
        this.conexionBBDD = new ConexionBBDD();
    }

    /**
     * Método para mostrar y pedir información de la base de datos en general.
     *
     * @throws java.sql.SQLException
     */
    public void mostrarInformacionBBDD() throws SQLException {
        try {
            conexionBBDD.getConexion();
            DatabaseMetaData dbmd = conexionBBDD.getConexion().getMetaData();// Creamos objeto DatabaseMetaData
            ResultSet resul;
            String nombre = dbmd.getDatabaseProductName();
            String driver = dbmd.getDriverName();
            String url = dbmd.getURL();
            String usuario = dbmd.getUserName();
            System.out.println("\nINFORMACIÓN DE LA BASE DE DATOS EN GENERAL:");
            System.out.println("_____________________________________________");
            System.out.printf("Nombre : %s %n", nombre);
            System.out.printf("Driver : %s %n", driver);
            System.out.printf("URL    : %s %n", url);
            System.out.printf("Usuario: %s %n", usuario);
            // Obtener información de las tablas y vistas que hay
            resul = dbmd.getTables("aerolinea", null, null, null);
            while (resul.next()) {
                String catalogo = resul.getString(1);// columna 1
                String esquema = resul.getString(2); // columna 2
                String tabla = resul.getString(3); // columna 3
                String tipo = resul.getString(4); // columna 4
                System.out.printf("%s - Catalogo: %s, Esquema: %s, Nombre Tabla: %s %n", tipo, catalogo, esquema, tabla);
            }
            conexionBBDD.getConexion().close(); // Cerrar conexion
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println(e.getErrorCode());
            System.out.println(e.getSQLState());
        }

    }

    /**
     * Método para mostrar la información de la tabla pasajeros.
     *
     * @throws SQLException
     */
    public void consultaPasajeros() throws SQLException {
        //Recogemos la conexión a la bbdd de la clase conexionBBDD
        conexionBBDD.getConexion();

        if (conexionBBDD == null) {
            System.out.println("No se ha podido conectar con la base de datos");
        } else {
            //Sentencia SQL que mandamos a la bbdd y recogemos el resultado que nos genera
            PreparedStatement ps = conexionBBDD.getConexion().prepareStatement("SELECT * FROM pasajeros");
            ResultSet resultado = ps.executeQuery();
            System.out.println("\nDATOS DE LA TABLA PASAJEROS");
            System.out.println("______________________________");
            while (resultado.next()) {//Recorremos línea a línea la base de datos y mostramos los campos
                System.out.println("#" + resultado.getInt("NUM") + "\t" + resultado.getString("COD_VUELO") + "\t" + resultado.getString("TIPO_PLAZA") + "\t" + resultado.getString("FUMADOR") + "\t");
            }
            //Cerramos conectores
            resultado.close();
            ps.close();
            conexionBBDD.getConexion().close();
        }
    }

    /**
     * Método para ver la información de los pasajeros de un vuelo, pasando el
     * código de vuelo como parámetro.
     *
     * @param codigoVuelo String de máx. 10 caractéres
     * @throws SQLException
     */
    public void consultaPasajerosVuelo(String codigoVuelo) throws SQLException {
        //Recogemos la conexión a la bbdd de la clase conexionBBDD
        conexionBBDD.getConexion();

        if (conexionBBDD == null) {
            System.out.println("No se ha podido conectar con la base de datos");
        } else {
            //Sentencia SQL que mandamos a la bbdd y recogemos el resultado que nos genera
            PreparedStatement ps = conexionBBDD.getConexion().prepareStatement("SELECT * FROM pasajeros WHERE COD_VUELO= ?");
            ps.setString(1, codigoVuelo);//Pasamos a la sentencia SQL el parámetro recibido
            ResultSet resultado = ps.executeQuery();
            if (!resultado.next()) {
                System.out.println("No existe ningún vuelo con ese código en la base de datos");
            } else {
                while (resultado.next()) {
                    System.out.println("#" + resultado.getInt("NUM") + "\t" + resultado.getString("COD_VUELO") + "\t" + resultado.getString("TIPO_PLAZA") + "\t" + resultado.getString("FUMADOR") + "\t");
                }
                resultado.close();
                ps.close();
                conexionBBDD.getConexion().close();
            }
        }

    }

    /**
     * Método para insertar un vuelo cuyos valores se pasan como parámetros,
     * dentro de la tabla vuelos
     *
     * @param vuelo objeto de la clase del tipo Vuelo
     * @throws SQLException
     */
    public void insertarVuelo(Vuelo vuelo) throws SQLException {
        //Recogemos la conexión a la bbdd de la clase conexionBBDD
        conexionBBDD.getConexion();

        if (conexionBBDD == null) {
            System.err.println("No se ha podido conectar con la base de datos");
        } else {
            //Sentencia SQL que mandamos a la bbdd y recogemos el resultado que nos genera
            PreparedStatement ps = conexionBBDD.getConexion().prepareStatement("INSERT INTO vuelos VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
            //Pasamos a la sentencia SQL los parámetros recibidos
            ps.setString(1, vuelo.getCod_vuelo());
            ps.setString(2, vuelo.getHora_salida());
            ps.setString(3, vuelo.getDestino());
            ps.setString(4, vuelo.getProcedencia());
            ps.setInt(5, vuelo.getPlazas_fumador());
            ps.setInt(6, vuelo.getPlazas_nofumador());
            ps.setInt(7, vuelo.getPlazas_turista());
            ps.setInt(8, vuelo.getPlazas_primera());
            ps.executeUpdate();
            //Cerramos todas las conexiones
            System.out.println("Datos introducidos correctamente en la tabla de vuelos");
            ps.close();
            conexionBBDD.getConexion().close();
        }

    }

    /**
     * Método para borrar un vuelo, de la tabla vuelos, que se mete como
     * parámetro
     *
     * @param codigoVuelo String de máx. 10 caractéres
     * @throws SQLException
     */
    public void borrarVuelo(String codigoVuelo) throws SQLException {
        //Recogemos la conexión a la bbdd de la clase conexionBBDD
        conexionBBDD.getConexion();

        if (conexionBBDD == null) {
            System.out.println("No se ha podido conectar con la base de datos");
        } else {
            //Sentencia SQL que mandamos a la bbdd y recogemos el resultado que nos genera
            PreparedStatement ps = conexionBBDD.getConexion().prepareStatement("DELETE FROM vuelos WHERE COD_VUELO= ?");
            ps.setString(1, codigoVuelo);//Pasamos a la sentencia SQL el parámetro recibido
            ps.executeUpdate();//Ejecutamos la actualización
            //Cerramos todas las conexiones
            System.out.println("Vuelo " + codigoVuelo + " se ha borrado de la tabla vuelos");
            ps.close();
            conexionBBDD.getConexion().close();
        }

    }

    /**
     * Método para modificar los vuelos de fumadores a no fumadores
     *
     * @throws SQLException
     */
    public void modificarVuelosFumadoresNoFumadores() throws SQLException {
        //Recogemos la conexión a la bbdd de la clase conexionBBDD
        conexionBBDD.getConexion();

        if (conexionBBDD == null) {
            System.out.println("No se ha podido conectar con la base de datos");
        } else {
            //Sentencia SQL que mandamos a la bbdd y recogemos el resultado que nos genera
            PreparedStatement ps = conexionBBDD.getConexion().prepareStatement("UPDATE vuelos SET PLAZAS_NO_FUMADOR = PLAZAS_FUMADOR+PLAZAS_NO_FUMADOR, PLAZAS_FUMADOR=0 WHERE PLAZAS_FUMADOR>0");
            ps.executeUpdate();
            //Cerramos todas las conexiones
            System.out.println("Modificados todos los vuelos de fumadores a no fumadores");
            ps.close();
            conexionBBDD.getConexion().close();
        }
    }
}
