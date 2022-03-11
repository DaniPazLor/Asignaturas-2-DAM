package actividad_6_1;

import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.*;
import java.util.logging.*;

/**
 * Clase principal que contiene el método main y el resto de métodos para
 * conformar la lógica de negocio de la aplicación
 *
 * @author Daniel Paz Lorenzo
 */
public class AplicacionPrincipal {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //Declaración de variables
        boolean correcto;
        Scanner teclado = new Scanner(System.in);
        //Declaración de los patrones
        final String patronLogin = "[a-z]{8}";
        final String patronNomFichero = "^[a-zA-Z0-9]{1,8}.[a-zA-Z]{3}$";//Patrón indicando cuál es el inicio el final de la cadena

        /*--------------------------------------------------------------------*/
        /*Establecemos un fichero de registros de la aplicación*/
        Logger logger = creaFicheroRegistro();
        /*--------------------------------------------------------------------*/
        //Solicitamos login al usuario
        do {
            correcto = false;
            try {
                System.out.println("Introduzca el nombre de usuario: ");
                String nomUsuario = teclado.next();

                if (comprobarPatron(patronLogin, nomUsuario)) {//Si patrón coincide
                    correcto = true;
                    // Añado un mensaje al log   
                    String textoLog = "El patron (" + patronLogin + ") del usuario " + nomUsuario + " es correcto";
                    logger.log(Level.INFO, textoLog);
                    Thread.sleep(200);//Pausa de aplicacion
                } else {
                    // Añado un mensaje al log   
                    String textoLog = "El patron (" + patronLogin + ") del usuario " + nomUsuario + " es INcorrecto";
                    logger.log(Level.WARNING, textoLog);
                    Thread.sleep(200);//Pausa de aplicacion
                }

            } catch (Exception e) {//Recogemos excepción si las hubiera
                System.out.println("Se ha producido la excepción " + e.getMessage());
                correcto = false;
            }

        } while (!correcto);

        //Solicitamos nombre del fichero al usuario
        do {
            correcto = false;
            try {
                System.out.println("Introduzca el nombre del fichero: ");
                String nomFichero = teclado.next();

                if (comprobarPatron(patronNomFichero, nomFichero)) {//Si patrón coincide
                    correcto = true;
                    // Añado un mensaje al log   
                    String textoLog = "El patron (" + patronNomFichero + ") del fichero " + nomFichero + " es correcto";
                    logger.log(Level.INFO, textoLog);
                    Thread.sleep(200);//Pausa de aplicacion
                    correcto = visualizarFichero(nomFichero, logger);
                } else {
                    // Añado un mensaje al log   
                    String textoLog = "El patron (" + patronNomFichero + ") del fichero " + nomFichero + " es INcorrecto";
                    logger.log(Level.WARNING, textoLog);
                    Thread.sleep(200);//Pausa de aplicacion
                }

            } catch (Exception e) {//Recogemos excepción si las hubiera
                System.out.println("Se ha producido la excepción " + e.getMessage());
                correcto = false;
            }

        } while (!correcto);

    }

    /**
     * Método para comparar el patrón con el texto introducido
     *
     * @param patron de la clase Pattern
     * @param texto introducido por el usuario
     * @return resultado boolean de la comparación
     */
    public static boolean comprobarPatron(String patron, String texto) {
        Pattern pat;
        Matcher mat;
        //Compilamos y comparamos el patrón con el texto
        pat = Pattern.compile(patron);
        mat = pat.matcher(texto);

        return mat.find();//boolean true si coinciden patrón y texto
    }

    /**
     * Método que permite visualizar por pantalla el contenido de un fichero
     * pasado por parámetro.
     *
     * @param nombreFichero del tipo String
     * @param logger de la clse Logger
     * @return boolean true/false dependiendo si existe o no el fichero
     */
    public static boolean visualizarFichero(String nombreFichero, Logger logger) {
        File archivo;
        FileReader fr = null;
        BufferedReader br;
        boolean existeFichero = false;

        try {
            // Apertura del fichero y creacion de BufferedReader para poder
            // hacer una lectura comoda (disponer del metodo readLine()).
            archivo = new File("C:\\datos\\" + nombreFichero);
            fr = new FileReader(archivo);
            br = new BufferedReader(fr);

            //Comprueba que fichero exista
            if (archivo.exists()) {
                existeFichero = true;
                // Añado un mensaje al log   
                String textoLog = "El fichero " + nombreFichero + " existe dentro de la carpeta C:\\datos\\";
                logger.log(Level.INFO, textoLog);
                Thread.sleep(200);//Pausa de aplicacion
                // Lectura del fichero
                System.out.println("-------------------------------------------");
                System.out.println("- CONTENIDO DEL FICHERO " + nombreFichero + "-");
                System.out.println("-------------------------------------------");
                String linea;
                while ((linea = br.readLine()) != null) {
                    System.out.println(linea);
                }
            }

        } catch (Exception e) {
            existeFichero = false;
            // Añado un mensaje al log   
            String textoLog = "El fichero " + nombreFichero + " NO existe dentro de la carpeta C:\\datos\\";
            logger.log(Level.WARNING, textoLog);
            Thread.sleep(200);//Pausa de aplicacion
        } finally {
            // En el finally cerramos el fichero, para asegurarnos
            // que se cierra tanto si todo va bien como si salta 
            // una excepcion.
            try {
                if (fr != null) {
                    fr.close();
                }
            } catch (Exception e2) {
                System.out.println("Ocurrió una excepción " + e2.getMessage());
            }
            return existeFichero;
        }
    }

    /**
     * Método para generar un fichero de log de todos los registros que se van
     * generando en la aplicación
     *
     * @return un log de la clase Logger
     */
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
        //Recogemos excepciones
        } catch (IOException ex) {
            Logger.getLogger(AplicacionPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SecurityException ex) {
            Logger.getLogger(AplicacionPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }

        return logger;
    }

}
