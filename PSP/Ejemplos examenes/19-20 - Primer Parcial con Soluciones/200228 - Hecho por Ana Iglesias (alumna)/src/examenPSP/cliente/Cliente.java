package examenPSP.cliente;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.*;

public class Cliente extends Thread {
    private static int idCliente;
    private Socket socket;
    private DataInputStream flujo_entrada;
    private DataOutputStream flujo_salida;

    @Override
    public void run() {
        //variable para lectura de teclado
        Scanner entrada = new Scanner(System.in);
        //información a buscar
        String datoBuscar = null;
        //información recibida del servidor
        double precio;
        //desconectar cliente
        boolean desconectar = false;

        try {
            //conectar cliente
            socket = new Socket("localhost", 2000);

            //creamos los flujos de entrada y salida
            flujo_entrada = new DataInputStream(socket.getInputStream());
            flujo_salida = new DataOutputStream(socket.getOutputStream());

            //recibimos el id del cliente
            idCliente = flujo_entrada.readInt();
            System.out.println("Cliente " + idCliente + " conectado...");

            while (!desconectar) {

                //pedir dato de búsqueda 
                do {
                    System.out.print("\nIntroducir nombre o codigo de producto (para salir 'Fin'):");
                    if (entrada.hasNextLine()) {
                        datoBuscar = entrada.nextLine();
                        //enviamos el datoBuscar al servidor
                        flujo_salida.writeUTF(datoBuscar);
                        if (!datoBuscar.equalsIgnoreCase("fin")) {

                            System.out.println("Dato a Buscar: " + datoBuscar);
                            precio = flujo_entrada.readDouble();
                            if (precio != 0.0) {
                                System.out.println("Precio de " + datoBuscar + ": " + precio);
                            } else {
                                System.out.println("Producto no encontrado");
                            }
                        }

                    } else {
                        System.out.println("Se debe introducir un dato");
                        //limpiar el buffer
                        entrada.next();
                    }

                } while (!datoBuscar.equalsIgnoreCase("Fin"));

                //informar de desconexión del cliente
                desconectar = true;

            }

            System.out.println("Cliente " + idCliente + " desconectado");

            /*//cerrar flujos y socket
            flujo_entrada.close();
            flujo_salida.close();
            socket.close();*/
        } catch (IOException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) {
        //crear objeto Cliente
        Cliente cliente = new Cliente();
        //arrancar hilo cliente
        cliente.start();
    }
}