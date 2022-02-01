package examen1_procesos;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 *
 * @author jjalv
 */
public class Servidor {

    static int PUERTO = 1500;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            Servidor nuevo = new Servidor();
            ArrayList<Productos> productos = new ArrayList();

            Productos pan = new Productos("123", "Pan", "111");
            Productos leche = new Productos("12", "Leche", "222");
            Productos agua = new Productos("56", "agua", "333");

            productos.add(pan);
            productos.add(leche);
            productos.add(agua);

            ServerSocket ss = new ServerSocket(PUERTO);
            while (true) {

                Socket socket = new Socket();

                socket = ss.accept();
                BufferedReader input = new BufferedReader(
                        new InputStreamReader(socket.getInputStream()));

                PrintWriter output = new PrintWriter(
                        new BufferedWriter(
                                new OutputStreamWriter(socket.
                                        getOutputStream())), true);

                String datosEntrada = input.readLine();

                System.out.println("recibido en el servidor "
                        + "referencia o nombre: " + datosEntrada);

                for (Productos c : productos) {

                    if (datosEntrada.equals(c.getReferencia())
                            || datosEntrada.equals(c.getNombre())) {

                        System.out.println("el precio del producto: "
                                + c.getNombre() + " es de :" + c.getPrecio());

                    }

                }

                output.close();
                input.close();
                socket.close();

            }

        } catch (IOException ex) {
            System.out.println(ex);
            System.out.println("Error de entrada/salida");        }

    }

}
