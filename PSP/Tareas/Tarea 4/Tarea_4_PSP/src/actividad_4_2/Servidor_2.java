package actividad_4_2;

import java.io.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Denominamos a esta clase como la parte servidor del proyecto cliente-servidor
 * en ella crearemos un SocketServer con los datos del servidor y esperamos la
 * peticiones del cliente. Recoge el nombre del fichero suministrado por el
 * cliente, comprueba si existe dentro del proyecto y si es así le envía al
 * cliente el contenido. Acepta concurrencia de conexiones mediante herencia 
 * de la clase Thread
 *
 * @author Daniel Paz Lorenzo
 */
public class Servidor_2 extends Thread {
    //Declaramos variables y objetos de clase
    File ficheroServidor;
    String nombreUsuarioFichero, mensajeServidor;
    int in;
    Socket socketCli;

    //Declaración de constantes con el puerto de conexión del servidor
    static final int Puerto = 1500;

    public Servidor_2(Socket socketCliente) {
        this.socketCli = socketCliente;
    }

    /**
     * Método principal que crea Socket de conexión he instanciamos 
 la clase Servidor_2 un número infinito de veces mediante bucle
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int numCliente=0;
        try {
            //Creamos socket del servidor con el puerto 1500
            ServerSocket socketServidor = new ServerSocket(Puerto);
            System.out.println("Esperando peticion de cliente...");

            while (true) {
                //Espera la peticion de cliente y cuando entra la acepta                
                Socket socketCliente = socketServidor.accept();
                numCliente++;
                System.out.println("Cliente "+numCliente+" conectado");
                new Servidor_2(socketCliente).start();
            }
        } catch (IOException ex) {
            Logger.getLogger(Servidor_2.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Método run del hilo principal que evalúa la petición de fichero del
     * cliente y si existe devuelve al cliente su contenido
     */
    @Override
    public void run() {
        try {
            //Crea un flujo de entrada del socket para recoger el fichero enviado por el cliente
            DataInputStream flujoEntrada = new DataInputStream(socketCli.getInputStream());
            nombreUsuarioFichero = flujoEntrada.readUTF();

            //Creamos un flujo de salida del socket  
            DataOutputStream flujoSalida = new DataOutputStream(socketCli.getOutputStream());
            ficheroServidor = new File("ficheroServidor\\"+nombreUsuarioFichero);
            System.out.println("Fichero solicitado por el cliente: " + ficheroServidor);
            if (ficheroServidor.exists()) {//Comprueba si existe el fichero
                //Manda mensaje de que existe el fichero
                mensajeServidor = "El fichero existe y contiene lo siguiente: \n";
                System.out.println(mensajeServidor);
                flujoSalida.writeUTF(mensajeServidor);
                //Lee el fichero y lo escribe en el flujo de salida además de imprimir por pantalla
                BufferedInputStream bis = new BufferedInputStream(new FileInputStream(ficheroServidor));
                do {
                    flujoSalida.write(in);
                    System.out.print((char)in);
                }while ((in = bis.read()) != -1);
            } else {
                //Si no existe fichero también manda mensaje informando
                mensajeServidor = "El fichero no existe\n";
                System.out.println(mensajeServidor);
                flujoSalida.writeUTF(mensajeServidor);
            }
            flujoSalida.close();

        } catch (IOException ex) {
            Logger.getLogger(Servidor_2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
