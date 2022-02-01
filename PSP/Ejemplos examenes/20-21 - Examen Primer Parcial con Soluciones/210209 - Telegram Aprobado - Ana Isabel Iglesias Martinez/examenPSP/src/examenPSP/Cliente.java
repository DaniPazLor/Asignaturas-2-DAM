package examenPSP;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author AnaIglesias
 */
public class Cliente extends Thread{
    
    private static int nCliente;
    private Socket socket;
    private DataInputStream flujo_entrada;
    private DataOutputStream flujo_salida;
    

    @Override
    public void run() {
        
        boolean acertado = false;   
        String eleccion, cadena;
        Scanner entrada = new Scanner(System.in);
        
     try {
        // creamos un socket para enviar el nombre del fichero al servidor
        socket = new Socket("localhost",2000);
        
        //creamos los flujos de entrada y salida
        flujo_entrada = new DataInputStream(socket.getInputStream());
        flujo_salida = new DataOutputStream(socket.getOutputStream());
        
        //recibimos el número de Cliente
        nCliente = flujo_entrada.readInt();
        System.out.println("Cliente "+nCliente+" conectado.");
        
        //leemos y visualizamos la cadena enviada por el servidor
        cadena = flujo_entrada.readUTF();
        System.out.println(cadena);
        
        do {
            
            System.out.print("\nIntroducir letra o resolver: "); 
            eleccion = entrada.nextLine();
            
            //enviamos el datos introducido al servidor
            flujo_salida.writeUTF(eleccion);
            
            if (eleccion.length()==1 ){
                //recibimos la contestacion del servidor
                cadena = flujo_entrada.readUTF();
                System.out.println(cadena);
            }
               
            //recibimos la respuesta
            acertado = flujo_entrada.readBoolean();
                
        }while(!acertado);
        
        System.out.println("¡¡¡ Has acertado !!!");
        
        //cerramos los flujos y el socket
        System.out.println("Fin de la ejecución para el cliente "+nCliente);
        flujo_entrada.close();
        flujo_salida.close();
        socket.close();
        System.out.println("Cliente "+nCliente+" desconectado");
        
    } catch (IOException ex) {
        ex.printStackTrace();
    }
    }
    
    
    public static void main(String[] args) {
        Cliente cliente = new Cliente();
        cliente.start();
    }
    
}
