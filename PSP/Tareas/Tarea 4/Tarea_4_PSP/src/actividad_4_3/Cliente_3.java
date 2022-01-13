package actividad_4_3;

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
 * contiene si es que existe. 
 *
 * @author Daniel Paz Lorenzo
 */
public class Cliente_3 {
    //Declaración de constantes con los datos de la conexión con servidor
    static final String Host = "localhost";
    static final int Puerto = 1500;

    public Cliente_3(int numCliente) throws IOException {
        //Declaración de variables y objetos
        Scanner teclado = new Scanner(System.in);
        boolean valida;

        //Creamos la conexión al servidor
        Socket socketCliente = new Socket(Host, Puerto);
        System.out.println("Cliente " + numCliente);

        //Creamos un flujo de salida del Socket 
        DataOutputStream flujoSalida = new DataOutputStream(socketCliente.getOutputStream());
        //Creamos un flujo de entrada del socket
        DataInputStream flujoEntrada = new DataInputStream(socketCliente.getInputStream());
        //Comprueba si el usuario y contraseña son válidos
        valida = peticionUsuCon(false, flujoEntrada, flujoSalida);
        if (valida) {
            mostrarFicheroCliente(flujoEntrada, flujoSalida, teclado);
            System.out.println("\nCerrando conexión\n");
            socketCliente.close();
        } else {
            System.out.println("El cliente " + numCliente + " ha excedido el número máximo de intentos\n");
        }

    }

    /**
     * Método principal donde vamos a instanciar la clase cliente_3 3 veces 
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int numCliente = 0;
        
        do {
            numCliente++;
            try {
                new Cliente_3(numCliente);
            } catch (IOException ex) {
                Logger.getLogger(Cliente_3.class.getName()).log(Level.SEVERE, null, ex);
            }
        } while (numCliente < 3);

    }

    /**
     * Método que solicita al cliente el usuario y la contraseña la manda 
     * al servidor y recoge de este si es válida o no
     * 
     * @param valida true si usuario y contraseña son correctos
     * @param flujoEntrada 
     * @param flujoSalida
     * @return
     * @throws IOException 
     */
    private static boolean peticionUsuCon(boolean valida, DataInputStream flujoEntrada, DataOutputStream flujoSalida) throws IOException {
        //Declaración de variables
        int intentos = 0;
        String nomUsuario;
        String conUsuario;
        Scanner teclado = new Scanner(System.in);
        //Solicita usuario y contraseña un máximo de 3 intentos
        do {
            System.out.println("Nombre de usuario: ");
            nomUsuario = teclado.next();
            flujoSalida.writeUTF(nomUsuario);

            System.out.println("Contraseña de usuario: ");
            conUsuario = teclado.next();
            flujoSalida.writeUTF(conUsuario);

            valida = flujoEntrada.readBoolean();
            if (!valida) {
                System.out.println("Usuario o  contraseña no válida\n");
            }
            intentos++;
        } while (intentos < 3 && valida == false);

        return valida;
    }

    /**
     * Método que solicita al cliente el nombre del fichero o archivo que 
     * desea ver, se lo manda al servidor y este le devolverá su contenido si 
     * es que existe
     * 
     * @param flujoEntrada flujo entrada socket cliente
     * @param flujoSalida flujo salida socket cliente
     * @param teclado recoge datos introducidos por teclado
     */
    private void mostrarFicheroCliente(DataInputStream flujoEntrada, DataOutputStream flujoSalida, Scanner teclado) {
        //Declaración de variables
        String usuarioFichero;
        int caracterAsciiEntrada;
        int longitudArchivo;

        try {
            System.out.println("Usuario y contraseña correctas");
            int numFicheros = flujoEntrada.readInt();

            System.out.println("\nEl directorio actual contiene los siguientes ficheros y archivos");
            System.out.println("--------------------------------------------------------------------");
            for (int i = 0; i < numFicheros; i++) {
                System.out.println(flujoEntrada.readUTF());
            }
            System.out.println("--------------------------------------------------------------------");

            //Solicita el nombre del fichero, lo recoge en un String y se lo manda al servidor
            System.out.print("Introduzca el nombre del fichero que desea ver --> ");
            usuarioFichero = teclado.nextLine();
            flujoSalida.writeUTF(usuarioFichero);

            //Recoge respuesta de servidor
            int tipoFichero = flujoEntrada.readInt();
            switch (tipoFichero) {
                case 0://Si el fichero o archivo no existe
                    System.out.println(flujoEntrada.readUTF());
                    break;
                case 1://Si es un fichero
                    {
                        System.out.println(flujoEntrada.readUTF());
                        System.out.println("------------------------------------------------------------------");
                        BufferedInputStream bis = new BufferedInputStream(flujoEntrada);
                        //Recoge cada byte del flujo de entrada y lo muestra por pantalla
                        while ((caracterAsciiEntrada = flujoEntrada.read()) != -1) {
                            System.out.print((char) caracterAsciiEntrada);
                        }       System.out.println("------------------------------------------------------------------");
                        break;
                    }
                case 2://Si es un archivo
                    {
                        System.out.println(flujoEntrada.readUTF());
                        System.out.println("-------------------------------------------------------------------");
                        BufferedInputStream bis = new BufferedInputStream(flujoEntrada);
                        //Recoge la longitud del vector de nombres del archivo y lo muestra por pantalla
                        longitudArchivo = flujoEntrada.readInt();
                        for (int i = 0; i < longitudArchivo; i++) {
                            System.out.println(flujoEntrada.readUTF());
                        }       System.out.println("--------------------------------------------------------------------");
                        break;
                    }
                default:
                    break;
            }

        } catch (IOException ex) {
            Logger.getLogger(Cliente_3.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
