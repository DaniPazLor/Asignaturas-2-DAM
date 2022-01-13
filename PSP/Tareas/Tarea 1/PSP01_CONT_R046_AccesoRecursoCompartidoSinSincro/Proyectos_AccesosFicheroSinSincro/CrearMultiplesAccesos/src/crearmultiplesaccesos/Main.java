/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package crearmultiplesaccesos;

import java.io.*;
/**
 *
 * @author usuario
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Process nuevoProceso; //Definimos una variable de tipo Process
        try{
            PrintStream ps = new PrintStream(
                             new BufferedOutputStream(new FileOutputStream(
                             new File("javalog.txt"),true)), true);
            System.setOut(ps);
            System.setErr(ps);
            for (int i = 0; i <=20; i++){
                nuevoProceso = Runtime.getRuntime().exec("java -jar "+
                    "AccesoMultipleFichero.jar " + i + " nuevo.txt");
                //Creamos el nuevo proceso y le indicamos el número de orden y
                //el fichero que debe utilizar.
                System.out.println("Creado el proceso " + i);
                //Mostramos en consola que hemos creado otro proceso               
            }
        }catch (SecurityException ex){
            System.err.println("Ha ocurrido un error de Seguridad."+
                    "No se ha podido crear el proceso por falta de permisos.");
        }catch (Exception ex){
            System.err.println("Ha ocurrido un error, descripción: "+
                    ex.toString());
        }
    }

}
