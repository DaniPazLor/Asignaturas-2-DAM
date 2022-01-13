package actividad_3_1;

import java.io.*;
import java.io.IOException;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Denominamos a esta clase como la parte servidor del proyecto cliente-servidor
 * en ella crearemos un SocketServer con los datos del servidor y esperamos la
 * peticiones del cliente. Crea un número aleatorio de 0-100 y devuelve si es
 * mayor, menor o igual al número recibido por el cliente.
 *
 * @author Daniel Paz Lorenzo
 */
public class Servidor {
    //Constante que define el número del puerto
    static final int Puerto = 2000;

    /**
     * Constructor donde se define la estructura de la clase al instanciarla
     */
    public Servidor() {
        //Declaración de variables
        int numeroAleatorio, numUsuario;
        short valor;

        try {
            //Crea el servicio de socket servidor con los datos
            ServerSocket socketServidor = new ServerSocket(Puerto);
            System.out.println("Esperando peticion de cliente...");
            //En espera de petición de cliente
            Socket socketCliente = socketServidor.accept();

            //Genera número aleatorio del 0 al 199 y lo muestra por pantalla
            numeroAleatorio = (int) (Math.random() * 100);
            System.out.println("Numero aleatorio: " + numeroAleatorio);
            do {
                //Creamos un flujo de entrada del socket y leemos el entero de entrada
                DataInputStream flujoEntrada = new DataInputStream(socketCliente.getInputStream());
                numUsuario = flujoEntrada.readInt();
                //Comparamos números para saber si es mayor, menor o igual
                if (numUsuario < numeroAleatorio) {
                    valor = 2;
                } else if (numUsuario > numeroAleatorio) {
                    valor = 1;
                } else {
                    valor = 0;
                }
                DataOutputStream flujoSalida = new DataOutputStream(socketCliente.getOutputStream());
                flujoSalida.writeShort(valor);

            } while (valor > 0);//Repetimos bucle hasta que el valor sea 0 o correcto

            socketCliente.close();
        } catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) {
        new Servidor();
    }

}
