package modelo;

import java.sql.*;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase para realizar la conexión con la BBDD
 *
 * @author Daniel Paz Lorenzo
 */
public class ConexionBBDD {

    //Datos para la conexión con mi base de datos de MySql
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/aerolinea";
    static final String USER = "Dani";
    static final String PASS = "5678";

    private Connection Conexion;

    public ConexionBBDD() {

        try {
            Class.forName(JDBC_DRIVER);
            this.Conexion = DriverManager.getConnection(DB_URL, USER, PASS);

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ConexionBBDD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Connection getConexion() {
        return Conexion;
    }
}
