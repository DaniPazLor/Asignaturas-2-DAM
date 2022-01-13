package actividad_4_1;

import java.io.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Denominamos a esta clase como la parte servidor del proyecto cliente-servidor
 * en ella crearemos un SocketServer con los datos del servidor y esperamos la
 * peticiones del cliente. Crea un número aleatorio de 0-100 y devuelve si es
 * mayor, menor o igual al número recibido por el cliente. Acepta concurrencia
 * de conexiones de cliente mediante la herencia de la clase Thread
 *
 * @author Daniel Paz Lorenzo
 */
public class Servidor extends Thread {

    //Constante que define el número del puerto
    static final int Puerto = 2000;
    Socket sCliente;

    /**
     * Constructor donde se define la estructura de la clase al instanciarla
     *
     * @param socketCliente la conexión cliente-servidor
     */
    public Servidor(Socket socketCliente) {
        this.sCliente = socketCliente;
    }

    /**
     * El servidor crea un Socket con una dirección y se queda esperando las
     * peticiones de varios clientes de manera recurrente gracias a un bucle
     * infinito
     *
     * @param args
     */
    public static void main(String[] args) {
        int numCliente = 0;
        try {
            //Crea el servicio de socket servidor con los datos
            ServerSocket socketServidor = new ServerSocket(Puerto);
            System.out.println("Esperando peticion de cliente...");
            //En espera de petición de cliente
            while (true) {
                numCliente++;
                Socket socketCliente = socketServidor.accept();
                System.out.println("\nConectado cliente " + numCliente);
                new Servidor(socketCliente).start();
            }
        } catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Método run del hilo principal que ejecuta las operaciones de generar un
     * número aleatorio y lo compara con el que recibe del usuario para evaluar
     * si es mayor, menor o igual y lo devuelve al cliente
     */
    @Override
    public void run() {
        //Declaración de variables
        int numeroAleatorio, numUsuario;
        short valor = 3;
        //Genera número aleatorio del 0 al 100 y lo muestra por pantalla
        numeroAleatorio = (int) (Math.random() * 100);
        System.out.println("Numero aleatorio: " + numeroAleatorio);
        do {
            try {
                //Creamos un flujo de entrada del socket y leemos el entero de entrada
                DataInputStream flujoEntrada = new DataInputStream(sCliente.getInputStream());
                numUsuario = flujoEntrada.readInt();

                //Comparamos números para saber si es mayor, menor o igual
                if (numUsuario < numeroAleatorio) {
                    valor = 2;
                } else if (numUsuario > numeroAleatorio) {
                    valor = 1;
                } else {
                    valor = 0;
//                    flujoEntrada.close();
                }
                DataOutputStream flujoSalida = new DataOutputStream(sCliente.getOutputStream());
                flujoSalida.writeShort(valor);

            } catch (IOException ex) {
                Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
            }

        } while (valor > 0);//Repetimos bucle hasta que el valor sea 0 (correcto)
        try {
            sCliente.close();
        } catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
