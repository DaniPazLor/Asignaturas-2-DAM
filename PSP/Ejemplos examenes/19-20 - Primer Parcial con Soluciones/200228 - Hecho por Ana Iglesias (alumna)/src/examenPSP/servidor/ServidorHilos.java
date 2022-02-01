package examenPSP.servidor;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.*;

public class ServidorHilos extends Thread {
    //campos de clase
    private ArrayList<Productos> listaProductos;
    private Socket socket;
    private int idCliente;

    DataInputStream flujo_entrada;
    DataOutputStream flujo_salida;

    public ServidorHilos(Socket socket, int idCliente, ArrayList<Productos> listaProductos) {
        this.listaProductos = listaProductos;
        this.socket = socket;
        this.idCliente = idCliente;

        try {//creamos los flujos de entrada y salida de datos            
            flujo_entrada = new DataInputStream(socket.getInputStream());
            flujo_salida = new DataOutputStream(socket.getOutputStream());
        } catch (IOException ex) {
            ex.printStackTrace(System.out);
        }
    }

    @Override
    public void run() {
        //recoger el dato introduce por el cliente
        String dato;
        boolean encontrado = false;
        try {
            //informar del cliente conectado
            flujo_salida.writeInt(idCliente);

            do {
                //leer lo que envía el cliente
                dato = flujo_entrada.readUTF();

                if (!dato.equalsIgnoreCase("fin")) {

                    System.out.println("Dato a buscar solicitado por el cliente " + idCliente + ": " + dato);

                    //buscar información
                    for (Productos p : listaProductos) {
                        //comaparamos los datos
                        if (p.getCodigo().equals(dato) || p.getNombre().equalsIgnoreCase(dato)) {
                            System.out.println("Producto buscado para el cliente " + idCliente + " encontrado. Precio: " + p.getPrecio());
                            //enviamos el precio al cliente
                            flujo_salida.writeDouble(p.getPrecio());
                            encontrado = true;
                        }

                    }

                    if (!encontrado) {
                        System.out.println("Producto solicitado por el cliente " + idCliente + " no encontrado");
                        flujo_salida.writeDouble(0.0);
                    }
                }
            } while (!dato.equalsIgnoreCase("fin"));

            System.out.println("Cliente " + idCliente + " se ha desconectado.");

            //cerrar flujos y socket
            flujo_entrada.close();
            flujo_salida.close();
            socket.close();
        } catch (IOException ex) {
            Logger.getLogger(ServidorHilos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}