package actividad_3_2;

import java.io.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Denominamos a esta clase como la parte servidor del proyecto cliente-servidor
 * en ella crearemos un SocketServer con los datos del servidor y esperamos la
 * peticiones del cliente. Recoge el nombre del fichero suministrado por el
 * cliente, comprueba si existe dentro del proyecto y si es así le envía al
 * cliente el contenido
 *
 * @author Daniel Paz Lorenzo
 */
public class Servidor_2 {

    ////Declaración de constantes con el puerto de conexión del servidor
    static final int Puerto = 1500;

    public Servidor_2() {
        //Declaramos variables y objetos de clase
        File ficheroServidor;
        String nombreUsuarioFichero, mensajeServidor;
        int in;

        try {
            //Creamos socket del servidor con el puerto 1500
            ServerSocket socketServidor = new ServerSocket(Puerto);
            //Espera la peticion de cliente y cuando entra la acepta
            System.out.println("Esperando peticion de cliente...");
            Socket socketCliente = socketServidor.accept();
            //Crea un flujo de entrada del socket para recoger el fichero enviado por el cliente
            DataInputStream flujoEntrada = new DataInputStream(socketCliente.getInputStream());
            nombreUsuarioFichero = flujoEntrada.readUTF();
            //Creamos un flujo de salida del socket  
            DataOutputStream flujoSalida = new DataOutputStream(socketCliente.getOutputStream());
            ficheroServidor = new File(nombreUsuarioFichero);
            System.out.println("Fichero solicitado por el cliente: " + ficheroServidor);
            if (ficheroServidor.exists()) {//Comprueba si existe el fichero
                //Manda mensaje de que existe el fichero
                mensajeServidor = "El fichero existe y contiene lo siguiente: \n\n";
                System.out.println(mensajeServidor);
                flujoSalida.writeUTF(mensajeServidor);
                //Lee el fichero y lo escribe en el flujo de salida además de imprimir por pantalla
                BufferedInputStream bis = new BufferedInputStream(new FileInputStream(ficheroServidor));
                while ((in = bis.read()) != -1) {
                    flujoSalida.write(in);
                    System.out.print((char) in);
                }
            } else {
                //Si no existe fichero también manda mensaje informando
                mensajeServidor = "El fichero no existe";
                System.out.println(mensajeServidor);
                flujoSalida.writeUTF(mensajeServidor);
            }

            socketCliente.close();
        } catch (IOException ex) {
            Logger.getLogger(Servidor_2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Método principal en el que instanciamos la clase Servidor_2
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new Servidor_2();
    }

}
