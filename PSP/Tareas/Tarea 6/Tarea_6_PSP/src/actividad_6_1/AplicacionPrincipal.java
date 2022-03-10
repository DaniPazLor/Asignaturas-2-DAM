/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package actividad_6_1;

import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.*;
import java.util.logging.*;

/**
 *
 * @author Pauda
 */
public class AplicacionPrincipal {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        boolean correcto;
        Scanner teclado = new Scanner(System.in);
        final String patronLogin = "[a-z]{8}";
        final String patronNomFichero = "^[a-zA-Z0-9]{1,8}.[a-zA-Z]{3}$";//Patrón indicando cuál es el inicio el final de la cadena

        /*--------------------------------------------------------------------*/
        /*Establecemos un fichero de registros de la aplicación*/
        Logger logger = creaFicheroRegistro();

        /*--------------------------------------------------------------------*/
        do {
            correcto = false;
            try {
                System.out.println("Introduzca el nombre de usuario: ");
                String nomUsuario = teclado.next();

                if (comprobarPatron(patronLogin, nomUsuario)) {
                    correcto = true;
                    // Añado un mensaje al log   
                    String textoLog = "El nombre usuario " + nomUsuario + " es correcto";
                    logger.log(Level.INFO, textoLog);

                } else {
                    // Añado un mensaje al log   
                    String textoLog = "El nombre usuario " + nomUsuario + " es INcorrecto";
                    logger.log(Level.WARNING, textoLog);
                }

            } catch (Exception e) {
                System.out.println("Se ha producido la excepción " + e.getMessage());
            }

        } while (!correcto);

        do {
            correcto = false;
            try {
                System.out.println("Introduzca el nombre del fichero: ");
                String nomFichero = teclado.next();

                if (comprobarPatron(patronNomFichero, nomFichero)) {
                    correcto = true;
                    // Añado un mensaje al log   
                    String textoLog = "El nombre del fichero " + nomFichero + " es correcto";
                    logger.log(Level.INFO, textoLog);
                    correcto=visualizarFichero(nomFichero, logger);
                } else {
                    // Añado un mensaje al log   
                    String textoLog = "El nombre usuario " + nomFichero + " es INcorrecto";
                    logger.log(Level.WARNING, textoLog);
                }

            } catch (Exception e) {
                System.out.println("Se ha producido la excepción " + e.getMessage());
            }

        } while (!correcto);
        
    }

    public static boolean comprobarPatron(String patron, String texto) {
        Pattern pat;
        Matcher mat;

        pat = Pattern.compile(patron);
        mat = pat.matcher(texto);

        return mat.find();
    }

    public static boolean visualizarFichero(String nombreFichero, Logger logger) {
        File archivo;
        FileReader fr = null;
        BufferedReader br;
        boolean existeFichero=false;

        try {
            // Apertura del fichero y creacion de BufferedReader para poder
            // hacer una lectura comoda (disponer del metodo readLine()).
            archivo = new File("C:\\datos\\" + nombreFichero);
            fr = new FileReader(archivo);
            br = new BufferedReader(fr);

            //Comprueba que fichero exista
            if (archivo.exists()) {
                existeFichero=true;
                // Añado un mensaje al log   
                String textoLog = "El fichero " + nombreFichero + " existe dentro de la carpeta C:\\datos\\";
                logger.log(Level.INFO, textoLog);
                // Lectura del fichero
                String linea;
                while ((linea = br.readLine()) != null) {
                    System.out.println(linea);
                }
            } else {
                existeFichero=false;
                // Añado un mensaje al log   
                String textoLog = "El fichero " + nombreFichero + " NO existe dentro de la carpeta C:\\datos\\";
                logger.log(Level.WARNING, textoLog);
                
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // En el finally cerramos el fichero, para asegurarnos
            // que se cierra tanto si todo va bien como si salta 
            // una excepcion.
            try {
                if (fr != null) {
                    fr.close();
                }
            } catch (IOException e2) {
                e2.printStackTrace();
            }
            return existeFichero;
        }
    }

    public static Logger creaFicheroRegistro() {
        Logger logger = Logger.getLogger("Registro");
        try {
            FileHandler fh = new FileHandler("c:\\datos\\FicheroRegistros.log", true);
            logger.addHandler(fh);//Añado el handler al log
            //Tipo de formato para el registro
            SimpleFormatter formatter = new SimpleFormatter();
            fh.setFormatter(formatter);

            logger.setUseParentHandlers(true);//Mostrar por pantalla
            logger.setLevel(Level.INFO);//Nivel de seguridad INFO
            logger.setLevel(Level.ALL);//Registrar todos los eventos

        } catch (IOException ex) {
            Logger.getLogger(AplicacionPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SecurityException ex) {
            Logger.getLogger(AplicacionPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }

        return logger;
    }


}
