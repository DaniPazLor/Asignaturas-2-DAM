package actividad_3_2;

import java.io.*;
import java.net.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Denominamos a esta clase como la parte cliente del proyecto
 * cliente_2-servidor_2 en ella crearemos un Socket con los datos del servidor
 * al que queremos conectar y va a solicitar que ingresemos por teclado el
 * nombre de un fichero que luego mandará al servidor y este le devolverá lo que
 * contiene si es que existe
 *
 * @author Daniel Paz Lorenzo
 */
public class Cliente_2 {
    //Declaración de constantes con los datos de la conexión con servidor
    static final String Host = "localhost";
    static final int Puerto = 1500;

    public Cliente_2() {
        //Declaración de variables y objetos
        String nombreFichero;
        Scanner teclado = new Scanner(System.in);
        int caracterAsciiEntrada;

        try {
            //Creamos la conexión al servidor
            Socket socketCliente = new Socket(Host, Puerto);
            //Solicita el nombre del fichero y lo recoge en un String
            System.out.println("Introduzca el nombre del fichero --> ");
            nombreFichero = teclado.nextLine();
            //Creamos un flujo de salida del Socket y escribimos en él el nombre del fichero
            DataOutputStream flujoSalida = new DataOutputStream(socketCliente.getOutputStream());
            flujoSalida.writeUTF(nombreFichero);
            //Creamos un flujo de entrada del socket y mostramos por pantalla la información leida
            DataInputStream flujoEntrada = new DataInputStream(socketCliente.getInputStream());
            System.out.println(flujoEntrada.readUTF());
            //Recoge cada byte del flujo de entrada y lo muestra por pantalla
            while ((caracterAsciiEntrada = flujoEntrada.read()) != -1) {
                System.out.print((char) caracterAsciiEntrada);
            }

            socketCliente.close();
        } catch (IOException ex) {
            Logger.getLogger(Cliente_2.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Método principal donde vamos a instanciar la clase cliente_2
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new Cliente_2();
    }

}
