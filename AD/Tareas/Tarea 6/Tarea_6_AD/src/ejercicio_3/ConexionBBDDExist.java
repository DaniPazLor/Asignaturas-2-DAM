package ejercicio_3;

import javax.xml.xquery.XQConnection;
import javax.xml.xquery.XQDataSource;
import javax.xml.xquery.XQException;
import javax.xml.xquery.XQPreparedExpression;
import javax.xml.xquery.XQSequence;
import net.xqj.exist.ExistXQDataSource;

/**
 * Clase que sirve para realizar consultas a la bbdd nativa XML de Exist-db
 *
 * @author Daniel Paz Lorenzo
 */
public class ConexionBBDDExist {

    /**
     * Método principal sin argumentos
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) throws XQException {
        //Creamos las instancias XQDataSource, XQConnection necesarias para
        //la conexión con la bbdd
        XQDataSource ds = new ExistXQDataSource();
        ds.setProperty("serverName", "localhost");
        ds.setProperty("port", "8088");
        ds.setProperty("user", "admin");
        ds.setProperty("password", "");
        XQConnection con = ds.getConnection();

        //Creamos la consulta Xquery
        String query = "for $librosActualizado in //bib/libro return <libro>{$librosActualizado/titulo}</libro>";
        //Creamos una instancia de XQPreparedExpression con las propiedades de conexión y la consulta
        XQPreparedExpression expr = con.prepareExpression(query);
        //Ejecutamos la consulta y recogemos resultado
        XQSequence result = expr.executeQuery();
        //Imprime por pantalla el resultado de la consulta
        System.out.println(result.getSequenceAsString(null));
        //Cerramos todas las conexiones
        result.close();
        expr.close();
        con.close();
    }

}
