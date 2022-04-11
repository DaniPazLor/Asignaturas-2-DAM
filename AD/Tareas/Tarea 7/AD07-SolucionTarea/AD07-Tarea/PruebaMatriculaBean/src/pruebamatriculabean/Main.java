/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pruebamatriculabean;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author usuario
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        AccedeBD gestion = new AccedeBD();

        gestion.listado();
        gestion.anade();
        try {
            gestion.listadoDNI("12345678A");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
