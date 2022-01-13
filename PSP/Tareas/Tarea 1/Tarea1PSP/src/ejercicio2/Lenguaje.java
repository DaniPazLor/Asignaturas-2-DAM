package ejercicio2;

import java.io.*;
import java.nio.channels.*;
import java.util.Random;

/**
 * Esta clase crea un fichero con el nombre y de tantas palabras aleatorias como
 * solicite el usuario pasadas como parámetros. A mayores, se ha incorporado un
 * tercer parámetro con el id del proceso que se escribirá en un fichero log.txt
 * con el que podemos comprobar que proceso escribe y que es lo que escribe
 *
 * @author Daniel Paz Lorenzo
 */
public class Lenguaje {

    /**
     * Clase principal que se encarga de generar los ficheros principal y
     * log.txt y gestiona el acceso de escritura y lectura de los mismos de
     * manera sincrona
     *
     * @param args arg[0]=número de palabras, arg[1]=nombre fichero, arg[2]=id
     * proceso
     */
    public static void main(String[] args) {
        //Bloque declaración de variables locales
        Random letraAleatoria = new Random();
        FileLock bloqueo = null;
        String argNumProceso = "";

        //Comprobamos que tengamos los argumentos que necesitamos
        if (args.length == 2) {
            argNumProceso = "proceso";
        } else if (args.length == 3) {
            argNumProceso = args[2];
        } else if (args.length < 4) {
            System.err.println("No se han expecificado los argumentos necesarios");
        }

        try {
            //Creamos los objetos File que contendrán los ficheros
            File ficheroLog = new File("ficheros\\log.txt");
            File ficheroTexto = new File(args[1]);
            //Comprobamos que no existieran ya y si fuera así los eliminamos
            if (ficheroTexto.exists()) {
                ficheroTexto.delete();
            }
            if (ficheroLog.exists()) {
                ficheroLog.delete();
            }
            //Generamos los flujos de los ficheros
            FileWriter fwFicheroLog = new FileWriter(ficheroLog, true);
            BufferedWriter bufferFicheroLog = new BufferedWriter(fwFicheroLog);
            RandomAccessFile randomFicheroLenguaje = new RandomAccessFile(ficheroTexto, "rwd");//Generamos un acceso de tipo lectura-escritura del fichero            

            //*******************
            //Sección crítica de acceso sincrono al fichero
            bloqueo = randomFicheroLenguaje.getChannel().lock();
            //bloqueamos el canal de acceso al fichero. Obtenemos el objeto que
            //representa el bloqueo para después poder liberarlo
            int caracterAleatorio = 0;
            bufferFicheroLog.write("ENTRA " + argNumProceso.toUpperCase());//Escribimos en el log que proceso entra
            bufferFicheroLog.newLine();
            for (int i = 0; i < Integer.parseInt(args[0]); i++) {//args [0] indica el número de líneas

                for (int j = 0; j < letraAleatoria.nextInt(30) + 1; j++) {//Generamos palabras de un tamaño máximo de 30
                    caracterAleatorio = letraAleatoria.nextInt(25) + 97;//Letras aleatorias de a-z en ascii (97-122)
                    System.out.print((char) caracterAleatorio);//Muestra por pantalla los caracteres que se van generando
                    randomFicheroLenguaje.seek(randomFicheroLenguaje.length());//Nos colocamos al final del fichero
                    randomFicheroLenguaje.write(caracterAleatorio);//Escribe letra en el fichero
                    bufferFicheroLog.write(caracterAleatorio);//Escribe la misma letra en el fichero log.txt
                }
                bufferFicheroLog.newLine();//Genera un salto de línea en el fichero log.txt
                randomFicheroLenguaje.writeChar(13);//Genera un salto de línea en el fichero                
                System.out.println();
            }
            bloqueo.release(); //Liberamos el bloqueo del canal del fichero
            bloqueo = null;

            bufferFicheroLog.write("SALE " + argNumProceso.toUpperCase());//Escribimos en el log que proceso sale
            bufferFicheroLog.newLine();
            //Cerramos los flujos creados
            bufferFicheroLog.close();
            fwFicheroLog.close();
            //Fin sección crítica
            //*******************
        } catch (Exception e) {
            System.err.println("Error, los ficheros no se han generado");
        }
    }

}
