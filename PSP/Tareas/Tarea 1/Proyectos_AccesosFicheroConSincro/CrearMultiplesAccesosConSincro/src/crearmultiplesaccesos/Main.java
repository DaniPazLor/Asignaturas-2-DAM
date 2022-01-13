/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package crearmultiplesaccesos;

import java.io.File;
import java.io.RandomAccessFile;
import java.io.PrintStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
/**
 *
 * @author usuario
 */
public class Main {

    /**
     * @param args argumentos de la línea de comandos
     * Puede no recibir ningún argumento. En el caso de que
     * reciba alguno, será uno, y será la ruta del archivo a
     * utilizar en los accesos
     */
    public static void main(String[] args) {
        Process nuevoProceso; //Definimos una variable de tipo Process
        String nombreFichero;
        File archivo = null;
        RandomAccessFile raf = null;

         //Identificamos el sistema operativo para poder acceder por su ruta al
      //fichero de forma correcta.
      String osName = System.getProperty("os.name");
      if (osName.toUpperCase().contains("WIN")){ //Windows
        if (args.length > 0)
            nombreFichero = args[0].replace("\\", "\\\\");
            //Hemos recibido la ruta del fichero en la línea de comandos
        else{
            nombreFichero = "C:\\valor.txt";
            //Fichero que se utilizará por defecto
          }
      }else{ //GNU/Linux
          if (args.length > 0)
            nombreFichero = args[0];
          //Hemos recibido la ruta del fichero en la línea de comandos
          else{
               nombreFichero = "/home/margye/valor.txt";
               //Fichero que se utilizará por defecto
        }
      }
        try{
            //Redirigimos salida estándar y de error a un fichero
            PrintStream ps = new PrintStream(
                             new BufferedOutputStream(new FileOutputStream(
                             new File("javalog.txt"),true)), true);
            System.setOut(ps);
            System.setErr(ps);
        }catch(Exception e){
            System.err.println("Error al redirigir las salidas");
            System.err.println(e.toString());
        }

      archivo = new File(nombreFichero);
      //Preparamos el acceso al fichero
      if (!archivo.exists()){
        //Si no existe el fichero
        try {
            archivo.createNewFile(); //Lo creamos
            raf = new RandomAccessFile(archivo,"rw"); //Abrimos el fichero
            //El modo tiene que ser lectura-escritura. No es posible sólo escritura
            raf.writeInt(0); //Escribimos el valor inicial 0
            System.out.println("Creado el fichero.");
        }catch(Exception e){
            System.err.println("Error al crear el fichero");
            System.err.println(e.toString());
        }finally{
            try{ // Nos asegurarnos que se cierra el fichero.
                if (null != raf)
                    raf.close();
               } catch (Exception e2) {
                    System.err.println("Error al cerrar el fichero");
                    System.err.println(e2.toString());
                    System.exit(1); //Si hay error, finalizamos
               }
        }
       }
       //Creamos un grupo de procesos que accederán al mismo fichero
       try{
            for (int i = 0; i <=25; i++){
                nuevoProceso = Runtime.getRuntime().exec("java -jar "+
                    "AccesoMultipleFichero.jar " + i + " "+ args[0]);
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
