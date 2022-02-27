/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicio_1;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import java.io.File;

/**
 *
 * @author Pauda
 */
public class BDJefeHjio {
    public ObjectContainer baseDatos;
    
    public BDJefeHjio() {

    }
    
    public void crearBBDD(){
        File fichero = new File("BDJefeHijo.db4o");
        fichero.delete();
        /*Este código anterior lo ponemos por si la base de datos ya existiera y quisiéramos empezar desde el principio.*/
//        ObjectContainer baseDatos = Db4oEmbedded.openFile("BDJefeHijo");
        this.baseDatos = Db4oEmbedded.openFile("BDJefeHijo.db4o");
        
        baseDatos.store(new Jefe("Ángel", 5, 53, new Hijo("Gustavo", 7)));
        baseDatos.store(new Jefe("Nieves", 3, 45, new Hijo("Iván", 3)));
        baseDatos.store(new Jefe("Jesús", 3, 5, new Hijo("Noelia", 3)));
        baseDatos.store(new Jefe("Dolores", 5, 63, new Hijo("Sergio", 7)));
        baseDatos.store(new Jefe("Vicki", 3, 5, null));
        baseDatos.store(new Jefe("Fátima", 5, 63, new Hijo("Lidia", 27)));
        baseDatos.store(new Jefe("Juan Luís", 3, 5, null));
        baseDatos.store(new Jefe("Elena", 1, 42, new Hijo("David", 19)));
        baseDatos.store(new Jefe("Miguel", 20, 45, new Hijo("Paula", 3)));
        baseDatos.store(new Jefe("Jesús", 19, 44, new Hijo("Rubén", 12)));
        
    }
    
}
