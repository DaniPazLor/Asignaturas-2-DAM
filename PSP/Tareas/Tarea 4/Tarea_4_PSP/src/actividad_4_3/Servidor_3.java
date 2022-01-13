package actividad_4_3;

import java.io.*;
import java.net.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Denominamos a esta clase como la parte servidor del proyecto cliente-servidor
 * en ella crearemos un SocketServer con los datos del servidor y esperamos la
 * peticiones del cliente. Muestra por pantalla el contenido del proyecto,
 * recoge el nombre del fichero suministrado por el cliente, comprueba si existe
 * y si es así le envía al cliente el contenido. Acepta varias conexiones 
 * concurrentes mediante herencia de la clase Thread
 *
 * @author Daniel Paz Lorenzo
 */
public class Servidor_3 extends Thread {
    //Declaramos variables e instanciamos objetos de la clase
    File ficheroServidor;
    File directorioActual;
    String nombreUsuarioFichero, mensajeServidor;
    String[] listaFicheros;
    int in;
    Socket socketCli;

    //Declaración de constantes con el puerto de conexión del servidor
    static final int Puerto = 1500;
    static final String usuario = "javier";
    static final String contraseña = "secreta";

    public Servidor_3(Socket socketCliente) {
        this.socketCli = socketCliente;
    }

    /**
     * Método principal en el que instanciamos la clase Servidor_3 un número
     * indefinido de veces mediante un bucle infinito
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int numCliente = 0;

        try {
            //Creamos socket del servidor con el puerto 1500
            ServerSocket socketServidor = new ServerSocket(Puerto);
            System.out.println("Esperando peticion de cliente...");

            while (true) {
                //Espera la peticion de cliente y cuando entra la acepta                
                Socket socketCliente = socketServidor.accept();
                numCliente++;
                System.out.println("Cliente " + numCliente + " conectado");
                new Servidor_3(socketCliente).start();
            }
        } catch (IOException ex) {
            Logger.getLogger(Servidor_3.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Método run de la clase Thread donde se ejecutará la lógica principal de
     * la aplicación. Valida usuario, muestra ficheros del proyecto, evalúa si
     * existe y manda al usuario el contenido
     */
    @Override
    public void run() {
        int intentos = 0;
        boolean valida = false;

        try {
            //Crea un flujo de entrada del socket para recoger el fichero enviado por el cliente
            DataInputStream flujoEntrada = new DataInputStream(socketCli.getInputStream());
            //Creamos un flujo de salida del socket  
            DataOutputStream flujoSalida = new DataOutputStream(socketCli.getOutputStream());
            //Permite 3 intentos erróneos de nombre de usuario y contraseña
            do {
                intentos++;
                valida = validacionUsuCon(valida, flujoEntrada, flujoSalida);
            } while (intentos < 3 && valida == false);

            if (valida) {//Si usuario y contraseña son correctos
                System.out.println("¡¡Bienvenido!!");
                mostrarDirectorioActual(flujoSalida);
                //Recoge el nombre del fichero solicitado por el usuario
                nombreUsuarioFichero = flujoEntrada.readUTF();
                ficheroServidor = new File(nombreUsuarioFichero);

                if (ficheroServidor.exists()) {//Comprueba si el fichero solicitado existe
                    if (ficheroServidor.isFile()) {//Comprobamos si es un fichero
                        flujoSalida.writeInt(1);
                        mostrarFichero(flujoSalida);
                    } else if (ficheroServidor.isDirectory()) {//Comprobamos si es un directorio
                        flujoSalida.writeInt(2);
                        mostrarArchivo(flujoSalida);
                    }
                    flujoEntrada.close();

                } else {
                    flujoSalida.writeInt(0);
                    flujoSalida.writeUTF("No existe un fichero con ese nombre");
                }

            } else {//Si usuario y contraseña no son correctos
                System.out.println("Acceso de cliente no verificado");
            }
        } catch (IOException ex) {
            Logger.getLogger(Servidor_3.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Método para validar si el usuario y contraseña recibidos por el cliente
     * son válidos
     *
     * @param valida true si usuario y contraseña son correctos
     * @param flujoEntrada flujo entrada socket cliente
     * @param flujoOut flujo salida socket cliente
     * @return
     * @throws IOException
     */
    private static boolean validacionUsuCon(boolean valida, DataInputStream flujoEntrada, DataOutputStream flujoOut) throws IOException {
        HashMap<String, String> acceso = new HashMap<>();
        acceso.put(contraseña, usuario);
        String conUsuario;
        String nomUsuario;

        nomUsuario = flujoEntrada.readUTF();
        conUsuario = flujoEntrada.readUTF();

        //Recorre el HashMap y compara el contenido con usuario y contraseña dados por el usuario 
        for (Map.Entry<String, String> combinacion : acceso.entrySet()) {
            if (combinacion.getKey().equals(conUsuario) && combinacion.getValue().equals(nomUsuario)) {
                valida = true;//Si coinciden usuario y contraseña 
            } else {
                valida = false;//Si alguna no es igual
            }
        }
        //Manda al cliente si claves válidas o no
        flujoOut.writeBoolean(valida);
        return valida;
    }

    /**
     * Método que recoge el contenido del fichero y lo manda al cliente a través
     * del flujo de salida
     *
     * @param flujoSalida flujo salida socket cliente
     */
    private void mostrarFichero(DataOutputStream flujoSalida) {
        try {
            System.out.println("Fichero solicitado por el cliente: " + ficheroServidor);
            //Manda mensaje de que existe el fichero
            mensajeServidor = "El fichero existe dentro del servidor \n";
            System.out.println(mensajeServidor);
            flujoSalida.writeUTF(mensajeServidor);

            //Lee el fichero y lo escribe en el flujo de salida
            BufferedInputStream bis = new BufferedInputStream(new FileInputStream(ficheroServidor));
            do {
                flujoSalida.write(in);
            } while ((in = bis.read()) != -1);

            System.out.println("Cerrando conexion con cliente");
            flujoSalida.close();

        } catch (IOException ex) {
            Logger.getLogger(Servidor_3.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Método que recoge el contenido del archivo y manda su contenido a través
     * del flujo de salida
     *
     * @param flujoSalida flujo salida socket del cliente
     */
    private void mostrarArchivo(DataOutputStream flujoSalida) {
        try {
            System.out.println("Archivo solicitado por el cliente: " + ficheroServidor);
            //Manda mensaje de que existe el fichero
            mensajeServidor = "El archivo existe dentro del servidor \n";
            System.out.println(mensajeServidor);
            flujoSalida.writeUTF(mensajeServidor);

            //Introduce en un vector los nombres de los archivos para a 
            //continuación mandarlos a través del flujo
            listaFicheros = ficheroServidor.list();
            flujoSalida.writeInt(listaFicheros.length);
            for (String fichero : listaFicheros) {
                flujoSalida.writeUTF(fichero);
            }

            System.out.println("Cerrando conexion con cliente");
            flujoSalida.close();

        } catch (IOException ex) {
            Logger.getLogger(Servidor_3.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Método que muestra el contenido del directorio actual del proyecto
     *
     * @param flujoSalida flujo salida socket cliente
     * @throws IOException
     */
    private void mostrarDirectorioActual(DataOutputStream flujoSalida) throws IOException {
        directorioActual = new File(".");
        listaFicheros = directorioActual.list();
        flujoSalida.writeInt(listaFicheros.length);
        System.out.println("Mandando ficheros que contiene el proyecto al cliente");
        for (String fichero : listaFicheros) {
            flujoSalida.writeUTF(fichero);
        }
    }

}
