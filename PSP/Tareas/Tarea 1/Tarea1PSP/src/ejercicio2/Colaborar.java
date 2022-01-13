package ejercicio2;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Esta clase nos lanza 10 instancias de la aplicación "lenguaje" durante 10
 * ciclos con acceso sincrono al recurso, para generar un fichero llamado
 * "colaborar.txt" que contenga 550 palabras aleatorias
 *
 * @author Daniel Paz Lorenzo
 */
public class Colaborar {

    /**
     * Método principal que genera las instancias a la aplicación "lenguaje"
     * 
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {

        Path path = Paths.get("");
        final String rutaFichero = path.toAbsolutePath().toString() + "\\build\\classes";
        final String nombreFichero = "ficheros\\colaborar.txt";

        //Creamos y lanzamos 10 instancias de proceso y las repetimos 10 veces
        for (int i = 1; i < 11; i++) {
            //Cada instancia creada recibe (por comandos) los argumentos de la cantidad de palabras, el nombre del fichero y el id del proceso
            ProcessBuilder instLenguaje1 = new ProcessBuilder("Java", "-cp", rutaFichero, "ejercicio2.Lenguaje", String.valueOf(i), nombreFichero, "proceso 1");
            instLenguaje1.start();
            ProcessBuilder instLenguaje2 = new ProcessBuilder("Java", "-cp", rutaFichero, "ejercicio2.Lenguaje", String.valueOf(i), nombreFichero, "proceso 2");
            instLenguaje2.start();
            ProcessBuilder instLenguaje3 = new ProcessBuilder("Java", "-cp", rutaFichero, "ejercicio2.Lenguaje", String.valueOf(i), nombreFichero, "proceso 3");
            instLenguaje3.start();
            ProcessBuilder instLenguaje4 = new ProcessBuilder("Java", "-cp", rutaFichero, "ejercicio2.Lenguaje", String.valueOf(i), nombreFichero, "proceso 4");
            instLenguaje4.start();
            ProcessBuilder instLenguaje5 = new ProcessBuilder("Java", "-cp", rutaFichero, "ejercicio2.Lenguaje", String.valueOf(i), nombreFichero, "proceso 5");
            instLenguaje5.start();
            ProcessBuilder instLenguaje6 = new ProcessBuilder("Java", "-cp", rutaFichero, "ejercicio2.Lenguaje", String.valueOf(i), nombreFichero, "proceso 6");
            instLenguaje6.start();
            ProcessBuilder instLenguaje7 = new ProcessBuilder("Java", "-cp", rutaFichero, "ejercicio2.Lenguaje", String.valueOf(i), nombreFichero, "proceso 7");
            instLenguaje7.start();
            ProcessBuilder instLenguaje8 = new ProcessBuilder("Java", "-cp", rutaFichero, "ejercicio2.Lenguaje", String.valueOf(i), nombreFichero, "proceso 8");
            instLenguaje8.start();
            ProcessBuilder instLenguaje9 = new ProcessBuilder("Java", "-cp", rutaFichero, "ejercicio2.Lenguaje", String.valueOf(i), nombreFichero, "proceso 9");
            instLenguaje9.start();
            ProcessBuilder instLenguaje10 = new ProcessBuilder("Java", "-cp", rutaFichero, "ejercicio2.Lenguaje", String.valueOf(i), nombreFichero, "proceso 10");
            instLenguaje10.start();
        }
        
        //Muestra por pantalla la dirección absoluta de los ficheros que se han creado
        System.out.println("Se han generado los siguientes ficheros");
        System.out.println(path.toAbsolutePath().toString() + "\\" + nombreFichero);
        System.out.println(path.toAbsolutePath().toString() + "\\ficheros\\log.txt");        
    }
}
