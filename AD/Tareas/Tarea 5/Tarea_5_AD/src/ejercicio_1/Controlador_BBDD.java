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

    public void consultarTodosJefes(ObjectContainer db){
        Jefe j = new Jefe(); //prototipo de búsqueda
        ObjectSet res = db.queryByExample(j); //realización de consulta
        mostrarBBDD(res);
    }
    public void consultarJefes55(ObjectContainer db){
        Query query = db.query();
        query.constrain(Jefe.class);
        //se declara una de las restricciones con Constraint
    //Constraint constra1 = query.descend("cache").constrain(200).smaller();
    Constraint constra1 = query.descend("edad").constrain(55).greater();
    //se enlazan las dos restricciones a aplicar
//    query.descend("cache").constrain(50).greater().and(constra1);
//    ObjectSet result = query.execute();

    }
    
        public void mostrarBBDD(ObjectSet resul) {
         System.out.println("Recuperados " + resul.size() + " Objetos");
            while (resul.hasNext()) {
            System.out.println(resul.next());
        }
  

        
    }
}
