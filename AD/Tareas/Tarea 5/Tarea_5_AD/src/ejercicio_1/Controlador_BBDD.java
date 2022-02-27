/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicio_1;

import com.db4o.*;
import com.db4o.query.Constraint;
import com.db4o.query.Query;

/**
 *
 * @author Pauda
 */
public class Controlador_BBDD {

    public Controlador_BBDD() {

    }

    public void consultarJefes55(ObjectContainer db) {
        Query query = db.query();
        query.constrain(Jefe.class);
        //se declara una de las restricciones con Constraint mayor de 55 años
        Constraint constra1 = query.descend("edad").constrain(55).greater();
        //se enlazan las dos restricciones a aplicar
        ObjectSet res = query.execute();
        mostrarBBDD(res);

    }

    //método que modifica el e-mail de un ponente cuyo nif se pasa como parámetro
  // y almacena en la base de objetos los nuevos valores
  public void actualizarEdadMiguel(ObjectContainer db) {
    //se consulta a la base de objetos por el ponente del nif indicado
    ObjectSet res = db.queryByExample(new Jefe("Miguel", 0, 0, null));
    Jefe j = (Jefe) res.next(); //se obtiene el objeto consultado en p
    j.setEdad(j.getEdad()+1); //se cambia el email del objeto
    db.store(j); //se alamcena de nuevo el objeto poenente
      consultarTodosJefes(db);
  }
    
    // Método que elimina de la base de objetos [con delete()] el ponente cuyo
  //nif se pasa como parámetro
  public void borrarMasAnios(ObjectContainer db) {
              Query query = db.query();
        query.constrain(Jefe.class);
        //se declara una de las restricciones con Constraint mayor de 55 años
        Constraint constra1 = query.descend("anioEmpresa").constrain(6).greater();
        //se enlazan las dos restricciones a aplicar
        ObjectSet<Jefe> res = query.execute();
      
       while (res.hasNext()) {
           
           Jefe j = res.next();

           db.delete(j); //se elimina el objeto poenente de la base de objetos
                      
           
        }
       System.out.println("BORRADOS " + res.size() + " REGISTROS");
     consultarTodosJefes(db);
//    //se consulta a la base de objetos por el ponente del nif indicado
//    ObjectSet res = db.queryByExample(new ponente(nif, null, null, 0));
//    ponente p = (ponente) res.next(); //se obtiene el objeto consultado en p
    
  }
  
    public void consultarTodosJefes(ObjectContainer db) {
        Jefe j = new Jefe(); //prototipo de búsqueda
        ObjectSet res = db.queryByExample(j); //realización de consulta
        mostrarBBDD(res);
    }

    
    
    public void mostrarBBDD(ObjectSet resul) {
        System.out.println("Recuperados " + resul.size() + " Objetos");
        while (resul.hasNext()) {
            System.out.println(resul.next());
        }

    }
}
