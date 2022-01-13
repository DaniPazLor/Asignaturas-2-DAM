package actividad_4_1;

import java.io.*;
import java.net.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Denominamos a esta clase como la parte cliente del proyecto cliente-servidor
 * en ella crearemos un Socket con los datos del servidor al que queremos
 * conectar
 *
 * @author Daniel Paz Lorenzo
 */
public class Cliente {

    //Definición de constantes de clase
    static final String Host = "localhost";//Dirección local del servidor
    static final int Puerto = 2000;//Puerto del servidor

    /**
     * Constructor de la clase donde definimos que hará la clase cuando la
     * instanciemos
     *
     * @param numCliente contabilizamos el número de clientes que se conectan al
     * servidor
     */
    public Cliente(int numCliente) {
        //Declaración de variables
        int numUsuario;
        int numIntento = 1;
        short valor;

        System.out.println("Cliente " + numCliente);
        System.out.println("\nADIVINE EL NUMERO DEL 0 AL 100 GENERADO POR EL SERVIDOR");
        System.out.println("---------------------------------------------------------");
        try {
            //Creamos el Socket con los datos del servidor
            Socket socketCliente = new Socket(Host, Puerto);

            do {
                //Solicitamos al usuario el número
                numUsuario = peticionNumUsuario(numIntento);
                //Creamos un flujo de salida al socket y escribimos el número 
                //para que lo recoja el servidor
                DataOutputStream flujoSalida = new DataOutputStream(socketCliente.getOutputStream());
                flujoSalida.writeInt(numUsuario);
                //Creamos un flujo de entrada del socket para recoger la respuesta del servidor
                DataInputStream flujoEntrada = new DataInputStream(socketCliente.getInputStream());

                valor = flujoEntrada.readShort();
                //Evalúa la respuesta del servidor y muestra un mensaje por pantalla
                switch (valor) {
                    case 2:
                        System.out.println("-- ES MAYOR --");
                        break;
                    case 1:
                        System.out.println("-- ES MENOR --");
                        break;
                    case 0:
                        System.out.println("ENHORABUENA, HAS ACERTADO EL NUMERO SECRETO !!!!!\n");
                        break;
                    default:
                        break;
                }
                numIntento++;

            } while (valor > 0);//Repite bucle hasta que el valor sea correcto
            //Cerramos el socket
            socketCliente.close();
        } catch (IOException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Instanciamos la Clase Cliente 3 veces para probar la concurrencia con el Servidor
     * 
     * @param args 
     */
    public static void main(String[] args) {
        int numCliente = 0;
        do {
            numCliente++;
            new Cliente(numCliente);
        } while (numCliente < 3);
    }

    /**
     * Método para solicitar el número al usuario, evalúa si es correcto y lo
     * retorna
     *
     * @param numIntento incrementa la variable cada vez que se realiza un
     * intento
     * @return numero introducido por el usuario
     */
    private int peticionNumUsuario(int numIntento) {
        //Definición de variables y objetos
        Scanner teclado = new Scanner(System.in);
        int numUsuario = 0;
        boolean error;
        //Solicita el número hasta que sea correcto
        do {
            error = false;
            try {
                System.out.println("\nIntento " + numIntento + ": ");
                numUsuario = teclado.nextInt();
                if (numUsuario > 100) {
                    throw new Exception();//Lanza excepción si es mayor 100
                }
            } catch (Exception e) {
                System.err.println("El dato introducido no es correcto");
                teclado.next();
                error = true;
            }

        } while (error);

        return numUsuario;
    }

}
