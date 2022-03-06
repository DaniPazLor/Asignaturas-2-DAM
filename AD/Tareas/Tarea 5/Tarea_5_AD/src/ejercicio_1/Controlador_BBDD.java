package ejercicio_1;

import com.db4o.*;
import com.db4o.query.Constraint;
import com.db4o.query.Query;

/**
 * Clase que contiene los métodos para gestionar la BBDD
 *
 * @author Daniel Paz Lorenzo
 */
public class Controlador_BBDD {

    public Controlador_BBDD() {

    }

    /**
     * Método que consulta a la bbdd los jefes mayores de 55 años
     *
     * @param db ObjectContainer de la bbdd
     */
    public void consultarJefes55(ObjectContainer db) {
        Query query = db.query();
        query.constrain(Jefe.class);
        //se declara una de las restricciones con Constraint mayor de 55 años
        Constraint constra1 = query.descend("edad").constrain(55).greater();
        //Se obtiene el resultado de la ejecución de la sentencia
        ObjectSet res = query.execute();
        //LLamada a método que muestra el resultado
        mostrarBBDD(res);
    }

    /**
     * Método para incrementar en 1 la edad del jefe llamado Miguel
     *
     * @param db ObjectContainer de la bbdd
     */
    public void actualizarEdadMiguel(ObjectContainer db) {
        //se consulta a la base de objetos por el jefe con nombre Miguel
        ObjectSet res = db.queryByExample(new Jefe("Miguel", 0, 0, null));
        Jefe j = (Jefe) res.next(); //se obtiene el objeto consultado en j
        j.setEdad(j.getEdad() + 1); //se añade 1 al atributo edad de j
        db.store(j); //se alamcena de nuevo el objeto jefe
        System.out.println("\n Incrementada la edad de Miguel en un año");
        consultarTodosJefes(db);
    }

    /**
     * Método para eliminar todos los jefes de la base de datos que lleven más 6
     * años en la empresa
     *
     * @param db ObjectContainer de la bbdd
     */
    public void borrarMasAnios(ObjectContainer db) {
        Query query = db.query();
        query.constrain(Jefe.class);
        //se declara una de las restricciones con Constraint mayor de 6 años
        Constraint constra1 = query.descend("anioEmpresa").constrain(6).greater();
        //Se obtiene el resultado de la ejecución de la sentencia
        ObjectSet<Jefe> res = query.execute();
        //Recorre la lista de resultados de la consulta a la vez que los elimina
        while (res.hasNext()) {
            Jefe j = res.next();
            db.delete(j); //se elimina el objeto jefe de la base de objetos                               
        }
        System.out.println("BORRADOS " + res.size() + " REGISTROS");
        consultarTodosJefes(db);
    }

    /**
     * Método para consultar todos los jefes que contiene la base de datos
     *
     * @param db ObjectContainer de la bbdd
     */
    public void consultarTodosJefes(ObjectContainer db) {
        Jefe j = new Jefe(); //Creación del objeto jefe
        ObjectSet res = db.queryByExample(j); //realización de consulta
        mostrarBBDD(res);
    }

    /**
     * Método para mostrar los reultados obtenidos de la consulta de la base de
     * datos
     *
     * @param resul ObjectSet de la base de datos DB4o
     */
    public void mostrarBBDD(ObjectSet resul) {
        System.out.println("\nRecuperados " + resul.size() + " Objetos");
        while (resul.hasNext()) {
            System.out.println(resul.next());
        }
        System.out.println("");
    }
}
