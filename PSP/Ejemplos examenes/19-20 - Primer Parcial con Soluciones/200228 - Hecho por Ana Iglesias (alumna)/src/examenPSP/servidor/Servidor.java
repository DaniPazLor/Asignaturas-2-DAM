package examenPSP.servidor;

import java.io.IOException;
import java.net.*;
import java.util.ArrayList;
import java.util.logging.*;

public class Servidor {
    public static void main(String[] args) {
        ServerSocket servidor = null;
        Socket socket = null;

        //identificación del cliente
        int nCliente = 0;

        //lista de los productos
        ArrayList<Productos> listaProductos = new ArrayList<>();

        //creación de objetos Productos
        Productos producto01 = new Productos("pan", "123", 0.95);
        Productos producto02 = new Productos("leche", "234", 1.97);
        Productos producto03 = new Productos("cafe", "654", 3.54);

        //añdadir los objetos Productos a la lista
        listaProductos.add(producto01);
        listaProductos.add(producto02);
        listaProductos.add(producto03);

        try {
            //iniciar servidor
            servidor = new ServerSocket(2000);
            System.out.println("Servidor iniciado...");

            while (true) {
                //aceptar Cliente
                socket = servidor.accept();

                //informar del número de cliente conectado
                nCliente++;
                System.out.println("Cliente " + nCliente + " conectado...");

                ServidorHilos atenderCliente = new ServidorHilos(socket, nCliente, listaProductos);
                atenderCliente.start();
            }
        } catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException ex) {
                    Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (servidor != null) {
                try {
                    servidor.close();
                } catch (IOException ex) {
                    Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
}